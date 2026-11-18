package com.example.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.bt
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite

@Composable
fun WaterRoadScreen(
  language: AppLanguage,
  entries: List<DirectoryEntry>,
  onOpenComplaintForm: () -> Unit,
  modifier: Modifier = Modifier,
) {
  DirectoryScreenContent(
    language = language,
    headerIcon = Icons.Filled.WaterDrop,
    headerTitle = bt("पानी एवं सड़क (DEMO)", "Pani & Sadak (DEMO)").resolve(language),
    headerSubtitle = bt(
      "नल-जल आपूर्ति, चापाकल मरम्मत व ग्रामीण सड़क से जुड़ी प्रक्रिया की जानकारी।",
      "Nal-Jal supply, chapakal marammat aur gramin sadak prakriya ki jankari.",
    ).resolve(language),
    demoNotice = bt(
      "DEMO प्रविष्टियाँ: यह केवल जानकारी का नमूना प्रारूप है। कोई नकली नंबर या पता शामिल नहीं है।",
      "DEMO Entries: Yeh sirf jankari ka sample praroop hai. Koi nakli number ya pata shamil nahi hai.",
    ).resolve(language),
    entries = entries,
    useGreenAccent = true,
    footerContent = {
      Button(
        onClick = onOpenComplaintForm,
        modifier = Modifier
          .fillMaxWidth()
          .height(52.dp)
          .testTag("water_road_to_complaint_button"),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = DeepNavyBlue,
          contentColor = PureWhite,
        ),
      ) {
        Text(
          text = bt("समस्या फॉर्म पूर्वावलोकन खोलें (DEMO)", "Samasya Form Preview Kholein (DEMO)").resolve(language),
          style = MaterialTheme.typography.labelLarge,
          fontWeight = FontWeight.Bold,
        )
      }
    },
    modifier = modifier,
  )
}
