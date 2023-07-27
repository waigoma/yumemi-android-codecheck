/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import jp.co.yumemi.android.codecheck.Item.Companion.NOTHING_ITEM_NAME
import jp.co.yumemi.android.codecheck.MainActivity.Companion.lastSearchDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Repository 検索の ViewModel
 */
class RepoSearchViewModel : ViewModel() {
    companion object {
        // GitHub API の URL
        private const val API_URL = "https://api.github.com/search/repositories"
        private const val HEADER_ACCEPT = "Accept"
        private const val HEADER_ACCEPT_VALUE = "application/vnd.github.v3+json"
        private const val PARAMETER_Q = "q"
    }

    // 検索結果がないときに表示する Item
    private val nothingItem = listOf(
        Item(
            name = NOTHING_ITEM_NAME,
            ownerIconUrl = "",
            language = "",
            stargazersCount = 0,
            watchersCount = 0,
            forksCount = 0,
            openIssuesCount = 0,
        ))

    /**
     * 検索結果を Item のリストで返す
     * @param inputText 検索する文字列
     * @return 検索結果 Item のリスト
     */
    fun searchResults(inputText: String): List<Item> = runBlocking {
        lastSearchDate = Date()

        if (inputText.isEmpty())
            return@runBlocking nothingItem

        // 検索結果を取得する
        val client = HttpClient(Android)
        viewModelScope.async(Dispatchers.IO) {
            try {
                val response: HttpResponse =
                    client.get(API_URL) {
                        header(HEADER_ACCEPT, HEADER_ACCEPT_VALUE)
                        parameter(PARAMETER_Q, inputText)
                    }

                return@async parseResponse(JSONObject(response.receive<String>()))
            } catch (e: Exception) {
                return@async nothingItem
            }
        }.await()
    }

    /**
     * 検索結果をパースし Item のリストにする
     * @param jsonBody 検索結果
     * @return 検索結果を Item のリストにしたもの
     */
    private fun parseResponse(jsonBody: JSONObject): List<Item> {
        val jsonItems = jsonBody.optJSONArray("items")
        val items = mutableListOf<Item>()

        if (jsonItems == null || jsonItems.length() == 0)
            return nothingItem

        // 検索結果から Item のリストを作成する
        jsonItems.forEach {
            val name = it.optString("full_name")
            val ownerIconUrl = it.optJSONObject("owner")?.optString("avatar_url") ?: "avatar_url がありません"
            val language = it.optString("language")
            val stargazersCount = it.optLong("stargazers_count")
            val watchersCount = it.optLong("watchers_count")
            val forksCount = it.optLong("forks_conut")
            val openIssuesCount = it.optLong("open_issues_count")

            items.add(
                Item(
                    name = name,
                    ownerIconUrl = ownerIconUrl,
                    language = language,
                    stargazersCount = stargazersCount,
                    watchersCount = watchersCount,
                    forksCount = forksCount,
                    openIssuesCount = openIssuesCount,
                ),
            )
        }

        return items.toList()
    }

    /**
     * JSONArray を forEach できるようにする
     */
    private fun JSONArray.forEach(action: (JSONObject) -> Unit) {
        for (i in 0 until length()) {
            action(getJSONObject(i))
        }
    }
}
