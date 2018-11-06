package app.controllers.common

import app.service.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
class WebController {

	@Autowired
	lateinit var service : Service

	@GetMapping(value = ["/" , "/index"])
	fun index() = "index"

	@GetMapping(value = ["/category"])
	fun category(@RequestParam("name") categoryName : String , model : Model) : String {
		model.addAttribute("title" , categoryName)
		return "category"
	}

	@GetMapping(value = ["/product"])
	fun product() = "product"

	@GetMapping(value = ["/login"])
	fun loginPage() = "login"

	@GetMapping(value = ["/wishList"])
	fun wishList() = "wishList"

}