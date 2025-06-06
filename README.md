﻿# AvitoPlayer

## Описание проекта
- **Язык программирования**: Kotlin
- **Работа с сетью**: Retrofit, OkHttp
- **Многопоточность**: Kotlin Coroutines, Flow
- **UI**: Jetpack Compose
- **Сериализация**: Kotlinx Serialization
- **Навигация**: Jetpack Navigation
- **Архитектура**: MVVM
- **Локальная база данных**: Room

Приложение включает в себя пример работы с REST API, локальным кэшированием данных, декларативным UI и асинхронной обработкой операций.

### Настройка проекта

1. Склонируйте репозиторий:

   ```bash
   git clone https://github.com/KorotaevaVasilisa/AvitoPlayer
   ```
2. Откройте проект в Android Studio.
3. Синхронизируйте проект с Gradle, нажав на кнопку **Sync Project with Gradle Files**.
4. Соберите и запустите приложение на эмуляторе или физическом устройстве с помощью команды:

   ```bash
   ./gradlew installDebug
   ```

### Структура проекта

- **data**: Содержит модели данных, сетевые запросы (Retrofit), локальную базу данных (Room) и репозитории.
- **presentation**: Реализация интерфейса с использованием Jetpack Compose и Jetpack Navigation.
- **domain**: Бизнес логика

## Проблемы и решения

### Проблема: Не удалось реализовать Foreground Service и MediaSessionService

**Описание проблемы**: Попытка интеграции сервисов для фонового воспроизведения медиа столкнулась с трудностями. Основные проблемы:

- Ошибки при настройке уведомлений для Foreground Service.
- Неправильная интеграция MediaSessionService с Dagger Hilt.
