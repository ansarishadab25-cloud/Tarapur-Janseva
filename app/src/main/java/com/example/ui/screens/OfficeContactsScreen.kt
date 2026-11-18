package com.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.AppLanguage
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.bt

@Composable
fun OfficeContactsScreen(
  language: AppLanguage,
  entries: List<DirectoryEntry>,
  modifier: Modifier = Modifier,
) {
  DirectoryScreenContent(
    language = language,
    headerIcon = Icons.Filled.Contacts,
    headerTitle = bt("सरकारी कार्यालय संपर्क (DEMO)", "Karyalaya Sampark (DEMO)").resolve(language),
    headerSubtitle = bt(
      "प्रखंड, अंचल व ग्राम पंचायत कार्यालय संपर्क सूची का डेमो प्रारूप।",
      "Block, Anchal aur Gram Panchayat karyalaya sampark suchi ka demo praroop.",
    ).resolve(language),
    demoNotice = bt(
      "महत्वपूर्ण (DEMO): किसी भी कार्यालय का फ़ोन नंबर या सटीक पता मन से नहीं लिखा गया है। सत्यापन के बाद इन्हें एडमिन द्वारा जोड़ा जाएगा।",
      "Zaroori (DEMO): Kisi bhi karyalaya ka phone number ya pata man se nahi likha gaya hai. Satyapan ke baad admin dwara joda jayega.",
    ).resolve(language),
    entries = entries,
    useGreenAccent = false,
    modifier = modifier,
  )
}
