package com.gamecampanion.org.digitallibraryapp.httprestclient;

import okhttp3.OkHttpClient

class MyRESTClient() {

    private var client: OkHttpClient

    init {
        client = OkHttpClient()

    }

    fun getClient(): OkHttpClient {
        return client
    }

}
