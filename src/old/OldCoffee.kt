package old
import java.util.Scanner

val scanner = Scanner(System.`in`)
var water = 400
var milk = 540
var beans = 120
var cups = 9
var money = 550

fun coffeeHas() {
    println(
        "The coffee machine has:\n$water of water\n$milk of milk" +
                "\n$beans of coffee beans\n$cups of disposable cups\n$$money of money"
    )
}

fun getNum(strPassed: String): Int {
    print(strPassed)
    return (scanner.nextInt())
}

fun getString(strPassed: String): String {
    print(strPassed)
    return (scanner.next())
}

fun getCoffee(waterNeed: Int, milkNeed: Int, beansNeed: Int, cost: Int) {
    when {
        cups <= 0 -> println("Sorry, not enough cups!")
        waterNeed > water -> println("Sorry, not enough water!")
        milkNeed > milk -> println("Sorry, not enough milk!")
        beansNeed > beans -> println("Sorry, not enough coffee beans!")
        else -> {
            water -= waterNeed
            milk -= milkNeed
            beans -= beansNeed
            cups--
            money += cost
            println("I have enough resources, making you a coffee!")
        }
    }
}

fun main() {
    var action: String
    val strAction = "Write action (buy, fill, take, remaining, exit): "
    val strBuy = "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "
    val strFillWater = "Write how many ml of water do you want to add: "
    val strFillMilk = "Write how many ml of milk do you want to add: "
    val strFillBeans = "Write how many grams of coffee beans do you want to add: "
    val strFillCups = "Write how many disposable cups of coffee do you want to add: "

    action = getString(strAction)
    while (action != "exit") {
        println("")
        when (action) {
            "buy" -> {
                when (getString(strBuy)) {
                    "1" -> { // espresso
                        getCoffee(250, 0, 16, 4)
                    }
                    "2" -> { //latte
                        getCoffee(350, 75, 20, 7)
                    }
                    "3" -> { // cappuccino
                        getCoffee(200, 100, 12, 6)
                    }
                }
            }
            "fill" -> {
                water += getNum(strFillWater)
                milk += getNum(strFillMilk)
                beans += getNum(strFillBeans)
                cups += getNum(strFillCups)
            }
            "take" -> {
                println("I gave you $$money")
                money = 0
            }
            "remaining" -> coffeeHas()
        }
        println("")
        action = getString(strAction)
    }
}