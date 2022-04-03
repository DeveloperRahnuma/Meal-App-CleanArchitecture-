package com.jc.mealappcleanarchitecture.presentation.mealdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jc.mealappcleanarchitecture.R
import com.jc.mealappcleanarchitecture.databinding.FragmentMealDetailsBinding
import com.jc.mealappcleanarchitecture.databinding.FragmentMealSearchBinding
import com.jc.mealappcleanarchitecture.presentation.mealsearch.MealSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private var _binding : FragmentMealDetailsBinding? = null
    private val binding : FragmentMealDetailsBinding
    get() = _binding!!


    private val args : MealDetailsFragmentArgs by navArgs()
    private val mealDetailsViewModel : MealDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         _binding = FragmentMealDetailsBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.mealId.let {
            if (it != null) {
                mealDetailsViewModel.getMealDetails(it)
            }
        }

        lifecycle.coroutineScope.launchWhenCreated {
            mealDetailsViewModel.mealsDetails.collect {
                if(it.isLoading){

                }
                if(it.error.isNotBlank()){

                }
                it.data.let {
                    binding.mealDetails =  it
                }
            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}