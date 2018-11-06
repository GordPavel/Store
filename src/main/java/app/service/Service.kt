package app.service

import app.models.Category
import app.models.Product
import org.springframework.stereotype.Service
import java.lang.String.format
import java.util.*

@Service
open class Service {

	fun getCategories() : List<Category> {
		val list = (1 until 8).map { "Category $it" }.map { name -> Category(name) }.toMutableList()
		list.add(4 , Category("Category" , listOf(Category("SubCategory_4_1") , Category("SubCategory_4_1"))))
		list.add(7 , Category("Category" , listOf(Category("SubCategory_7_1") , Category("SubCategory_7_1"))))
		return list
	}

	fun getProducts() : List<Product> {
		val random = Random()
		return (1..15).map { "Product $it" }
				.map { name ->
					Product(name ,
					        format("%.2f" , random.nextDouble() * 1000).replace("," , ".").toDouble())
				}.toList()
	}
}