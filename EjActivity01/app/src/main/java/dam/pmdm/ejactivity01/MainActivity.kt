package dam.pmdm.ejactivity01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var guardarButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var startForResult: ActivityResultLauncher<Intent> // Declarar fuera de onCreate
    private lateinit var personaAdapter: PersonaAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bind
        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        guardarButton = findViewById(R.id.guardarButton)
        recyclerView = findViewById(R.id.recyclerView)
        //crear lista

        //crear adaptador
        personaAdapter = PersonaAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        //asignar adaptador al recycler
        recyclerView.adapter = personaAdapter

        // Registrar el ActivityResultLauncher
        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val position = data.getIntExtra("position", -1)
                    val updatedName = data.getStringExtra("updatedName")
                    val updatedAge = data.getIntExtra("updatedAge", -1)

                    if (position != -1 && updatedName != null && updatedAge != -1) {

                        var p=Persona(updatedName, updatedAge)
                        println(""+p.age +" "+p.name)
                        personaAdapter.changeItem(position, p)
                    }
                }
            }
        }


        guardarButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toIntOrNull() ?: 0
            personaAdapter.addpersona(Persona(name, age))

            nameEditText.text.clear()
            ageEditText.text.clear()
        }

        //implementar callback
        personaAdapter.setAdaptadorCallback(object:PersonaAdapter.PersonaAdapterCallback{

            override fun onClickPersona(p: Persona, position: Int) {
                val intent = Intent(this@MainActivity, EditActivity::class.java).apply {
                    putExtra("position", position)
                    putExtra("name", p.name)
                    putExtra("age", p.age)
                }
                startForResult.launch(intent)
            }

        })

    }
}