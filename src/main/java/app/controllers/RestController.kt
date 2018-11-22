package app.controllers

import org.springframework.http.MediaType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
open class RestController {
	@GetMapping("/authorities" ,
	            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE , MediaType.APPLICATION_XML_VALUE])
	fun getPrincipal() : Collection<GrantedAuthority>? = SecurityContextHolder.getContext().authentication.authorities
}


