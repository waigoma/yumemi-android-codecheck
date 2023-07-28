/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.codecheck

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 検索結果の Item
 * @param name リポジトリ名
 * @param ownerIconUrl アイコンの URL
 * @param language リポジトリの主要言語
 * @param stargazersCount Starred の数
 * @param watchersCount Watch の数
 * @param forksCount Fork の数
 * @param openIssuesCount Open Issue の数
 */
@Parcelize
data class Item(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable {
    companion object {
        const val NOTHING_ITEM_NAME = "検索結果がありません"
    }
}