class Password {

    private fun password(): String {
        //функция генерации пароля
        val passwordGeneratorList = (0..3).map { ('0'..'9').random() }.toMutableList()
        val passwordGenerator = passwordGeneratorList.toList().fold("") { a, b -> "$a$b" }.toString()
        return passwordGenerator
    }

    fun passwordList(length: Int): List<String> {
        //список сгенерированных паролей
        val passwordList = mutableListOf<String>()
        for (i in 0..<length){
            passwordList.add(password())
        }
        return passwordList
    }
}