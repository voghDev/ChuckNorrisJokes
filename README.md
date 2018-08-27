# ChuckNorrisJokes
Sample project to render data from http://chucknorris.io/ 

Used to practice Test Driven Development in Kotlin. 

![Kotlin][kotlinLogo]

- Used in the following workshops / live codings:
  - "Effective TDD on Android with Kotlin" @ GDG DevFestGRX 2017 - [Video][gdgVideo], [Slides][gdgSlides]
  - "Effective TDD on Android with Kotlin" @ MADGEvents. Madrid (Spain) -  Video available soon, [Slides][madgSlides]

# Demo

![Screenshot][appScreenshot] 

*2018*

It's been a great year. 

Android ecosystem has improved, Kotlin has improved, a lot of valuable resources (online courses, books) have been released, compilation times have been noticeably reduced, and an awesome set of tools have been developed to make our lives a bit happier. 

One of these tools is [arrow-kt](https://github.com/arrow-kt/arrow), a functional companion to Kotlin standard library. So the codebase for this entire project has been refactored to [arrow-kt](https://github.com/arrow-kt/arrow), replacing the good-old homemade `Disjunction` using a `Pair` of values.

[appScreenshot]: ./screenshots/app.gif
[gdgVideo]: https://youtu.be/WLM5hw3ndP0
[gdgSlides]: https://github.com/voghDev/ChuckNorrisJokes/tree/master/slides/devfest.pdf
[madgSlides]: https://github.com/voghDev/ChuckNorrisJokes/tree/master/slides/madg.pdf
[kotlinLogo]: ./img/kotlin.png
