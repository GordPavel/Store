package app.controllers

import app.models.UserEntity
import app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@RepositoryRestController
open class UserRestController {
	@Autowired
	private lateinit var userRepository : UserRepository
	@Autowired
	private lateinit var passwordEncoder : PasswordEncoder

	@PostMapping("/users" , consumes = [APPLICATION_JSON_UTF8_VALUE])
	@ResponseBody
	@ResponseStatus(CREATED)
	fun registration(@RequestBody user : UserEntity) {
		userRepository.save(user.apply {
			userPassword = passwordEncoder.encode(userPassword)
		})
	}
}