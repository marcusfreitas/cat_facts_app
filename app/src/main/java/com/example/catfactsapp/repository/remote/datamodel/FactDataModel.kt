package com.example.catfactsapp.repository.remote.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FactDataModel(
    val text: String,
    val upvotes: Int,
    val user: UserDataModel
): Parcelable