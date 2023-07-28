/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.yumemi.android.codecheck.model.Item
import jp.co.yumemi.android.codecheck.R
import jp.co.yumemi.android.codecheck.model.RepoItemAdapter
import jp.co.yumemi.android.codecheck.api.RepoSearchViewModel
import jp.co.yumemi.android.codecheck.databinding.RepoSearchFragmentBinding

class RepoSearchFragment : Fragment(R.layout.repo_search_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = RepoSearchFragmentBinding.bind(view)
        val viewModel = RepoSearchViewModel()

        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        val adapter = RepoItemAdapter(object : RepoItemAdapter.OnItemClickListener {
            override fun itemClick(item: Item) {
                gotoRepoViewFragment(item)
            }
        })

        binding.searchInputText
            .setOnEditorActionListener { editText, action, _ ->
                viewModel.onEditorActionListener(editText, action, adapter)
            }

        binding.recyclerView.also {
            it.layoutManager = layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter = adapter
        }
    }

    fun gotoRepoViewFragment(item: Item) {
        val actionGotoRepoView =
            RepoSearchFragmentDirections.actionRepoSearchFragmentToRepoViewFragment(item = item)
        findNavController().navigate(actionGotoRepoView)
    }
}
