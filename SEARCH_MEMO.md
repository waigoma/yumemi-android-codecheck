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
https://qiita.com/blendthink/items/aa70b8b3106fb4e3555f

## branch の運用
https://qiita.com/c6tower/items/fe2aa4ecb78bef69928f

## ktlint の導入
https://pinterest.github.io/ktlint/0.50.0/
https://qiita.com/takusemba/items/04c9ad7d28c4b91dc1a4
https://qiita.com/hkusu/items/f1c55a0e0d03543b24d5
https://atsum.in/android/ktlint/
