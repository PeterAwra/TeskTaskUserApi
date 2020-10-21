package com.stud.awra.tesktaskuserapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stud.awra.tesktaskuserapi.databinding.UserItemViewBinding
import com.stud.awra.tesktaskuserapi.model_data.User

class UsersAdapter(val users: List<User>, val clickListener: UserClickListener) :
    RecyclerView.Adapter<UserItemHolder>() {
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
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}