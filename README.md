### Moviez
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwj6SEBhAOEiwAvFRuKKXAVffTbMzF5R2cZYPvXRCR-cnlfgjncIxXBK59y9m86KftO_aqdBoCoS0QAvD_BwE&gclsrc=aw.ds)
- [MVVM Architecture & ViewModel](https://developer.android.com/jetpack/guide?gclid=CjwKCAjwj6SEBhAOEiwAvFRuKHuHt1pTUyslihJ4oyClRc4S3v-ZD24xObP0sz972zFaZGvGcAaleBoCIJMQAvD_BwE&gclsrc=aw.ds)
- [Live data](https://developer.android.com/topic/libraries/architecture/livedata)
- [Navigation & Paging](https://developer.android.com/guide/navigation#:~:text=Navigation%20refers%20to%20the%20interactions,bars%20and%20the%20navigation%20drawer.)
- [Retrofit](https://square.github.io/retrofit/)
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Glide](https://github.com/bumptech/glide)
- [SmoothBottomBar](https://github.com/ibrahimsn98/SmoothBottomBar)

### UI
### **Home page** 
  - contains the search bar to query movies by keywords
  - contains available category listing
    - can be clicked to load movie list of selected category 
  - movie listing 
    - Contains 
      - Title
      - Rating 
      - Image
    - each card in cardview is clickable 
      - will be directed to Details page once clicked for more details
  
<img src = "app%20screenshot/home.png" width ="300" /> <img src = "app%20screenshot/home_search.png" width ="300" /> 

### **Filter page** 
  - contains drop down filterby options
  - movie listing 
    - scrollable
    - pull to refresh 
    - Contains 
      - Title
      - Rating 
      - Image
    - each card in cardview is clickable 
      - will be directed to Details page once clicked for more details

<img src = "app%20screenshot/filter.png" width ="300" /> <img src = "app%20screenshot/filter_selection.png" width ="300" /> 

### **Details page** 
  - contains details of selected movies
    - scrollable  
    - Contains 
      - Image
      - Title
      - Rating 
      - Runtime
      - Category
      - Languages
      - Synopsis
  - contains Book Tickets icon beside Image 
    - will redirect to Booking Page

<img src = "app%20screenshot/details.png" width ="300" /> <img src = "app%20screenshot/details_scrolled.png" width ="300" /> 

### **Booking page** 
  - contains webview of [link](https://www.cathaycineplexes.com.sg/)

<img src = "app%20screenshot/booking.png" width ="300" />
