package com.gamecampanion.org.digitallibraryapp.httprestclient

import org.junit.jupiter.api.Test

internal class MyRESTClientTest{

    val underTest: MyRESTClient = MyRESTClient()

    @Test
    fun test1(){

        underTest.getGameList()

    }
}