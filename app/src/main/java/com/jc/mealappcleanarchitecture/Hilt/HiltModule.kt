package com.jc.mealappcleanarchitecture.Hilt

import com.jc.mealappcleanarchitecture.common.Constent
import com.jc.mealappcleanarchitecture.data.remote.MealSearchApi
import com.jc.mealappcleanarchitecture.data.repository.GetMealDetailsIMPL
import com.jc.mealappcleanarchitecture.data.repository.GetMealListIMPL
import com.jc.mealappcleanarchitecture.domain.repository.GetMealDetailsRepository
import com.jc.mealappcleanarchitecture.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideMealSearchAPI() : MealSearchApi {
        return Retrofit.Builder().baseUrl(Constent.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(MealSearchApi::class.java)
    }


    @Provides
    fun provideMealSearchRepository(mealSearchApi: MealSearchApi) : MealSearchRepository{
        return GetMealListIMPL(mealSearchApi)
    }


    @Provides
    fun provideMealDetailRepository(mealSearchApi: MealSearchApi) : GetMealDetailsRepository{
        return GetMealDetailsIMPL(mealSearchApi)
    }
}