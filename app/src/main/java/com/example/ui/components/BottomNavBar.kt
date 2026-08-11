package com.example.ui.components

import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.NaturalBorder
import com.example.ui.theme.SageContainer
import com.example.ui.theme.SageText
import com.example.ui.theme.SurfaceLight
import com.example.ui.theme.TextPrimaryLight
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.UiLanguage

@Composable
fun AppBottomNavBar(
    currentScreen: AppScreen,
    currentLanguage: UiLanguage,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.border(width = (0.5).dp, color = NaturalBorder),
        containerColor = SurfaceLight,
        contentColor = ForestPrimary
    ) {
        val isUrdu = currentLanguage == UiLanguage.URDU

        NavigationBarItem(
            modifier = Modifier.testTag("nav_home"),
            selected = currentScreen == AppScreen.HOME,
            onClick = { onNavigate(AppScreen.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text(if (isUrdu) "ہوم" else "Home", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestPrimary,
                selectedTextColor = ForestPrimary,
                unselectedIconColor = SageText,
                unselectedTextColor = SageText,
                indicatorColor = SageContainer
            )
        )

        NavigationBarItem(
            modifier = Modifier.testTag("nav_alphabet"),
            selected = currentScreen == AppScreen.ALPHABET,
            onClick = { onNavigate(AppScreen.ALPHABET) },
            icon = { Icon(Icons.Default.Abc, contentDescription = "Alphabet") },
            label = { Text(if (isUrdu) "حروف" else "Alphabet", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestPrimary,
                selectedTextColor = ForestPrimary,
                unselectedIconColor = SageText,
                unselectedTextColor = SageText,
                indicatorColor = SageContainer
            )
        )

        NavigationBarItem(
            modifier = Modifier.testTag("nav_lessons"),
            selected = currentScreen == AppScreen.LESSONS || currentScreen == AppScreen.ACTIVE_LESSON,
            onClick = { onNavigate(AppScreen.LESSONS) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Lessons") },
            label = { Text(if (isUrdu) "اسباق" else "Lessons", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestPrimary,
                selectedTextColor = ForestPrimary,
                unselectedIconColor = SageText,
                unselectedTextColor = SageText,
                indicatorColor = SageContainer
            )
        )

        NavigationBarItem(
            modifier = Modifier.testTag("nav_flashcards"),
            selected = currentScreen == AppScreen.FLASHCARDS,
            onClick = { onNavigate(AppScreen.FLASHCARDS) },
            icon = { Icon(Icons.Default.Style, contentDescription = "Flashcards") },
            label = { Text(if (isUrdu) "کارڈز" else "Cards", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestPrimary,
                selectedTextColor = ForestPrimary,
                unselectedIconColor = SageText,
                unselectedTextColor = SageText,
                indicatorColor = SageContainer
            )
        )

        NavigationBarItem(
            modifier = Modifier.testTag("nav_conversation"),
            selected = currentScreen == AppScreen.CONVERSATION || currentScreen == AppScreen.ACTIVE_CONVERSATION || currentScreen == AppScreen.PRONUNCIATION_LAB,
            onClick = { onNavigate(AppScreen.CONVERSATION) },
            icon = { Icon(Icons.Default.Forum, contentDescription = "Practice") },
            label = { Text(if (isUrdu) "گفتگو" else "Practice", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = ForestPrimary,
                selectedTextColor = ForestPrimary,
                unselectedIconColor = SageText,
                unselectedTextColor = SageText,
                indicatorColor = SageContainer
            )
        )
    }
}

