package com.gamecampanion.org.digitallibraryapp.corountines

import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieDB
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity
import java.util.concurrent.Callable

class CollectionCallableMovie(db: MovieDB, entity: MovieEntity) : Callable<Unit> {

    private var db = db

    private var entity = entity

    override fun call() {
        db.movieDao().insertMovie(entity)
    }

}