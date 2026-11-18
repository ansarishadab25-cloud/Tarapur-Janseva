package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.domain.model.ComplaintStage
import com.example.domain.model.DemoComplaintStatusSample
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.DemoPillBadge
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.SubtleBorder
import com.example.ui.theme.VillageGreen

@Composable
fun ComplaintStatusScreen(
  language: AppLanguage,
  samples: List<DemoComplaintStatusSample>,
  onOpenRegisterScreen: () -> Unit,
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
          icon = Icons.AutoMirrored.Filled.FactCheck,
          title = bt("शिकायत की स्थिति (DEMO लेआउट)", "Shikayat Ki Stithi (DEMO Layout)").resolve(language),
          subtitle = bt(
            "भविष्य में सुरक्षित बैकएंड जुड़ने पर शिकायत के चरण यहाँ किस प्रकार दिखेंगे, उसका डेमो उदाहरण।",
            "Aage backend judne par shikayat ke charan yahan kaise dikhenge, uska DEMO sample.",
          ).resolve(language),
          useGreenAccent = true,
        )

        DemoDisclaimerBanner(
          language = language,
          customMessage = bt(
            "कोई नकली ट्रैकिंग नहीं: अभी लाइव शिकायत खोज उपलब्ध नहीं है। नीचे केवल UI डिज़ाइन दिखाने के लिए [DEMO SAMPLE] कार्ड दिए गए हैं।",
            "Koi nakli tracking nahi: Abhi live shikayat khoj chalu nahi hai. Neeche sirf UI design dikhane ke liye [DEMO SAMPLE] cards hain.",
          ).resolve(language),
        )
      }
    }

    items(samples, key = { it.sampleCode }) { sample ->
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
              text = sample.sampleCode,
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = DeepNavyBlue,
            )
            DemoPillBadge(text = "DEMO SAMPLE")
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = sample.categoryLabel.resolve(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Text(
            text = sample.areaLabel.resolve(language),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Visual Timeline Preview
          ComplaintStage.entries.forEachIndexed { index, stage ->
            val reached = index <= sample.currentStepIndex
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(vertical = 4.dp),
            ) {
              Box(
                modifier = Modifier
                  .size(26.dp)
                  .clip(CircleShape)
                  .background(if (reached) VillageGreen else SubtleBorder),
                contentAlignment = Alignment.Center,
              ) {
                if (reached) {
                  Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = PureWhite,
                    modifier = Modifier.size(16.dp),
                  )
                }
              }
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = stage.label.resolve(language),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (reached) FontWeight.Bold else FontWeight.Normal,
                color = if (reached) VillageGreen else MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline)
          Spacer(modifier = Modifier.height(10.dp))

          Surface(
            shape = RoundedCornerShape(10.dp),
            color = SoftGreenContainer,
            modifier = Modifier.fillMaxWidth(),
          ) {
            Column(modifier = Modifier.padding(10.dp)) {
              Text(
                text = sample.updatedLabel.resolve(language),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = VillageGreen,
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = sample.statusNote.resolve(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
              )
            }
          }
        }
      }
    }

    item {
      Column(modifier = Modifier.widthIn(max = 600.dp)) {
        Button(
          onClick = onOpenRegisterScreen,
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("status_to_register_button"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = DeepNavyBlue,
            contentColor = PureWhite,
          ),
        ) {
          Text(
            text = bt("शिकायत फॉर्म (DEMO) खोलें", "Shikayat Form (DEMO) Kholein").resolve(language),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
          )
        }
      }
    }
  }
}
