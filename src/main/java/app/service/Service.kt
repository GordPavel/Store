package app.service

import app.models.*
import app.repositories.CategoryRepository
import app.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
open class Service {

	@Autowired
	private lateinit var categoryRepository : CategoryRepository

	@Autowired
	private lateinit var productsRepository : ProductRepository

	@Transactional(readOnly = true)
	open fun findCategoryById(id : Long?) : CategoryEntity? {
		return if(id != null) categoryRepository.findById(id).get()
		else null
	}

	@Transactional(rollbackFor = [Throwable::class])
	open fun modifyProduct(productDTO : ProductDTO) {
		val product = productsRepository.findById(productDTO.id)
				.orElseThrow { IllegalArgumentException("No product with id ${productDTO.id}") }
		product.name = productDTO.name
		product.price = productDTO.price
		product.properties = productDTO.properties
		val category =
				categoryRepository.findById(productDTO.categoryId)
						.orElseThrow { IllegalArgumentException("No category with id ${productDTO.id}") }
		product.category = category
		productsRepository.save(product)
	}

	@Transactional(rollbackFor = [Throwable::class])
	open fun modifyCategory(categoryDTO : CategoryDTO) {
		val category =
				categoryRepository.findById(categoryDTO.id)
						.orElseThrow { IllegalArgumentException("No category with id ${categoryDTO.id}") }
		category.name = categoryDTO.name
		category.properties = categoryDTO.properties
		if(categoryDTO.parentCategoryId != null) {
			if(categoryDTO.id == categoryDTO.parentCategoryId) throw java.lang.IllegalArgumentException("Category ${categoryDTO.id} can't be parent of itself")
			val parent = categoryRepository.findById(categoryDTO.parentCategoryId)
					.orElseThrow { IllegalArgumentException("No category with id ${categoryDTO.parentCategoryId}") }
			category.parentCategory = parent
		}
		categoryRepository.save(category)
	}

	@Transactional(readOnly = true)
	open fun getAll() : Iterable<CategoryEntity> = categoryRepository.findAll(0)

	@Transactional(rollbackFor = [Throwable::class])
	open fun saveCategory(categoryDTO : CategoryDTO) : CategoryEntity {
		val parentCategory = findCategoryById(categoryDTO.parentCategoryId)
		val subCategories = categoryRepository.findAllById(categoryDTO.subCategoriesIds)
		val products = productsRepository.findAllById(categoryDTO.productsIds)
		val newCategory =
				CategoryEntity(categoryDTO.name , subCategories.toList() , parentCategory , products.toList()).apply {
					properties = categoryDTO.properties
				}
		return categoryRepository.save(newCategory)
	}

	@Transactional(rollbackFor = [Throwable::class])
	open fun saveProduct(productDTO : ProductDTO) : ProductEntity {
		val parentCategory =
				findCategoryById(productDTO.categoryId)
				?: throw IllegalArgumentException("No category with id ${productDTO.categoryId}")
		val newProduct = ProductEntity(productDTO.name , productDTO.price , null).apply {
			category = parentCategory
			properties = productDTO.properties
		}
		return productsRepository.save(newProduct)
	}

	@Transactional(readOnly = true)
	open fun getAllParents(item : WithParent ,
	                       parents : MutableList<CategoryEntity> = mutableListOf()) : MutableList<CategoryEntity> {
		val parent = item.parent
		return if(parent != null) {
			val loadedParent =
					categoryRepository
							.findById(parent.id!!)
							.orElseThrow { IllegalStateException("Something went wrong!!") }
			getAllParents(loadedParent , parents).apply { add(parent) }
		} else parents
	}

	@Transactional(readOnly = true)
	open fun getTree() : Iterable<CategoryEntity> =
			categoryRepository.getRootCategories().onEach { it.subCategories = getTree(it).toList() }

	@Transactional(readOnly = true)
	open fun getTree(root : CategoryEntity) : Iterable<CategoryEntity> {
		val parentId = (root.id
		                ?: throw IllegalArgumentException("Not persisted entity"))
		val children =
				categoryRepository.getSubCategoriesByParentId(parentId)
		root.subCategories = children.toList()
		for(child in children) getTree(child)
		return children
	}
}