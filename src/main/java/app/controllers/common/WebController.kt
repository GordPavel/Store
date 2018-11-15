package app.controllers.common

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

	@GetMapping(value = ["/" , "/index"])
	fun index() = "index"

	@GetMapping(value = ["/category"])
	fun category(@RequestParam("name") categoryName : String , model : Model) : String {
		model.addAttribute("title" , categoryName)
		model.addAttribute("category" , categoryName)
		return "category"
	}

	@GetMapping(value = ["/product"])
	fun product() = "product"

	@GetMapping(value = ["/login"])
	fun loginPage() = "login"

	@GetMapping(value = ["/wishList"])
	fun wishList() = "wishList"

	@PostMapping(value = ["/search"])
	fun search() = "search"

}