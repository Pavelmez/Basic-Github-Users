package com.example.submisionawal.ui.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submisionawal.adapter.FollowerAdapter
import com.example.submisionawal.adapter.FollowingAdapter
import com.example.submisionawal.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {

    private lateinit var binding: FragmentFollowBinding
    private lateinit var viewModel: FollowViewModel
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var followingAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FollowViewModel::class.java]

        initRecyclerView()

        val username = requireArguments().getString(ARG_USERNAME)
        val position = requireArguments().getInt(ARG_POSITION)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        username?.let {
            if (position == 1) {
                viewModel.getFollowingUser(it)
                binding.rvList2.adapter = followingAdapter
            } else {
                viewModel.getFollowerUser(it)
                binding.rvList2.adapter = followerAdapter
            }
        }

        binding.rvList2.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()
    }

    private fun initRecyclerView() {
        followerAdapter = FollowerAdapter()
        followingAdapter = FollowingAdapter()
    }

    private fun observeViewModel() {
        viewModel.followerList.observe(viewLifecycleOwner) { follower ->
            followerAdapter.submitList(follower)
        }

        viewModel.followingList.observe(viewLifecycleOwner) { following ->
            followingAdapter.submitList(following)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val ARG_USERNAME = "username"
        const val ARG_POSITION = "position"

        fun newInstance(username: String, position: Int): FollowFragment {
            val fragment = FollowFragment()
            val args = Bundle().apply {
                putString(ARG_USERNAME, username)
                putInt(ARG_POSITION, position)
            }
            fragment.arguments = args
            return fragment
        }
    }
}