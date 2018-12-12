## Reactive kotlin

Данный проект демонстрирует построение простого веб-приложения, построенного с помощью 
`Spring 5` в реактивном стиле, и написанного на языке Kotlin.

Включены следующие функции:

* [Поддержку фрэймворком `Spring` 5 языка Котлина](https://spring.io/blog/2017/01/04/introducing-kotlin-support-in-spring-framework-5-0);
* [Gradle Kotlin DSL](https://github.com/gradle/kotlin-dsl)
* Null-safety Spring, Reactor API;
* `WebFlux Reactive` веб-сервер и клиент;
* Функциональные `RESTful API` с `Spring Webflux` спецификой `RouterFunction`;
* Kotlin предпочел  `BeanDefinitionDSL` для объявления  бинов в стиле DSL;
* [`Junit 5` `@BeforeAll` и `@AfterAll`](https://github.com/sdeleuze/spring-kotlin-functional/blob/spring_boot/src/test/kotlin/functional/IntegrationTests.kt) 
на нестатических методах.
