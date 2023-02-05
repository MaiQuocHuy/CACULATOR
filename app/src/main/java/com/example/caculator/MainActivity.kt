package com.example.caculator

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.caculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val pi: String = "3.14159265358979"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.b0.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}0"

        }

        binding.b1.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}1"

        }
        binding.b2.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}2"

        }
        binding.b3.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}3"

        }
        binding.b4.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}4"

        }
        binding.b5.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}5"

        }
        binding.b6.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}6"

        }
        binding.b7.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}7"

        }
        binding.b8.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}8"

        }
        binding.b9.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}9"
        }

        binding.bdot.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}."
        }

        binding.bac.setOnClickListener {
            binding.tvmain.text = ""
            binding.tvsec.text = ""
        }

        binding.bc.setOnClickListener {
            var text = binding.tvmain.text.toString().trim()
            if (text.isNotEmpty()) {
                text = text.substring(0, text.length - 1)
            }
            binding.tvmain.text = text
        }

        binding.bplus.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}+"
        }

        binding.bmin.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}-"
        }

        binding.bmul.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}×"
        }

        binding.bdiv.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}÷"
        }

        binding.bsqrt.setOnClickListener {
            try {
                var text = binding.tvmain.text.toString().trim()
                var r = Math.sqrt(text.toDouble())
                binding.tvsec.text = "√${text.toString().trim()}"
                binding.tvmain.text = r.toString().trim()
            } catch (e: Exception) {
                binding.tvsec.text = ""
                binding.tvmain.text = "Error syntax(Enter number before)"
            }
        }

        binding.bb1.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}("

        }

        binding.bb2.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text})"
        }

        binding.bpi.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}${pi}"

        }

        binding.bsin.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}sin"

        }

        binding.bcos.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}cos"

        }

        binding.btan.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}tan"

        }

        binding.bcot.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}cot"

        }

        binding.bln.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}ln"
        }

        binding.blog.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}log"
        }

        binding.binv.setOnClickListener {
            binding.tvmain.text = "${binding.tvmain.text}^(-1)"
        }

        binding.bfactorial.setOnClickListener {
            try {
                var value = binding.tvmain.text.toString().trim().toDouble().toInt()
                var fact = factorial(value)
                if(fact > 9223372036854775807 && fact < -9223372036854775807) {
                    binding.tvmain.text = "Too much data"
                    binding.tvsec.text = "${value.toString().trim()}!"
                } else {
                    binding.tvmain.text = fact.toString().trim()
                    binding.tvsec.text = "${value.toString().trim()}!"
                }
            } catch (e: Exception) {
                binding.tvmain.text = "Error syntax(Enter number before)"
                binding.tvsec.text = ""
            }
        }

        binding.bsquare.setOnClickListener {
            try {
                var doub = binding.tvmain.text.toString().trim().toDouble()
                var square = doub * doub
                binding.tvmain.text = square.toString().trim()
                binding.tvsec.text = "${doub}²"
            } catch (e: Exception) {
                binding.tvmain.text = "Error syntax(Enter number before)"
                binding.tvsec.text = ""
            }
        }

        binding.bequal.setOnClickListener {
            try {
                var value = binding.tvmain.text.toString().trim()
                var replacedstr = value.replace('÷', '/').replace('×', '*')
                var result = eval(replacedstr)
                binding.tvmain.text = result.toString().trim()
                binding.tvsec.text = value
            } catch (e: Exception) {
                binding.tvmain.text = "Error syntax(Enter number before)"
                binding.tvsec.text = ""
            }
        }


        //factorial function
    }

    fun factorial(n: Int): Long {
        if (n == 1 || n == 0) {
            return 1
        } else {
            return n*factorial(n - 1)
        }
    }


    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.code) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                try {
                    val x = parseExpression()
                    if (pos < str.length) binding.tvmain.text = "Error syntax"
                    return x
                } catch (e: Exception) {
                    binding.tvmain.text = "Error syntax"
                    return 0.0
                }
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.code)) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.code)) x *= parseFactor() // multiplication
                    else if (eat('/'.code)) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                try {
                    if (eat('+'.code)) return parseFactor() // unary plus
                    if (eat('-'.code)) return -parseFactor() // unary minus
                    var x: Double
                    val startPos = pos
                    if (eat('('.code)) { // parentheses
                        x = parseExpression()
                        eat(')'.code)
                    } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) { // numbers
                        while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                        x = str.substring(startPos, pos).toDouble()
                    } else if (ch >= 'a'.code && ch <= 'z'.code) { // functions
                        while (ch >= 'a'.code && ch <= 'z'.code) nextChar()
                        val func = str.substring(startPos, pos)
                        x = parseFactor()
                        x =
                            if (func == "sqrt") Math.sqrt(x) else if (func == "sin") Math.sin(
                                Math.toRadians(
                                    x
                                )
                            ) else if (func == "cos") Math.cos(
                                Math.toRadians(x)
                            ) else if (func == "tan") Math.tan(Math.toRadians(x))
                            else if (func == "cot") 1 / Math.tan(Math.toRadians(x))
                            else if (func == "log") Math.log10(
                                x
                            )
                            else if (func == "ln") Math.log(x)
                            else
                                throw RuntimeException(
                                    "Unknown function: $func"
                                )
                    } else {
                        throw RuntimeException("Unexpected: " + ch.toChar())
                    }
                    if (eat('^'.code)) x = Math.pow(x, parseFactor()) // exponentiation
                    return x
                } catch (e: RuntimeException) {
                    binding.tvmain.text = "error syntax"
                    return 0.0
                }
            }
        }.parse()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            }
        }
    }
}

