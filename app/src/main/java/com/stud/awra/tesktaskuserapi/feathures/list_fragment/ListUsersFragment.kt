package com.stud.awra.tesktaskuserapi.feathures.list_fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.transition.MaterialElevationScale
import com.stud.awra.tesktaskuserapi.*
import com.stud.awra.tesktaskuserapi.model_data.User
import com.stud.awra.tesktaskuserapi.view_models.UsersViewModel

class ListUsersFragment : Fragment(R.layout.fragment_list_users) {
    private lateinit var viewModel: UsersViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val clickListener = object : UserClickListener {
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
    private val usersAdapter = UsersAdapter(clickListener)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.usersLiveData.observe(viewLifecycleOwner, { usersAdapter.submitList(it) })
        viewModel.errorLiveData.observe(viewLifecycleOwner,
            { it?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() } })
        viewModel.updatingLiveData.observe(viewLifecycleOwner,
            { swipeRefreshLayout.isRefreshing = it })
        view.findViewById<RecyclerView>(R.id.rv_user_list).adapter = usersAdapter
        swipeRefreshLayout = view.findViewById(R.id.srl_user_list)
        swipeRefreshLayout.setOnRefreshListener { viewModel.update() }
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

    }
}
