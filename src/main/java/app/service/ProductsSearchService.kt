package app.service

import app.models.ProductEntity
import app.models.SearchProductFilter
import app.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Comparator.*
import java.util.stream.Collectors.toList

@Service
@Transactional
open class ProductsSearchService {

	@Autowired
	private lateinit var productRepository : ProductRepository

	@Value("\${search.name.coef}")
	private val nameCoef : Double = 5.0

	@Value("\${search.property.coef}")
	private val propertiesCoef : Double = 1.0

	@Autowired
	private lateinit var stemmer : Stemmer

	data class ProductsList(val page : Page<ProductEntity> , val minCost : Double , val maxCost : Double)

	@Transactional(readOnly = true)
	open fun search(filter : SearchProductFilter , page : Pageable) : ProductsList {
		val predicate = filter.toPredicate()
		val comparator = page.toProductComparator()

		var products = productRepository.findAll(1).toList().stream().sequential()
				.filter(predicate)
				.sorted(comparator)

		if(filter.searchString != null) {
			products = products
					.filter { fitFunction(filter.searchString , it) > 0 }
					.sorted(comparingDouble { fitFunction(filter.searchString , it) })
		}
		var final = products.collect(toList()).toMutableList()
		val minCost = final.parallelStream().mapToDouble { it.price }.min().orElse(0.0)
		val maxCost = final.parallelStream().mapToDouble { it.price }.max().orElse(10000.0)
		val total = final.size.toLong()
		if(page.isPaged) final = final.stream().skip(page.offset).limit(page.pageSize.toLong()).collect(toList())

		return ProductsList(PageImpl(final , page , total) , minCost , maxCost)
	}

	private fun fitFunction(searchString : String , product : ProductEntity) : Double {
		val namePoint = if(stemmer.fits(searchString , product.name)) nameCoef else 0.0
		val propertiesPoints = product.properties.values.stream()
				                       .filter { stemmer.fits(searchString , it) }
				                       .count() * propertiesCoef
		return namePoint + propertiesPoints
	}
}

fun Pageable.toProductComparator() : Comparator<ProductEntity> {
	return sort.get().collect(
			comparingInt(ProductEntity::visitorsCount)::reversed ,
			{ comparator , order ->
				val orderComparator = when(order.property) {
					"name"         -> comparing(ProductEntity::name)
					"price"        -> comparingDouble(ProductEntity::price)
					"categoryName" -> comparing<ProductEntity , String> { it.category.name }
					else           -> nullsLast(comparing<ProductEntity , String> { product ->
						product.properties[order.property]
					})
				}
				comparator.thenComparing(if(!order.isDescending) orderComparator else orderComparator.reversed())
			} ,
			{ comparator1 , comparator2 -> comparator1.thenComparing(comparator2) }
	                         )
}