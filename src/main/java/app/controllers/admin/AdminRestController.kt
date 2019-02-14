package app.controllers.admin

import app.models.CategoryDTO
import app.models.ProductDTO
import app.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.APPLICATION_XML_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/rest/")
open class AdminRestController {

	@Autowired
	private lateinit var service : Service

	@PostMapping("/categories" ,
	             consumes = [APPLICATION_JSON_UTF8_VALUE] ,
	             produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	open fun saveCategory(@RequestBody category : CategoryDTO) = service.saveCategory(category).id

	@PostMapping("/products" ,
	             consumes = [APPLICATION_JSON_UTF8_VALUE] ,
	             produces = [APPLICATION_JSON_UTF8_VALUE , APPLICATION_XML_VALUE])
	open fun saveProduct(@RequestBody product : ProductDTO) = service.saveProduct(product).id
}