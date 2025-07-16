package com.wgaray.appmonitoreo.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.wgaray.appmonitoreo.domain.model.Usuario

private val Context.dataStore by preferencesDataStore("user_prefs")

class SessionPreferencesDataSource(private val context: Context) {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_ROL = stringPreferencesKey("user_rol")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun saveSession(user: Usuario) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID] = user.id
            prefs[USER_NAME] = user.name
            prefs[USER_EMAIL] = user.email
            prefs[USER_ROL] = user.rol
            user.token?.let { prefs[USER_TOKEN] = it }
        }
    }

    fun getSession(): Flow<Usuario?> {
        return context.dataStore.data.map { prefs ->
            val id = prefs[USER_ID] ?: return@map null
            val name = prefs[USER_NAME] ?: return@map null
            val email = prefs[USER_EMAIL] ?: return@map null
            val rol = prefs[USER_ROL] ?: return@map null
            val token = prefs[USER_TOKEN]

            Usuario(id, name, email, rol, token)
        }
    }

    // Borra todos los datos de usuario de DataStore (logout local)
    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}
