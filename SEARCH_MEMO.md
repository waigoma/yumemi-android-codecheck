# 分からない言葉やエラー解決のメモ
ここには、何に躓いたのか、どう解決したのか、何を調べたのかをメモしていく。

# エラーと解決

## clone で出くわしたエラー
workflow の yml ファイルがあると権限がないと言われ、push できなかった。  
ssh key を追加して、push --mirror git@~ のように、URL ではなく ssh を使うことで解決。  
https://qiita.com/masato_makino/items/93c4429cdb4d54c007c7


## Android Studio でプロジェクトを開いたときに出くわしたエラー
`No matching variant of com.android.tools.build:gradle:8.0.2 was found.`  
File > Setting > Build, Execution, Deployment > Build Tools > Gradle の JDK のバージョンを上げることで解決。  
https://qiita.com/kotlinObasan/items/0193f7676a97071b5f2a

## ktlint 導入時のエラー
`./gradlew ktlint` を実行しようとすると、以下のエラーが出た。
このエラーは JDK のバージョンが低いと出るらしい。
```
No matching variant of com.android.tools.build:gradle:8.0.2 was found. The consumer was configured to find a library for use during runtime, compatible with Java 8, packaged as a jar, and its dependencies declared externally, as well as attribute 'org.gradl e.plugin.api-version' with value '8.0' but:
```

しかし、Build Tools > Gradle の JDK のバージョンを上げても解決しなかった。
ここで、 `./gradlew -v` を実行すると JDK のバージョンが 1.8 となっていた。

これは、JAVA_HOME のパスが JDK 1.8 となっていたためであった。  
なので、JAVA_HOME のパスを Android Studio の JDK と同じパスを通すことで解決した。  

## ビルド時 Unresolved reference: item
item クラスを Item と名前を変更した際、android studio の refactor 機能を信じ切っていたらエラー。  
res > navigation > nav_graph.xml にある item を Item に変更する必要があり、変更したら解決した。  
https://stackoverflow.com/questions/64221874/unresolved-references-in-automatically-generated-files-android-studio-kotlin

## fragment の名前と navigation の名前変えたらエラー
fragment ファイルの名前を変更し、それに対応する kt ファイル内のクラス名と、navigation の名前を変更したらエラー。  
どうしてエラーが出て直ったのかは不明だが、 build clean して、 redo して、もう一回名前変更したら直った。


# 参考
* https://qiita.com/blendthink/items/aa70b8b3106fb4e3555f

## branch の運用
* https://qiita.com/c6tower/items/fe2aa4ecb78bef69928f

### pull request の merge ミスを直す
* https://qiita.com/ponsuke0531/items/5e15f74f9d5dfca4e7b2

## pull request の運用
* https://applis.io/posts/how-to-write-git-pull-request
* https://notepm.jp/template/pull-request

## ktlint の導入
* https://pinterest.github.io/ktlint/0.50.0/
* https://qiita.com/takusemba/items/04c9ad7d28c4b91dc1a4
* https://qiita.com/hkusu/items/f1c55a0e0d03543b24d5
* https://atsum.in/android/ktlint/

### ktlint を GitHub Actions で実行する
* https://qiita.com/omix222/items/42cde8dfd83c32cfd4ca

## wildcard import を無効にする
* https://stackoverflow.com/questions/67341957/how-do-i-disable-wildcard-imports-for-all-kotlin-files-in-android-studio/67341958#67341958

## strings.xml の書式設定
* https://developer.android.com/guide/topics/resources/string-resource?hl=ja#formatting-strings

## アーキテクチャー設計
* https://www.wantedly.com/companies/progrit/post_articles/474355

## android coroutine
* https://developer.android.com/kotlin/coroutines?hl=ja

## dark mode
* https://qiita.com/aiko_han/items/c742c1a62cb38f9232c8

