package com.example.catfactsapp.repository.remote.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDataModel(val name: NameDataModel): Parcelable
