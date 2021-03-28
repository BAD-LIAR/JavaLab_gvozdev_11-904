<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/profile.css">
    <title>Profile</title>
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
        <h1 align="right"><span style="color: #0048ff; ">И</span><span style="color: white">Т</span><span
                style="color: #0048ff ">И</span><span style="color: white">С</span></h1>
    </div>
</nav>
<div class="profile">
    <img src="img/noavatar.png" class="profile">
    <div class="profile_info">
        <h3 class="profile">FirstName LastName</h3>
        <div>
            <%
            User user = (User) request.getAttribute("userForJsp");
            %>
            <p><%=user.getInfo()%></p>

            <table class="table my_table" border="2">
                <tr>
                    <td>Средний балл</td>
                    <td><%=user.count()%></td>
                </tr>
                <tr>
                    <td>Курс</td>
                    <td><%=user.course()%></td>
                </tr>
                <tr>
                    <td>Группа</td>
                    <td><%=user.groupNumber()%></td>
                </tr>
                <tr>
                    <td>Казанский/общажный</td>
                    <td><%=user.getLocationType()%></td>
                </tr>
            </table>
        </div>
    </div>
    <button>Редактировать профиль</button>
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