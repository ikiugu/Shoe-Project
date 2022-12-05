package com.udacity.shoestore.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    var shoeName = ""
    var shoeCompany = ""
    var shoeSize = ""
    var shoeDescription = ""

    init {
        Timber.i("initialized")
        _shoes.value = mutableListOf()
        initializeDefaultShoe()
    }

    private fun initializeDefaultShoe() {
        val defaultShoe = Shoe(name = "Default shoe", company = "Shoe city", size = 11.1, description = "This is the default shoe that is always populated when the app launches")
        addShoe(defaultShoe)
    }

    fun isNewShoeAdded(): Boolean {
        if (shoeValid()) {
            addShoe(name = shoeName, size = shoeSize.toDouble(), company = shoeCompany, description = shoeDescription)
            return true
        }

        return false;
    }

    private fun shoeValid(): Boolean {
        if (shoeName.isBlank() || shoeSize.isBlank() || shoeCompany.isBlank() || shoeDescription.isBlank()) {
            return false
        }
        return true
    }

    private fun addShoe(shoe: Shoe) {
        _shoes.value!!.add(shoe)
    }

    private fun addShoe(name: String, size: Double, company: String, description: String) {
        val shoe = Shoe(name, size, company, description)
        _shoes.value!!.add(shoe)
    }
}