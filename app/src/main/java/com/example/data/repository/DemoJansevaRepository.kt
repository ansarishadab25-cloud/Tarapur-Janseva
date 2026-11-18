package com.example.data.repository

import com.example.domain.model.AccentTone
import com.example.domain.model.DemoComplaintStatusSample
import com.example.domain.model.DemoScheme
import com.example.domain.model.DirectoryEntry
import com.example.domain.model.DirectorySection
import com.example.domain.model.HomeFeatureItem
import com.example.domain.model.LocalNotice
import com.example.domain.model.NoticeCategory
import com.example.domain.model.RtpsGuideItem
import com.example.domain.model.SchemeCategory
import com.example.domain.model.bt
import com.example.ui.navigation.Routes

class DemoJansevaRepository : JansevaRepository {

  override fun getHomeFeatures(): List<HomeFeatureItem> = listOf(
    HomeFeatureItem(
      number = 1,
      route = Routes.SCHEMES,
      title = bt("सरकारी योजनाएँ", "Sarkari Yojanaen"),
      subtitle = bt("पात्रता व दस्तावेज़ गाइड (डेमो)", "Patrata aur kagaz guide (Demo)"),
      accentTone = AccentTone.BLUE,
      testTag = "home_tile_schemes",
    ),
    HomeFeatureItem(
      number = 2,
      route = Routes.CERTIFICATES_RTPS,
      title = bt("सरकारी प्रमाणपत्र एवं RTPS", "Pramanpatra & RTPS"),
      subtitle = bt("जाति, आवासीय, आय तैयारी चेकलिस्ट", "Jati, Niwas, Aay checklist"),
      accentTone = AccentTone.GREEN,
      testTag = "home_tile_certificates",
    ),
    HomeFeatureItem(
      number = 3,
      route = Routes.REGISTER_COMPLAINT,
      title = bt("शिकायत दर्ज करें", "Shikayat Darj Karein"),
      subtitle = bt("स्थानीय समस्या फॉर्म (डेमो पूर्वावलोकन)", "Samasya form (Demo preview)"),
      accentTone = AccentTone.BLUE,
      testTag = "home_tile_register_complaint",
    ),
    HomeFeatureItem(
      number = 4,
      route = Routes.COMPLAINT_STATUS,
      title = bt("शिकायत की स्थिति", "Shikayat Ki Stithi"),
      subtitle = bt("स्थिति ट्रैकिंग लेआउट (डेमो)", "Status tracking layout (Demo)"),
      accentTone = AccentTone.GREEN,
      testTag = "home_tile_complaint_status",
    ),
    HomeFeatureItem(
      number = 5,
      route = Routes.HEALTH_HOSPITAL,
      title = bt("स्वास्थ्य एवं अस्पताल", "Swasthya & Aspatal"),
      subtitle = bt("स्वास्थ्य केंद्र विवरण (डेमो)", "Health center jankari (Demo)"),
      accentTone = AccentTone.GREEN,
      testTag = "home_tile_health",
    ),
    HomeFeatureItem(
      number = 6,
      route = Routes.EDUCATION_SCHOOL,
      title = bt("शिक्षा एवं स्कूल", "Shiksha & School"),
      subtitle = bt("स्कूल व आंगनवाड़ी जानकारी (डेमो)", "School & Anganwadi info (Demo)"),
      accentTone = AccentTone.BLUE,
      testTag = "home_tile_education",
    ),
    HomeFeatureItem(
      number = 7,
      route = Routes.WATER_ROAD,
      title = bt("पानी एवं सड़क", "Pani & Sadak"),
      subtitle = bt("पेयजल, नल-जल व सड़क सूचना", "Pani, Nal-Jal & Sadak suchna"),
      accentTone = AccentTone.GREEN,
      testTag = "home_tile_water_road",
    ),
    HomeFeatureItem(
      number = 8,
      route = Routes.OFFICE_CONTACTS,
      title = bt("सरकारी कार्यालय संपर्क", "Karyalaya Sampark"),
      subtitle = bt("प्रखंड व पंचायत कार्यालय सूची (डेमो)", "Block & Panchayat list (Demo)"),
      accentTone = AccentTone.BLUE,
      testTag = "home_tile_office_contacts",
    ),
    HomeFeatureItem(
      number = 9,
      route = Routes.LOCAL_NEWS,
      title = bt("स्थानीय समाचार एवं सूचनाएँ", "Sthaniya Samachar"),
      subtitle = bt("गाँव व प्रखंड सूचना पट्ट (डेमो)", "Gaon & Block notice board (Demo)"),
      accentTone = AccentTone.BLUE,
      testTag = "home_tile_local_news",
    ),
    HomeFeatureItem(
      number = 10,
      route = Routes.HELP_CONTACT,
      title = bt("सहायता एवं संपर्क", "Sahayata & Sampark"),
      subtitle = bt("ऐप उपयोग गाइड, भाषा व नियम", "App guide, bhasha aur niyam"),
      accentTone = AccentTone.GREEN,
      testTag = "home_tile_help_contact",
    ),
  )

