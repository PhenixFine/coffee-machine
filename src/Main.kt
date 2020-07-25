import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val getString = { scanner.nextLine().toLowerCase() }
    val coffee = CoffeeMachine()

    coffee.start()
    while (coffee.on()) coffee.run(getString())
}