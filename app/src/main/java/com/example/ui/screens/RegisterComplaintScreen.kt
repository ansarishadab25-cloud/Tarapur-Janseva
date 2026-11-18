package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.ComplaintCategory
import com.example.domain.model.ComplaintPreviewDraft
import com.example.domain.model.bt
import com.example.ui.components.DemoDisclaimerBanner
import com.example.ui.components.SectionHeaderCard
import com.example.ui.theme.DeepNavyBlue
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueContainer
import com.example.ui.theme.VillageGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RegisterComplaintScreen(
  language: AppLanguage,
  draft: ComplaintPreviewDraft,
  showPreviewDialog: Boolean,
  onCategorySelect: (ComplaintCategory) -> Unit,
  onAreaChange: (String) -> Unit,
  onDescriptionChange: (String) -> Unit,
  onCitizenNameChange: (String) -> Unit,
  onOpenPreviewDialog: () -> Unit,
  onDismissPreviewDialog: () -> Unit,
  onResetDraft: () -> Unit,
  onViewStatusLayout: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 12.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(
      modifier = Modifier.widthIn(max = 600.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      SectionHeaderCard(
        icon = Icons.Filled.ReportProblem,
        title = bt("शिकायत दर्ज करें (डेमो UI)", "Shikayat Darj Karein (Demo UI)").resolve(language),
        subtitle = bt(
          "यह स्क्रीन केवल फॉर्म डिज़ाइन का डेमो पूर्वावलोकन दिखाती है।",
          "Yeh screen sirf form design ka DEMO preview dikhati hai.",
        ).resolve(language),
      )

      DemoDisclaimerBanner(
        language = language,
        customMessage = bt(
          "महत्वपूर्ण (DEMO मोड): अभी वास्तविक शिकायत सबमिट नहीं होती और कोई ट्रैकिंग नंबर नहीं बनता। लाइव सेवा सुरक्षित Firebase जुड़ने के बाद चालू होगी।",
          "Zaroori (DEMO Mode): Abhi asli shikayat submit nahi hoti aur koi tracking number nahi banta. Live seva Firebase judne ke baad shuru hogi.",
        ).resolve(language),
      )

      // Strict Privacy Guardrail Banner
      Surface(
        shape = RoundedCornerShape(14.dp),
        color = SoftBlueContainer,
        border = BorderStroke(1.dp, DeepNavyBlue.copy(alpha = 0.2f)),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Icon(
            imageVector = Icons.Filled.Lock,
            contentDescription = null,
            tint = DeepNavyBlue,
            modifier = Modifier.size(20.dp),
          )
          Spacer(modifier = Modifier.width(10.dp))
          Text(
            text = bt(
              "सुरक्षा नियम: कृपया यहाँ आधार नंबर, बैंक खाता, पैन या कोई गुप्त जानकारी न लिखें।",
              "Suraksha Niyam: Kripya yahan Aadhaar number, bank khata, PAN ya koi gupt jankari na likhein.",
            ).resolve(language),
            style = MaterialTheme.typography.bodySmall,
            color = DeepNavyBlue,
            fontWeight = FontWeight.SemiBold,
          )
        }
      }

      // Form Card
      Surface(
        shape = RoundedCornerShape(18.dp),
        color = PureWhite,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          Text(
            text = bt("1. समस्या की श्रेणी चुनें:", "1. Samasya ki shreni chunein:").resolve(language),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = DeepNavyBlue,
          )

          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
          ) {
            ComplaintCategory.entries.forEach { cat ->
              val selected = cat == draft.category
              FilterChip(
                selected = selected,
                onClick = { onCategorySelect(cat) },
                modifier = Modifier.testTag("complaint_cat_${cat.name}"),
                label = {
                  Text(
                    text = cat.label.resolve(language),
                    style = MaterialTheme.typography.labelMedium,
                  )
                },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = DeepNavyBlue,
                  selectedLabelColor = PureWhite,
                ),
              )
            }
          }

          OutlinedTextField(
            value = draft.panchayatOrWard,
            onValueChange = onAreaChange,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("complaint_area_input"),
            label = {
              Text(
                bt("2. गाँव / पंचायत / वार्ड (डेमो)", "2. Gaon / Panchayat / Ward (Demo)").resolve(language),
              )
            },
            placeholder = {
              Text(
                bt("उदाहरण: तारापुर वार्ड उदाहरण", "Udaharan: Tarapur ward sample").resolve(language),
              )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
          )

          OutlinedTextField(
            value = draft.citizenName,
            onValueChange = onCitizenNameChange,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("complaint_name_input"),
            label = {
              Text(
                bt("3. आपका नाम (वैकल्पिक, डेमो)", "3. Aapka naam (Optional, Demo)").resolve(language),
              )
            },
            placeholder = {
              Text(bt("केवल नाम लिखें", "Sirf naam likhein").resolve(language))
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
          )

          OutlinedTextField(
            value = draft.description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("complaint_desc_input"),
            label = {
              Text(
                bt("4. समस्या का संक्षिप्त विवरण", "4. Samasya ka sankshipt vivaran").resolve(language),
              )
            },
            placeholder = {
              Text(
                bt(
                  "जैसे: नल-जल या सड़क मरम्मत की आवश्यकता (आधार/बैंक न लिखें)",
                  "Jaise: Nal-Jal ya sadak marammat ki zaroorat (Aadhaar/Bank na likhein)",
                ).resolve(language),
              )
            },
            minLines = 3,
            maxLines = 5,
            shape = RoundedCornerShape(12.dp),
          )

          Button(
            onClick = onOpenPreviewDialog,
            modifier = Modifier
              .fillMaxWidth()
              .height(54.dp)
              .testTag("complaint_demo_preview_button"),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = DeepNavyBlue,
              contentColor = PureWhite,
            ),
          ) {
            Icon(
              imageVector = Icons.Filled.Visibility,
              contentDescription = null,
              modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = bt("फॉर्म पूर्वावलोकन देखें (DEMO)", "Form Preview Dekhein (DEMO)").resolve(language),
              style = MaterialTheme.typography.labelLarge,
              fontWeight = FontWeight.Bold,
            )
          }

          OutlinedButton(
            onClick = onViewStatusLayout,
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("go_to_status_layout_button"),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, VillageGreen),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = VillageGreen),
          ) {
            Text(
              text = bt("शिकायत स्थिति लेआउट देखें (DEMO)", "Shikayat Stithi Layout Dekhein (DEMO)").resolve(language),
              style = MaterialTheme.typography.labelLarge,
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(12.dp))
    }
  }

  if (showPreviewDialog) {
    AlertDialog(
      onDismissRequest = onDismissPreviewDialog,
      containerColor = PureWhite,
      title = {
        Text(
          text = bt("DEMO पूर्वावलोकन (सबमिट नहीं हुआ)", "DEMO Preview (Submit Nahi Hua)").resolve(language),
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = DeepNavyBlue,
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = bt(
              "यह केवल डिज़ाइन पूर्वावलोकन है। कोई वास्तविक शिकायत दर्ज या सेव नहीं की गई है और कोई नकली ट्रैकिंग नंबर जारी नहीं हुआ है।",
              "Yeh sirf design preview hai. Koi asli shikayat darj ya save nahi hui hai aur koi nakli tracking number nahi bana hai.",
            ).resolve(language),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.SemiBold,
          )
          Text(
            text = "${bt("श्रेणी:", "Shreni:").resolve(language)} ${draft.category.label.resolve(language)}",
            style = MaterialTheme.typography.bodyMedium,
          )
          Text(
            text = "${bt("क्षेत्र:", "Kshetra:").resolve(language)} ${draft.panchayatOrWard.ifBlank { bt("(नहीं लिखा गया)", "(Khali)").resolve(language) }}",
            style = MaterialTheme.typography.bodyMedium,
          )
          Text(
            text = "${bt("विवरण:", "Vivaran:").resolve(language)} ${draft.description.ifBlank { bt("(नहीं लिखा गया)", "(Khali)").resolve(language) }}",
            style = MaterialTheme.typography.bodyMedium,
          )
        }
      },
      confirmButton = {
        Button(
          onClick = onDismissPreviewDialog,
          colors = ButtonDefaults.buttonColors(containerColor = DeepNavyBlue),
        ) {
          Text(bt("ठीक है", "Theek Hai").resolve(language))
        }
      },
      dismissButton = {
        TextButton(onClick = onResetDraft) {
          Text(bt("फ़ॉर्म साफ़ करें", "Form Saaf Karein").resolve(language), color = VillageGreen)
        }
      },
    )
  }
}
