import java.util.*

fun getString(): String {
    val scanner = Scanner(System.`in`)
    return (scanner.nextLine().toUpperCase())
}

fun main() {
    val coffee = CoffeeMachine()
    coffee.start()
    while (coffee.on()) coffee.run(getString())
}