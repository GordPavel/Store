package app.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletResponse

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

	@Bean(name = ["passwordEncoder"])
	open fun passwordEncoder() = SCryptPasswordEncoder()

	@Autowired
	@Qualifier("userDetails")
	private lateinit var userDetailsService : UserDetailsService

	override fun userDetailsServiceBean() : UserDetailsService = userDetailsService

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
				.exceptionHandling()
				.defaultAuthenticationEntryPointFor(AuthenticationEntryPoint { _ , response , exception ->
					// todo pavelgordeev Разные статусы в зависимости от ошибки
					response.sendError(HttpServletResponse.SC_FORBIDDEN , exception.message)
				} , AntPathRequestMatcher("/repository/**"))
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
}