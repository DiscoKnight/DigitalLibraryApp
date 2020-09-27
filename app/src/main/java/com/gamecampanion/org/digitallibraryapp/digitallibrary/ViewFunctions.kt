package com.gamecampanion.org.digitallibraryapp.digitallibrary

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageSwitcher
import androidx.core.graphics.drawable.toDrawable
import com.gamecampanion.org.digitallibraryapp.Database.firestore.DigitalLibraryModel
import com.gamecampanion.org.digitallibraryapp.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import java.util.stream.Collectors

class ViewFunctions {

    lateinit var bitmapDrawable: BitmapDrawable

    fun filterByPlatform(platform: String, gameList: List<DigitalLibraryModel>): List<DigitalLibraryModel> {
        return gameList.stream().filter { e -> e.platform.equals(platform) }.collect(
            Collectors.toList()
        )

    }

    fun filterByRating(rating: Int, gameList: List<DigitalLibraryModel>): List<DigitalLibraryModel> {
        return gameList.stream().filter { e -> e.rating!! <= rating }.collect(Collectors.toList())
    }

    fun calcuateTimeToRelease(releaseDate: String?, localDate: String): Period {
        var getEntityList = releaseDate?.split(Pattern.compile("\\W"), 0)

        if (getEntityList != null) {
            return Period.between(
                LocalDate.parse(
                    String.format(
                        "%s-%s-%s",
                        isDaysValid(Integer.valueOf(getEntityList.get(0))),
                        isDaysValid(Integer.valueOf(getEntityList.get(1))),
                        getEntityList?.get(2)
                    ), DateTimeFormatter.ofPattern("d-M" +
                            "-yyyy")
                ), LocalDate.parse(localDate)
            )
        }else{
            return Period.ZERO
        }

    }

    private fun isDaysValid(days: Int): Int {
        var str: String

        if (days < 10) {
            return Integer.valueOf("0" + days)
        } else {
            return days
        }

    }

    fun createAlertDialogPreOwned(context: Context, period: Period, game: DigitalLibraryModel) {
        var dialog = AlertDialog.Builder(
            context,
            R.style.MyDialogTheme
        )

        dialog.setTitle(R.string.dialogTitle)
        dialog.setMessage(String.format("%s years to release, %s months to release, %s days to release\n Title: %s \n Platform: %s \n Rating: %s ",
            period.years,
            period.months.toString().substring(1),
            period.days.toString().substring(1),
            game.name,
            game.platform,
            game.rating)
        )

        dialog.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        dialog.show()
    }

    fun createAlertDialog(game: DigitalLibraryModel, context: Context){
        var dialog = AlertDialog.Builder(
            context,
            R.style.MyDialogTheme
        )

        dialog.setTitle(R.string.dialogTitleGame)
        dialog.setMessage(String.format("Title: %s \n Platform: %s \n Rating: %s ",
            game.name,
            game.platform,
            game.rating)
        )

        dialog.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        dialog.show()
    }

    fun loadGameImageFromUrlLocal(url: String, imageSwitcher : ImageSwitcher) {

        runBlocking {
            var r = GlobalScope.async {
                bitmapDrawable =
                    Picasso.get().load(url).get().toDrawable(Resources.getSystem())
            }

            r.await()
        }

        imageSwitcher.setImageDrawable(bitmapDrawable.current)

    }

//    private fun loadMusicImageFromUrl(musicEntity: MusicEntity) {
//
//        runBlocking {
//            var r = GlobalScope.async {
//                bitmapDrawable =
//                    Picasso.get().load(musicEntity.album).get().toDrawable(Resources.getSystem())
//            }
//
//            r.await()
//        }
//
//        imageSwitcher.setImageDrawable(bitmapDrawable.current)
//
//    }
//
//    private fun loadMovieImageFromUrl(movieEntity: MovieEntity) {
//
//        runBlocking {
//            var r = GlobalScope.async {
//                bitmapDrawable =
//                    Picasso.get().load(movieEntity.artist).get().toDrawable(Resources.getSystem())
//            }
//
//            r.await()
//        }
//
//        imageSwitcher.setImageDrawable(bitmapDrawable.current)
//
//    }


}