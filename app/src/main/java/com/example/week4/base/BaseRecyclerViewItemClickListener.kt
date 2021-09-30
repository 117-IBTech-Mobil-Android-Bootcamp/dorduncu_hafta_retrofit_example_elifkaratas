package com.example.week3

import androidx.annotation.IdRes

interface BaseRecyclerViewItemClickListener<T>{

    fun onItemClicked(clickedObject : T, @IdRes id : Int = 0)
}