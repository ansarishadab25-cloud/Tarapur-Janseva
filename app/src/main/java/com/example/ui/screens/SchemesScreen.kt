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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.domain.model.DemoScheme
import com.example.domain.model.SchemeCategory
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.DemoPillBadge
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueContainer
import com.example.ui.theme.VillageGreen

@Composable
fun SchemesScreen(
  language: AppLanguage,
  selectedCategory: SchemeCategory,
  schemes: List<DemoScheme>,
  onCategorySelect: (SchemeCategory) -> Unit,
  modifier: Modifier = Modifier,
) {
  val filtered = if (selectedCategory == SchemeCategory.ALL) {
    schemes
  } else {
    schemes.filter { it.category == selectedCategory }
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
          icon = Icons.Filled.AccountBalance,
          title = bt("सरकारी योजनाएँ (डेमो गाइड)", "Sarkari Yojanaen (Demo Guide)").resolve(language),
          subtitle = bt(
            "योजनाओं की श्रेणी चुनें और सामान्य पात्रता व कागजात की समझ देखें।",
            "Yojana ki shreni chunein aur samanya patrata aur kagaz guide dekhein.",
          ).resolve(language),
        )

        DemoDisclaimerBanner(
          language = language,
          customMessage = bt(
            "DEMO सामग्री: यहाँ कोई नकली सरकारी योजना या अनधिकृत लिंक नहीं जोड़ा गया है। सटीक पात्रता केवल प्रखंड/पंचायत कार्यालय में सत्यापित करें।",
            "DEMO Samagri: Yahan koi nakli yojana ya link nahi hai. Sahi patrata sirf Block/Panchayat office mein verify karein.",
          ).resolve(language),
        )

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          SchemeCategory.entries.forEach { category ->
            val selected = category == selectedCategory
            FilterChip(
              selected = selected,
              onClick = { onCategorySelect(category) },
              modifier = Modifier.testTag("scheme_filter_${category.name}"),
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

    items(filtered, key = { it.id }) { scheme ->
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
            DemoPillBadge(text = "DEMO")
            Text(
              text = scheme.category.label.resolve(language),
              style = MaterialTheme.typography.labelSmall,
              color = VillageGreen,
              fontWeight = FontWeight.Bold,
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = scheme.title.resolve(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = DeepNavyBlue,
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = scheme.summary.resolve(language),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Spacer(modifier = Modifier.height(12.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline)
          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = bt("सामान्य पात्रता बिंदु (उदाहरण):", "Samanya Patrata Bindu (Sample):").resolve(language),
            style = MaterialTheme.typography.labelLarge,
            color = DeepNavyBlue,
          )
          Spacer(modifier = Modifier.height(4.dp))
          scheme.eligibility.forEach { bullet ->
            Text(
              text = "• ${bullet.resolve(language)}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(vertical = 2.dp),
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = bt("तैयार रखने योग्य कागजात:", "Taiyar Rakhne Yogya Kagaz:").resolve(language),
            style = MaterialTheme.typography.labelLarge,
            color = VillageGreen,
          )
          Spacer(modifier = Modifier.height(4.dp))
          scheme.requiredDocs.forEach { doc ->
            Text(
              text = "✓ ${doc.resolve(language)}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(vertical = 2.dp),
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Surface(
            shape = RoundedCornerShape(10.dp),
            color = SoftBlueContainer,
            modifier = Modifier.fillMaxWidth(),
          ) {
            Text(
              text = scheme.verificationNote.resolve(language),
              style = MaterialTheme.typography.bodySmall,
              color = DeepNavyBlue,
              fontWeight = FontWeight.Medium,
              modifier = Modifier.padding(10.dp),
            )
          }
        }
      }
    }
  }
}
