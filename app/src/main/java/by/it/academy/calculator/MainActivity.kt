package by.it.academy.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.it.academy.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener() { setTextFields(getString(R.string.Num_0)) }
        binding.btn1.setOnClickListener() { setTextFields(getString(R.string.Num_1)) }
        binding.btn2.setOnClickListener() { setTextFields(getString(R.string.Num_2)) }
        binding.btn3.setOnClickListener() { setTextFields(getString(R.string.Num_3)) }
        binding.btn4.setOnClickListener() { setTextFields(getString(R.string.Num_4)) }
        binding.btn5.setOnClickListener() { setTextFields(getString(R.string.Num_5)) }
        binding.btn6.setOnClickListener() { setTextFields(getString(R.string.Num_6)) }
        binding.btn7.setOnClickListener() { setTextFields(getString(R.string.Num_7)) }
        binding.btn8.setOnClickListener() { setTextFields(getString(R.string.Num_8)) }
        binding.btn9.setOnClickListener() { setTextFields(getString(R.string.Num_9)) }
        binding.btnDot.setOnClickListener() { setTextFields(getString(R.string.Dot)) }
        binding.btnMinus.setOnClickListener() { setTextFields(getString(R.string.Minus)) }
        binding.btnPlus.setOnClickListener() { setTextFields(getString(R.string.Plus)) }
        binding.btnDivide.setOnClickListener() { setTextFields("/") }
        binding.btnMultiply.setOnClickListener() { setTextFields("*") }

        binding.btnClear.setOnClickListener() {
            binding.operationField.text = ""
            binding.resultField.text = ""
        }

        binding.btnBack.setOnClickListener {
            val str = binding.operationField.text.toString()
            if (str.isNotEmpty()) binding.operationField.text = str.substring(0, str.length - 1)
            binding.resultField.text = ""
        }

        binding.btnEqual.setOnClickListener {
            try {
                val ex = ExpressionBuilder(binding.operationField.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble()) binding.resultField.text = longRes.toString()
                else binding.resultField.text = result.toString()
            } catch (e: Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }
    }

    fun setTextFields(str: String) {
        if (binding.resultField.text != "") {
            binding.operationField.text = binding.resultField.text
            binding.resultField.text = ""
        }
        binding.operationField.append(str)
    }
}

