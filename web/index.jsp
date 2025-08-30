<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Адаптивная верстка -->
    <title>Стартовая страница</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<div class="container">
    <h1>Добро пожаловать в наше теннисное приложение!</h1>
    <a href="/new-match" class="main-button">Создать новый матч</a>
    <a href="/matches" class="main-button">Посмотреь список матчей</a>

</div>

</body>
</html>