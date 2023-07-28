/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck.viewmodel

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.codecheck.MainActivity.Companion.lastSearchDate
import jp.co.yumemi.android.codecheck.api.RepoSearch
import jp.co.yumemi.android.codecheck.model.Item
import jp.co.yumemi.android.codecheck.model.RepoItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Repository 検索の ViewModel
 */
class RepoSearchViewModel : ViewModel() {
    /**
     * EditorActionListener の処理
     * @param editText 検索文字列入力欄
     * @param action EditorInfo の action
     * @param adapter RecyclerView の Adapter
     * @return Boolean
     */
    fun onEditorActionListener(editText: TextView, action: Int, adapter: RepoItemAdapter): Boolean {
        if (action != EditorInfo.IME_ACTION_SEARCH) {
            return false
        }

        // 検索結果を RecyclerView に反映
        editText.text.toString().let {
            searchResults(it).apply {
                adapter.submitList(this)
            }
        }
        return true
    }

    /**
     * 検索結果を Item のリストで返す
     * @param inputText 検索する文字列
     * @return 検索結果 Item のリスト
     */
    private fun searchResults(inputText: String): List<Item> = runBlocking {
        val repoSearch = RepoSearch()
        lastSearchDate = Date()

        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            repoSearch.results(inputText)
        }
    }
}
