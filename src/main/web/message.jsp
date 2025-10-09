<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

    <title>Messages</title>
    <script>
        setTimeout(function () {
            window.location.href = "<%= request.getContextPath() %>/";
        }, 3000);
    </script>

    <div class="container">
        <h2> Что то случилось</h2>


    </div>
</head>
<body>
<h1>Случилось событие</h1>


</body>
</html>