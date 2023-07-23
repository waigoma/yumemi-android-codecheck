# 分からない言葉やエラー解決のメモ
ここには、何に躓いたのか、どう解決したのか、何を調べたのかをメモしていく。

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
