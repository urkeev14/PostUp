package com.example.postup.repo.local

import androidx.lifecycle.MutableLiveData

object LocalRepositoryObserver {
    var _isModified = MutableLiveData(false)
    fun isCacheModified() = _isModified.value!!
}