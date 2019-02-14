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
		registry.apply {
			addViewController("/").setViewName("index")
			addViewController("/index").setViewName("index")
			addViewController("/category").setViewName("category")
			addViewController("/product").setViewName("product")
			addViewController("/login").setViewName("login")
			addViewController("/wishList").setViewName("wishList")
			addViewController("/search").setViewName("search")
			addViewController("/registration").setViewName("registration")
			addViewController("/admin").setViewName("admin")
		}
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