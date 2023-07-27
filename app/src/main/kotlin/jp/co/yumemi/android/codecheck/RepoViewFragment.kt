/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.codecheck.MainActivity.Companion.lastSearchDate
import jp.co.yumemi.android.codecheck.databinding.RepoViewFragmentBinding

class RepoViewFragment : Fragment(R.layout.repo_view_fragment) {
    private val args: RepoViewFragmentArgs by navArgs()

    private lateinit var binding: RepoViewFragmentBinding
    private val _binding get() = binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RepoViewFragmentBinding.bind(view)

        // レポジトリ表示のテキストをセット
        val item = args.item

        _binding.ownerIconView.load(item.ownerIconUrl)
        _binding.nameView.text = item.name
        _binding.languageView.text = getString(R.string.written_language, item.language)
        _binding.starsView.text = getString(R.string.star_count, item.stargazersCount)
        _binding.watchersView.text = getString(R.string.watcher_count, item.watchersCount)
        _binding.forksView.text = getString(R.string.fork_count, item.forksCount)
        _binding.openIssuesView.text = getString(R.string.open_issue_count, item.openIssuesCount)

        Log.d("検索した日時", lastSearchDate.toString())
    }
}
