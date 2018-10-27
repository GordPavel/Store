package app.controllers.common

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class JspController {

	@RequestMapping(value = ["/" , "/index"])
	fun index() = "index"

}