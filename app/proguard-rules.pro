### Generic ###
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes Exceptions
-keepattributes SourceFile,LineNumberTable
-dontwarn javax.annotation.**

### Kotlin ###
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

### Moshi ###
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

### OkHttp3 ###
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn okhttp3.internal.platform.ConscryptPlatform
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.**

### Okio ###
-dontwarn okio.**

### Retrofit ###
-dontwarn retrofit2.Platform$Java8
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
