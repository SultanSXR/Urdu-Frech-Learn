package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.NaturalBorder
import com.example.ui.theme.NeutralChipBg
import com.example.ui.theme.SageContainer
import com.example.ui.theme.SageText
import com.example.ui.theme.TextPrimaryLight

@Composable
fun UrduFrenchPhoneticSpotlightCard(
    isUrduUi: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, NaturalBorder, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SageContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Phonetic Secret",
                    tint = ForestPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isUrduUi) "اردو اور فرانسیسی کا قدرتی تعلق" else "Urdu Phonetic Advantage",
                    color = ForestPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isUrduUi)
                    "اردو رسم الخط میں وہ تمام فرانسیسی آوازیں موجود ہیں جو انگریزی میں ناپید ہیں:"
                else
                    "Urdu scripts naturally include unique French sounds missing in English:",
                color = SageText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PhoneticPill("ژ (Zh)", "Bonjour", "بونژور")
                PhoneticPill("ن٘ (Nasal)", "Maison", "میکاں")
                PhoneticPill("غ / خ (Guttural)", "Paris", "پاری غین")
            }
        }
    }
}

@Composable
private fun PhoneticPill(
    urduLetter: String,
    frenchExample: String,
    urduScript: String
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = NeutralChipBg),
        border = androidx.compose.foundation.BorderStroke(1.dp, NaturalBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(urduLetter, color = ForestPrimary, fontWeight = FontWeight.Black, fontSize = 13.sp)
            Text(frenchExample, color = TextPrimaryLight, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            Text(urduScript, color = SageText, fontSize = 10.sp)
        }
    }
}

