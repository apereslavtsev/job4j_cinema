# Описание проекта
Данный проект представляет собой сервис по покупке билетов в кинотеатр.  
На главной странице присутствуют ссылки на список фильмов, и список сеансов.  
На странице списка фильмов можно подробнее узнать какие фильмы идут в прокате.  
На странице сеансов можно узнать расписание показа.  
При выборе сеанса пользователь попадет на страницу с покупкой билета, где можно выбрать места и купить билет.  
Пользователь может купить билет на сеанс только при условии, что выбранные им места не заняты.  
Приобрести билет могут только авторизованные пользователи - для этого нужно зарегистрироваться, а затем пройти авторизацию.
# Стек технологий
- Java 17
- SpringBoot 2.7.6
  - Junit
  - AssertJ
  - Thymeleaf
  - Mockito
- Log4j 1.2.17
- Slf4j 1.7.30
- Sql2o 1.6.0
- H2database 2.1.214
- Bootstrap 5.2.3
- PostgreSQL 14
- Checkstyle-plugin 3.1.2
- Liquibase 4.15.0
# Требования к окружению
- Java 17
- Maven 3.8
- PostgreSQL 14
# Запуск проекта
1. В PostgreSQL создать базу данных cinema ```jdbc:postgresql://127.0.0.1:5432/cinema```
2. Собрать jar файл с помощью ```mvn install```
3. Запустить приложение с помощью собранного jar-файла ```java -jar target/job4j_cinema-1.0.jar```
4. Перейти по адресу ```http://localhost:8080/index```
# Взаимодействие с приложением

### Главная страница
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/index.PNG)

### Страница со списком фильмов
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/films.PNG)

### Страница со списком сеансов
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/filmSessions.PNG)

### Страница с информацией о сеансе, и выборе места
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/byTicket.PNG)

### Страница успешной покупки билета
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/succefulPurchase.PNG)

### Страница неуспешной покупки билета
![](https://github.com/apereslavtsev/job4j_cinema/blob/master/img/unsuccefulPurchase.PNG)
