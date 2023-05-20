# job4j_cinema
Проект "Кинотеатр" 

Spring MVC и Spring Boot как основной фреймворк.
Для отображения клиентского интерфейса используется Thymeleaf/Bootstrap

В системе существуют следующие модели:
Фильмы, Постеры фильмов, Сеансы фильмов, Жанры, Кинозалы, Билеты, Пользователи.

Все данные, кроме билетов и пользователей, заполняются через insert напрямую в базу данных.
 
Пользователь через web интерфейс может зарегистрироваться, 
посмотреть список фильмов,
посмотреть список сеансов фильмов,
выбрать сеанс, 
посмотреть детальную информацию о фильме и постер, 
выбрать ряд, место, и купить билет.