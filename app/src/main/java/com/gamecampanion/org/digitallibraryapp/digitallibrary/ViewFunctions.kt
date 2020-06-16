package com.gamecampanion.org.digitallibraryapp.digitallibrary

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.R
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import java.util.stream.Collectors

class ViewFunctions {

    fun getInfoText(counter: Int, gameList: List<GameEntity>, textViewInfo: TextView) {
        textViewInfo.setText(gameList[counter].gameName + "\n" + gameList[counter].platform + "\n" + gameList[counter].genre)
    }

    fun filterByPlatform(platform: String, gameList: List<GameEntity>): List<GameEntity> {
        return gameList.stream().filter { e -> e.platform.equals(platform) }.collect(
            Collectors.toList()
        )

    }

    fun filterByRating(rating: Int, gameList: List<GameEntity>): List<GameEntity> {
        return gameList.stream().filter { e -> e.rating!! >= rating }.collect(Collectors.toList())
    }

    fun createDaysToReleasePrompt(gameEntity: GameEntity, localDate: String): Int {
        var getEntityList = gameEntity.releaseDate.toString().split(Pattern.compile("\\W"), 0)

        return Period.between(
            LocalDate.parse(
                String.format(
                    "%s-%s-%s",
                    isDaysValid(Integer.valueOf(getEntityList.get(0))),
                    isDaysValid(Integer.valueOf(getEntityList.get(1))),
                    getEntityList.get(2)
                ), DateTimeFormatter.ofPattern("M-d" +
                        "-yyyy")
            ), LocalDate.parse(localDate)
        ).days

    }

    private fun isDaysValid(days: Int): Int {
        var str: String

        if (days < 10) {
            return Integer.valueOf("0" + days)
        } else {
            return days
        }

    }

    fun createAlertDialog(context: Context, daysBetween: Int) {
        var dialog = AlertDialog.Builder(
            context,
            R.style.MyDialogTheme
        )

        dialog.setTitle(R.string.dialogTitle)
        dialog.setMessage(String.format("%s days to release", daysBetween))
        dialog.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        dialog.show()
    }
}