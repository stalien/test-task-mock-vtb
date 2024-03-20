## Заглушка для обработки REST-запросов

Пример запроса:
```
{
    "name": "Иван",
    "age": 37,
    "mother": {
        "name": "Ольга",
        "age": 58
    },
    "children": [
        "Маша",
        "Игорь",
        "Таня"
    ],
    "married": true,
    "dog": null
}
```

Пример ответа:

```
{
    "name": "Иван",
    "age": 54,
    "mother": {
        "name": "Ольга",
        "age": 54
    },
    "children": [
        "Маша",
        "Игорь",
        "Таня"
    ],
    "married": true,
    "dog": null
}
```

Спецификация в формате OpenAPI лежит здесь [mock_openapi3_0.yaml](./mock_openapi3_0.yaml).

## Запуск приложения
1. Запустите spring boot приложение командой `./mvnw spring-boot:run`
2. Отправьте запрос с помощью утилиты командной строки curl. Тело запроса содержится в файле [request.json](./request.json).
   Выполните команду `curl -X POST -d "@request.json" -H "Content-Type: application/json" http://localhost:8080/mock`
   В ответ придет примерно такой JSON:
```
{
    "name": "Иван",
    "age": 54,
    "mother": {
        "name": "Ольга",
        "age": 54
    },
    "children": [
        "Маша",
        "Игорь",
        "Таня"
    ],
    "married": true,
    "dog": null
}
```

логика формирования ответа:
* Модуль принимает входные данные в виде POST-запросов на ручку `/mock` и отдает ответ (меняет значение любого `age` на 54).

## Настраиваемые параметры
в файле application.properties можно задать значение следующих параметров:

1. Задержка ответа в миллисекундах, значение по умолчанию 0.5 сек

   `mockResponse.delay=500`

2. Активация задержки, по умолчанию задержка выключена

   `mockResponse.delay.on=false`

3. Процент ошибок, значение по умолчанию 0% (в заданном проценте случаев сервис возвращает Status: 500 INTERNAL_SERVER_ERROR
   )

   `error.rate=0`

## Метрики

* в проекте настроена выгрузка метрик в формате Prometheus посредством Spring Actuator(Micrometer) http://localhost:8080/actuator/prometheus
