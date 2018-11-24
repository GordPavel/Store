package app.controllers

import app.models.CategoryDTO
import app.models.CategoryEntity
import app.repositories.CategoryRepository
import app.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType
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
	private lateinit var categoryRepository : CategoryRepository

	@GetMapping("/authorities" ,
	            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	open fun getPrincipal() : Collection<GrantedAuthority>? = SecurityContextHolder.getContext().authentication.authorities

	@GetMapping("/categories" , produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	open fun getTree() : Iterable<CategoryEntity> = service.getTree()

	@PostMapping("/categories" ,
	             consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE] ,
	             produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	@ResponseStatus(CREATED)
	open fun saveCategory(@RequestBody category : CategoryDTO) {
		service.saveCategory(category)
	}

}


