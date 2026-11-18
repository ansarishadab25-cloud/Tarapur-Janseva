package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.LocalNotice
import com.example.domain.model.NoticeCategory
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.DemoPillBadge
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.VillageGreen

@Composable
fun LocalNewsNoticesScreen(
  language: AppLanguage,
  selectedCategory: NoticeCategory,
  notices: List<LocalNotice>,
  onCategorySelect: (NoticeCategory) -> Unit,
  modifier: Modifier = Modifier,
) {
  val filtered = if (selectedCategory == NoticeCategory.ALL) {
    notices
  } else {
    notices.filter { it.category == selectedCategory }
  }

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
          icon = Icons.Filled.Campaign,
          title = bt("स्थानीय समाचार एवं सूचनाएँ", "Sthaniya Samachar & Suchnaen").resolve(language),
          subtitle = bt(
            "तारापुर और आसपास के गाँवों की सामुदायिक सूचनाओं का डेमो बुलेटिन बोर्ड।",
            "Tarapur aur aas-paas ke gaon ki samudayik suchnaon ka DEMO bulletin board.",
          ).resolve(language),
        )

        DemoDisclaimerBanner(
          language = language,
          customMessage = bt(
            "DEMO सूचना बोर्ड: लाइव अपडेट और सत्यापित समाचार भविष्य में सुरक्षित एडमिन सिस्टम से जुड़ेंगे।",
            "DEMO Notice Board: Live updates aur verified news aage secure admin system se judenge.",
          ).resolve(language),
        )

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          NoticeCategory.entries.forEach { category ->
            val selected = category == selectedCategory
            FilterChip(
              selected = selected,
              onClick = { onCategorySelect(category) },
              modifier = Modifier.testTag("notice_filter_${category.name}"),
              label = {
                Text(
                  text = category.label.resolve(language),
                  style = MaterialTheme.typography.labelMedium,
                )
              },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = DeepNavyBlue,
                selectedLabelColor = PureWhite,
              ),
            )
          }
        }
      }
    }

    items(filtered, key = { it.id }) { notice ->
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
            if (notice.isDemo) {
              DemoPillBadge(text = "DEMO")
            } else {
              Surface(
                shape = RoundedCornerShape(50),
                color = SoftGreenContainer,
              ) {
                Text(
                  text = bt("सुरक्षा संदेश", "Suraksha Sandesh").resolve(language),
                  style = MaterialTheme.typography.labelSmall,
                  color = VillageGreen,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                )
              }
            }

            Text(
              text = notice.dateLabel.resolve(language),
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = notice.title.resolve(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DeepNavyBlue,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = notice.body.resolve(language),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = notice.sourceNote.resolve(language),
            style = MaterialTheme.typography.labelSmall,
            color = VillageGreen,
            fontWeight = FontWeight.SemiBold,
          )
        }
      }
    }
  }
}
