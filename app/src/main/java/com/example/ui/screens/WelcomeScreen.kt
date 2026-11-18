package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.VerifiedUser
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.model.AppLanguage
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.LargeLanguageSelector
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueContainer
import com.example.ui.theme.SoftGreenContainer
import com.example.ui.theme.VillageGreen

@Composable
fun WelcomeScreen(
  language: AppLanguage,
  onLanguageChange: (AppLanguage) -> Unit,
  onContinue: () -> Unit,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(PureWhite)
      .safeDrawingPadding(),
    contentAlignment = Alignment.TopCenter,
  ) {
    Column(
      modifier = Modifier
        .widthIn(max = 560.dp)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Column(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(horizontal = 20.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        // App Logo Card
        Surface(
          shape = CircleShape,
          color = PureWhite,
          border = BorderStroke(2.dp, SoftBlueContainer),
          shadowElevation = 4.dp,
          modifier = Modifier.size(84.dp),
        ) {
          Image(
            painter = painterResource(id = R.drawable.img_janseva_logo),
            contentDescription = bt("तारापुर मुंगेर जनसेवा लोगो", "Tarapur Munger Janseva Logo").resolve(language),
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .fillMaxSize()
              .clip(CircleShape),
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
          shape = RoundedCornerShape(50),
          color = SoftGreenContainer,
        ) {
          Text(
            text = bt("तारापुर • मुंगेर • बिहार", "Tarapur • Munger • Bihar").resolve(language),
            style = MaterialTheme.typography.labelMedium,
            color = VillageGreen,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 5.dp),
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = bt("तारापुर मुंगेर जनसेवा", "Tarapur Munger Janseva").resolve(language),
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.Bold,
          color = DeepNavyBlue,
          textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
          text = bt(
            "गाँव और प्रखंड की जन-सुविधा जानकारी, सरल हिन्दी और आसान नेविगेशन के साथ।",
            "Gaon aur prakhand ki jan-suvidha jankari, saral Hindi aur aasan navigation ke saath.",
          ).resolve(language),
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Language Choice Section (placed prominently above fold for first-time users)
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = MaterialTheme.colorScheme.surfaceVariant,
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier.fillMaxWidth(),
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Text(
              text = bt("अपनी भाषा चुनें / Bhasha Chunein", "Apni Bhasha Chunein / भाषा चुनें").resolve(language),
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold,
              color = DeepNavyBlue,
            )
            Spacer(modifier = Modifier.height(10.dp))
            LargeLanguageSelector(
              language = language,
              onLanguageChange = onLanguageChange,
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Welcome Banner Illustration
        Surface(
          shape = RoundedCornerShape(20.dp),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
          modifier = Modifier
            .fillMaxWidth()
            .height(156.dp),
        ) {
          Image(
            painter = painterResource(id = R.drawable.img_tarapur_welcome),
            contentDescription = bt("तारापुर गाँव जनसेवा दृश्य", "Tarapur village illustration").resolve(language),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Safety & Non-Government Notice
        Surface(
          shape = RoundedCornerShape(14.dp),
          color = SoftBlueContainer,
          modifier = Modifier.fillMaxWidth(),
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Icon(
              imageVector = Icons.Filled.VerifiedUser,
              contentDescription = null,
              tint = DeepNavyBlue,
              modifier = Modifier.size(22.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
              text = bt(
                "गोपनीयता सुरक्षा: यह ऐप आधार नंबर, बैंक खाता या OTP नहीं मांगता।",
                "Privacy Suraksha: Yeh app Aadhaar number, bank khata ya OTP nahi maangta.",
              ).resolve(language),
              style = MaterialTheme.typography.bodySmall,
              color = DeepNavyBlue,
              fontWeight = FontWeight.Medium,
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))
        DemoDisclaimerBanner(language = language)
      }

      // Pinned bottom CTA so first-time smartphone users always see the Start button immediately
      HorizontalDivider(color = MaterialTheme.colorScheme.outline)
      Surface(
        color = PureWhite,
        modifier = Modifier.fillMaxWidth(),
      ) {
        Button(
          onClick = onContinue,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .height(56.dp)
            .testTag("welcome_continue_button"),
          shape = RoundedCornerShape(16.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = VillageGreen,
            contentColor = PureWhite,
          ),
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
          ) {
            Text(
              text = bt("शुरू करें", "Shuru Karein").resolve(language),
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowForward,
              contentDescription = null,
            )
          }
        }
      }
    }
  }
}
