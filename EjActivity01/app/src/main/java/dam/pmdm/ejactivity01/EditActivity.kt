package dam.pmdm.ejactivity01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit)

        // bind
        nameEditText = findViewById(R.id.editNameEditText)
        ageEditText = findViewById(R.id.editAgeEditText)
        saveButton = findViewById(R.id.saveButton)
        // Recuperar datos del Intent
        val pos = intent.getIntExtra("position", 0)
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", -1)

        // Mostrar datos en los campos de texto
        nameEditText.setText(name)
        ageEditText.setText(age.toString())

        // Listener para guardar cambios
        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedAge = ageEditText.text.toString().toIntOrNull() ?: 0

            // Crear Intent para devolver los datos actualizados
            val resultIntent = Intent().apply {
                putExtra("position", pos)
                putExtra("updatedName", updatedName)
                putExtra("updatedAge", updatedAge)
            }

            setResult(RESULT_OK, resultIntent)
            finish() // Cerrar la actividad
        }
    }
}