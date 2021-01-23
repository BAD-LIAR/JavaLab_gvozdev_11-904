<html lang="en">
<body>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>LastName</th>
    </tr>
    <#list users as listUser>
        <tr>
            <td>${listUser.id}</td>
            <td>${listUser.firstName}</td>
            <td>${listUser.lastName}</td>
        </tr>
    </#list>
</table>


</body>
</html>