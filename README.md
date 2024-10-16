# CryptoApp
This project is a cryptocurrency dashboard that leverages the CoinGecko API to provide users with a comprehensive list of cryptocurrencies and their details. Users can view real-time data about various cryptocurrencies and convert between different cryptocurrencies seamlessly. The application offers an intuitive interface, making it easy to explore market trends and perform conversions efficiently.

For more information on the CoinGecko API, visit the [CoinGecko: Cryptocurrency Prices, Charts, and Crypto Market](https://www.coingecko.com/).

# Screen Shot
<div align="center">
    <img src="https://github.com/user-attachments/assets/d4d6c48f-b03a-4723-b766-b4b86fa82c2b" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
    <img src="https://github.com/user-attachments/assets/9215a10d-3942-48d4-84f2-42e58547a665" data-canonical-src="https://gyazo.com/eb5c5741b6a9a16c692170a41a49c858.png" width="300" height="600" />
</div>

[crypto3.webm](https://github.com/user-attachments/assets/b6e90fc9-8d60-45ce-ad5f-3d7dd554bb8e)

# Technologies

* MVI Architecture
* Channel Coroutines
* Kotlin Flow
* LiveData
* Pagination
* Single activity pattern
* REST API
* Dynamic Response
* Base Activity ,Fragment
* Dependency injection

# Built with
[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

[Font Calligraphy](https://github.com/InflationX/ViewPump) - View Inflation you can intercept.

[Chart](https://github.com/diogobernardino/williamchart) - Android Library to rapidly develop attractive and insightful charts in android applications.

[Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/) - RESTful API and networking client.

[Hilt](https://dagger.dev/hilt/) - Dependency injection.

[ViewBinding](https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that allows you to more easily write code that interacts with views.

[Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - A collections of libraries that help you design robust, testable, and maintainable apps.

[ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - UI related data holder, lifecycle aware.

[Navigation component](https://developer.android.com/guide/navigation) - Fragment routing handler.

[Coroutines](https://developer.android.com/kotlin/coroutines) - Concurrency design pattern for asynchronous programming.

[Flow](https://developer.android.com/kotlin/flow) - Stream of value that returns from suspend function.

[Coil](https://github.com/coil-kt/coil) - Image loading.

[DynamicSize](https://github.com/MrNouri/DynamicSizes) - New units of measure for support all of screen devices.

# Architectures
![1689111067509](https://github.com/user-attachments/assets/fb183b1a-99a0-4966-a120-cc8949673da2)

* **View**: View is the UI renderer, displaying the app’s state to the user without handling business logic. It updates based on the Model’s state changes.

* **Intent**: Represents user actions or the app itself, like button clicks or text input, it’s all about what the user wants to do in the app. The View catches these intentions and sends them to the Model, which then takes actions (like updating the app’s status)

* **Model**: Holds all the app’s data and logic, like a single source of truth. It’s never directly changed, but updated by creating new states.

# Contact
Have a project? DM me at

hossein.arabameri69@gmail.com

# Acknowledgments
Special thanks to [Mr Mohammad Nouri](https://github.com/MrNouri) for providing the course that helped me.

