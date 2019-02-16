package app.controllers

import app.models.CategoryDTO
import app.models.CategoryEntity
import app.models.SearchProductFilter
import app.repositories.CategoryRepository
import app.repositories.ProductRepository
import app.service.ProductsSearchService
import app.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.APPLICATION_XML_VALUE
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
open class RestController {

	@Autowired
	private lateinit var service : Service

	@Autowired
	private lateinit var productRepository : ProductRepository

	@Autowired
	private lateinit var categoryRepository : CategoryRepository

	@Autowired
	private lateinit var productsSearchService : ProductsSearchService

	@GetMapping("/authorities" ,
	            produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	open fun getPrincipal() : Collection<GrantedAuthority>? =
			SecurityContextHolder.getContext().authentication.authorities

	@GetMapping("/tree" , produces = [APPLICATION_JSON_UTF8_VALUE])
	open fun getTree() : Iterable<CategoryEntity> = service.getTree()

	@GetMapping("/categories" , produces = [APPLICATION_JSON_UTF8_VALUE])
	open fun getAllCategories() : Iterable<CategoryEntity> = service.getAll()

	@PostMapping("/categories" ,
	             consumes = [APPLICATION_JSON_UTF8_VALUE] ,
	             produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	@ResponseStatus(CREATED)
	open fun saveCategory(@RequestBody category : CategoryDTO) {
		service.saveCategory(category)
	}

	@GetMapping("/products" ,
	            consumes = [APPLICATION_JSON_UTF8_VALUE] ,
	            produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	open fun searchProducts(@RequestParam(required = false) searchString : String? ,
	                        @RequestParam("ids[]" , required = false) ids : List<Long>? ,
	                        @RequestParam("exceptIds[]" , required = false) exceptIds : List<Long>? ,
	                        @RequestParam("categories[]" , required = false) categories : List<Long>? ,
	                        @RequestParam(required = false) minPrice : Double? ,
	                        @RequestParam(required = false) maxPrice : Double? ,
	                        page : Pageable) =
			productsSearchService.search(SearchProductFilter(searchString ,
			                                                 ids ,
			                                                 exceptIds ,
			                                                 categories ,
			                                                 minPrice ,
			                                                 maxPrice) ,
			                             page)

	@GetMapping("/breadcrumb" , produces = [APPLICATION_JSON_UTF8_VALUE])
	open fun breadcrumb(@RequestParam itemId : Long ,
	                    @RequestParam(required = false) isProduct : Boolean = true) : Iterable<CategoryEntity> {
		val item = (
				if(isProduct) productRepository.findById(itemId)
				else categoryRepository.findById(itemId)
		           )
				.orElseThrow { IllegalArgumentException("Incorrect id: $isProduct") }
		val categories = service.getAllParents(item)
		if(!isProduct) categories.add(item as CategoryEntity)
		return categories.onEach { it.subCategories = emptyList() }
	}

}


