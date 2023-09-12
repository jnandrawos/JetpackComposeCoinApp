package com.example.coinapp.base.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

fun <T : ViewModel> obtainViewModel(
    owner: ViewModelStoreOwner,
    viewModelClass: Class<T>,
    viewmodelFactory: ViewModelProvider.Factory
) =   ViewModelProvider(owner, viewmodelFactory)[viewModelClass]