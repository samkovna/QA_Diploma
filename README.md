# Дипломный проект профессии "Тестировщик ПО". QA-56

## Документация
1. [Задание](https://github.com/netology-code/qa-diploma#readme)
2. [План автоматизации](https://github.com/samkovna/QA_Diploma/blob/main/documentation/Plan.md)
3. [Отчётные документы по итогам тестирования](https://github.com/samkovna/QA_Diploma/blob/main/documentation/Report.md)
4. [Отчётные документы по итогам автоматизации](https://github.com/samkovna/QA_Diploma/blob/main/documentation/Summary.md)

## Процедура запуска автотестов

**Предусловия**
1. Установлены приложения IntelliJ IDEA, Docker Desktop
2. Клонирован [репозиторий](https://github.com/samkovna/QA_Diploma.git)
3. Порты 8080, 9999, 5432, 3306 должны быть свободны

**Запуск приложения и автотестов**
1. Для поднятия вспомогательных сервисов выполнить в терминале команду `docker-compose up`
2. Запустить SUT в терминале
* для mySQL с помощью команды `java -jar aqa-shop.jar`
* для PostgreSQL с помощью команды `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres" -jar artifacts/aqa-shop.jar`
3. Проверить запущенное приложение по [адресу](http://localhost:8080)
4. Запустить автотесты в терминале 
* для mySQL с помощью команды `./gradlew clean test`
* для PostgreSQL с помощью команды `./gradlew clean test -DdbUrl=jdbc:postgresql://localhost:5432/postgres`

## Формирование отчета Allure
После завершения всех автотестов в терминале ввести команду `./gradlew allureServe`


