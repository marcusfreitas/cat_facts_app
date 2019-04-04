package com.example.catfactsapp.repository.remote.catfacts.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameDataModel(val first: String, val last: String): Parcelable
