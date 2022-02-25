# compile as the windows application
```
flutter config --enable-windows-desktop
```
開啟後就能用flutter create製作專案。不過在windows上如果有用到額外的library，就會需要把symlink功能打開。這個功能只有在windows的開發者模式才會有。因此要先把設定打開():
```
 start ms-settings:developers
```
跳出GUI以後，就把開發者模式開關打開就行了。  
要編譯成windows程式的話，就使用:
```
flutter run -d windows
```
編譯後的程式放在 **\build\windows\runner\Debug** 底下。

# 讀檔案所需之lib設定
修改dependency的yml檔(pubspec.yaml)，在dependency的section加上:
```yml
dependencies:
  flutter:
    sdk: flutter

  path_provider: ^1.6.11
```
再透過flutter把lib抓下:
```
flutter pub get
```

# 其他坑
因為讀寫檔會涉及權限問題，所以需要把寫的權限放寬。編譯的時候要加上:
```
flutter run --no-sound-null-safety -d windows
```