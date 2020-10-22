package com.stud.awra.tesktaskuserapi

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stud.awra.tesktaskuserapi.model_data.User

@BindingAdapter("android:imageUri")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)
        .load(Uri.parse(url))
        .into(view)
}

fun fakeUser(count: Int) = List(count) {
    User().apply {
        id = it
        firstName = "Name $it"
        lastName = "Surname $it"
        email = "Email $it"
        avatar =
            "https://lh3.googleusercontent.com/BQUYd1Th9Z_XI5wtklPQDHmiNkSOzBakOnpk-Ni8CBTyHb0E7UM5LpyjRW9BWs4fUuVD=s180-rw"
    }
}

fun String?.log() {
    Log.d("logger", this?:"null")
}