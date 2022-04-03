package com.jc.mealappcleanarchitecture.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("urlToImage")
fun urlToImage(view : ImageView, str: String?){
    str.let {
        Glide.with(view).load(str).into(view)
    }
}