package com.example.postup.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
class PostEntity : BaseObservable() {

    @get: Bindable
    var userId: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }

    @PrimaryKey(autoGenerate = false)
    @get: Bindable
    var id: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @get: Bindable
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get: Bindable
    var body: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.body)
        }
}