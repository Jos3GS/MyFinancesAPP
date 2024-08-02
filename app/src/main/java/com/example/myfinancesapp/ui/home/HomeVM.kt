package com.example.myfinancesapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeVM: ViewModel() {

    private val _bottomAppbarItemSelected = MutableLiveData<Int>()
    val bottomAppbarItemSelected: MutableLiveData<Int> = _bottomAppbarItemSelected

    fun onBottomAppbarItemSelectedChanged(item: Int) {
        _bottomAppbarItemSelected.value = item
    }


}