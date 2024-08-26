class Cart {


    private fun cartNumber(): String {

        fun generatorCartNumber(): String {
            //функция генерации номера карты
            val cartNumberGeneratorList = (0..4).map { ('0'..'9').random() }.toMutableList()
            val cartNumberGenerator = cartNumberGeneratorList.toList().fold("") { a, b -> "$a$b" }.toString()
            return cartNumberGenerator
        }

        val cartNumberList =
            listOf(generatorCartNumber(), generatorCartNumber(), generatorCartNumber(), generatorCartNumber())
        val cartNumber = cartNumberList.joinToString(
            separator = " "
        )
        return cartNumber
    }

    fun cartNumberList(length: Int): List<String> {
        //список сгенерированных номеров карт
        val cartList = mutableListOf<String>()
        for (i in 0..<length) {
            cartList.add(cartNumber())
        }
        return cartList
    }
}