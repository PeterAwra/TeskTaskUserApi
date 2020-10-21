package com.stud.awra.tesktaskuserapi.model_data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User() : Parcelable {
    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null

    val fullName: String get() = "$firstName $lastName"

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        email = parcel.readString()
        firstName = parcel.readString()
        lastName = parcel.readString()
        avatar = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(email)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}