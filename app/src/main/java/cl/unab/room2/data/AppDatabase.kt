package cl.unab.room2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Persona::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personaDao(): PersonaDao
    companion object {
        private var instancia: AppDatabase? = null

        fun obtenerDB(context: Context): AppDatabase {
            if (instancia == null) {
                instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personas.db"
                ).build()
            }
            return instancia!!
        }
    }
}
