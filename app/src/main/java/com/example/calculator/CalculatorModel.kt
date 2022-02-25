package com.example.calculator

class CalculatorModel {


    private var firstArg = 0.0
    private var secondArg = 0.0

    private val inputStr = StringBuilder()

    private var actionSelected = 0

    private enum class State {
        firstArgInput, operationSelected, secondArgInput, resultShow
    }

    private var state: State = State.firstArgInput


    fun onNumPressed(buttonId: Int) {
        if (state == State.resultShow) {
            state = State.firstArgInput
            inputStr.setLength(0)
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput
            inputStr.setLength(0)
        }
        if (inputStr.length < 8) {
            when (buttonId) {
                R.id.zero ->
                    if (inputStr.isNotEmpty())
                        inputStr.append("0")
                    else
                        inputStr.append("0.")
                R.id.one -> inputStr.append("1")
                R.id.two -> inputStr.append("2")
                R.id.three -> inputStr.append("3")
                R.id.four -> inputStr.append("4")
                R.id.five -> inputStr.append("5")
                R.id.six -> inputStr.append("6")
                R.id.seven -> inputStr.append("7")
                R.id.eight -> inputStr.append("8")
                R.id.nine -> inputStr.append("9")
                R.id.dot ->
                    if (inputStr.isEmpty())
                        inputStr.append("0.")
                    else if(!inputStr.contains("."))
                        inputStr.append(".")
            }
        }
    }

    fun onActionPressed(actionId: Int) {
        if (actionId == R.id.equals && state == State.secondArgInput && inputStr.isNotEmpty()) {
            secondArg = inputStr.toString().toDouble()
            state = State.resultShow
            inputStr.setLength(0)
            when (actionSelected) {
                R.id.plus -> inputStr.append(firstArg + secondArg)
                R.id.minus -> inputStr.append(firstArg - secondArg)
                R.id.multiply -> inputStr.append(firstArg * secondArg)
                R.id.division -> inputStr.append(firstArg / secondArg)
                R.id.remainder -> inputStr.append(firstArg % secondArg)
            }
        } else if (inputStr.isNotEmpty() && state == State.firstArgInput && actionId != R.id.equals) {
            firstArg = inputStr.toString().toDouble()
            state = State.operationSelected
            actionSelected = actionId
        }
    }

    fun getText(): String {
        val str = StringBuilder()
        return when (state) {
            State.operationSelected -> str.append(firstArg).toString()
            State.secondArgInput -> str.append(inputStr).toString()
            State.resultShow -> str.append(inputStr.toString()).toString()
            else -> inputStr.toString()
        }
    }

    fun reset() {
        state = State.firstArgInput
        inputStr.setLength(0)
    }
}