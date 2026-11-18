package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.bt
import com.example.ui.components.LargeLanguageSelector
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.EmergencyGreenBg
import com.example.ui.theme.EmergencyGreenBorder
import com.example.ui.theme.EmergencyPurpleBg
import com.example.ui.theme.EmergencyPurpleBorder
import com.example.ui.theme.EmergencyPurpleText
import com.example.ui.theme.EmergencyRedBg
import com.example.ui.theme.EmergencyRedBorder
import com.example.ui.theme.EmergencyRedText
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueContainer
import com.example.ui.theme.SoftSurface
import com.example.ui.theme.VillageGreen

@Composable
fun HelpContactScreen(
  language: AppLanguage,
  onLanguageChange: (AppLanguage) -> Unit,
  onRevisitWelcome: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val dialNumber: (String) -> Unit = { number ->
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    runCatching { context.startActivity(intent) }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 14.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(
      modifier = Modifier.widthIn(max = 600.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
      // 1. Statutory Transparency & Non-Affiliation Box
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = SoftBlueContainer,
        border = BorderStroke(1.dp, DeepNavyBlue.copy(alpha = 0.18f)),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Row(
          modifier = Modifier.padding(16.dp),
          verticalAlignment = Alignment.Top,
        ) {
          Icon(
            imageVector = Icons.Filled.VerifiedUser,
            contentDescription = null,
            tint = DeepNavyBlue,
            modifier = Modifier
              .padding(top = 2.dp)
              .size(24.dp),
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = bt(
                "पारदर्शिता एवं गैर-संबद्धता घोषणा",
                "Pardarshita & Gair-Sambaddhta Ghoshna",
              ).resolve(language),
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = DeepNavyBlue,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = bt(
                "\"तारापुर मुंगेर जनसेवा\" एक स्वतंत्र नागरिक सूचना सहायता प्रोटोटाइप है। यह किसी सरकारी विभाग का आधिकारिक प्रतिनिधि नहीं है। ऐप का उद्देश्य ग्रामीणों को आधिकारिक प्रक्रियाओं के प्रति जागरूक करना है।",
                "\"Tarapur Munger Janseva\" ek swatantra nagrik suchna sahayata prototype hai. Yeh kisi sarkari vibhag ka adhikarik pratinidhi nahi hai. App ka uddeshya graminon ko prakriyaon ke prati jagruk karna hai.",
              ).resolve(language),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
        }
      }

      // 2. App Language & Accessibility Preferences Card
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.Tune,
              contentDescription = null,
              tint = VillageGreen,
              modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = bt(
                "भाषा एवं इंटरफ़ेस सेटिंग (Language & Settings)",
                "Bhasha & Interface Settings",
              ).resolve(language),
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          // Row 1: App Language
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = bt("प्राथमिक भाषा (App Language)", "Prathmik Bhasha (App Language)").resolve(language),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = bt("हिन्दी / Hinglish", "Hindi / Hinglish").resolve(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            OutlinedButton(
              onClick = {
                val next = if (language == AppLanguage.HINDI) AppLanguage.HINGLISH else AppLanguage.HINDI
                onLanguageChange(next)
              },
              shape = RoundedCornerShape(10.dp),
              border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
              modifier = Modifier.testTag("help_quick_toggle_lang"),
            ) {
              Text(
                text = bt("बदलें", "Badlein").resolve(language),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = DeepNavyBlue,
              )
            }
          }

          LargeLanguageSelector(
            language = language,
            onLanguageChange = onLanguageChange,
            modifier = Modifier.padding(bottom = 8.dp),
          )

          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))

          // Row 2: Text Size
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = bt("अक्षर का आकार (Text Size)", "Akshar Ka Aakar (Text Size)").resolve(language),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = bt(
                  "बड़ा एवं सुपाठ्य (Accessibility Standard)",
                  "Bada aur supathya (Accessibility Standard)",
                ).resolve(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = SoftSurface,
            ) {
              Text(
                text = bt("मानक", "Standard").resolve(language),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = DeepNavyBlue,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
              )
            }
          }

          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))

          // Row 3: Data Privacy
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = bt("गोपनीयता नीति (Data Privacy)", "Gopniyata Niti (Data Privacy)").resolve(language),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
              )
              Text(
                text = bt(
                  "कोई आधार या बैंक डेटा संग्रह नहीं (No sensitive data)",
                  "Koi Aadhaar ya bank data sangrah nahi (No sensitive data)",
                ).resolve(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
            Icon(
              imageVector = Icons.Filled.Lock,
              contentDescription = bt("डेटा सुरक्षित", "Data Surakshit").resolve(language),
              tint = VillageGreen,
              modifier = Modifier.size(22.dp),
            )
          }
        }
      }

      // 3. Technical Architecture Roadmap (Future Ready)
      Surface(
        shape = RoundedCornerShape(16.dp),
        color = SoftSurface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.Architecture,
              contentDescription = null,
              tint = DeepNavyBlue,
              modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = bt(
                "मॉड्यूलर आर्किटेक्चर (Future Ready)",
                "Modular Architecture (Future Ready)",
              ).resolve(language),
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = DeepNavyBlue,
            )
          }
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = bt(
              "यह एप्लीकेशन Jetpack Compose + Clean Architecture के अनुरूप संरचित है। भविष्य में सुरक्षित Firebase प्रमाणीकरण एवं प्रशासनिक नियंत्रण प्रणाली (Admin System) को सुगमता से जोड़ा जा सकता है।",
              "Yeh application Jetpack Compose + Clean Architecture ke anuroop bana hai. Aage surakshit Firebase authentication aur Admin System ko aasani se joda ja sakta hai.",
            ).resolve(language),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )
          Spacer(modifier = Modifier.height(12.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
          ) {
            Surface(
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              color = PureWhite,
              border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
              Text(
                text = "Kotlin + Compose M3",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = DeepNavyBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
              )
            }
            Surface(
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp),
              color = PureWhite,
              border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
              Text(
                text = "Firebase Ready",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = VillageGreen,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
              )
            }
          }
        }
      }

      // 4. Standard Emergency Help Numbers (Bihar)
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
          text = bt(
            "मानक सरकारी हेल्पलाइन नंबर्स (Bihar)",
            "Manak Sarkari Helpline Numbers (Bihar)",
          ).resolve(language),
          style = MaterialTheme.typography.titleSmall,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          EmergencyNumberTile(
            number = "112",
            label = bt("आपातकालीन", "Aapatkalin").resolve(language),
            bgColor = EmergencyRedBg,
            borderColor = EmergencyRedBorder,
            numberColor = EmergencyRedText,
            onClick = { dialNumber("112") },
            modifier = Modifier
              .weight(1f)
              .testTag("helpline_112"),
          )
          EmergencyNumberTile(
            number = "102",
            label = bt("एम्बुलेंस", "Ambulance").resolve(language),
            bgColor = EmergencyGreenBg,
            borderColor = EmergencyGreenBorder,
            numberColor = VillageGreen,
            onClick = { dialNumber("102") },
            modifier = Modifier
              .weight(1f)
              .testTag("helpline_102"),
          )
          EmergencyNumberTile(
            number = "181",
            label = bt("महिला हेल्पलाइन", "Mahila Helpline").resolve(language),
            bgColor = EmergencyPurpleBg,
            borderColor = EmergencyPurpleBorder,
            numberColor = EmergencyPurpleText,
            onClick = { dialNumber("181") },
            modifier = Modifier
              .weight(1f)
              .testTag("helpline_181"),
          )
        }
      }

      OutlinedButton(
        onClick = onRevisitWelcome,
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("revisit_welcome_button"),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, DeepNavyBlue),
      ) {
        Text(
          text = bt("स्वागत स्क्रीन दोबारा देखें", "Welcome Screen Dobara Dekhein").resolve(language),
          style = MaterialTheme.typography.labelLarge,
          color = DeepNavyBlue,
          fontWeight = FontWeight.Bold,
        )
      }

      Spacer(modifier = Modifier.height(10.dp))
    }
  }
}

@Composable
private fun EmergencyNumberTile(
  number: String,
  label: String,
  bgColor: Color,
  borderColor: Color,
  numberColor: Color,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .clickable(onClick = onClick),
    shape = RoundedCornerShape(12.dp),
    color = bgColor,
    border = BorderStroke(1.dp, borderColor),
  ) {
    Column(
      modifier = Modifier.padding(vertical = 12.dp, horizontal = 6.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = number,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = numberColor,
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        color = numberColor,
        textAlign = TextAlign.Center,
        maxLines = 1,
      )
    }
  }
}
