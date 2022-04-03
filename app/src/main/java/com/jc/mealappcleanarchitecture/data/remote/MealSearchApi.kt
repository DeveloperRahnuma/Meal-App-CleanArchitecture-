package com.jc.mealappcleanarchitecture.data.remote

import com.jc.mealappcleanarchitecture.data.model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchApi {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealList(@Query("s") s : String) : MealsDTO

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetail(@Query("i") s : String) : MealsDTO
}