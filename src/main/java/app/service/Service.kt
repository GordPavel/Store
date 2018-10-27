package app.service

import app.models.Category
import app.models.Product
import org.springframework.stereotype.Service
import java.util.*

@Service
open class Service {

	fun getCategories() : List<Category> {
		val list = (1 until 8).map { "Category $it" }.map { name -> Category(name) }.toMutableList()
		list.add(4 , Category("Category" , listOf(Category("Category") , Category("Category"))))
		list.add(7 , Category("Category" , listOf(Category("Category") , Category("Category"))))
		return list
	}

	fun getProducts() : List<Product> {
		val random = Random()
		return (1 until 3).map { "Product $it" }.map { name -> Product(name , random.nextDouble()) }.toList()
	}
}