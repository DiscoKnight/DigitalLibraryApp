package com.gamecampanion.org.digitallibraryapp.corountines

import com.gamecampanion.org.digitallibraryapp.Database.music.MusicDB
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity
import java.util.concurrent.Callable

class CollectionCallableMusic(db: MusicDB, entity: MusicEntity) : Callable<Unit> {

    private var db = db

    private var entity = entity

    override fun call() {
        db.musicDao().insertMusic(entity)
    }

}