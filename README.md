# liveDataCallAdapter [![](https://jitpack.io/v/Tobeyr1/liveDataCallAdapter.svg)](https://jitpack.io/#Tobeyr1/liveDataCallAdapter)
---------------------------
**liveDataCallAdapter is use for java retrofit +LiveData**

# Quick Setup
**Add it in your root build.gradle at the end of repositories:**

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Then add the dependency:**
```java
dependencies {
	       implementation 'com.github.Tobeyr1:liveDataCallAdapter:1.0.2'
	}
```
# Basic Usage
**Add to retrofit first**
```kotin
 Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttp())
		addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create()) //add this line to you httpService
                .addConverterFactory(GsonConverterFactory.create())
                .build()
```
**add on api:**

```kotlin
    @GET("banner")
    fun getBanner(@Query("type")type:Int): LiveData<ApiResponse<xxx>>
```
详细使用也可以参考博文liveDataCallAdapter库 用来实现Retrofit+LiveData具有生命周期的网络请求](https://blog.csdn.net/Tobey_r1/article/details/125674337?spm=1001.2014.3001.5501)
