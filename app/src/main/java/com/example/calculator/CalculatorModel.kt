package com.example.calculator

class CalculatorModel {

    private var firstArg = 0.0
    private var secondArg = 0.0

    private var inputStr = StringBuilder()

    private var actionSelected = 0

    private enum class State {
        FirstArgInput, OperationSelected, SecondArgInput, ResultShow
    }

    private var state: State = State.FirstArgInput


    fun onNumPressed(buttonId: Int) {
        if (state == State.ResultShow) {
            state = State.FirstArgInput
            inputStr.setLength(0)
        }
        if (state == State.OperationSelected) {
            state = State.SecondArgInput
            inputStr.setLength(0)
        }
        if (inputStr.length < 9) {
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
                R.id.sign ->
                    if(inputStr.isNotEmpty())
                        inputStr = StringBuilder((inputStr.toString().toDouble()*(-1)).toString())
            }
        }
    }

    fun onActionPressed(actionId: Int) {
        if(state == State.ResultShow && actionId != R.id.equals){
            firstArg = inputStr.toString().toDouble()
            state = State.OperationSelected
            actionSelected = actionId
        }
        if (state == State.FirstArgInput && actionId != R.id.equals && inputStr.isNotEmpty() ) {
            firstArg = inputStr.toString().toDouble()
            state = State.OperationSelected
            actionSelected = actionId
        }
        if (state == State.SecondArgInput && actionId == R.id.equals && inputStr.isNotEmpty()) {
            secondArg = inputStr.toString().toDouble()
            state = State.ResultShow
            inputStr.setLength(0)
            when (actionSelected) {
                R.id.plus -> inputStr.append(firstArg + secondArg)
                R.id.minus -> inputStr.append(firstArg - secondArg)
                R.id.multiply -> inputStr.append(firstArg * secondArg)
                R.id.division -> inputStr.append(firstArg / secondArg)
                R.id.remainder -> inputStr.append(firstArg % secondArg)
            }
        }
    }

    private  fun correctOutput(str: String): String{
        return if(str.length <= 9)
            str
        else{
            if(str.contains('E')){
                '≈' + str.substring(0, 6)+'E'+str.substringAfter('E')
            } else {
                '≈' + str.substring(0, 9)
            }
        }
    }

    fun getText(): String {
        return when (state) {
            State.OperationSelected -> correctOutput(firstArg.toString())
            State.SecondArgInput -> correctOutput(inputStr.toString())
            State.ResultShow -> correctOutput(inputStr.toString())
            else -> correctOutput(inputStr.toString())
        }
    }

    fun reset() {
        state = State.FirstArgInput
        inputStr.setLength(0)
    }
}