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

    <div>
        <form action="${pageContext.request.contextPath}/index" method="post">
            <div>
                <input class="input-style" type="text" id="firstPlayer" name="firstPlayer" required>
                <label class="label-style" for="firstPlayer">Имя первого игрока.</label>
            </div>
            <div>
                <input class="input-style" type="text" id="secondPlayer" name="secondPlayer" required>
                <label class="label-style" for="firstPlayer">Имя первого игрока.</label>
            </div>
            <div>
                <button class="main-button" type="submit">Начать новый матч между игроками.</button>
            </div>
        </form>
    </div>
    <div>
        <a href="/matches" class="main-button">Посмотреь список матчей</a>
    </div>

</div>

</body>
</html>