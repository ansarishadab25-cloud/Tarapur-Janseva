package com.example.domain.model

enum class AccentTone {
  BLUE,
  GREEN,
}

data class HomeFeatureItem(
  val number: Int,
  val route: String,
  val title: BilingualText,
  val subtitle: BilingualText,
  val accentTone: AccentTone,
  val testTag: String,
)

enum class SchemeCategory(val label: BilingualText) {
  ALL(bt("सभी", "Sabhi")),
  AGRICULTURE(bt("कृषि एवं किसान", "Krishi & Kisan")),
  HOUSING(bt("आवास एवं बुनियादी सुविधा", "Awas & Suvidha")),
  PENSION(bt("पेंशन एवं सामाजिक सहायता", "Pension & Sahayata")),
  WOMEN_STUDENT(bt("महिला एवं छात्रवृत्ति", "Mahila & Chhatravritti")),
}

data class DemoScheme(
  val id: String,
  val category: SchemeCategory,
  val title: BilingualText,
  val summary: BilingualText,
  val eligibility: List<BilingualText>,
  val requiredDocs: List<BilingualText>,
  val verificationNote: BilingualText,
  val isDemo: Boolean = true,
)

data class RtpsGuideItem(
  val id: String,
  val title: BilingualText,
  val subtitle: BilingualText,
  val checklistDocs: List<BilingualText>,
  val steps: List<BilingualText>,
  val isDemo: Boolean = true,
)

enum class ComplaintCategory(val label: BilingualText) {
  WATER(bt("पेयजल / नल-जल", "Pani / Nal-Jal")),
  ROAD(bt("सड़क / नाली", "Sadak / Nali")),
  ELECTRICITY(bt("बिजली / स्ट्रीट लाइट", "Bijli / Street Light")),
  SANITATION(bt("साफ़-सफ़ाई", "Saf-Safai")),
  OTHER(bt("अन्य स्थानीय विषय", "Anya Sthaniya Vishay")),
}

data class ComplaintPreviewDraft(
  val category: ComplaintCategory = ComplaintCategory.WATER,
  val panchayatOrWard: String = "",
  val description: String = "",
  val citizenName: String = "",
)

enum class ComplaintStage(val label: BilingualText) {
  RECEIVED(bt("प्राप्त (डेमो)", "Prapt (Demo)")),
  UNDER_REVIEW(bt("समीक्षाधीन (डेमो)", "Samiksha Mein (Demo)")),
  IN_PROGRESS(bt("कार्य प्रगति पर (डेमो)", "Kaam Chal Raha (Demo)")),
  RESOLVED(bt("निष्पादित (डेमो)", "Poora Hua (Demo)")),
}

data class DemoComplaintStatusSample(
  val sampleCode: String,
  val categoryLabel: BilingualText,
  val areaLabel: BilingualText,
  val currentStepIndex: Int,
  val statusNote: BilingualText,
  val updatedLabel: BilingualText,
  val isDemo: Boolean = true,
)

enum class DirectorySection {
  HEALTH,
  EDUCATION,
  WATER_ROAD,
  OFFICE_CONTACTS,
}

data class DirectoryEntry(
  val id: String,
  val section: DirectorySection,
  val badge: BilingualText,
  val title: BilingualText,
  val details: BilingualText,
  val hoursOrProcess: BilingualText,
  val contactPlaceholder: BilingualText,
  val locationPlaceholder: BilingualText,
  val isDemo: Boolean = true,
)

enum class NoticeCategory(val label: BilingualText) {
  ALL(bt("सभी सूचनाएँ", "Sabhi Suchna")),
  GRAM_SABHA(bt("ग्राम सभा", "Gram Sabha")),
  HEALTH_CAMP(bt("स्वास्थ्य शिविर", "Swasthya Shivir")),
  INFRASTRUCTURE(bt("पानी एवं सड़क", "Pani & Sadak")),
  AWARENESS(bt("जन जागरूकता", "Jan Jagrukta")),
}

data class LocalNotice(
  val id: String,
  val category: NoticeCategory,
  val dateLabel: BilingualText,
  val title: BilingualText,
  val body: BilingualText,
  val sourceNote: BilingualText,
  val isDemo: Boolean = true,
)
