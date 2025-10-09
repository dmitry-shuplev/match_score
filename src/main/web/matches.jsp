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

<div class="pagination">
    <%
        int totalPages = (int) request.getAttribute("totalPages");
        int currentPage = (int) request.getAttribute("currentPage");
        String spanClass;
        String servletMethod = (request.getAttribute("method") == null) ? "get" : "post";

        for (int i = 1; i <= totalPages; i++) {
            spanClass = (i == currentPage) ? "page-item active" : "page-item";
    %>
    <form action="${pageContext.request.contextPath}/matches" method="<%=servletMethod%>">
        <input type="hidden" name="currentPage" value="<%=i%>">
        <button type="submit" class="<%=spanClass%>"><%= i %>
        </button>
    </form>
    <%--    <a href="${pageContext.request.contextPath}/matches?currentPage=<%= i %>" class="<%= spanClass %>"><%= i %></a>--%>
    <%--    <button type="submit" class="<%=spanClass%>"> <%=i%></button>--%>
    <%}%>

</div>

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
                    String fPlayer = match.getFirstPlayerName();
                    String sPlayer = match.getSecondPlayerName();
        %>
        <tr>
            <td>
                <%= match.getMatchDate() %>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/matches" method="post" style="display: inline">
                    <button type="submit" name="pName" value="<%=fPlayer%>" class="button-link">
                        <%=fPlayer%>
                    </button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/matches" method="post" style="display: inline">
                    <button type="submit" name="pName" value="<%=sPlayer%>" class="button-link">
                        <%=sPlayer%>
                    </button>
                </form>
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