![App Screenshot](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/cama_read_me_banner.png)

# CAMA - Clean Architecture Movie Application

<img src="https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/CAMA%20(2).png" align="left"
width="100" hspace="10" vspace="10">

The CAMA application has a completely compose UI and has been developed in accordance with clean architecture principles.<br> It is a movie listing application developed using TMDb API. You can list movies, search, go to their details, save them to your favorite and watch list, see their comments, scores and watch their trailers.</br></br>

## 🏗 Tech Stacks and Whys 
* [MVVM with Clean Architecture](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture) – Helps structure the app into layers for better separation of concerns, testability, and maintainability.
* [Coroutines & Flow](https://developer.android.com/kotlin/flow) – Enables efficient asynchronous programming and reactive data handling in a concise and readable way.
* [Hilt for Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android) – Simplifies dependency injection with built-in Android lifecycle awareness and less boilerplate.
* [Jetpack Compose](https://developer.android.com/compose) – Offers a modern, declarative UI toolkit that speeds up UI development with less code.
* [Jetpack Compose Navigation](https://developer.android.com/develop/ui/compose/navigation – Manages screen-to-screen navigation in a clean, structured, and type-safe manner.
* [Room Database](https://developer.android.com/training/data-storage/room) – Provides an abstraction over SQLite for easier and safer local data persistence. 
* [Coil](https://github.com/coil-kt/coil) – A fast, lightweight image loading library built for Kotlin and Jetpack Compose.
* [Retrofit](https://square.github.io/retrofit) – Simplifies network requests and API integration with a clean and modular HTTP client.
* [Moshi](https://github.com/square/moshi) – Efficiently parses JSON data to Kotlin objects and integrates seamlessly with Retrofit.
* [Lottie](https://github.com/LottieFiles/lottie-android) – Renders high-quality animations from JSON, enhancing user experience with minimal performance cost.
* [Paging 3](https://proandroiddev.com/paging-3-easier-way-to-pagination-part-1-584cad1f4f61) – Handles large datasets and infinite scrolling efficiently with built-in support for remote and local sources.
* [Youtube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player) – Embeds and controls YouTube videos in the app with customizable and easy-to-use components.
* [TMDb API](https://developers.themoviedb.org/3) – Supplies comprehensive movie and TV show data through a well-documented public API.</br></br>

## 📷 Light Theme & English Screnshoots

![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/1.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/3.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/5.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/7.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/9.png)</br></br>

## 📷 Dark Theme & Turkish Screnshoots

![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/2.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/4.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/6.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/8.png)
![](https://github.com/usarican/Clean-Architecture-Movie-Application/blob/main/ScreenShoots/10.png)</br></br>

## 🏅 Contributors 👏
* [icanerdogan](https://github.com/icanerdogan)

## 🤝 How to Contribute
Contributions are welcome! Whether it's a bug fix, feature suggestion, or code improvement, feel free to get involved.

## 🚀 Getting Started
1. **Fork the Project**
Click the Fork button at the top right of this repo to create your own copy.

2. **Clone Your Fork Locally**<br>
```bash
git clone https://github.com/usarican/Clean-Architecture-Movie-Application.git
```
3. **Add Your TMDb Token** <br>
To run the project locally, you need a TMDb API token.</br>
Create a local.properties file in the root directory if it doesn't exist, and add the following:

```ini
TMDB_AUTH_TOKEN=your_tmdb_token_here
```
> ⚠️ **Important:** Keep your token private and **never commit it** to the repository.

4. **Create a New Branch**<br>
```bash
git checkout -b feature/your-feature-name
```

5. **Make Your Changes**<br>
Make sure your changes follow the existing code style and conventions.

6. **Commit and Push**<br>
```bash
git commit -m "Add: your feature/fix description"
git push origin feature/your-feature-name
```

7. **Open a Pull Request**
Go to your forked repo on GitHub and click "Compare & pull request".
Fill out the details and submit your PR. I'll review it as soon as possible!</br></br>

## Roadmap & Task List
- [ ] Migrate project to Multi - Module Architecture
- [ ] Write Unit Test, Instrumented Tests and UI Tests
- [ ] Convert Retrofit to Ktor and Dagger/Hilt to Coin
- [ ] Run the project in KMM
- [ ] Create Native iOS App with SwiftUI using KMM

## Did you find the repository useful?
### Don't forget to give a star ⭐ 

## LICENSE
```
MIT License

Copyright (c) 2025 usarican (Ibrahim Utku Sarican)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


