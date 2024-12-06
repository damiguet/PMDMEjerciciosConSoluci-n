package dam.pmdm.ejwidgets02

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var checkBox5: CheckBox
    private lateinit var checkButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar componentes
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkBox5 = findViewById(R.id.checkBox5)
        checkButton = findViewById(R.id.checkButton)
        resultText = findViewById(R.id.resultText)

        configurarOyentes()

        // Listener para el botón
        checkButton.setOnClickListener {
            comprobarCombinacion()
        }
    }

    private fun configurarOyentes() {
        checkBox1.setOnClickListener {
            if (checkBox1.isChecked) {
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = false
                checkBox5.isChecked = false
                resultText.append("\nOpción 1: Desmarco todas las demás.")
            }
        }

        checkBox2.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                    checkBox1.isChecked = !checkBox1.isChecked
                    checkBox4.isChecked = !checkBox4.isChecked
                    checkBox5.isChecked = !checkBox5.isChecked
                    resultText.append("\nOpción 2: Invirtió el estado de 1, 4 y 5.")
            }
        })

        checkBox3.setOnClickListener {
            if (checkBox3.isChecked) {
                checkBox1.isChecked = true
                checkBox4.isChecked = false
                resultText.append("\nOpción 3: Activó la 1 y desactivó la 4.")
            }
        }

        checkBox4.setOnClickListener {
            if (checkBox4.isChecked && !checkBox3.isChecked) {
                checkBox4.isChecked = false
                resultText.append("\nOpción 4: Primero activa la opción 3.")
            }
        }

        checkBox5.setOnClickListener {
            if (checkBox5.isChecked) {
                checkBox1.isChecked = true
                checkBox4.isChecked = false
                resultText.append("\nOpción 5: Activó la 1 y desactivó la 4. Habilito botón")
            }
        }

        checkBox5.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
                if (!checked) {
                    checkButton.isEnabled = false
                } else {
                    checkButton.isEnabled = true
                }
            }

        })
    }


    private fun comprobarCombinacion() {
        if (checkBox1.isChecked &&
            checkBox2.isChecked &&
            checkBox3.isChecked &&
            checkBox4.isChecked &&
            checkBox5.isChecked
        ) {
            resultText.text = "¡Combinación correcta!"
        } else {
            resultText.append("\nCombinación incorrecta. Intenta de nuevo.")
        }
    }
}
