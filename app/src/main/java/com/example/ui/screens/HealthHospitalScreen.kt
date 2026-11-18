package com.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.AppLanguage
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.bt

@Composable
fun HealthHospitalScreen(
  language: AppLanguage,
  entries: List<DirectoryEntry>,
  modifier: Modifier = Modifier,
) {
  DirectoryScreenContent(
    language = language,
    headerIcon = Icons.Filled.LocalHospital,
    headerTitle = bt("स्वास्थ्य एवं अस्पताल (DEMO)", "Swasthya & Aspatal (DEMO)").resolve(language),
    headerSubtitle = bt(
      "तारापुर क्षेत्र के स्वास्थ्य केंद्र, टीकाकरण व मातृ-शिशु सहायता का नमूना प्रारूप।",
      "Tarapur kshetra ke swasthya kendra aur tikakaran jankari ka sample praroop.",
    ).resolve(language),
    demoNotice = bt(
      "DEMO प्रविष्टियाँ: यहाँ कोई मनगढ़ंत फ़ोन नंबर या पता नहीं दिया गया है। सत्यापित होने पर असली संपर्क विवरण जोड़े जाएंगे।",
      "DEMO Entries: Yahan koi nakli phone number ya pata nahi diya gaya hai. Verify hone par asli contact jode jayenge.",
    ).resolve(language),
    entries = entries,
    useGreenAccent = true,
    modifier = modifier,
  )
}
