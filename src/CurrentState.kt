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