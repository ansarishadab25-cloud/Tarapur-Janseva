package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.DirectoryEntry
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.DemoPillBadge
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.VillageGreen

@Composable
fun DirectoryScreenContent(
  language: AppLanguage,
  headerIcon: ImageVector,
  headerTitle: String,
  headerSubtitle: String,
  demoNotice: String,
  entries: List<DirectoryEntry>,
  useGreenAccent: Boolean = false,
  footerContent: (@Composable () -> Unit)? = null,
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
          icon = headerIcon,
          title = headerTitle,
          subtitle = headerSubtitle,
          useGreenAccent = useGreenAccent,
        )
        DemoDisclaimerBanner(
          language = language,
          customMessage = demoNotice,
        )
      }
    }

    items(entries, key = { it.id }) { entry ->
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
            DemoPillBadge(text = entry.badge.resolve(language))
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = entry.title.resolve(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = if (useGreenAccent) VillageGreen else DeepNavyBlue,
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = entry.details.resolve(language),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
          )

          Spacer(modifier = Modifier.height(12.dp))
          HorizontalDivider(color = MaterialTheme.colorScheme.outline)
          Spacer(modifier = Modifier.height(10.dp))

          DirectoryInfoRow(
            icon = Icons.Filled.Schedule,
            text = entry.hoursOrProcess.resolve(language),
          )
          Spacer(modifier = Modifier.height(6.dp))
          DirectoryInfoRow(
            icon = Icons.Filled.LocationOn,
            text = entry.locationPlaceholder.resolve(language),
          )
          Spacer(modifier = Modifier.height(6.dp))
          DirectoryInfoRow(
            icon = Icons.Filled.Phone,
            text = entry.contactPlaceholder.resolve(language),
          )
        }
      }
    }

    if (footerContent != null) {
      item {
        Column(modifier = Modifier.widthIn(max = 600.dp)) {
          footerContent()
        }
      }
    }
  }
}

@Composable
private fun DirectoryInfoRow(
  icon: ImageVector,
  text: String,
) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = DeepNavyBlue,
      modifier = Modifier.size(18.dp),
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(
      text = text,
      style = MaterialTheme.typography.bodySmall,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Medium,
    )
  }
}
