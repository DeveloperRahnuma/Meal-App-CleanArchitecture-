package com.jc.mealappcleanarchitecture.domain.usecase

import com.jc.mealappcleanarchitecture.common.Resource
import com.jc.mealappcleanarchitecture.data.model.toDomainMeal
import com.jc.mealappcleanarchitecture.domain.model.Meal
import com.jc.mealappcleanarchitecture.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository ) {
    operator fun invoke(s : String) : Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())
            val responce = repository.getMealList(s)
            val list = if(responce.meals.isNullOrEmpty()) emptyList<Meal>() else responce.meals.map { it.toDomainMeal() }
            emit(Resource.Success(list))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}