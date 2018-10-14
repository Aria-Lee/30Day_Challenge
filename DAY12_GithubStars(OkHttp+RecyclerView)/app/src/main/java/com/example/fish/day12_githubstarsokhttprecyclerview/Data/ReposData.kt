package com.example.fish.day12_githubstarsokhttprecyclerview.Data

import android.os.Parcel
import android.os.Parcelable

data class ReposData(val repoName: String, val watchers_count: String, val forks_count: String, val stargazers_count: String, val language: String, val creatTIme: String, val updateTime: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(repoName)
        parcel.writeString(watchers_count)
        parcel.writeString(forks_count)
        parcel.writeString(stargazers_count)
        parcel.writeString(language)
        parcel.writeString(creatTIme)
        parcel.writeString(updateTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReposData> {
        override fun createFromParcel(parcel: Parcel): ReposData {
            return ReposData(parcel)
        }

        override fun newArray(size: Int): Array<ReposData?> {
            return arrayOfNulls(size)
        }
    }
}