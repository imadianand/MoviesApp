package com.popularmovies.vpaliy.popularmoviesapp.ui.home

import com.popularmovies.vpaliy.domain.entity.MediaType
import com.popularmovies.vpaliy.popularmoviesapp.App
import com.popularmovies.vpaliy.popularmoviesapp.R
import com.popularmovies.vpaliy.popularmoviesapp.di.component.DaggerViewComponent
import com.popularmovies.vpaliy.popularmoviesapp.di.module.PresenterModule
import com.popularmovies.vpaliy.popularmoviesapp.di.qualifier.TV
import com.popularmovies.vpaliy.popularmoviesapp.ui.color
import javax.inject.Inject

class TVFragment:HomeFragment(){
    override var presenter: HomeContract.Presenter?=null
        @Inject set(@TV value) {
            field=value
            field?.attach(this)
        }
    override fun types()=arrayOf(MediaType.TOP)

    override fun getColor(type: MediaType)=color(R.color.colorTvShows)

    override fun getTitle(type: MediaType): String {
        return when(type){
            MediaType.POPULAR -> getString(R.string.popular_media)
            MediaType.NOW_PLAYING -> getString(R.string.now_playing_media)
            MediaType.UPCOMING -> getString(R.string.upcoming_media)
            MediaType.TOP -> getString(R.string.top_rated_media)
            else -> throw IllegalArgumentException()
        }
    }

    override fun inject() {
        DaggerViewComponent.builder()
                .applicationComponent(App.component)
                .presenterModule(PresenterModule())
                .build().inject(this)
    }
}