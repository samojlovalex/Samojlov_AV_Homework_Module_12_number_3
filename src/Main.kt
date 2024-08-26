import kotlinx.coroutines.flow.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

suspend fun main() {
    println("1. Задание\n")

    val firsNumber = 0
    val lastNumber = 100

    val numberList = (firsNumber..lastNumber).toList().asFlow()

    val outNumber = numberList.toList().map { number -> number }.reduce { a, b -> a + (b * b) }


    println(
        "Сумма всех элементов списка [$firsNumber...$lastNumber], " +
                "возведенных в квадрат: $outNumber"
    )

    getLine("-")

    println("\n2. Задание\n")

    println("Введите первый символ имени:")
    val first = readln()

    println("Введите возраст:")
    val age = readln().toInt()

    val personFilter = getPerson(first, age).toList()
    println("\n${personFilter.joinToString(separator = "\n")}")

    getLine("-")

    println("\n3.Задание\n")

    val person = mutableListOf<Person>()

    personFlow().collect { i -> person.add(i) }

    println(person.joinToString(separator = "\n"))
}

val persons = listOf(
    Person("Илья", 45),
    Person("Анна", 25),
    Person("Алексей", 18),
    Person("Илья", 33),
    Person("Влада", 55),
    Person("Полина", 28),
    Person("Александр", 39),
    Person("Андрей", 25),
    Person("Ренат", 43),
    Person("Ирина", 49)
)


fun getPerson(first: String, age: Int) = flow {
    //функция фильтрации списка Person по первой букве имени и возрасту
    val personOut = persons.filter { it.name.first().toString() == first.uppercase() && it.age == age }
    for (i in personOut.indices) {
        emit(personOut[i])
    }
}

fun first() = flow {
    //первый поток, с именами, для формирования списка Person
    for (i in persons.indices) {
        emit(persons[i].name)
    }
}

val cart = Cart()

fun second() = cart.cartNumberList(persons.size).asFlow()
//второй поток, с номерами карт, для формирования списка Person

val password = Password()

fun third() = password.passwordList(persons.size).asFlow()
//третий поток, с паролями, для формирования списка Person

fun personFlow(): Flow<Person> {
    //функция объединяющая три потока для формирования объекта Flow<Person>
    val personListOut = first().zip(second()) { name, cart -> Pair(name, cart) }
        .zip(third()) { pair, password ->
            Person(
                pair.first,
                null,
                pair.second,
                password
            )
        }
    return personListOut
}