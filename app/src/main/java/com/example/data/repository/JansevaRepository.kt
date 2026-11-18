package com.example.data.repository

import com.example.domain.model.DemoComplaintStatusSample
import com.example.domain.model.DemoScheme
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.DirectorySection
import com.example.domain.model.HomeFeatureItem
import com.example.domain.model.LocalNotice
import com.example.domain.model.RtpsGuideItem

/**
 * Modular repository contract for Tarapur Munger Janseva.
 * Currently backed by [DemoJansevaRepository]; a future Firebase/Firestore + verified Admin
 * implementation can implement this interface without changing UI screens.
 */
interface JansevaRepository {
  fun getHomeFeatures(): List<HomeFeatureItem>
  fun getDemoSchemes(): List<DemoScheme>
  fun getRtpsGuides(): List<RtpsGuideItem>
  fun getDemoComplaintStatusSamples(): List<DemoComplaintStatusSample>
  fun getDirectoryEntries(section: DirectorySection): List<DirectoryEntry>
  fun getLocalNotices(): List<LocalNotice>
}
