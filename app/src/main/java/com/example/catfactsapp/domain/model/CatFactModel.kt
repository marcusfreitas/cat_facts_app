package com.example.catfactsapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatFactModel(val fact: String, val upvotes: Int) : Parcelable