package cl.unab.room2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.unab.room2.R
import cl.unab.room2.databinding.FragmentListaPersonasBinding

class ListaPersonasFragment : Fragment() {

    private var _binding: FragmentListaPersonasBinding? = null
    private val binding get() = _binding!!
    private val vm: PersonaViewModel by viewModels()
    private lateinit var adapter: PersonaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPersonasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PersonaAdapter(emptyList())
        binding.rvPersonas.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPersonas.adapter = adapter

        vm.personas.observe(viewLifecycleOwner) {
            adapter.actualizarLista(it)
        }
//        vm.cargarPersonas()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}