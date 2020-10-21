package com.stud.awra.tesktaskuserapi

import android.view.View
import com.stud.awra.tesktaskuserapi.model_data.User

interface UserClickListener {
    fun onClick(view: View, user: User)
}