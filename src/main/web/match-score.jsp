<%@ page import="models.MatchProcessDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- Адаптивная верстка -->
    <title>Стартовая страница</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <!-- Подключение CSS -->
</head>
<body>

<div class="container">
    <h1>Табло текущего матча</h1>
    <%-- Табло матча --%>
    <% MatchProcessDto match = (MatchProcessDto) request.getAttribute("match"); %>
    <%!
        String convertedScore(int score1, int score2) {
            if (score1 > 3) {
                if (score1 <= score2) {
                    score1 = 3;
                } else if (score1 > score2) {
                    score1 = 4;
                }

            }
            switch (score1) {
                case 0:
                    return "0";
                case 1:
                    return "15";
                case 2:
                    return "30";
                case 3:
                    return "40";
                case 4:
                    return "40*"; // Обозначение для преимущества
                default:
                    return ""; // Для других значений
            }
        }
    %>
    <table class="scoreboard">
        <tr class="player-info">
            <td>
                <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline">
                    <button type="submit" name="playerName" value="${match.getFirstPlayerName()}" class="button-link">
                        ${match.getFirstPlayerName()}
                    </button>
                </form>
            </td>
            <td><%=convertedScore(match.getFirstPlayerGameScore(), match.getSecondPlayerGameScore())%></td>
            <td>${match.getFirstPlayerSetScore()}</td>
            <td>${match.getFirstPlayerMatchScore()}</td>
        </tr>
        <tr class="score-labels">
            <td>игрок</td>
            <td>гейм</td>
            <td>сет</td>
            <td>матч</td>
        </tr>
        <tr class="player-info">
            <td>
                <form action="${pageContext.request.contextPath}/match-score" method="post" style="display: inline">
                    <button type="submit" name="playerName" value="${match.getSecondPlayerName()}" class="button-link">
                        ${match.getSecondPlayerName()}
                    </button>
                </form>
            </td>
            <td><%=convertedScore(match.getSecondPlayerGameScore(), match.getFirstPlayerGameScore())%></td>
            <td>${match.getSecondPlayerSetScore()}</td>
            <td>${match.getSecondPlayerMatchScore()}</td>
        </tr>
    </table>
    <%--    --%>

    <a href="/" class="main-button">Вернуться в главное меню</a>
</div>

</body>
</html>