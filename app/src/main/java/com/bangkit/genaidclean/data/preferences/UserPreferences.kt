package com.bangkit.genaidclean.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bangkit.genaidclean.data.remote.response.login.Payload
import com.bangkit.genaidclean.data.remote.response.login.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore by preferencesDataStore(name="loginSession")

class UserPreferences private constructor(private val dataStore:DataStore<Preferences>) {

    suspend fun saveUserPref(user: UserModel){
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = user.name
            preferences[ROLE_KEY] = user.role
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getUserPref() : Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                name = preferences[NAME_KEY] ?: "",
                role = preferences[ROLE_KEY] ?: "",
                token = preferences[TOKEN_KEY] ?: "",
            )
        }
    }

    suspend fun clearUserPref(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }



    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val ROLE_KEY = stringPreferencesKey("role")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(context: Context): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val dataStore = context.datastore
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}