package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.RtpsGuideItem
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.DemoPillBadge
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.VillageGreen

@Composable
fun CertificatesRtpsScreen(
  language: AppLanguage,
  guides: List<RtpsGuideItem>,
  checkedDocs: Set<String>,
  onToggleDoc: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    item {
      Column(
        modifier = Modifier.widthIn(max = 600.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
      ) {
        SectionHeaderCard(
          icon = Icons.Filled.Badge,
          title = bt("सरकारी प्रमाणपत्र एवं RTPS", "Pramanpatra & RTPS Guide").resolve(language),
          subtitle = bt(
            "जाति, आवासीय व आय प्रमाणपत्र बनवाने से पहले अपनी तैयारी चेकलिस्ट टिक करें।",
            "Jati, Niwas aur Aay pramanpatra ke liye apni taiyari checklist tick karein.",
          ).resolve(language),
          useGreenAccent = true,
        )

        DemoDisclaimerBanner(
          language = language,
          customMessage = bt(
            "DEMO गाइड: यह केवल दस्तावेज़ तैयारी गाइड है। यहाँ से कोई ऑनलाइन आवेदन सबमिट नहीं होता और न कोई बाहरी लिंक दिया गया है।",
            "DEMO Guide: Yeh sirf kagaz taiyari guide hai. Yahan se koi online form submit nahi hota aur na koi bahari link diya gaya hai.",
          ).resolve(language),
        )
      }
    }

    items(guides, key = { it.id }) { guide ->
      Surface(
        modifier = Modifier
          .widthIn(max = 600.dp)
          .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              text = guide.title.resolve(language),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DeepNavyBlue,
              modifier = Modifier.weight(1f),
            )
            DemoPillBadge(text = "DEMO")
          }

          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = guide.subtitle.resolve(language),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = bt("तैयारी चेकलिस्ट (टैप करके टिक करें):", "Taiyari Checklist (Tap karke tick karein):").resolve(language),
            style = MaterialTheme.typography.labelLarge,
            color = VillageGreen,
          )
          Spacer(modifier = Modifier.height(6.dp))

          guide.checklistDocs.forEachIndexed { idx, docLabel ->
            val key = "${guide.id}_$idx"
            val isChecked = key in checkedDocs
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onToggleDoc(key) }
                .testTag("rtps_check_$key"),
              shape = RoundedCornerShape(12.dp),
              color = if (isChecked) SoftGreenContainer else MaterialTheme.colorScheme.surfaceVariant,
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
              ) {
                Checkbox(
                  checked = isChecked,
                  onCheckedChange = { onToggleDoc(key) },
                  colors = CheckboxDefaults.colors(
                    checkedColor = VillageGreen,
                    uncheckedColor = DeepNavyBlue,
                  ),
                )
                Text(
                  text = docLabel.resolve(language),
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(end = 8.dp),
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline)
          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = bt("सामान्य प्रक्रिया चरण:", "Samanya Prakriya Charan:").resolve(language),
            style = MaterialTheme.typography.labelLarge,
            color = DeepNavyBlue,
          )
          Spacer(modifier = Modifier.height(4.dp))
          guide.steps.forEach { step ->
            Text(
              text = step.resolve(language),
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(vertical = 3.dp),
            )
          }
        }
      }
    }
  }
}
