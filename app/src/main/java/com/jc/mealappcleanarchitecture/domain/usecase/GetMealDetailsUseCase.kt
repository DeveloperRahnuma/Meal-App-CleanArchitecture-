package com.jc.mealappcleanarchitecture.domain.usecase

import com.jc.mealappcleanarchitecture.common.Resource
import com.jc.mealappcleanarchitecture.data.model.toDomainMealDetails
import com.jc.mealappcleanarchitecture.domain.model.MealDetails
import com.jc.mealappcleanarchitecture.domain.repository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: GetMealDetailsRepository) {
    operator fun invoke(id : String) : Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())
            val responce = repository.getMealDetails(id).meals[0].toDomainMealDetails()
            emit(Resource.Success(responce))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}