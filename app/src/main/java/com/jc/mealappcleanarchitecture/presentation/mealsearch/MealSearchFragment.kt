package com.jc.mealappcleanarchitecture.presentation.mealsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.jc.mealappcleanarchitecture.R
import com.jc.mealappcleanarchitecture.databinding.FragmentMealSearchBinding
import com.jc.mealappcleanarchitecture.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@AndroidEntryPoint
class MealSearchFragment : Fragment() {
    var _binding : FragmentMealSearchBinding? = null
    val binding : FragmentMealSearchBinding
        get() = _binding!!

    private val mealSearchviewModel : MealSearchViewModel by viewModels()
    private val mealSearchAdapter : MealSearchAdapter = MealSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMealSearchBinding.inflate(inflater,container,false)
        return _binding?.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { mealSearchviewModel.searchMealList(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })


        binding.mealSearchRecycler.apply {
            adapter = mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            try {
                mealSearchviewModel.mealSearchList.collect {
                    if(it.isLoading){
                        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                    }
                    if(it.error.isNotBlank()){
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                    it.data.let {
                        if(it != null) {
                            mealSearchAdapter.setContentList(it as MutableList<Meal>)
                        }
                    }
                }
            }catch (e : Exception){
                e.printStackTrace()
            }

        }

        mealSearchAdapter.itemClickListener {
            findNavController().navigate(MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(it.mealId))
        }
    }
}