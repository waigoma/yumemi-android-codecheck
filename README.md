# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ（以下弊社）が、弊社に Android エンジニアを希望する方に出す課題のベースプロジェクトです。 
README には、セットアップ方法や開発環境、開発方法などが書いてあります。

その他、課題内容や取り組み、説明は別のファイルに書いてあります。  
こちらは目次を参考にしてください。


## 目次

- [コーディングテスト説明](CODINGTEST_OVERVIEW.md)
- [調べたメモ](SEARCH_MEMO.md)


## 開発環境
- IDE:          Android Studio Flamingo | 2022.2.1 Patch 2
- JVM:          17.0.6 (JetBrains s.r.o. 17.0.6+0-b2043.56-9586694)
- Gradle:       8.0
- Kotlin:       1.8.10
- minSdk:       23
- targetSdk:    31
- Groovy:       3.0.13
- Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
- OS:           Windows 10 10.0 amd64



## セットアップ方法
[株式会社ゆめみ/android-engineer-codecheck](https://github.com/yumemi-inc/android-engineer-codecheck) を clone して、自分のリポジトリに Duplicate する。  
git bash を clone するディレクトリで開き、以下のコマンドを実行する。  
```bash
# リポジトリをローカル環境に clone する
$ git clone --bare https://github.com/yumemi-inc/android-engineer-codecheck

# clone したリポジトリに移動する
$ cd android-engineer-codecheck.git
# 自分のリポジトリに mirror push する
$ git push --mirror git@github.com:waigoma/yumemi-android-codecheck.git

# clone したリポジトリを削除する
$ cd .. 
$ rm -rf android-engineer-codecheck.git

# 自分のリポジトリで作業するために clone する
$ git clone https://github.com/waigoma/yumemi-android-codecheck
```

## プロジェクトの構成
### api (API 通信を行うためのクラス)
* GitHub API を叩くコードが書かれている

### fragment (画面を構成する Fragment)
* RepoSearchFragment: リポジトリ検索画面 (初期画面)
* RepoDetailFragment: リポジトリ詳細画面

### model (データクラス)
* GitHub API から取得したデータを格納するデータクラスが書かれている


## 開発方法
1. `main` branch から開発の branch `develop` を切る。
2. `develop` branch から開発の branch `feature/xxx` を切る。 (branch の規則は以下参照)
3. `feature/xxx` branch で開発を行う。
4. 開発が終わったら、`feature/xxx` branch を `develop` branch に pull request する。
5. `develop` branch で動作確認を行い、問題なければ `main` branch に pull request する。
6. `main` branch で動作確認を行い、問題なければ `main` branch に merge する。

## branch の規則
- `feature/xxx` : 機能追加の branch
- `fix/xxx` : バグ修正の branch

## commit の規則
- 英語日本語指定なし
- 何をしたかをわかりやすく端的に記述する
- コミットの粒度をなるべく細かくする
