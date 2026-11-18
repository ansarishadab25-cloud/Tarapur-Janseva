package com.example.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.model.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.jansevaDataStore: DataStore<Preferences> by preferencesDataStore(
  name = "tarapur_janseva_prefs"
)

class UserPreferencesRepository(private val context: Context) {

  private object Keys {
    val LANGUAGE = stringPreferencesKey("selected_language")
    val WELCOME_SEEN = booleanPreferencesKey("welcome_seen")
  }

  val selectedLanguage: Flow<AppLanguage> =
    context.jansevaDataStore.data.map { prefs ->
      when (prefs[Keys.LANGUAGE]) {
        AppLanguage.HINGLISH.code -> AppLanguage.HINGLISH
        else -> AppLanguage.HINDI
      }
    }

  val hasCompletedWelcome: Flow<Boolean> =
    context.jansevaDataStore.data.map { prefs ->
      prefs[Keys.WELCOME_SEEN] ?: false
    }

  suspend fun setLanguage(language: AppLanguage) {
    context.jansevaDataStore.edit { prefs ->
      prefs[Keys.LANGUAGE] = language.code
    }
  }

  suspend fun setWelcomeCompleted(completed: Boolean) {
    context.jansevaDataStore.edit { prefs ->
      prefs[Keys.WELCOME_SEEN] = completed
    }
  }
}
