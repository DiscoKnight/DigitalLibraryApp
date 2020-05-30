package com.gamecampanion.org.digitallibraryapp.corountines

import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import java.util.concurrent.Callable

class CollectionCallableGame(db: GameDB, entity: GameEntity) : Callable<Unit> {

    private var db = db

    private var entity = entity

    override fun call() {
        db.gameDao().insertGame(entity)
    }

}