package com.example.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.AppLanguage
import com.example.domain.model.bt
import com.example.ui.components.JansevaBottomBar
import com.example.ui.components.JansevaTopBar
import com.example.ui.screens.CertificatesRtpsScreen
import com.example.ui.screens.ComplaintStatusScreen
import com.example.ui.screens.EducationSchoolScreen
import com.example.ui.screens.HealthHospitalScreen
import com.example.ui.screens.HelpContactScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LocalNewsNoticesScreen
import com.example.ui.screens.OfficeContactsScreen
import com.example.ui.screens.RegisterComplaintScreen
import com.example.ui.screens.SchemesScreen
import com.example.ui.screens.WaterRoadScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.state.JansevaViewModel
import com.example.ui.theme.PureWhite

@Composable
fun JansevaApp(viewModel: JansevaViewModel) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val navController = rememberNavController()
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route

  val showChrome = currentRoute != null && currentRoute != Routes.WELCOME
  val showBottomBar = showChrome
  val canNavigateBack = currentRoute != null &&
    currentRoute != Routes.WELCOME &&
    currentRoute != Routes.HOME

  val navigateToRoute: (String) -> Unit = { route ->
    navController.navigate(route) {
      launchSingleTop = true
    }
  }

  val navigateFromBottomBar: (String) -> Unit = { route ->
    navController.navigate(route) {
      popUpTo(Routes.HOME) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = PureWhite,
    topBar = {
      if (showChrome) {
        JansevaTopBar(
          title = screenTitleForRoute(currentRoute, uiState.language),
          subtitle = screenSubtitleForRoute(currentRoute, uiState.language),
          language = uiState.language,
          onLanguageChange = viewModel::setLanguage,
          canNavigateBack = canNavigateBack,
          onNavigateBack = { navController.popBackStack() },
        )
      }
    },
    bottomBar = {
      if (showBottomBar) {
        JansevaBottomBar(
          currentRoute = currentRoute,
          language = uiState.language,
          onNavigate = navigateFromBottomBar,
        )
      }
    },
  ) { innerPadding ->
    NavHost(
      navController = navController,
      startDestination = Routes.WELCOME,
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
    ) {
      composable(Routes.WELCOME) {
        WelcomeScreen(
          language = uiState.language,
          onLanguageChange = viewModel::setLanguage,
          onContinue = {
            viewModel.completeWelcome()
            navController.navigate(Routes.HOME) {
              popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
              }
              launchSingleTop = true
            }
          },
        )
      }

      composable(Routes.HOME) {
        HomeScreen(
          language = uiState.language,
          features = uiState.homeFeatures,
          onFeatureClick = navigateToRoute,
        )
      }

      composable(Routes.SCHEMES) {
        SchemesScreen(
          language = uiState.language,
          selectedCategory = uiState.selectedSchemeCategory,
          schemes = uiState.schemes,
          onCategorySelect = viewModel::selectSchemeCategory,
        )
      }

      composable(Routes.CERTIFICATES_RTPS) {
        CertificatesRtpsScreen(
          language = uiState.language,
          guides = uiState.rtpsGuides,
          checkedDocs = uiState.checkedRtpsDocs,
          onToggleDoc = viewModel::toggleRtpsDocChecked,
        )
      }

      composable(Routes.REGISTER_COMPLAINT) {
        RegisterComplaintScreen(
          language = uiState.language,
          draft = uiState.complaintDraft,
          showPreviewDialog = uiState.showComplaintDemoPreviewDialog,
          onCategorySelect = viewModel::updateComplaintCategory,
          onAreaChange = viewModel::updateComplaintArea,
          onDescriptionChange = viewModel::updateComplaintDescription,
          onCitizenNameChange = viewModel::updateComplaintCitizenName,
          onOpenPreviewDialog = viewModel::openComplaintPreviewDialog,
          onDismissPreviewDialog = viewModel::dismissComplaintPreviewDialog,
          onResetDraft = viewModel::resetComplaintDraft,
          onViewStatusLayout = { navigateToRoute(Routes.COMPLAINT_STATUS) },
        )
      }

      composable(Routes.COMPLAINT_STATUS) {
        ComplaintStatusScreen(
          language = uiState.language,
          samples = uiState.statusSamples,
          onOpenRegisterScreen = { navigateToRoute(Routes.REGISTER_COMPLAINT) },
        )
      }

      composable(Routes.HEALTH_HOSPITAL) {
        HealthHospitalScreen(
          language = uiState.language,
          entries = uiState.healthEntries,
        )
      }

      composable(Routes.EDUCATION_SCHOOL) {
        EducationSchoolScreen(
          language = uiState.language,
          entries = uiState.educationEntries,
        )
      }

      composable(Routes.WATER_ROAD) {
        WaterRoadScreen(
          language = uiState.language,
          entries = uiState.waterRoadEntries,
          onOpenComplaintForm = { navigateToRoute(Routes.REGISTER_COMPLAINT) },
        )
      }

      composable(Routes.OFFICE_CONTACTS) {
        OfficeContactsScreen(
          language = uiState.language,
          entries = uiState.officeEntries,
        )
      }

      composable(Routes.LOCAL_NEWS) {
        LocalNewsNoticesScreen(
          language = uiState.language,
          selectedCategory = uiState.selectedNoticeCategory,
          notices = uiState.notices,
          onCategorySelect = viewModel::selectNoticeCategory,
        )
      }

      composable(Routes.HELP_CONTACT) {
        HelpContactScreen(
          language = uiState.language,
          onLanguageChange = viewModel::setLanguage,
          onRevisitWelcome = {
            navController.navigate(Routes.WELCOME) {
              launchSingleTop = true
            }
          },
        )
      }
    }
  }
}