  override fun getDemoSchemes(): List<DemoScheme> = listOf(
    DemoScheme(
      id = "demo_scheme_1",
      category = SchemeCategory.AGRICULTURE,
      title = bt(
        "[DEMO] किसान फसल एवं कृषि सहायता उदाहरण",
        "[DEMO] Kisan Fasal & Krishi Sahayata Udaharan",
      ),
      summary = bt(
        "यह एक डेमो कार्ड है। वास्तविक किसान योजनाओं की सही जानकारी और आवेदन केवल आधिकारिक कृषि कार्यालय या किसान सलाहकार से सत्यापित करें।",
        "Yeh ek DEMO card hai. Asli kisan yojanaon ki sahi jankari sirf adhikarik krishi karyalaya ya kisan salahkar se confirm karein.",
      ),
      eligibility = listOf(
        bt("डेमो बिंदु: स्थानीय निवासी किसान परिवार उदाहरण", "Demo point: Sthaniya niwasi kisan parivar udaharan"),
        bt("डेमो बिंदु: भूमि स्वामित्व या खेती से जुड़े सामान्य कागजात", "Demo point: Zameen ya kheti se jude samanya kagaz"),
      ),
      requiredDocs = listOf(
        bt("पहचान प्रमाण (ऐप में अपलोड नहीं करना है)", "Pehchan patra (app mein upload nahi karna hai)"),
        bt("जमीन का विवरण (सत्यापन कार्यालय में)", "Zameen ka vivaran (office verification ke liye)"),
      ),
      verificationNote = bt(
        "नोट: यह ऐप कोई सरकारी दावा नहीं करता और न ही कोई संवेदनशील नंबर मांगता है।",
        "Note: Yeh app koi sarkari dawa nahi karta aur na hi koi sensitive number maangta hai.",
      ),
    ),
    DemoScheme(
      id = "demo_scheme_2",
      category = SchemeCategory.HOUSING,
      title = bt(
        "[DEMO] ग्रामीण आवास एवं बुनियादी सुविधा उदाहरण",
        "[DEMO] Gramin Awas & Suvidha Udaharan",
      ),
      summary = bt(
        "डेमो प्रारूप: ग्रामीण आवास व शौचालय जैसी कल्याणकारी योजनाओं की सामान्य समझ देने के लिए नमूना विवरण।",
        "Demo praroop: Gramin awas aur suvidha yojanaon ki samanya samajh ke liye sample vivaran.",
      ),
      eligibility = listOf(
        bt("डेमो बिंदु: ग्राम पंचायत की सत्यापित सूची के अनुसार", "Demo point: Gram Panchayat ki satyapit suchi ke anusar"),
        bt("डेमो बिंदु: पक्का आवास न होने की स्थिति में समीक्षा", "Demo point: Pakka makan na hone par samiksha"),
      ),
      requiredDocs = listOf(
        bt("निवास प्रमाणपत्र (कार्यालय में दिखाने हेतु)", "Niwas pramanpatra (office mein dikhane ke liye)"),
        bt("ग्राम पंचायत से प्राप्त पात्रता पर्ची (यदि लागू हो)", "Gram Panchayat parchi (yadi lagu ho)"),
      ),
      verificationNote = bt(
        "डेमो सूचना: वास्तविक पात्रता सूची हेतु पंचायत सचिव या प्रखंड कार्यालय से संपर्क करें।",
        "Demo suchna: Asli list ke liye Panchayat sachiv ya Block office se sampark karein.",
      ),
    ),
    DemoScheme(
      id = "demo_scheme_3",
      category = SchemeCategory.PENSION,
      title = bt(
        "[DEMO] वृद्धावस्था एवं सामाजिक सुरक्षा पेंशन उदाहरण",
        "[DEMO] Vridhavastha & Samajik Pension Udaharan",
      ),
      summary = bt(
        "डेमो प्रारूप: बुजुर्ग, विधवा या दिव्यांग नागरिकों के लिए सामाजिक सहायता से जुड़े सामान्य मार्गदर्शन का उदाहरण।",
        "Demo praroop: Buzurg, vidhwa ya divyang nagrikon ke liye samajik sahayata ka sample guide.",
      ),
      eligibility = listOf(
        bt("डेमो बिंदु: आयु या श्रेणी मानदंड के अनुसार पात्रता", "Demo point: Umra ya shreni niyam ke anusar patrata"),
        bt("डेमो बिंदु: स्थानीय सत्यापन प्रक्रिया पूरी होना", "Demo point: Sthaniya verification poora hona"),
      ),
      requiredDocs = listOf(
        bt("आयु एवं निवास से जुड़े सामान्य दस्तावेज़", "Umra aur niwas se jude samanya kagaz"),
        bt("नवीनतम पासपोर्ट आकार फोटो", "Naya passport size photo"),
      ),
      verificationNote = bt(
        "सचेत रहें: इस ऐप में कभी भी बैंक खाता या आधार नंबर दर्ज न करें।",
        "Sachet rahein: Is app mein kabhi bhi bank khata ya Aadhaar number na daalein.",
      ),
    ),
    DemoScheme(
      id = "demo_scheme_4",
      category = SchemeCategory.WOMEN_STUDENT,
      title = bt(
        "[DEMO] छात्रवृत्ति एवं महिला स्वावलंबन सहायता उदाहरण",
        "[DEMO] Chhatravritti & Mahila Sahayata Udaharan",
      ),
      summary = bt(
        "डेमो प्रारूप: विद्यार्थियों और स्वयं सहायता समूहों की जानकारी व्यवस्थित रूप से दिखाने का नमूना लेआउट।",
        "Demo praroop: Vidyarthiyon aur swayam sahayata samuhon ki jankari dikhane ka sample layout.",
      ),
      eligibility = listOf(
        bt("डेमो बिंदु: विद्यालय/महाविद्यालय में नियमित नामांकन", "Demo point: School ya college mein regular admission"),
        bt("डेमो बिंदु: आय एवं जाति प्रमाणपत्र का सत्यापन", "Demo point: Aay aur jati pramanpatra ka satyapan"),
      ),
      requiredDocs = listOf(
        bt("विद्यालय से जारी बोनाफाइड प्रमाणपत्र", "School se mila bonafide certificate"),
        bt("जाति, आवासीय व आय प्रमाणपत्र (RTPS से)", "Jati, Niwas aur Aay pramanpatra"),
      ),
      verificationNote = bt(
        "यह डेमो जानकारी है; वास्तविक तिथि और नियम विद्यालय या आधिकारिक केंद्र से जानें।",
        "Yeh DEMO jankari hai; asli date aur niyam school ya adhikarik kendra se jaanein.",
      ),
    ),
  )

