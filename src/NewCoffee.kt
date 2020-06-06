import java.util.*

class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    private var currentState: CurrentState = CurrentState.EXIT
    private var count = -1
    private val fill: Array<String> = arrayOf(
        "Write how many ml of milk do you want to add: ",
        "Write how many grams of coffee beans do you want to add: ",
        "Write how many disposable cups of coffee do you want to add: "
    )

    enum class DRINK(val water: Int, val milk: Int, val beans: Int, val money: Int) {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6)
    }

    enum class CurrentState(val dialog: String) {
        START("Write action (buy, fill, take, remaining, exit): "),
        BUY("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "),
        FILL("Write how many ml of water do you want to add: "),
        TAKE(""),
        REMAINING(""),
        EXIT("");

        companion object {
            fun getState(state: String): CurrentState {
                for (enum in values()) if (enum.toString() == state) return enum
                return START
            }
        }
    }

    fun start() {
        reset()
        run("START")
    }

    fun on() = currentState != CurrentState.EXIT

    private fun reset() {
        currentState = CurrentState.START
    }

    private fun coffeeHas() {
        println(
            "\nThe coffee machine has:\n${water} of water\n${milk} of milk" +
                    "\n${beans} of coffee beans\n${cups} of disposable cups\n$${money} of money"
        )
    }

    private fun checkNum(strPassed: String) = strPassed.toIntOrNull() != null

    private fun getCoffee(drink: DRINK) {
        when {
            cups <= 0 -> println("Sorry, not enough cups!")
            drink.water > water -> println("Sorry, not enough water!")
            drink.milk > milk -> println("Sorry, not enough milk!")
            drink.beans > beans -> println("Sorry, not enough coffee beans!")
            else -> {
                water -= drink.water
                milk -= drink.milk
                beans -= drink.beans
                cups--
                money += drink.money
                println("I have enough resources, making you a coffee!")
            }
        }
    }

    fun run(string: String) {
        if (on()) {
            if (currentState == CurrentState.START) currentState = CurrentState.getState(string)
            when (currentState) {
                CurrentState.START -> {
                    print(currentState.dialog)
                    return
                }
                CurrentState.BUY -> {
                    when (string) {
                        "1" -> {
                            getCoffee(DRINK.ESPRESSO)
                            reset()
                        }
                        "2" -> {
                            getCoffee(DRINK.LATTE)
                            reset()
                        }
                        "3" -> {
                            getCoffee(DRINK.CAPPUCCINO)
                            reset()
                        }
                        "BACK" -> reset()
                    }
                }
                CurrentState.FILL -> {
                    if (count > -1) {
                        if (checkNum(string)) {
                            when (count) {
                                0 -> water += string.toInt()
                                1 -> milk += string.toInt()
                                2 -> beans += string.toInt()
                                3 -> {
                                    cups += string.toInt()
                                    count = -2
                                    reset()
                                }
                            }
                        } else count--
                    }
                    count++
                }
                CurrentState.TAKE -> {
                    println("I gave you $${money}")
                    money = 0
                    reset()
                }
                CurrentState.REMAINING -> {
                    coffeeHas()
                    reset()
                }
                CurrentState.EXIT -> return
            }
            if (count <= 0) {
                println("")
                print(currentState.dialog)
            } else print(fill[count - 1])

        }
    }
}

fun getString(): String {
    val scanner = Scanner(System.`in`)
    return (scanner.nextLine().toUpperCase())
}

fun main() {
    val coffee = CoffeeMachine()
    coffee.start()
    while (coffee.on()) coffee.run(getString())
}