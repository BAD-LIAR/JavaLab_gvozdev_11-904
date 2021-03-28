<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/dean's_office.css">
    <title>Деканат</title>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-default navbar-dark bg-dark fixed-top">
    <div class="container-fluid">

        <ul class="navbar-nav">
            <li class="navbar-header disable">
                <a href="home" class="navbar-brand">Главная страница</a>
            </li>
            <li class="nav-item active ">
                <a href="dean's_office" class="nav-link btn active">Деканат</a>
            </li>
            <li class="nav-item active ">
                <a href="trade_union_committee" class="nav-link btn active">Профком</a>
            </li>
            <li class="nav-item active ">
                <a href="login" class="nav-link btn active">Выйти</a>
            </li>
        </ul>
    </div>
</nav>
<br>
<br>
<br>
<div class="news">
    <h1>Новости</h1>
        <%
                List<News> news = (List<News>) request.getAttribute("newsForDean'sOffice");
                for (int i = 0; i < news.size(); i++){
                    News news1 = news.get(i);%>
        <div class="news_object">
            <h3><%news1.getTittle();%></h3>
            <img class="news_photo" src=<%news1.getPhotoPath();%>>
            <p>#INFO</p>
            <div onclick="location.href='https://yraaa.ru';" style="cursor: pointer;">
                <p class="comments"><img class="comment_photo" src="img/comment.png">Коментарии         </p>
            </div>
        </div>
    <%
                }
                %>
<!--    <div class="news_object">-->
<!--        <h3>news 1</h3>-->
<!--        <img class="news_photo" src="img/noavatar.png">-->
<!--        <p>#INFO</p>-->
<!--        <div onclick="location.href='https://yraaa.ru';" style="cursor: pointer;">-->
<!--            <p class="comments"><img class="comment_photo" src="img/comment.png">Коментарии         </p>-->
<!--&lt;!&ndash;            <a rel="nofollow" href="http://google.com"></a>&ndash;&gt;-->
<!--        </div>-->
<!--    </div>-->
<!--    <div class="news_object">-->
<!--        <h3>news 1</h3>-->
<!--        <img class="news_photo" src="img/noavatar.png">-->
<!--        <p>#INFO-->
<!--        adawdadawdawmkfemrgegmergmekrgerjgrergerjglemlk gmklergmkekmlgeerglk</p>-->
<!--        <div onclick="location.href='https://yraaa.ru';" style="cursor: pointer;">-->
<!--            <p class="comments"><img class="comment_photo" src="img/comment.png">Коментарии         </p>-->
<!--            &lt;!&ndash;            <a rel="nofollow" href="http://google.com"></a>&ndash;&gt;-->
<!--        </div>-->
<!--    </div>-->
<!--    <div class="news_object">-->
<!--        <h3>news 1</h3>-->
<!--        <img class="news_photo" src="img/noavatar.png">-->
<!--        <p>#INFO</p>-->
<!--        <div onclick="location.href='https://yraaa.ru';" style="cursor: pointer;">-->
<!--            <p class="comments"><img class="comment_photo" src="img/comment.png">3 комментария</p>-->
<!--            &lt;!&ndash;            <a rel="nofollow" href="http://google.com"></a>&ndash;&gt;-->
<!--        </div>-->
<!--    </div>-->
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