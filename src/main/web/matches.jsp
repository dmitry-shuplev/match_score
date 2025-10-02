<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.MatchWebDto" %>
<%
    List<MatchWebDto> matches = (List<MatchWebDto>) request.getAttribute("matchWebDto");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matches</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table_styles.css">
</head>
<body>
<h1>Список матчей</h1>
<div>
    <form action="${pageContext.request.contextPath}/matches" method="post">

    </form>
</div>
<div class="container">
<table class="matches-table">
    <thead>
    <tr>
        <th>Дата</th>
        <th>Первый игрок</th>
        <th>Второй игрок</th>
        <th>Победитель</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (matches != null && !matches.isEmpty()) {
            for (MatchWebDto match : matches) {
    %>
    <tr>
        <td><%= match.getMatchDate() %>
        </td>
        <td><%= match.getFirstPlayerName() %>
        </td>
        <td><%= match.getSecondPlayerName() %>
        </td>
        <td><%= match.getWinnreName() %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5">Нет матчей для отображения.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>
</body>
</html>
