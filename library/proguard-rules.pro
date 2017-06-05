-keep public class com.hkm.ezwebview.webviewclients.HackContentClient.LoadListener
-keep public class * implements com.hkm.ezwebview.webviewclients.HackContentClient.LoadListener
-keepclassmembers class * implements com.hkm.ezwebview.webviewclients.HackContentClient.LoadListener {
    public *;
}
-keepclassmembers public interface com.hkm.ezwebview.webviewclients.HackContentClient.LoadListener {
    public *;
}

-keep public class com.hkm.ezwebview.** { public *; }
-keep public interface com.hkm.ezwebview.** { public *; }

# for inheritance of base fragment classes
-keep public class com.hkm.ezwebview.app.** { *; }
# for inheritance of clients
-keep public class com.hkm.ezwebview.webviewclients.** { *; }