class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    private var currentState: CurrentState = CurrentState.EXIT
    private var fillCount = -1
    private val fill: Array<String> = arrayOf(
        "Write how many ml of milk do you want to add: ",
        "Write how many grams of coffee beans do you want to add: ",
        "Write how many disposable cups of coffee do you want to add: "
    )

    fun start() {
        reset()
        print(currentState.dialog)
    }

    private fun reset() {
        currentState = CurrentState.START
    }

    fun run(string: String) {
        if (on()) {
            if (currentState == CurrentState.START) currentState = CurrentState.getState(string)
            when (currentState) {
                CurrentState.START -> return
                CurrentState.BUY -> buyCoffee(string)
                CurrentState.FILL -> fillMachine(string)
                CurrentState.TAKE -> take()
                CurrentState.REMAINING -> coffeeHas()
                CurrentState.EXIT -> return
            }
            printState()
        }
    }

    fun on() = currentState != CurrentState.EXIT

    private fun buyCoffee(string: String) {
        when (string) {
            "1" -> getCoffee(Drink.ESPRESSO)
            "2" -> getCoffee(Drink.LATTE)
            "3" -> getCoffee(Drink.CAPPUCCINO)
            "BACK" -> reset()
        }
    }

    private fun getCoffee(drink: Drink) {
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
        reset()
    }

    private fun fillMachine(string: String) {
        if (fillCount > -1) {
            if (checkNum(string)) {
                when (fillCount) {
                    0 -> water += string.toInt()
                    1 -> milk += string.toInt()
                    2 -> beans += string.toInt()
                    3 -> {
                        cups += string.toInt()
                        fillCount = -2
                        reset()
                    }
                }
            } else fillCount--
        }
        fillCount++
    }

    private fun take() {
        println("I gave you $${money}")
        money = 0
        reset()
    }

    private fun coffeeHas() {
        println(
            "\nThe coffee machine has:\n${water} of water\n${milk} of milk" +
                    "\n${beans} of coffee beans\n${cups} of disposable cups\n$${money} of money"
        )
        reset()
    }

    private fun printState() {
        if (fillCount <= 0) {
            println("")
            print(currentState.dialog)
        } else print(fill[fillCount - 1])
    }

    private fun checkNum(strPassed: String) = strPassed.toIntOrNull() != null
}