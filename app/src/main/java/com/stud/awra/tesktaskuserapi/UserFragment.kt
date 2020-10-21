package com.stud.awra.tesktaskuserapi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.stud.awra.tesktaskuserapi.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private val userArg by navArgs<UserFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUserBinding.inflate(inflater, container, false)
            .also { it.user = userArg.user }
            .root
    }
}