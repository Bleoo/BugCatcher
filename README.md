# BugCatcher
配合 [bug-catcher-server](https://github.com/Bleoo/bug-catcher-server) 使用。用于捕捉非必显BUG。

## Usage

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    compile 'com.github.Bleoo:BugCatcher:1.0.1'
}
```


```java
BugCatcher.init(new BugCatcher.Config()
        .context(this)
        .baseUrl("http://10.0.1.89:3000")
        .debug(true));

BugTrigger bugTrigger = new BugTrigger("#103", new BugTrigger.onTriggerListener() {
        @Override
        public String onActivated() {
            return "android 测试1";
        }
    });

// if need activate
BugCatcher.activate(bugTrigger);
```