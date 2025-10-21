package cl.unab.room2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.unab.room2.data.Persona
import cl.unab.room2.databinding.ItemPersonaBinding

class PersonaAdapter(
    private var personas: List<Persona>
) : RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder>() {

    inner class PersonaViewHolder(val binding: ItemPersonaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personas[position]
        holder.binding.tvNombre.text = persona.nombre
        holder.binding.tvEdad.text = "${persona.edad} años"
    }

    override fun getItemCount(): Int = personas.size

    fun actualizarLista(nuevaLista: List<Persona>) {
        personas = nuevaLista
        notifyDataSetChanged()
    }
}
