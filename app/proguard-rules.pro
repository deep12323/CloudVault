# Proguard rules for CloudVault Android

# TDLib JNI classes, interfaces and native callbacks
-keep class org.drinkless.tdlib.** { *; }
-keep interface org.drinkless.tdlib.** { *; }
-keepclasseswithmembers class * {
    native <methods>;
}

# LibVLC Android
-keep class org.videolan.libvlc.** { *; }
-keep interface org.videolan.libvlc.** { *; }
-dontwarn org.videolan.libvlc.**

# AndroidX Media & WorkManager
-keep class androidx.media.** { *; }
-keep class androidx.work.** { *; }
-keep class androidx.work.impl.** { *; }

# App Models, Services, and Workers
-keep class com.cloudvault.app.VaultMediaItem { *; }
-keep class com.cloudvault.app.MediaType { *; }
-keep class com.cloudvault.app.TelegramAuthState* { *; }
-keep class com.cloudvault.app.AudioPlayerService { *; }
-keep class com.cloudvault.app.AutoBackupWorker { *; }

# Custom Views and constructors required by XML layouts
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Kotlin reflection and coroutines
-dontwarn kotlinx.coroutines.**
-keepattributes *Annotation*,InnerClasses,EnclosingMethod,Signature