private fun screenTitleForRoute(route: String?, language: AppLanguage): String =
  when (route) {
    Routes.HOME -> bt("तारापुर मुंगेर जनसेवा", "Tarapur Munger Janseva").resolve(language)
    Routes.SCHEMES -> bt("सरकारी योजनाएँ", "Sarkari Yojanaen").resolve(language)
    Routes.CERTIFICATES_RTPS -> bt("सरकारी प्रमाणपत्र एवं RTPS", "Pramanpatra & RTPS").resolve(language)
    Routes.REGISTER_COMPLAINT -> bt("शिकायत दर्ज करें", "Shikayat Darj Karein").resolve(language)
    Routes.COMPLAINT_STATUS -> bt("शिकायत की स्थिति", "Shikayat Ki Stithi").resolve(language)
    Routes.HEALTH_HOSPITAL -> bt("स्वास्थ्य एवं अस्पताल", "Swasthya & Aspatal").resolve(language)
    Routes.EDUCATION_SCHOOL -> bt("शिक्षा एवं स्कूल", "Shiksha & School").resolve(language)
    Routes.WATER_ROAD -> bt("पानी एवं सड़क", "Pani & Sadak").resolve(language)
    Routes.OFFICE_CONTACTS -> bt("सरकारी कार्यालय संपर्क", "Karyalaya Sampark").resolve(language)
    Routes.LOCAL_NEWS -> bt("स्थानीय समाचार एवं सूचनाएँ", "Sthaniya Samachar").resolve(language)
    Routes.HELP_CONTACT -> bt("सहायता एवं संपर्क", "Sahayata & Sampark").resolve(language)
    else -> bt("तारापुर मुंगेर जनसेवा", "Tarapur Munger Janseva").resolve(language)
  }

private fun screenSubtitleForRoute(route: String?, language: AppLanguage): String =
  when (route) {
    Routes.HOME -> bt("प्रखंड तारापुर (बिहार) • DEMO", "Prakhand Tarapur (Bihar) • DEMO").resolve(language)
    Routes.SCHEMES -> "Schemes & Eligibility Guide (DEMO)"
    Routes.CERTIFICATES_RTPS -> "RTPS Document Checklist (DEMO)"
    Routes.REGISTER_COMPLAINT -> "Grievance Form Preview (DEMO)"
    Routes.COMPLAINT_STATUS -> "Status Layout Sample (DEMO)"
    Routes.HEALTH_HOSPITAL -> "Health & Hospital Directory (DEMO)"
    Routes.EDUCATION_SCHOOL -> "School & Anganwadi Info (DEMO)"
    Routes.WATER_ROAD -> "Water & Rural Roads Guide (DEMO)"
    Routes.OFFICE_CONTACTS -> "Block & Panchayat Offices (DEMO)"
    Routes.LOCAL_NEWS -> "Local Notice Board (DEMO)"
    Routes.HELP_CONTACT -> "Help, Guidelines & Architecture (DEMO)"
    else -> "Tarapur Munger Janseva"
  }
