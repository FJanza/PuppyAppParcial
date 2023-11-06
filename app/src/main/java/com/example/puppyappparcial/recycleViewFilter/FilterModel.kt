package com.example.puppyappparcial.recycleViewFilter

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

class FilterModel(breed: String?, subBreed: String?, location: String?, isChecked: Boolean): Parcelable {
    var breed: String = ""
    var subBreed: String = ""
    var location: String = ""
    var isChecked: Boolean = false

    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readBoolean()
    )

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(breed)
        parcel.writeString(subBreed)
        parcel.writeString(location)
        parcel.writeBoolean(isChecked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterModel> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): FilterModel {
            return FilterModel(parcel)
        }

        override fun newArray(size: Int): Array<FilterModel?> {
            return arrayOfNulls(size)
        }
    }
}