  override fun getRtpsGuides(): List<RtpsGuideItem> = listOf(
    RtpsGuideItem(
      id = "rtps_residence",
      title = bt("आवासीय प्रमाणपत्र (निवास)", "Awasiya Pramanpatra (Residence)"),
      subtitle = bt(
        "डेमो चेकलिस्ट: आवेदन से पहले ये सामान्य कागजात तैयार रखें",
        "Demo checklist: Aavedan se pehle yeh samanya kagaz taiyar rakhein",
      ),
      checklistDocs = listOf(
        bt("स्व-अभिप्रमाणित पहचान पत्र की प्रति (केंद्र पर दिखाने हेतु)", "Self-attested Pehchan patra copy"),
        bt("हाल का पासपोर्ट आकार फोटो", "Haal ka passport size photo"),
        bt("वार्ड / ग्राम / पंचायत का सही पता विवरण", "Ward / Gaon / Panchayat ka sahi pata"),
        bt("सक्रिय मोबाइल नंबर (रसीद व संदेश के लिए)", "Chalu mobile number (receipt ke liye)"),
      ),
      steps = listOf(
        bt("1. [DEMO गाइड] अपने सभी आवश्यक दस्तावेज़ों की साफ़ प्रति तैयार करें।", "1. [DEMO Guide] Apne zaroori kagazon ki saaf copy taiyar karein."),
        bt("2. [DEMO गाइड] नजदीकी आधिकारिक RTPS / लोक सेवा केंद्र या प्रखंड काउंटर पर जाएं।", "2. [DEMO Guide] Nazdiki adhikarik RTPS / Lok Seva Kendra ya Block counter par jayein."),
        bt("3. [DEMO गाइड] आवेदन रसीद (Receipt) संभाल कर रखें ताकि समय पर प्रमाणपत्र मिल सके।", "3. [DEMO Guide] Aavedan receipt sambhal kar rakhein."),
      ),
    ),
    RtpsGuideItem(
      id = "rtps_caste",
      title = bt("जाति प्रमाणपत्र (Caste Certificate)", "Jati Pramanpatra (Caste)"),
      subtitle = bt(
        "डेमो चेकलिस्ट: सत्यापन के लिए सामान्य दस्तावेज़ सूची",
        "Demo checklist: Satyapan ke liye samanya kagaz suchi",
      ),
      checklistDocs = listOf(
        bt("आवेदक का पासपोर्ट फोटो", "Aavedak ka passport photo"),
        bt("माता-पिता / परिवार से जुड़ा जाति व निवास संदर्भ (यदि उपलब्ध हो)", "Parivar ka jati/niwas sandarbh (yadi ho)"),
        bt("पहचान व पता से जुड़े सामान्य दस्तावेज़", "Pehchan aur pata se jude samanya kagaz"),
      ),
      steps = listOf(
        bt("1. [DEMO गाइड] नाम की वर्तनी (हिन्दी और अंग्रेज़ी) पहले से जांच लें।", "1. [DEMO Guide] Naam ki spelling (Hindi & English) pehle check kar lein."),
        bt("2. [DEMO गाइड] अधिकृत सेवा केंद्र से आवेदन कर रसीद प्राप्त करें।", "2. [DEMO Guide] Adhikrit seva kendra se aavedan karke receipt lein."),
        bt("3. [DEMO गाइड] किसी अजनबी या अनधिकृत व्यक्ति को बैंक/आधार OTP न दें।", "3. [DEMO Guide] Kisi anjan vyakti ko bank ya Aadhaar OTP na dein."),
      ),
    ),
    RtpsGuideItem(
      id = "rtps_income",
      title = bt("आय प्रमाणपत्र (Income Certificate)", "Aay Pramanpatra (Income)"),
      subtitle = bt(
        "डेमो चेकलिस्ट: छात्रवृत्ति व कल्याण सेवाओं के लिए तैयारी",
        "Demo checklist: Chhatravritti aur sevaon ke liye taiyari",
      ),
      checklistDocs = listOf(
        bt("वार्षिक पारिवारिक आय की सही अनुमानित जानकारी", "Salana parivarik aay ki sahi jankari"),
        bt("पहचान एवं निवास से जुड़ा सामान्य कागज", "Pehchan aur niwas se juda samanya kagaz"),
        bt("आवेदक का हस्ताक्षर एवं फोटो", "Aavedak ka signature aur photo"),
      ),
      steps = listOf(
        bt("1. [DEMO गाइड] कृषि, मजदूरी या अन्य स्रोत की वार्षिक आय का विवरण स्पष्ट रखें।", "1. [DEMO Guide] Krishi, mazdoori ya anya srot ki salana aay saaf rakhein."),
        bt("2. [DEMO गाइड] आधिकारिक प्रखंड / RTPS काउंटर पर जमा कर पावती लें।", "2. [DEMO Guide] Adhikarik Block / RTPS counter par jama karke receipt lein."),
      ),
    ),
  )

