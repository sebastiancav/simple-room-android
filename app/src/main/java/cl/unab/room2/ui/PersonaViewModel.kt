package cl.unab.room2.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.unab.room2.data.AppDatabase
import cl.unab.room2.data.Persona
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class PersonaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.obtenerDB(application).personaDao()
//    val personas: LiveData<List<Persona>> = dao.observarTodas()


        private val _personas = MutableLiveData<List<Persona>>()
        val personas: LiveData<List<Persona>> = _personas

        private val _cargando = MutableLiveData(false)
        val cargando: LiveData<Boolean> = _cargando

        fun cargarPersonas() {
            viewModelScope.launch(Dispatchers.IO) {
                _cargando.postValue(true)
                val lista = dao.obtenerTodas()
                _personas.postValue(lista)
                _cargando.postValue(false)
            }
        }

}