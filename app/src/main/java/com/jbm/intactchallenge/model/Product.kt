package com.jbm.intactchallenge.model

class Product {
    var id = 0
    var title = ""
    var brand = ""
    var shordDescription = ""
    var fullDescription = ""
    var price = 0.0
    var imageUrl = ""
    var colors: MutableList<Color> = mutableListOf()
    var size = Size("","","")
    var quantityInStock = 0
    var wishListed = 0
    var rating = 0

    @Override
    override fun toString(): String {
        return "Title = " + title
    }
}