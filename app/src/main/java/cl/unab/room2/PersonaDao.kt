package cl.unab.room2
import androidx.room.*

@Dao
interface PersonaDao {
    @Insert
    suspend fun insertar(persona: Persona)

    @Query("SELECT * FROM Persona LIMIT 1")
    suspend fun obtenerPrimeraPersona(): Persona?

    @Query("SELECT * FROM Persona ORDER BY id DESC LIMIT 1")
    suspend fun obtenerUltimaPersona(): Persona?


}