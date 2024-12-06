package dam.pmdm.ejactivity01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PersonaAdapter() : RecyclerView.Adapter<PersonaViewHolder>() {
    private val personList: MutableList<Persona> = mutableListOf()
    private lateinit var listener: PersonaAdapterCallback
    interface PersonaAdapterCallback{
        fun onClickPersona(p: Persona, position: Int)
    }
    fun setAdaptadorCallback(listener: PersonaAdapterCallback) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_persona, parent, false)
        return PersonaViewHolder(view)
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val person = personList[position]
        holder.nameTextView.text = person.name
        holder.ageTextView.text = person.age.toString()

        // Listener para el clic
        holder.itemView.setOnClickListener {
            //que lo haga la actividad
            listener.onClickPersona(person, position)
        }
    }

    fun changeItem(position: Int, p: Persona) {
        personList[position]=p
        notifyItemChanged(position)
    }

    fun addpersona(persona: Persona) {
        personList.add(persona)
        notifyItemInserted(personList.size - 1)
    }


}
