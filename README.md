
# CryptoApp
The application will work with `local` and `remote` data sources. And for the remote data source, a third-party API for food recipes, called [Spoonacular API](https://spoonacular.com/food-api) , was used. I am going to teach you how to establish a connection between your application and the server. So basically we will send a `GET request` to our `API`  and receive a list of food recipes as a response. Also, the application will not be able to handle any internet connection and store or cache the data locally in the database when needed.

Also from the application we can search recipes directly from our API. So if you have your favorite flavor, you can search for it here and you will probably find a lot of amazing recipes. It will also have Favorites Fragment where we can save our favorite recipes to use later when we need them. And finally the app will have its fun side, and that's a food joke snippet.
# Screen Shot
<img src="https://github.com/user-attachments/assets/d4d6c48f-b03a-4723-b766-b4b86fa82c2b" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="400" height="855" /> | <img src="https://github.com/user-attachments/assets/9215a10d-3942-48d4-84f2-42e58547a665" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="400" height="855" />

[crypto3.webm](https://github.com/user-attachments/assets/a0d74995-d9ca-42ab-92fb-4b3cf36e0c3f)


Kotlin Coroutines + Flow

LiveData

Single activity pattern

Offline Cache

REST API

Motion Layout

ViewPager2



Dependency injection

# Built with
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.

[DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers

[Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.

[Hilt](https://dagger.dev/hilt/)  - Dependency injection.

[ViewBinding](https://developer.android.com/topic/libraries/view-binding)  - View binding is a feature that allows you to more easily write code that interacts with views.

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture)  - A collections of libraries that help you design rebust, testable and maintainable apps.

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel)  - UI related data holder, lifecycle aware.

[Navigation component](https://developer.android.com/guide/navigation)  -  Fragment routing handler

[Coroutines](https://developer.android.com/kotlin/coroutines) - Concurrency design pattern for asynchronous programming.

[Flow](https://developer.android.com/kotlin/flow) - Stream of value that returns from suspend function.

[Coil](https://github.com/coil-kt/coil) - Image loading

# Architectures
![alt - Github](https://raw.githubusercontent.com/amitshekhariitbhu/MVVM-Architecture-Android/master/assets/mvvm-arch.png)

**View** : Activity/Fragment with UI-specific logics only.

**ViewModel** : It keeps the logic away from View layer, provides data streams for UI and handle user interactions

**Model** :  Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources. The local data sources will serve as single source of truth

# Contact
Have an project? DM me at

hossein.arabameri69@gmail.com
