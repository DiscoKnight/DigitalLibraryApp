package com.gamecampanion.org.digitallibraryapp.digitallibrary

import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ViewFunctionsTest {

    val gameEntity: GameEntity =
        GameEntity(1, "God Of War", "PS4", "2020-1-1", 5, "Action", true, "testUrl")

    val viewFunctions: ViewFunctions = ViewFunctions()

    @Test
    fun getInfoText() {
    }

    @Test
    fun filterByPlatform() {
    }

    @Test
    fun filterByRating() {
    }

    @Test
    fun createDaysToReleasePrompt() {

        assertThat(viewFunctions.createDaysToReleasePrompt(gameEntity, "")).isEqualTo(1)
    }

    @Test
    fun createAlertDialog() {
    }
}