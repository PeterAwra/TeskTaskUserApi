package com.stud.awra.tesktaskuserapi.model_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListUsersData {
    @SerializedName("page")
    @Expose
    var page = 0

    @SerializedName("per_page")
    @Expose
    var perPage = 0

    @SerializedName("total")
    @Expose
    var total = 0

    @SerializedName("total_pages")
    @Expose
    var totalPages = 0

    @SerializedName("data")
    @Expose
    var data: List<User>? = null

    @SerializedName("ad")
    @Expose
    var ad: Ad? = null
}