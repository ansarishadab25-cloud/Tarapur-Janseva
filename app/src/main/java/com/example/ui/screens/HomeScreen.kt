package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.AccentTone
import com.example.domain.model.AppLanguage
import com.example.domain.model.HomeFeatureItem
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.navigation.Routes
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.OnSoftGreenContainer
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.SoftSurface
import com.example.ui.theme.VillageGreen

@Composable
fun HomeScreen(
  language: AppLanguage,
  features: List<HomeFeatureItem>,
  onFeatureClick: (String) -> Unit,
  modifier: Modifier = Modifier,
) {
  BoxWithConstraints(modifier = modifier.fillMaxSize()) {
    val columns = if (maxWidth >= 680.dp) 3 else 2

    LazyVerticalGrid(
      columns = GridCells.Fixed(columns),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.fillMaxSize(),
    ) {
      item(span = { GridItemSpan(columns) }) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          // Offline Safe Chip (aligned with setup guide)
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = SoftGreenContainer,
            border = BorderStroke(1.dp, VillageGreen.copy(alpha = 0.25f)),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("offline_ready_chip"),
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Icon(
                imageVector = Icons.Filled.OfflinePin,
                contentDescription = null,
                tint = VillageGreen,
                modifier = Modifier.size(20.dp),
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = bt(
                  "ऑफलाइन सुरक्षित: इंटरनेट के बिना भी जानकारी उपलब्ध",
                  "Offline Surakshit: Internet ke bina bhi jankari uplabdh",
                ).resolve(language),
                style = MaterialTheme.typography.bodySmall,
                color = OnSoftGreenContainer,
                fontWeight = FontWeight.SemiBold,
              )
            }
          }

          Text(
            text = bt("सभी 10 सेवाएँ एवं सूचनाएँ", "Sabhi 10 Sevaen aur Suchnaen").resolve(language),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = DeepNavyBlue,
          )
        }
      }

      items(features, key = { it.route }) { item ->
        HomeFeatureTile(
          item = item,
          language = language,
          onClick = { onFeatureClick(item.route) },
        )
      }

      item(span = { GridItemSpan(columns) }) {
        Column {
          Spacer(modifier = Modifier.height(4.dp))
          DemoDisclaimerBanner(language = language)
          Spacer(modifier = Modifier.height(6.dp))
        }
      }
    }
  }
}

@Composable
private fun HomeFeatureTile(
  item: HomeFeatureItem,
  language: AppLanguage,
  onClick: () -> Unit,
) {
  val isGreen = item.accentTone == AccentTone.GREEN
  val accentColor = if (isGreen) VillageGreen else DeepNavyBlue

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .height(148.dp)
      .clip(RoundedCornerShape(16.dp))
      .clickable(onClick = onClick)
      .testTag(item.testTag),
    shape = RoundedCornerShape(16.dp),
    color = SoftSurface,
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.55f)),
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(12.dp),
    ) {
      Surface(
        shape = CircleShape,
        color = accentColor.copy(alpha = 0.12f),
        modifier = Modifier.align(Alignment.TopEnd),
      ) {
        Text(
          text = "${item.number}",
          style = MaterialTheme.typography.labelSmall,
          fontWeight = FontWeight.Bold,
          color = accentColor,
          modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        )
      }

      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Box(
          modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(PureWhite),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            imageVector = routeIcon(item.route),
            contentDescription = item.title.resolve(language),
            tint = accentColor,
            modifier = Modifier.size(25.dp),
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = item.title.resolve(language),
          style = MaterialTheme.typography.titleSmall,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          textAlign = TextAlign.Center,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = item.subtitle.resolve(language),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
      }
    }
  }
}

private fun routeIcon(route: String): ImageVector =
  when (route) {
    Routes.SCHEMES -> Icons.Filled.Policy
    Routes.CERTIFICATES_RTPS -> Icons.Filled.Description
    Routes.REGISTER_COMPLAINT -> Icons.Filled.EditNote
    Routes.COMPLAINT_STATUS -> Icons.Filled.TrackChanges
    Routes.HEALTH_HOSPITAL -> Icons.Filled.LocalHospital
    Routes.EDUCATION_SCHOOL -> Icons.Filled.School
    Routes.WATER_ROAD -> Icons.Filled.WaterDrop
    Routes.OFFICE_CONTACTS -> Icons.Filled.CorporateFare
    Routes.LOCAL_NEWS -> Icons.Filled.Campaign
    Routes.HELP_CONTACT -> Icons.Filled.SupportAgent
    else -> Icons.AutoMirrored.Filled.Assignment
  }
