package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.bt
import com.example.ui.navigation.Routes
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.DemoAmberBackground
import com.example.ui.theme.DemoAmberBorder
import com.example.ui.theme.DemoAmberText
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueContainer
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.VillageGreen

@Composable
fun JansevaTopBar(
  title: String,
  subtitle: String,
  language: AppLanguage,
  onLanguageChange: (AppLanguage) -> Unit,
  canNavigateBack: Boolean,
  onNavigateBack: () -> Unit,
) {
  Surface(
    color = DeepNavyBlue,
    shadowElevation = 4.dp,
    modifier = Modifier.fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier
        .windowInsetsPadding(WindowInsets.statusBars)
        .fillMaxWidth()
        .padding(horizontal = 12.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (canNavigateBack) {
        IconButton(
          onClick = onNavigateBack,
          modifier = Modifier
            .minimumInteractiveComponentSize()
            .testTag("top_bar_back_button"),
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = bt("पीछे जाएं", "Peeche jayein").resolve(language),
            tint = PureWhite,
          )
        }
      } else {
        Spacer(modifier = Modifier.width(6.dp))
      }

      Column(
        modifier = Modifier
          .weight(1f)
          .padding(end = 8.dp),
      ) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = PureWhite,
        )
        Text(
          text = subtitle,
          style = MaterialTheme.typography.labelSmall,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = PureWhite.copy(alpha = 0.88f),
        )
      }

      CompactLanguageToggle(
        language = language,
        onLanguageChange = onLanguageChange,
      )
    }
  }
}

@Composable
fun CompactLanguageToggle(
  language: AppLanguage,
  onLanguageChange: (AppLanguage) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(50),
    color = PureWhite.copy(alpha = 0.14f),
    border = BorderStroke(1.dp, PureWhite.copy(alpha = 0.35f)),
  ) {
    Row(
      modifier = Modifier.padding(3.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      AppLanguage.entries.forEach { option ->
        val selected = option == language
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (selected) PureWhite else Color.Transparent)
            .clickable { onLanguageChange(option) }
            .padding(horizontal = 9.dp, vertical = 5.dp)
            .testTag("lang_chip_${option.code}"),
          contentAlignment = Alignment.Center,
        ) {
          Text(
            text = option.displayLabel,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = if (selected) DeepNavyBlue else PureWhite,
          )
        }
      }
    }
  }
}

@Composable
fun LargeLanguageSelector(
  language: AppLanguage,
  onLanguageChange: (AppLanguage) -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
  ) {
    AppLanguage.entries.forEach { option ->
      val selected = option == language
      Surface(
        modifier = Modifier
          .weight(1f)
          .height(54.dp)
          .clip(RoundedCornerShape(16.dp))
          .clickable { onLanguageChange(option) }
          .testTag("large_lang_${option.code}"),
        shape = RoundedCornerShape(16.dp),
        color = if (selected) DeepNavyBlue else PureWhite,
        border = BorderStroke(
          width = if (selected) 2.dp else 1.5.dp,
          color = if (selected) DeepNavyBlue else MaterialTheme.colorScheme.outline,
        ),
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
        ) {
          Box(
            modifier = Modifier
              .size(10.dp)
              .clip(CircleShape)
              .background(if (selected) VillageGreen else MaterialTheme.colorScheme.outline),
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = if (option == AppLanguage.HINDI) "हिन्दी (प्रमुख)" else "Hinglish",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = if (selected) PureWhite else MaterialTheme.colorScheme.onSurface,
          )
        }
      }
    }
  }
}

@Composable
fun DemoDisclaimerBanner(
  language: AppLanguage,
  customMessage: String? = null,
  modifier: Modifier = Modifier,
) {
  val defaultMsg = bt(
    "DEMO पूर्वावलोकन: यह एक सामुदायिक सूचना ऐप है (सरकारी ऐप नहीं)। यहाँ दिखाए गए उदाहरण केवल डिज़ाइन समझने के लिए हैं।",
    "DEMO Preview: Yeh ek samudayik suvidha app hai (Sarkari app nahi). Yahan dikhaye gaye udaharan sirf design samajhne ke liye hain.",
  ).resolve(language)

  Surface(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(14.dp),
    color = DemoAmberBackground,
    border = BorderStroke(1.dp, DemoAmberBorder.copy(alpha = 0.6f)),
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
      verticalAlignment = Alignment.Top,
    ) {
      Icon(
        imageVector = Icons.Filled.Info,
        contentDescription = "Demo Notice",
        tint = DemoAmberText,
        modifier = Modifier
          .padding(top = 2.dp)
          .size(20.dp),
      )
      Spacer(modifier = Modifier.width(10.dp))
      Text(
        text = customMessage ?: defaultMsg,
        style = MaterialTheme.typography.bodySmall,
        color = DemoAmberText,
        fontWeight = FontWeight.Medium,
      )
    }
  }
}

