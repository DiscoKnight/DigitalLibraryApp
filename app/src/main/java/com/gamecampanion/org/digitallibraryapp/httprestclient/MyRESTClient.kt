package com.gamecampanion.org.digitallibraryapp.httprestclient;

import com.gamecampanion.org.digitallibraryapp.model.GameRestModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class MyRESTClient() {

    var client: OkHttpClient
    var request: Request

    init {
        client = OkHttpClient()

        request = Request.Builder()
            .get()
            .url("http://localhost:8080/api/v1.0/mongo/game/getAllGames")
            .build()

    }



    fun getGameList(): List<GameRestModel> {
        var response = client.newCall(request).execute()

        return Gson().fromJson(response.body()?.string(), Array<GameRestModel>::class.java).toList()

    }
}
