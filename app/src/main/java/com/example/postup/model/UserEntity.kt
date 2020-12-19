package com.example.postup.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class UserEntity: BaseObservable(){

    @get: Bindable
    var id: Int? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.id)
    }

    @get: Bindable
    var name: String? = null
    set(value){
        field = value
        notifyPropertyChanged(BR.name)
    }

    @get: Bindable
    var email: String? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR.email)
    }
}