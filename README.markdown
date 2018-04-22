# Реализация REST API по работе со счетами

Как запустить?

1. Создать базу данных и выполнить в ней запросы из **migrations.txt**
2. Чтобы запустить app нужно выполнить main метод в классе ru.moysklad.startup.RestestSpringStartup
поставив в опциях виртульной машины поля из **restest.properties** 

Например: 

    -Dhttp.port=8092
    -Ddb.dialect=org.hibernate.dialect.PostgreSQLDialect
    -Ddb.driver=org.postgresql.Driver
    -Ddb.username=postgres
    -Ddb.password=123456
    -Ddb.url=jdbc:postgresql://localhost:5432/restest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull

Возможные ответы :
1. Sorry, there is no such page!
2. There is no such id!
3. Such id already exist!
4. Success!
5. There is no money!

###Заметка 
Вся основная логика в ru.moysklad.service.BankService