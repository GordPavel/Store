package app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EntityScan(basePackages = ["app.models"])
@EnableNeo4jRepositories(basePackages = ["app.repositories"])
@EnableTransactionManagement
@Configuration
open class Application : SpringBootServletInitializer() {

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