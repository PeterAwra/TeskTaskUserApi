package com.stud.awra.tesktaskuserapi.model_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserData {
    @SerializedName("data")
    @Expose
    var data: User? = null

    @SerializedName("ad")
    @Expose
    var ad: Ad? = null
}