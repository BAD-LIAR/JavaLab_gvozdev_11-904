<%@ page import="ru.ru.itis.models.News" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.Writer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../styles/dean's_office.css">
    <script src="bootstrap-4.0.0-dist/js/bootstrap.min.js" type="text/css"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"
            integrity="sha512-WNLxfP/8cVYL9sj8Jnp6et0BkubLP31jhTG9vhL/F5uEZmg5wEzKoXp1kJslzPQWwPT1eyMiSxlKCgzHLOTOTQ=="
            crossorigin="anonymous"></script>
    <title>Dean's office</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-default navbar-dark bg-dark fixed-top">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="navbar-header disable">
                <a href="/home" class="navbar-brand">Home page</a>
            </li>
            <li class="nav-item active ">
                <a href="/dean's" class="nav-link btn active">Dean's office</a>
            </li>
            <li class="nav-item active ">
                <a href="/union" class="nav-link btn active">Trade union committee</a>
            </li>
            <li class="nav-item active ">
                <a href="/logout" class="nav-link btn active">Log out</a>
            </li>
            <li class="nav-item active profile">
                <a href="/profile" class="nav-link btn active">Profile</a>
            </li>
        </ul>
    </div>
</nav>
<div class="field_for_news">
    <%
        List<News> news = (List<News>) request.getAttribute("newsForDean'sOffice");
        for (News current : news) {%>
    <div class="news_object">
        <h3><%=current.getTitle()%>
        </h3>
        <img class="news_photo" src="<%=current.getPhotoPath()%>" alt="Image">
        <p><%=current.getInfo()%>
        </p>
        <div onclick="location.href='https://yraaa.ru';" style="cursor: pointer;">
            <p class="comments"><img class="comment_photo" src="../img/comment.png" alt="Comment image">Comments</p>
        </div>
    </div>
    <%}%>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>