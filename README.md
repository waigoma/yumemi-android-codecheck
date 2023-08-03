# 株式会社ゆめみ Android エンジニアコードチェック課題

## 概要

本プロジェクトは株式会社ゆめみ（以下弊社）が、弊社に Android エンジニアを希望する方に出す課題のベースプロジェクトです。 
README には、セットアップ方法や開発環境、開発方法などが書いてあります。

その他、課題内容や取り組み、説明は別のファイルに書いてあります。  
こちらは目次を参考にしてください。

## Feedback
<details>
  
## 課題完成度
- [ ]：未達成
- [/]：一部達成
- [x]：達成

### 初級
- [x] 可読性の向上
- [/] 安全性の向上
- [/] バグの修正
- [x] Fat Fragment の回避

### 中級
- [x] 構造のリファクタリング
- [/] アーキテクチャの適用
- [ ] テストの追加

### ボーナス
- [ ] UIのブラッシュアップ
- [/] 機能の追加

## フィードバック
### 良かった点
- コードへのコメントやコミットメッセージ、isseuへのコメントなど他の人にも実装の意図や背景をわかりやすく伝えることを意識している点
- 役割ごとにディレクトリやファイルを分割できている点
- APIの呼び出し時にエラーハンドリングを行なっている点
- DarkThemeやGitHubActionsの利用など広く着手を行なっている点

### 機会点
- Androidでは画面を回転した時にActivityやFragmentが再生成される問題があります。そのため状態を保持しておくために、`val viewModel by *viewModels*<RepoSearchViewModel>()` のようにデリゲートを用いて取得することで再生成した場合でも共通のインスタンスを取得するようにします。現在の実装ではViewModelを直接インスタンス化しているため再生成に合わせてViewModelが作り直されてしまっているため注意が必要です（参考：https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-apis）
- 主にbuild.gradleなどでwarningされている箇所が多く見受けられます。可能な限り対応できると良さそうだなと思いました
- `RepoSearchFragment` と`RepoViewFragment` でbindingの記述方法が異なるのが気になりました。ViewModel然り、基本的に公式のドキュメント（https://developer.android.com/topic/libraries/view-binding#fragments）を参考にしながらプロジェクト内で記述を合わせることを意識できると良さそうです。
- 通信処理に関して、ローディング中であったりエラーであったりをユーザーに伝えることができるような構成になっていると良さそうだなと思いました。

### その他
- Androidは公式のドキュメントがとてもよくまとまっています。繰り返しにはなりますが、公式のドキュメントを参考にしながら開発や調査を行うことができると良さそうです（参考：https://developer.android.com/guide）。
- 合わせて、全体的に今回のフィードバックでは指摘をしていない以上の改善点が見受けられます。ChatGPT等のサービスを利用してコードの良し悪しの足がかりを探しても良いかもしれないなと思いました。
- またUdemyやCodelabsといったサービスを利用して体系的に0からアプリケーションを作りあげていくような学習をしていくこともおすすめです。
ーーーーーーーーーーーーーーーーーーーー
## 課題完成度
- [ ]：未達成
- [/]：一部達成
- [x]：達成

### 初級
- [x] 可読性の向上
- [x] 安全性の向上
- [/] バグの修正
- [x] Fat Fragment の回避

### 中級
- [/] 構造のリファクタリング
- [/] アーキテクチャの適用
- [ ] テストの追加

### ボーナス
- [ ] UIのブラッシュアップ
- [ ] 機能の追加

## フィードバック
### 良かった点
* Pull Request が丁寧でとてもわかりやすいです。変更の粒度も適切でした。作業の進め方がとても好印象でした。
* 機内モードなどネットワークエラー状態でもアプリがクラッシュしないように対策されていました。

### 機会点
* runBlocking が使われている箇所が見受けられたのですが、スレッドを止めてしまいコルーチンの良さが失われるため基本的に避けた方がよいです。（テストコード中ややむを得ない場合は使うこともありますが、ほとんどの場合は別の解決策があります。）

ViewModelでは viewModelScope.launch { } でコルーチンを起動して、データ層では suspend fun で扱うのがおすすめです。

https://developer.android.com/topic/architecture （言語の設定を英語にするとサンプルコード等が出てきます。）

このページ以外でもAndroidは公式サイトが充実しているので、学習に活かすことをおすすめします。

* RepoViewFragment の binding 変数はローカル変数に変更できそうです。Fragmentで持ってしまうと、onDestroyViewでViewが破棄された後も参照が残るので、リークを防ぐための実装が必要になります。

</details>


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

### viewmodel (画面の状態を管理する ViewModel)
* RepoSearchViewModel: リポジトリ検索画面の状態を管理する ViewModel

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
