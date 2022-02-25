package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var calculator: CalculatorModel? = null

    private var text: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberIds = intArrayOf(
            R.id.zero,//0
            R.id.one,//1
            R.id.two,//2
            R.id.three,//3
            R.id.four,//4
            R.id.five,//5
            R.id.six,//6
            R.id.seven,//7
            R.id.eight,//8
            R.id.nine,//9
            R.id.dot,//,
        )
        val actionIds = intArrayOf(
            R.id.plus,//+
            R.id.minus,//-
            R.id.multiply,//*
            R.id.division,//÷
            R.id.equals,//=
            R.id.remainder,//%
            R.id.memory,//+/-
            R.id.reset,//AC
        )

        text = findViewById(R.id.textView2)
        calculator = CalculatorModel()

        val numberButtonClickListener =
            View.OnClickListener { view ->
                calculator!!.onNumPressed(view.id)
                text?.text = calculator!!.getText()
            }

        val actionButtonOnclickListener =
            View.OnClickListener { view ->
                calculator!!.onActionPressed(view.id)
                text?.text = calculator!!.getText()
            }

        for (element in numberIds) {
            findViewById<View>(element).setOnClickListener(numberButtonClickListener)
        }
        for (i in actionIds) {
            findViewById<View>(i).setOnClickListener(actionButtonOnclickListener)
        }

        findViewById<View>(R.id.reset).setOnClickListener {
            calculator!!.reset()
            text?.text = calculator!!.getText()
        }
    }
}