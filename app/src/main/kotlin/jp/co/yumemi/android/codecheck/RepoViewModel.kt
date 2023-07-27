/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import jp.co.yumemi.android.codecheck.MainActivity.Companion.lastSearchDate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.util.*

/**
 * RepoViewFragment で使う
 */
class RepoViewModel(
    val context: Context,
) : ViewModel() {

    // 検索結果
    fun searchResults(inputText: String): List<Item> = runBlocking {
        val client = HttpClient(Android)

        return@runBlocking GlobalScope.async {
            val response: HttpResponse = client?.get("https://api.github.com/search/repositories") {
                header("Accept", "application/vnd.github.v3+json")
                parameter("q", inputText)
            }

            val jsonBody = JSONObject(response.receive<String>())
            val jsonItems = jsonBody.optJSONArray("items")
            val items = mutableListOf<Item>()

            // 検索結果がない場合
            if (jsonItems == null) {
                items.add(
                    Item(
                        name = "検索結果がありません",
                        ownerIconUrl = "",
                        language = "",
                        stargazersCount = 0,
                        watchersCount = 0,
                        forksCount = 0,
                        openIssuesCount = 0,
                    ),
                )
                return@async items.toList()
            }

            // 検索結果がある場合
            for (i in 0 until jsonItems.length()) {
                val jsonItem = jsonItems.optJSONObject(i)
                val name = jsonItem.optString("full_name")
                val ownerIconUrl = jsonItem.optJSONObject("owner")?.optString("avatar_url") ?: "avatar_url がありません"
                val language = jsonItem.optString("language")
                val stargazersCount = jsonItem.optLong("stargazers_count")
                val watchersCount = jsonItem.optLong("watchers_count")
                val forksCount = jsonItem.optLong("forks_conut")
                val openIssuesCount = jsonItem.optLong("open_issues_count")

                items.add(
                    Item(
                        name = name,
                        ownerIconUrl = ownerIconUrl,
                        language = context.getString(R.string.written_language, language),
                        stargazersCount = stargazersCount,
                        watchersCount = watchersCount,
                        forksCount = forksCount,
                        openIssuesCount = openIssuesCount,
                    ),
                )
            }

            lastSearchDate = Date()

            return@async items.toList()
        }.await()
    }
}

@Parcelize
data class Item(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable