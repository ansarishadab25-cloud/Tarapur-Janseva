package com.example

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.core.app.ApplicationProvider
import com.example.data.repository.DemoJansevaRepository
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun `app name matches Tarapur Munger Janseva`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Tarapur Munger Janseva", appName)
  }

  @Test
  fun `home repository provides all 10 features`() {
    val repo = DemoJansevaRepository()
    assertEquals(10, repo.getHomeFeatures().size)
  }

  @Test
  fun `welcome flow language switch and navigation work`() {
    composeTestRule.waitForIdle()

    // Welcome screen language toggle to Hinglish and back to Hindi
    composeTestRule.onNodeWithTag("large_lang_hinglish").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("Shuru Karein").assertIsDisplayed()

    composeTestRule.onNodeWithTag("large_lang_hi").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("शुरू करें").assertIsDisplayed()

    // Enter Home screen
    composeTestRule.onNodeWithTag("welcome_continue_button").performClick()
    composeTestRule.waitForIdle()

    // Verify bottom navigation and Home first tile
    composeTestRule.onNodeWithTag("bottom_nav_home").assertIsDisplayed()
    composeTestRule.onNodeWithTag("bottom_nav_schemes").assertIsDisplayed()
    composeTestRule.onNodeWithTag("bottom_nav_complaint").assertIsDisplayed()
    composeTestRule.onNodeWithTag("bottom_nav_news").assertIsDisplayed()
    composeTestRule.onNodeWithTag("bottom_nav_help").assertIsDisplayed()
    composeTestRule.onNodeWithTag("offline_ready_chip").assertIsDisplayed()
    composeTestRule.onNodeWithTag("home_tile_schemes").performScrollTo().assertIsDisplayed()

    // Navigate to Schemes screen and back
    composeTestRule.onNodeWithTag("home_tile_schemes").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("top_bar_back_button").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("home_tile_schemes").performScrollTo().assertIsDisplayed()

    // Navigate via Bottom Nav to Help screen and verify helpline tiles & revisit button
    composeTestRule.onNodeWithTag("bottom_nav_help").performClick()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithTag("helpline_112").performScrollTo().assertIsDisplayed()
    composeTestRule.onNodeWithTag("revisit_welcome_button").performScrollTo().assertIsDisplayed()
  }
}
