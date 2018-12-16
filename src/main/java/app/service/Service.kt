package app.service

import app.models.CategoryDTO
import app.models.CategoryEntity
import app.models.ProductDTO
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

	@Transactional(readOnly = true)
	open fun getTree() : Iterable<CategoryEntity> {
		val root = categoryRepository.findById(0 , 0).orElseThrow { IllegalStateException("No root category!!") }
		return getTree(root)
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