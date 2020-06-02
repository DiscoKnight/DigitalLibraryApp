package com.gamecampanion.org.digitallibraryapp.corountines.game

import com.gamecampanion.org.digitallibraryapp.Database.game.GameDB
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import java.util.concurrent.Callable

class CollectionCallableGame_Insert(db: GameDB, entity: GameEntity) : Callable<Unit> {

    private var db = db

    private var entity = entity

    override fun call() {
        db.gameDao().insertGame(entity)

    }

}