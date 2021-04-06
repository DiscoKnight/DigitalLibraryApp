package com.gamecampanion.org.digitallibraryapp.httprestclient

import okhttp3.OkHttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MyRESTClientTest {

    val underTest: MyRESTClient = MyRESTClient()

    @Test
    fun test_RESTClientSuccess() {

        var client: OkHttpClient = underTest.getClient()

        assertThat(client).isNotNull()

    }
}