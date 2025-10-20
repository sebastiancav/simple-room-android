package cl.unab.room2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import cl.unab.room2.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.obtenerDB(this)

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val edad = binding.etEdad.text.toString().toIntOrNull() ?: 0
            val persona = Persona(nombre = nombre, edad = edad)

            lifecycleScope.launch {
                db.personaDao().insertar(persona)
                runOnUiThread {
                    binding.tvResultado.text = "✅ Persona guardada"
                }
            }
        }

        binding.btnMostrar.setOnClickListener {
            lifecycleScope.launch {
                val persona = db.personaDao().obtenerUltimaPersona()
                runOnUiThread {
                    if (persona != null) {
                        binding.tvResultado.text =
                            "👤 ${persona.nombre}, ${persona.edad} años (id=${persona.id})"
                    } else {
                        binding.tvResultado.text = "⚠️ No hay registros"
                    }
                }
            }
        }


    }
}