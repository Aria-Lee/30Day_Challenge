package com.example.fish.day12_githubstarsokhttprecyclerview.Data

import android.graphics.Bitmap
import android.os.Binder
import android.os.Parcel
import android.os.Parcelable

data class UserData(val account :String, val userName: String, val avatar: Bitmap, val reposCount: String, val followers: String, val following :String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(account)
        parcel.writeString(userName)
        parcel.writeParcelable(avatar, flags)
        parcel.writeString(reposCount)
        parcel.writeString(followers)
        parcel.writeString(following)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}