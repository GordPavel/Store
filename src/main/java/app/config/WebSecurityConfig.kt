package app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
	override fun configure(http : HttpSecurity) {
		http
				.csrf()
				.disable()
		http
				.authorizeRequests()
				.antMatchers("/wishList").authenticated()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
		http
				.formLogin()
				.loginPage("/login?login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?loginError")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
		http
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
	}

	@Bean
	open fun passwordEncoder() = BCryptPasswordEncoder()

	@Bean
	override fun userDetailsService() : UserDetailsService {
		val encoder = passwordEncoder()
		val admin = User.builder()
				.username("admin")
				.password("admin")
				.passwordEncoder(encoder::encode)
				.roles("ADMIN" , "USER")
				.build()
		val user = User.builder()
				.username("user")
				.password("user")
				.passwordEncoder(encoder::encode)
				.roles("USER")
				.build()
		return InMemoryUserDetailsManager(admin , user)
	}
}