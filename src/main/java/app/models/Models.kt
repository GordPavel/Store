package app.models

data class Category(val name : String , val subCategories : List<Category> = emptyList() ,
                    val products : List<Product> = emptyList())

data class Product(val name : String , val price : Double , val oldPrice : Double? = null , val visitorsCount : Int = 0)