  override fun getDemoComplaintStatusSamples(): List<DemoComplaintStatusSample> = listOf(
    DemoComplaintStatusSample(
      sampleCode = "DEMO-PREVIEW-01",
      categoryLabel = bt("पेयजल / चापाकल (डेमो नमूना)", "Peyjal / Chapakal (Demo Sample)"),
      areaLabel = bt("[DEMO] तारापुर क्षेत्र - नमूना वार्ड", "[DEMO] Tarapur Kshetra - Sample Ward"),
      currentStepIndex = 2,
      statusNote = bt(
        "यह केवल एक डेमो उदाहरण है। वास्तविक शिकायत ट्रैकिंग अभी सक्रिय नहीं है — भविष्य में Firebase और सत्यापित एडमिन पैनल जुड़ने पर यहाँ असली स्थिति दिखेगी।",
        "Yeh sirf ek DEMO sample hai. Asli complaint tracking abhi chalu nahi hai — aage Firebase aur verified admin judne par asli status dikhega.",
      ),
      updatedLabel = bt("नमूना स्थिति: कार्य प्रगति पर", "Sample status: Kaam chal raha hai"),
    ),
    DemoComplaintStatusSample(
      sampleCode = "DEMO-PREVIEW-02",
      categoryLabel = bt("ग्रामीण सड़क / नाली (डेमो नमूना)", "Gramin Sadak / Nali (Demo Sample)"),
      areaLabel = bt("[DEMO] मुंगेर-तारापुर संपर्क उदाहरण", "[DEMO] Munger-Tarapur Sample Area"),
      currentStepIndex = 1,
      statusNote = bt(
        "डेमो लेआउट उदाहरण: दिखाता है कि समीक्षा चरण में शिकायत कार्ड ग्रामीणों को कैसा दिखाई देगा।",
        "Demo layout sample: Dikhata hai ki samiksha charan mein shikayat card kaisa dikhega.",
      ),
      updatedLabel = bt("नमूना स्थिति: समीक्षाधीन", "Sample status: Samiksha mein"),
    ),
  )

