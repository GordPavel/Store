package app.controllers

import app.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
open class RestController {

	@Autowired
	private
	lateinit var service : Service

	@GetMapping("/categories" , produces = [MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	fun getCategories() = service.getCategories()

	@GetMapping(value = ["/products"] ,
	            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	fun listProducts() = service.getProducts()

}