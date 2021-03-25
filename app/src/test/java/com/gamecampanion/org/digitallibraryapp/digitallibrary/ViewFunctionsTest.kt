package com.gamecampanion.org.digitallibraryapp.digitallibrary

import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.jupiter.api.Test
import java.time.Period

internal class ViewFunctionsTest {

    val gameEntity: GameEntity =
        GameEntity(1, "God Of War", "PS4", "1-1-2020", 5, "Action", true, "testUrl")

    val viewFunctions: ViewFunctions = ViewFunctions()

    val PERIOD_EXPECTED = Period.of(0,5,15)

    @Ignore
    fun createDaysToReleasePrompt() {
        assertThat(viewFunctions.calcuateTimeToRelease(gameEntity.releaseDate, "2020-06-16")).isEqualTo(PERIOD_EXPECTED)
    }

}