package dam.pmdm.ejactivity01

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nameTextView: TextView
    var ageTextView: TextView
    init{
        nameTextView= itemView.findViewById(R.id.nameTextView)
        ageTextView= itemView.findViewById(R.id.ageTextView)
    }
}
