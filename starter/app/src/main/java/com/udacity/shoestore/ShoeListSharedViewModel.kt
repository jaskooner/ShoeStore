package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListSharedViewModel() : ViewModel() {

    private val TAG = "ShowListSharedViewModel"

    private lateinit var shoeData: ArrayList<Shoe>

    init {
        Log.i(TAG, "init called")
        shoeData = ArrayList<Shoe>()
    }


    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes : LiveData<MutableList<Shoe>>
        get() = _shoes

    fun addShoe(newShoe: Shoe) {
        val images = listOf<String>("no images")
        shoeData.add(newShoe)

        _shoes.value = shoeData
        Log.i(TAG, "shoe added to _shoes")
    }


}