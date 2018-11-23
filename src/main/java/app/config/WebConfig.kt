package app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.UrlBasedViewResolver
import org.springframework.web.servlet.view.tiles3.TilesConfigurer
import org.springframework.web.servlet.view.tiles3.TilesView

@Configuration
open class WebConfig : WebMvcConfigurer {
	override fun addViewControllers(registry : ViewControllerRegistry) {
		registry.addViewController("/").setViewName("index")
		registry.addViewController("/index").setViewName("index")
		registry.addViewController("/category").setViewName("category")
		registry.addViewController("/product").setViewName("product")
		registry.addViewController("/login").setViewName("login")
		registry.addViewController("/wishList").setViewName("wishList")
		registry.addViewController("/search").setViewName("search")
		registry.addViewController("/registration").setViewName("registration")
	}

	@Bean(name = ["viewResolver"])
	open fun getViewResolver() = UrlBasedViewResolver().apply {
		setViewClass(TilesView::class.java)
	}

	@Bean(name = ["tilesConfigurer"])
	open fun getTilesConfigurer() = TilesConfigurer().apply {
		setDefinitions("/WEB-INF/tiles.xml")
	}
}