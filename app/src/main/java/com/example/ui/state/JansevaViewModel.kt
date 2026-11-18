package com.example.ui.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.DemoJansevaRepository
import com.example.data.repository.JansevaRepository
import com.example.data.repository.UserPreferencesRepository
import com.example.domain.model.AppLanguage
import com.example.domain.model.ComplaintCategory
import com.example.domain.model.ComplaintPreviewDraft
import com.example.domain.model.DemoComplaintStatusSample
import com.example.domain.model.DemoScheme
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.DirectorySection
import com.example.domain.model.HomeFeatureItem
import com.example.domain.model.LocalNotice
import com.example.domain.model.NoticeCategory
import com.example.domain.model.RtpsGuideItem
import com.example.domain.model.SchemeCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class JansevaUiState(
  val language: AppLanguage = AppLanguage.HINDI,
  val hasCompletedWelcome: Boolean = false,
  val homeFeatures: List<HomeFeatureItem> = emptyList(),
  val selectedSchemeCategory: SchemeCategory = SchemeCategory.ALL,
  val schemes: List<DemoScheme> = emptyList(),
  val rtpsGuides: List<RtpsGuideItem> = emptyList(),
  val checkedRtpsDocs: Set<String> = emptySet(),
  val complaintDraft: ComplaintPreviewDraft = ComplaintPreviewDraft(),
  val showComplaintDemoPreviewDialog: Boolean = false,
  val statusSamples: List<DemoComplaintStatusSample> = emptyList(),
  val healthEntries: List<DirectoryEntry> = emptyList(),
  val educationEntries: List<DirectoryEntry> = emptyList(),
  val waterRoadEntries: List<DirectoryEntry> = emptyList(),
  val officeEntries: List<DirectoryEntry> = emptyList(),
  val selectedNoticeCategory: NoticeCategory = NoticeCategory.ALL,
  val notices: List<LocalNotice> = emptyList(),
)

class JansevaViewModel(
  private val repository: JansevaRepository,
  private val preferencesRepository: UserPreferencesRepository,
) : ViewModel() {

  private val languageMutex = Mutex()
  @Volatile
  private var pendingLanguageOverride: AppLanguage? = null

  private val _uiState = MutableStateFlow(
    JansevaUiState(
      homeFeatures = repository.getHomeFeatures(),
      schemes = repository.getDemoSchemes(),
      rtpsGuides = repository.getRtpsGuides(),
      statusSamples = repository.getDemoComplaintStatusSamples(),
      healthEntries = repository.getDirectoryEntries(DirectorySection.HEALTH),
      educationEntries = repository.getDirectoryEntries(DirectorySection.EDUCATION),
      waterRoadEntries = repository.getDirectoryEntries(DirectorySection.WATER_ROAD),
      officeEntries = repository.getDirectoryEntries(DirectorySection.OFFICE_CONTACTS),
      notices = repository.getLocalNotices(),
    )
  )
  val uiState: StateFlow<JansevaUiState> = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      preferencesRepository.selectedLanguage.collect { lang ->
        if (pendingLanguageOverride == null) {
          _uiState.update { it.copy(language = lang) }
        }
      }
    }
    viewModelScope.launch {
      preferencesRepository.hasCompletedWelcome.collect { seen ->
        _uiState.update { it.copy(hasCompletedWelcome = seen) }
      }
    }
  }

  fun setLanguage(language: AppLanguage) {
    pendingLanguageOverride = language
    _uiState.update { it.copy(language = language) }
    viewModelScope.launch {
      languageMutex.withLock {
        preferencesRepository.setLanguage(language)
        if (pendingLanguageOverride == language) {
          pendingLanguageOverride = null
        }
      }
    }
  }

  fun completeWelcome() {
    _uiState.update { it.copy(hasCompletedWelcome = true) }
    viewModelScope.launch {
      preferencesRepository.setWelcomeCompleted(true)
    }
  }

  fun selectSchemeCategory(category: SchemeCategory) {
    _uiState.update { it.copy(selectedSchemeCategory = category) }
  }

  fun toggleRtpsDocChecked(docKey: String) {
    _uiState.update { state ->
      val updated = if (docKey in state.checkedRtpsDocs) {
        state.checkedRtpsDocs - docKey
      } else {
        state.checkedRtpsDocs + docKey
      }
      state.copy(checkedRtpsDocs = updated)
    }
  }

  fun updateComplaintCategory(category: ComplaintCategory) {
    _uiState.update {
      it.copy(complaintDraft = it.complaintDraft.copy(category = category))
    }
  }

  fun updateComplaintArea(area: String) {
    _uiState.update {
      it.copy(complaintDraft = it.complaintDraft.copy(panchayatOrWard = area))
    }
  }

  fun updateComplaintDescription(description: String) {
    _uiState.update {
      it.copy(complaintDraft = it.complaintDraft.copy(description = description))
    }
  }

  fun updateComplaintCitizenName(name: String) {
    _uiState.update {
      it.copy(complaintDraft = it.complaintDraft.copy(citizenName = name))
    }
  }

  fun openComplaintPreviewDialog() {
    _uiState.update { it.copy(showComplaintDemoPreviewDialog = true) }
  }

  fun dismissComplaintPreviewDialog() {
    _uiState.update { it.copy(showComplaintDemoPreviewDialog = false) }
  }

  fun resetComplaintDraft() {
    _uiState.update {
      it.copy(
        complaintDraft = ComplaintPreviewDraft(),
        showComplaintDemoPreviewDialog = false,
      )
    }
  }

  fun selectNoticeCategory(category: NoticeCategory) {
    _uiState.update { it.copy(selectedNoticeCategory = category) }
  }

  class Factory(
    private val preferencesRepository: UserPreferencesRepository,
    private val repository: JansevaRepository = DemoJansevaRepository(),
  ) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      return JansevaViewModel(repository, preferencesRepository) as T
    }
  }
}
