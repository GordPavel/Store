package app.controllers

import app.models.CategoryDTO
import app.models.CategoryEntity
import app.models.SearchProductFilter
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
	private lateinit var productsSearchService : ProductsSearchService

	@GetMapping("/authorities" ,
	            produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	open fun getPrincipal() : Collection<GrantedAuthority>? =
			SecurityContextHolder.getContext().authentication.authorities

	@GetMapping("/categories" , produces = [APPLICATION_JSON_UTF8_VALUE])
	open fun getTree() : Iterable<CategoryEntity> = service.getTree()

	@PostMapping("/categories" ,
	             consumes = [APPLICATION_JSON_UTF8_VALUE] ,
	             produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	@ResponseStatus(CREATED)
	open fun saveCategory(@RequestBody category : CategoryDTO) {
		service.saveCategory(category)
	}

}


