package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.UrlBasedViewResolver
import org.springframework.web.servlet.view.tiles3.TilesConfigurer
import org.springframework.web.servlet.view.tiles3.TilesView

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = ["app.service" , "app.controllers"])
open class Application : SpringBootServletInitializer() {

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
			println("starting application...")
			SpringApplication.run(Application::class.java , *args)
		}
	}
}