  override fun getDirectoryEntries(section: DirectorySection): List<DirectoryEntry> =
    when (section) {
      DirectorySection.HEALTH -> listOf(
        DirectoryEntry(
          id = "health_1",
          section = DirectorySection.HEALTH,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] सामुदायिक / प्राथमिक स्वास्थ्य केंद्र उदाहरण", "[DEMO] Swasthya Kendra Udaharan"),
          details = bt(
            "ओपीडी, टीकाकरण, मातृ-शिशु स्वास्थ्य एवं प्राथमिक उपचार सेवा की जानकारी के लिए नमूना कार्ड।",
            "OPD, tikakaran, matru-shishu swasthya aur prathmik upchar jankari ke liye sample card.",
          ),
          hoursOrProcess = bt("समय (डेमो): स्थानीय सरकारी समय सारणी के अनुसार", "Samay (Demo): Sthaniya niyam ke anusar"),
          contactPlaceholder = bt("संपर्क: [DEMO - सत्यापित नंबर जोड़ा जाना बाकी]", "Sampark: [DEMO - Verified number baaki hai]"),
          locationPlaceholder = bt("स्थान: [DEMO - तारापुर प्रखंड क्षेत्र उदाहरण]", "Sthan: [DEMO - Tarapur block area sample]"),
        ),
        DirectoryEntry(
          id = "health_2",
          section = DirectorySection.HEALTH,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] ग्राम स्तर स्वास्थ्य एवं आशा / एएनएम सहायता", "[DEMO] Gaon Swasthya & ASHA Sahayata"),
          details = bt(
            "गर्भवती महिलाओं की जांच, पोषण व नियमित टीकाकरण दिवस की सूचना दिखाने का नमूना प्रारूप।",
            "Garbhvati mahilaon ki janch, poshan aur tikakaran diwas ki suchna dikhane ka sample format.",
          ),
          hoursOrProcess = bt("सेवा (डेमो): साप्ताहिक ग्राम स्वास्थ्य दिवस", "Seva (Demo): Saptahik Gram Swasthya Diwas"),
          contactPlaceholder = bt("संपर्क: [DEMO - स्थानीय आशा/स्वास्थ्य कर्मी विवरण]", "Sampark: [DEMO - Sthaniya ASHA/worker vivaran]"),
          locationPlaceholder = bt("स्थान: [DEMO - पंचायत आंगनवाड़ी / उप-केंद्र]", "Sthan: [DEMO - Panchayat Anganwadi / Sub-center]"),
        ),
      )

      DirectorySection.EDUCATION -> listOf(
        DirectoryEntry(
          id = "edu_1",
          section = DirectorySection.EDUCATION,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] प्राथमिक एवं मध्य विद्यालय सूचना उदाहरण", "[DEMO] Prathmik & Madhya Vidyalaya Udaharan"),
          details = bt(
            "नामांकन समय, मध्याह्न भोजन, पुस्तक वितरण व छात्र उपस्थिति से जुड़ी सूचनाओं का नमूना स्थान।",
            "Admission samay, mid-day meal, kitab vitaran aur upasthiti suchnaon ka sample card.",
          ),
          hoursOrProcess = bt("सत्र (डेमो): शैक्षणिक कैलेंडर के अनुसार", "Satra (Demo): School calendar ke anusar"),
          contactPlaceholder = bt("प्रधानाध्यापक संपर्क: [DEMO - सत्यापित नंबर अभी नहीं]", "Headmaster Sampark: [DEMO - Verified number abhi nahi]"),
          locationPlaceholder = bt("स्थान: [DEMO - ग्राम/पंचायत विद्यालय परिसर]", "Sthan: [DEMO - Gaon/Panchayat school parisar]"),
        ),
        DirectoryEntry(
          id = "edu_2",
          section = DirectorySection.EDUCATION,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] आंगनवाड़ी केंद्र एवं बाल पोषण सेवा", "[DEMO] Anganwadi Kendra & Bal Poshan"),
          details = bt(
            "0-6 वर्ष के बच्चों के लिए प्रारंभिक शिक्षा व पोषाहार सूचना दिखाने का डेमो कार्ड।",
            "0-6 saal ke bachchon ke liye shiksha aur poshan suchna dikhane ka demo card.",
          ),
          hoursOrProcess = bt("सत्र (डेमो): प्रातः कालीन केंद्र संचालन", "Satra (Demo): Subah ka kendra samay"),
          contactPlaceholder = bt("सेविका संपर्क: [DEMO - स्थानीय सूची जोड़ी जाएगी]", "Sevika Sampark: [DEMO - Sthaniya list judegi]"),
          locationPlaceholder = bt("स्थान: [DEMO - वार्ड आंगनवाड़ी केंद्र उदाहरण]", "Sthan: [DEMO - Ward Anganwadi sample]"),
        ),
      )

      DirectorySection.WATER_ROAD -> listOf(
        DirectoryEntry(
          id = "wr_1",
          section = DirectorySection.WATER_ROAD,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] हर घर नल-जल एवं चापाकल रखरखाव सूचना", "[DEMO] Har Ghar Nal-Jal & Chapakal Suchna"),
          details = bt(
            "पाइप लीकेज, मोटर खराबी या चापाकल मरम्मत से जुड़ी जानकारी पंचायत वार्ड स्तर पर साझा करने का नमूना कार्ड।",
            "Pipe leakage, motor kharabi ya chapakal marammat ki jankari ward level par dikhane ka sample card.",
          ),
          hoursOrProcess = bt("प्रक्रिया (डेमो): वार्ड सदस्य / पंचायत अनुरक्षक को सूचित करें", "Prakriya (Demo): Ward sadasya / anurakshak ko batayein"),
          contactPlaceholder = bt("संपर्क: [DEMO - वार्ड अनुरक्षक विवरण जोड़ा जाएगा]", "Sampark: [DEMO - Ward anurakshak vivaran judega]"),
          locationPlaceholder = bt("क्षेत्र: [DEMO - संबंधित वार्ड एवं पंचायत]", "Kshetra: [DEMO - Sambandhit ward & panchayat]"),
        ),
        DirectoryEntry(
          id = "wr_2",
          section = DirectorySection.WATER_ROAD,
          badge = bt("DEMO प्रविष्टि", "DEMO Entry"),
          title = bt("[DEMO] ग्रामीण सड़क, पुलिया एवं जल-जमाव सूचना", "[DEMO] Gramin Sadak, Puliya & Jal-Jamav"),
          details = bt(
            "सड़क टूट-फूट या नाली अवरोध की सूचना का वर्गीकरण व मार्गदर्शन दिखाने वाला डेमो ब्लॉक।",
            "Sadak toot-phoot ya nali rukawat ki suchna ka vargikaran dikhane wala demo block.",
          ),
          hoursOrProcess = bt("प्रक्रिया (डेमो): ग्राम सभा या पंचायत कार्यालय में दर्ज कराएं", "Prakriya (Demo): Gram Sabha ya Panchayat office mein batayein"),
          contactPlaceholder = bt("संपर्क: [DEMO - सत्यापित अभियंता/कार्यालय विवरण बाकी]", "Sampark: [DEMO - Verified office vivaran baaki]"),
          locationPlaceholder = bt("क्षेत्र: [DEMO - तारापुर ग्रामीण मार्ग उदाहरण]", "Kshetra: [DEMO - Tarapur gramin marg sample]"),
        ),
      )

      DirectorySection.OFFICE_CONTACTS -> listOf(
        DirectoryEntry(
          id = "off_1",
          section = DirectorySection.OFFICE_CONTACTS,
          badge = bt("DEMO संपर्क", "DEMO Contact"),
          title = bt("[DEMO] प्रखंड कार्यालय (Block Office) सूचना कार्ड", "[DEMO] Block Office (Prakhand) Card"),
          details = bt(
            "विकास योजनाओं, आवास एवं पंचायत समन्वय से जुड़े कार्यों के लिए नमूना कार्यालय निर्देशिका कार्ड।",
            "Vikas yojanaon, awas aur panchayat karyon ke liye sample office directory card.",
          ),
          hoursOrProcess = bt("कार्य दिवस (डेमो): सरकारी कार्यदिवस एवं समय", "Kaam ka din (Demo): Sarkari karyadiwas"),
          contactPlaceholder = bt("फ़ोन: [DEMO - सत्यापित नंबर अभी उपलब्ध नहीं]", "Phone: [DEMO - Verified number abhi uplabdh nahi]"),
          locationPlaceholder = bt("पता: [DEMO - तारापुर प्रखंड मुख्यालय उदाहरण]", "Pata: [DEMO - Tarapur Block HQ sample]"),
        ),
        DirectoryEntry(
          id = "off_2",
          section = DirectorySection.OFFICE_CONTACTS,
          badge = bt("DEMO संपर्क", "DEMO Contact"),
          title = bt("[DEMO] अंचल एवं राजस्व कार्यालय (Circle Office) कार्ड", "[DEMO] Anchal Karyalaya (Circle Office) Card"),
          details = bt(
            "दाखिल-खारिज, रसीद एवं प्रमाणपत्र सत्यापन संबंधी कार्यों की सामान्य निर्देशिका।",
            "Dakhil-kharij, lagan receipt aur pramanpatra satyapan ke liye samanya directory.",
          ),
          hoursOrProcess = bt("कार्य दिवस (डेमो): लोक सेवा काउंटर समयानुसार", "Kaam ka din (Demo): Counter samay ke anusar"),
          contactPlaceholder = bt("फ़ोन: [DEMO - सत्यापित नंबर जोड़ा जाना बाकी]", "Phone: [DEMO - Verified number jodna baaki]"),
          locationPlaceholder = bt("पता: [DEMO - अंचल कार्यालय परिसर उदाहरण]", "Pata: [DEMO - Anchal office parisar sample]"),
        ),
        DirectoryEntry(
          id = "off_3",
          section = DirectorySection.OFFICE_CONTACTS,
          badge = bt("DEMO संपर्क", "DEMO Contact"),
          title = bt("[DEMO] ग्राम पंचायत भवन एवं जनसेवा केंद्र", "[DEMO] Gram Panchayat Bhawan & Kendra"),
          details = bt(
            "स्थानीय जन्म-मृत्यु पंजीकरण मार्गदर्शन, ग्राम सभा सूचना व स्थानीय अभिलेख संबंधी संदर्भ।",
            "Sthaniya janm-mrityu panjikaran guide, Gram Sabha suchna aur sthaniya sandarbh.",
          ),
          hoursOrProcess = bt("कार्य दिवस (डेमो): पंचायत सचिव बैठक दिवस", "Kaam ka din (Demo): Panchayat sachiv baithak din"),
          contactPlaceholder = bt("फ़ोन: [DEMO - पंचायत सत्यापित संपर्क बाकी]", "Phone: [DEMO - Panchayat verified contact baaki]"),
          locationPlaceholder = bt("पता: [DEMO - संबंधित ग्राम पंचायत भवन]", "Pata: [DEMO - Sambandhit Gram Panchayat Bhawan]"),
        ),
      )
    }

  override fun getLocalNotices(): List<LocalNotice> = listOf(
    LocalNotice(
      id = "notice_1",
      category = NoticeCategory.GRAM_SABHA,
      dateLabel = bt("डेमो तिथि: आगामी बैठक उदाहरण", "Demo Date: Aagami Baithak Sample"),
      title = bt(
        "[DEMO] ग्राम सभा बैठक एवं स्वच्छता चर्चा सूचना",
        "[DEMO] Gram Sabha Baithak & Swachhata Suchna",
      ),
      body = bt(
        "यह एक डेमो सूचना कार्ड है। लाइव होने पर यहाँ गाँव और पंचायत की सत्यापित ग्राम सभा तिथियाँ व एजेंडा दिखाया जाएगा।",
        "Yeh ek DEMO suchna card hai. Live hone par yahan gaon aur panchayat ki verified Gram Sabha dates dikhayi jayengi.",
      ),
      sourceNote = bt("स्रोत: [DEMO सामुदायिक सूचना नमूना]", "Srot: [DEMO Samudayik Suchna Sample]"),
    ),
    LocalNotice(
      id = "notice_2",
      category = NoticeCategory.HEALTH_CAMP,
      dateLabel = bt("डेमो तिथि: साप्ताहिक शिविर उदाहरण", "Demo Date: Saptahik Shivir Sample"),
      title = bt(
        "[DEMO] नियमित टीकाकरण एवं स्वास्थ्य जांच जागरूकता",
        "[DEMO] Niyamit Tikakaran & Swasthya Janch",
      ),
      body = bt(
        "डेमो सामग्री: नजदीकी आंगनवाड़ी व स्वास्थ्य उप-केंद्र में नियमित टीकाकरण और सामान्य जांच की सूचना यहाँ प्रकाशित की जा सकेगी।",
        "Demo samagri: Nazdiki Anganwadi aur health sub-center mein tikakaran aur janch ki suchna yahan prakashit hogi.",
      ),
      sourceNote = bt("स्रोत: [DEMO स्वास्थ्य बुलेटिन नमूना]", "Srot: [DEMO Swasthya Bulletin Sample]"),
    ),
    LocalNotice(
      id = "notice_3",
      category = NoticeCategory.INFRASTRUCTURE,
      dateLabel = bt("डेमो तिथि: मरम्मत सूचना उदाहरण", "Demo Date: Marammat Suchna Sample"),
      title = bt(
        "[DEMO] नल-जल पाइपलाइन जांच व पेयजल सावधानी",
        "[DEMO] Nal-Jal Pipeline Janch & Pani Savdhani",
      ),
      body = bt(
        "डेमो सामग्री: पेयजल आपूर्ति या सड़क मरम्मत से जुड़ी स्थानीय सूचनाएँ सत्यापित एडमिन द्वारा जोड़ी जाएंगी।",
        "Demo samagri: Pani supply ya sadak marammat se judi sthaniya suchnaen verified admin dwara jodi jayengi.",
      ),
      sourceNote = bt("स्रोत: [DEMO बुनियादी सुविधा सूचना]", "Srot: [DEMO Buniyadi Suvidha Suchna]"),
    ),
    LocalNotice(
      id = "notice_4",
      category = NoticeCategory.AWARENESS,
      dateLabel = bt("महत्वपूर्ण जन जागरूकता", "Mahatvapurna Jan Jagrukta"),
      title = bt(
        "डिजिटल सुरक्षा: OTP, आधार या बैंक विवरण किसी से साझा न करें",
        "Digital Suraksha: OTP, Aadhaar ya Bank details share na karein",
      ),
      body = bt(
        "किसी भी अनजान फोन कॉल या संदेश पर अपना बैंक खाता नंबर, पिन, ओटीपी या आधार नंबर न बताएं। यह जनसेवा ऐप ऐसी कोई जानकारी नहीं मांगता।",
        "Kisi bhi anjan call ya message par apna bank number, PIN, OTP ya Aadhaar number na batayein. Yeh Janseva app aisi koi jankari nahi maangta.",
      ),
      sourceNote = bt("जनहित सुरक्षा संदेश", "Janhit Suraksha Sandesh"),
      isDemo = false,
    ),
  )
}
