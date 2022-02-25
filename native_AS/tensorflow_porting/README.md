The keys of using tensorflow lite in Android
==

There are 2 important points needs to be concerned. After setting in the Gradle, the TF-lite would works.

1. To rebuild the Tensorflow, including the .arr or .so, is not so essential. But if there is no aar or so of tf-lite, the correct setting of Gradle will become important.
2. The tf-lite setting will focus on the build.gradle at APP level, but not the project level. (build.gradle會有兩個，一個在app level，一個在project level). 

設定完Gradle後，開啟asset資料夾。開啟方式為: 
```
project欄位的app點右鍵 > 最底下有一堆小綠人的區域，點folder > asset
```
把模型檔放在這資料夾以後，就可以用assetManager來管理裡面的檔案。(用法在TensorflowImageClassifier.java中)

原始參考的source code : <p>
https://github.com/amitshekhariitbhu/Android-TensorFlow-Lite-Example