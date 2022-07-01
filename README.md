[![](https://jitpack.io/v/hyy-tool/Tool.svg)](https://jitpack.io/#hyy-tool/Tool)

# [HYYTool](https://github.com/hyy-tool/Tool)

>个人使用工具类集合



---



# 如何使用它


##  1.先在 build.gradle(Project:XXXX) 的 repositories 添加:

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

##  2.然后在 build.gradle(Module:app) 的 dependencies 添加:

```gradle
dependencies {
   implementation 'com.github.hyy-tool:Tool:0.0.3'

  
}
```

##  3.在Application中初始化


```java
HyTool.init(this);
```

# API使用文档

-  [点我看文档](https://github.com/hyy-tool/Tool/wiki/Tool)



