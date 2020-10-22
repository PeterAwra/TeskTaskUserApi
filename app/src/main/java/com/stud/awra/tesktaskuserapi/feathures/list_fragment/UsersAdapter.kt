package com.stud.awra.tesktaskuserapi.feathures.list_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.stud.awra.tesktaskuserapi.databinding.UserItemViewBinding
import com.stud.awra.tesktaskuserapi.feathures.list_fragment.UserClickListener
import com.stud.awra.tesktaskuserapi.feathures.list_fragment.UserItemHolder
import com.stud.awra.tesktaskuserapi.model_data.User

class UsersAdapter(private val clickListener: UserClickListener) :
    PagedListAdapter<User, UserItemHolder>(User.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemHolder {
        return UserItemHolder(
            UserItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: UserItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}