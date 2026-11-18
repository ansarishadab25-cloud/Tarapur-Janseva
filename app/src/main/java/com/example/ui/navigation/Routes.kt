package com.example.ui.navigation

object Routes {
  const val WELCOME = "welcome"
  const val HOME = "home"
  const val SCHEMES = "schemes"
  const val CERTIFICATES_RTPS = "certificates_rtps"
  const val REGISTER_COMPLAINT = "register_complaint"
  const val COMPLAINT_STATUS = "complaint_status"
  const val HEALTH_HOSPITAL = "health_hospital"
  const val EDUCATION_SCHOOL = "education_school"
  const val WATER_ROAD = "water_road"
  const val OFFICE_CONTACTS = "office_contacts"
  const val LOCAL_NEWS = "local_news"
  const val HELP_CONTACT = "help_contact"

  val bottomNavRoutes = setOf(
    HOME,
    REGISTER_COMPLAINT,
    LOCAL_NEWS,
    HELP_CONTACT,
  )
}