@Composable
fun DemoPillBadge(text: String, modifier: Modifier = Modifier) {
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(50),
    color = DemoAmberBackground,
    border = BorderStroke(1.dp, DemoAmberBorder.copy(alpha = 0.5f)),
  ) {
    Text(
      text = text,
      style = MaterialTheme.typography.labelSmall,
      color = DemoAmberText,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
    )
  }
}

@Composable
fun SectionHeaderCard(
  icon: ImageVector,
  title: String,
  subtitle: String,
  useGreenAccent: Boolean = false,
  modifier: Modifier = Modifier,
) {
  val containerColor = if (useGreenAccent) SoftGreenContainer else SoftBlueContainer
  val iconTint = if (useGreenAccent) VillageGreen else DeepNavyBlue

  Surface(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    color = containerColor,
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = PureWhite,
        modifier = Modifier.size(52.dp),
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(28.dp),
          )
        }
      }
      Spacer(modifier = Modifier.width(14.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = iconTint,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    }
  }
}

private data class BottomNavItem(
  val route: String,
  val labelHi: String,
  val labelHinglish: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val testTag: String,
)

@Composable
fun JansevaBottomBar(
  currentRoute: String?,
  language: AppLanguage,
  onNavigate: (String) -> Unit,
) {
  val items = listOf(
    BottomNavItem(
      route = Routes.HOME,
      labelHi = "होम",
      labelHinglish = "Home",
      selectedIcon = Icons.Filled.Home,
      unselectedIcon = Icons.Outlined.Home,
      testTag = "bottom_nav_home",
    ),
    BottomNavItem(
      route = Routes.SCHEMES,
      labelHi = "योजनाएँ",
      labelHinglish = "Yojana",
      selectedIcon = Icons.Filled.Policy,
      unselectedIcon = Icons.Outlined.Policy,
      testTag = "bottom_nav_schemes",
    ),
    BottomNavItem(
      route = Routes.REGISTER_COMPLAINT,
      labelHi = "शिकायत",
      labelHinglish = "Shikayat",
      selectedIcon = Icons.Filled.EditNote,
      unselectedIcon = Icons.Outlined.EditNote,
      testTag = "bottom_nav_complaint",
    ),
    BottomNavItem(
      route = Routes.LOCAL_NEWS,
      labelHi = "सूचना",
      labelHinglish = "Suchna",
      selectedIcon = Icons.Filled.Campaign,
      unselectedIcon = Icons.Outlined.Campaign,
      testTag = "bottom_nav_news",
    ),
    BottomNavItem(
      route = Routes.HELP_CONTACT,
      labelHi = "सहायता",
      labelHinglish = "Sahayata",
      selectedIcon = Icons.Filled.SupportAgent,
      unselectedIcon = Icons.Outlined.SupportAgent,
      testTag = "bottom_nav_help",
    ),
  )

  NavigationBar(
    containerColor = PureWhite,
    tonalElevation = 6.dp,
    windowInsets = WindowInsets.navigationBars,
  ) {
    items.forEach { item ->
      val selected = currentRoute == item.route
      val label = if (language == AppLanguage.HINDI) item.labelHi else item.labelHinglish
      NavigationBarItem(
        selected = selected,
        onClick = { onNavigate(item.route) },
        modifier = Modifier.testTag(item.testTag),
        icon = {
          Icon(
            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
            contentDescription = label,
          )
        },
        label = {
          Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = DeepNavyBlue,
          selectedTextColor = DeepNavyBlue,
          indicatorColor = SoftBlueContainer,
          unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
          unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
      )
    }
  }
}
