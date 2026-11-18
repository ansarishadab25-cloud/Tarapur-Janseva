# तारापुर मुंगेर जनसेवा — Android Native (Kotlin + Jetpack Compose) Implementation Guide

यह दस्तावेज़ आपके ऐप **"Tarapur Munger Janseva"** को Android Studio में Native Kotlin + Jetpack Compose + Material 3 के साथ रन करने के लिए पूर्ण आर्किटेक्चर, Gradle डिपेंडेंसीज़ और स्क्रीन कोड उपलब्ध कराता है।

---

## 1. Project Configuration

- **Package Name:** `com.tarapur.munger.janseva`
- **Minimum SDK:** `24` (Android 7.0 Nougat - ग्रामीण क्षेत्रों के बजट फोन्स हेतु उपयुक्त)
- **Target / Compile SDK:** `34` or `35` (Android 14 / 15)
- **UI Toolkit:** Jetpack Compose (Material 3)
- **Navigation:** `androidx.navigation:navigation-compose:2.7.7`

### `app/build.gradle.kts`
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.tarapur.munger.janseva"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tarapur.munger.janseva"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.activity:activity-compose:1.8.2")
}
```

---

## 2. Material 3 Color Theme (`ui/theme/Color.kt` & `Theme.kt`)

डिज़ाइन सिस्टम के अनुरूप Civic Navy (`#0D2F60`) और Emerald Green (`#12733E`) का उपयोग:

```kotlin
package com.tarapur.munger.janseva.ui.theme

import androidx.compose.ui.graphics.Color

val CivicNavy = Color(0xFF0D2F60)
val CivicGreen = Color(0xFF12733E)
val CivicAccent = Color(0xFFA04000)
val BackgroundWhite = Color(0xFFFFFFFF)
val SurfaceVariant = Color(0xFFF0F4FA)
val TextDark = Color(0xFF191C20)
val OutlineGrey = Color(0xFF52565E)
```

---

## 3. Navigation Routes (`navigation/AppNavigation.kt`)

सभी 12 स्क्रीन्स के नेविगेशन रूट्स:

```kotlin
package com.tarapur.munger.janseva.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Schemes : Screen("schemes")
    object Rtps : Screen("rtps")
    object GrievanceSubmit : Screen("grievance_submit")
    object GrievanceStatus : Screen("grievance_status")
    object Health : Screen("health")
    object Education : Screen("education")
    object WaterRoads : Screen("water_roads")
    object GovtOffices : Screen("govt_offices")
    object LocalNews : Screen("local_news")
    object HelpSupport : Screen("help_support")
}
```

---

## 4. Main Home Portal Screen (`ui/screens/HomeScreen.kt`)

10 प्रमुख सेवाओं के बड़े टच कार्ड्स और ऑफलाइन + वॉइस सर्च:

```kotlin
package com.tarapur.munger.janseva.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tarapur.munger.janseva.navigation.Screen
import com.tarapur.munger.janseva.ui.theme.*

data class ServiceItem(
    val titleHi: String,
    val titleEn: String,
    val icon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val services = listOf(
        ServiceItem("सरकारी योजनाएँ", "Govt Schemes", Icons.Default.Policy, Screen.Schemes.route),
        ServiceItem("प्रमाणपत्र & RTPS", "Certificates & RTPS", Icons.Default.Description, Screen.Rtps.route),
        ServiceItem("शिकायत दर्ज करें", "File Grievance", Icons.Default.EditNote, Screen.GrievanceSubmit.route),
        ServiceItem("शिकायत की स्थिति", "Track Status", Icons.Default.TrackChanges, Screen.GrievanceStatus.route),
        ServiceItem("स्वास्थ्य & अस्पताल", "Health & Clinics", Icons.Default.LocalHospital, Screen.Health.route),
        ServiceItem("शिक्षा एवं स्कूल", "Schools & College", Icons.Default.School, Screen.Education.route),
        ServiceItem("पानी एवं सड़क", "Water & Roads", Icons.Default.WaterDrop, Screen.WaterRoads.route),
        ServiceItem("सरकारी कार्यालय", "Govt Directory", Icons.Default.CorporateFare, Screen.GovtOffices.route),
        ServiceItem("स्थानीय सूचनाएँ", "Tarapur News", Icons.Default.Campaign, Screen.LocalNews.route),
        ServiceItem("सहायता & संपर्क", "Helpline & Support", Icons.Default.SupportAgent, Screen.HelpSupport.route)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("तारापुर मुंगेर जनसेवा", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
                        Text("प्रखंड तारापुर (बिहार) • DEMO", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = CivicNavy)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Offline Ready Chip
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.OfflinePin, contentDescription = null, tint = CivicGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("ऑफलाइन सुरक्षित: इंटरनेट के बिना भी जानकारी उपलब्ध", fontSize = 12.sp, color = Color(0xFF1B5E20))
                }
            }

            // Services Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(services) { service ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(service.route) },
                        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(service.icon, contentDescription = null, tint = CivicNavy)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(service.titleHi, fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
                            Text(service.titleEn, fontSize = 11.sp, color = OutlineGrey, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}
```

---

## 5. Privacy & Modular Expansion Architecture

1. **No Sensitive Tokens:** Aadhaar या बैंक अकाउंट इनपुट्स को कोड में शामिल न करें।
2. **Local Room Database (Offline Cache):** `RoomDatabase` में सरकारी योजनाओं और हेल्पलाइन नंबर्स को प्री-लोड करें।
3. **Firebase & Secure Admin System:** `FirebaseAuth` एवं `FirebaseFirestore` को बाद में एक अलग रिपॉजिटरी लेयर (`data/repository`) में प्लग-इन करने के लिए कोड क्लीन आर्किटेक्चर (MVVM) पर आधारित है।

---

## 6. How to Run in Android Studio

1. Android Studio खोलें और **"New Project -> Empty Compose Activity"** चुनें।
2. `com.tarapur.munger.janseva` पैकेज नाम डालें।
3. ऊपर दिए गए कोड फाइलों को संबंधित फ़ोल्डर में पेस्ट करें।
4. **Run 'app'** (`Shift + F10`) दबाएँ और एमुलेटर या अपने Android फ़ोन पर ऐप चलाएँ।
