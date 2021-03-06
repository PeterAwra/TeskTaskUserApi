package com.stud.awra.tesktaskuserapi.feathures.list_fragment

import androidx.recyclerview.widget.RecyclerView
import com.stud.awra.tesktaskuserapi.databinding.UserItemViewBinding
import com.stud.awra.tesktaskuserapi.feathures.list_fragment.UserClickListener
import com.stud.awra.tesktaskuserapi.model_data.User

class UserItemHolder(val viewBinding: UserItemViewBinding, clickListener: UserClickListener) :
    RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.clickListener = clickListener
    }

    fun bind(user: User?) {
        viewBinding.user = user
    }

}