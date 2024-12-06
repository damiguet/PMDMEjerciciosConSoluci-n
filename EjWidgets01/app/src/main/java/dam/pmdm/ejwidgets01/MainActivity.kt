package dam.pmdm.ejwidgets01

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var themeSpinner: Spinner
    private lateinit var musicCheckBox: CheckBox
    private lateinit var lightSwitch: Switch
    private lateinit var statusText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Inicialización de widgets
        themeSpinner = findViewById(R.id.themeSpinner);
        musicCheckBox = findViewById(R.id.musicCheckBox);
        lightSwitch = findViewById(R.id.lightSwitch);
        statusText = findViewById(R.id.statusText);

        // Configurar Spinner con temas
        val themes = arrayOf("80s", "Drum N Bass", "Rock", "Urbana")
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, themes);
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        themeSpinner.adapter = adaptador;

        // Listener del Spinner
        themeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                updateStatus();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //no hago nada
            }
        }

        // Listener del CheckBox
        musicCheckBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                updateStatus()
            }
        })


        // Listener del Switch
        lightSwitch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                updateStatus()

            }
        })
}

// Actualizar estado en TextView
fun updateStatus() {
    var theme = themeSpinner.getSelectedItem().toString();
    val music = if (musicCheckBox.isChecked) "Sí" else "No"
    val lights = if (lightSwitch.isChecked) "Encendidas" else "Apagadas"

    var status = "Tema: $theme\nMúsica: $music\nLuces: $lights";
    statusText.text = status;
}


}