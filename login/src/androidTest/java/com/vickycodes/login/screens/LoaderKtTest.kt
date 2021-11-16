package com.vickycodes.login.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import com.vickycodes.login.ui.theme.ComposeCleanArchitectureTheme
import org.junit.Rule
import org.junit.Test

class LoaderKtTest   {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun testLoaderNotExists() {
        // Start the app
        composeTestRule.setContent {
            ComposeCleanArchitectureTheme {
                Loader(isLoading = false)
            }
        }
        val loader = composeTestRule.onNode(hasTestTag("circularLoader"), useUnmergedTree = true)
        loader.assertDoesNotExist()
    }

    @Test
    fun testLoaderExists() {
        // Start the app
        composeTestRule.setContent {
            ComposeCleanArchitectureTheme {
                Loader(isLoading = true)
            }
        }
        val loader = composeTestRule.onNode(hasTestTag("circularLoader"), useUnmergedTree = true)
        loader.assertIsDisplayed()
    }
}