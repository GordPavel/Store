package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.UrlBasedViewResolver
import org.springframework.web.servlet.view.tiles3.TilesConfigurer
import org.springframework.web.servlet.view.tiles3.TilesView

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = ["app.service" , "app.controllers" , "app.config"])
@EntityScan(basePackages = ["app.models"])
@EnableNeo4jRepositories(basePackages = ["app.repositories"])
@EnableTransactionManagement
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
open class Application : SpringBootServletInitializer() , WebMvcConfigurer {

	override fun addViewControllers(registry : ViewControllerRegistry) {
		registry.addViewController("/").setViewName("index")
		registry.addViewController("/index").setViewName("index")
		registry.addViewController("/category").setViewName("category")
		registry.addViewController("/product").setViewName("product")
		registry.addViewController("/login").setViewName("login")
		registry.addViewController("/wishList").setViewName("wishList")
		registry.addViewController("/search").setViewName("search")
	}

	@Bean(name = ["viewResolver"])
	open fun getViewResolver() : ViewResolver {
		val viewResolver = UrlBasedViewResolver()
		viewResolver.setViewClass(TilesView::class.java)
		return viewResolver
	}

	@Bean(name = ["tilesConfigurer"])
	open fun getTilesConfigurer() : TilesConfigurer {
		val tilesConfigurer = TilesConfigurer()
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml")
		return tilesConfigurer
	}

	override fun configure(application : SpringApplicationBuilder) : SpringApplicationBuilder = application.sources(
			Application::class.java)

	companion object {
		@Throws(Exception::class)
		@JvmStatic
		fun main(args : Array<String>) {
			SpringApplication.run(Application::class.java , *args)
		}
	}
}