package com.example.domain.model

enum class AppLanguage(val code: String, val displayLabel: String) {
  HINDI("hi", "हिन्दी"),
  HINGLISH("hinglish", "Hinglish"),
}

data class BilingualText(
  val hi: String,
  val hinglish: String,
) {
  fun resolve(language: AppLanguage): String =
    when (language) {
      AppLanguage.HINDI -> hi
      AppLanguage.HINGLISH -> hinglish
    }
}

fun bt(hi: String, hinglish: String): BilingualText = BilingualText(hi = hi, hinglish = hinglish)
