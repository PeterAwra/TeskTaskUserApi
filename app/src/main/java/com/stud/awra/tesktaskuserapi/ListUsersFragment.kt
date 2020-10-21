package com.stud.awra.tesktaskuserapi

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialElevationScale
import com.stud.awra.tesktaskuserapi.model_data.User

class ListUsersFragment : Fragment(R.layout.fragment_list_users) {
    val clickListener = object : UserClickListener {
        override fun onClick(view: View, user: User) {
            val extras =
                FragmentNavigatorExtras(
                    view.findViewById<ImageView>(R.id.iv_icon_item_user) to getString(R.string.name_transition)
                )
            findNavController().navigate(
                ListUsersFragmentDirections.actionListUsersFragmentToUserFragment(user),
                extras
            )
            exitTransition = MaterialElevationScale(false).apply {
                duration = resources.getInteger(R.integer.transition_duration).toLong()
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = resources.getInteger(R.integer.transition_duration).toLong()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        view.findViewById<RecyclerView>(R.id.rv_user_list).adapter =
            UsersAdapter(fakeUser(20), clickListener)
    }
}
