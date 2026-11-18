package com.example.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.AppLanguage
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.bt

@Composable
fun EducationSchoolScreen(
  language: AppLanguage,
  entries: List<DirectoryEntry>,
  modifier: Modifier = Modifier,
) {
  DirectoryScreenContent(
    language = language,
    headerIcon = Icons.Filled.School,
    headerTitle = bt("शिक्षा एवं स्कूल (DEMO)", "Shiksha & School (DEMO)").resolve(language),
    headerSubtitle = bt(
      "स्थानीय विद्यालय, आंगनवाड़ी व छात्र सूचना केंद्र के लिए डेमो निर्देशिका।",
      "Sthaniya school, Anganwadi aur chhatra suchna ke liye demo directory.",
    ).resolve(language),
    demoNotice = bt(
      "DEMO प्रविष्टियाँ: विद्यालयों के नाम, संपर्क व सूचनाएँ केवल नमूना लेआउट के रूप में दिखाई गई हैं।",
      "DEMO Entries: School ke naam, sampark aur suchnaen sirf sample layout ke roop mein dikhayi gayi hain.",
    ).resolve(language),
    entries = entries,
    useGreenAccent = false,
    modifier = modifier,
  )
}
