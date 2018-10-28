package app.controllers.common

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class JspController {

	@RequestMapping(value = ["/" , "/index"])
	fun index() = "index"

	@RequestMapping(value = ["/category"])
	fun category() = "category"

	@RequestMapping(value = ["/product"])
	fun product() = "product"

	@RequestMapping(value = ["/login"])
	fun loginPage() = "login"

	@RequestMapping(value = ["/wishList"])
	fun wishList() = "wishList"

}