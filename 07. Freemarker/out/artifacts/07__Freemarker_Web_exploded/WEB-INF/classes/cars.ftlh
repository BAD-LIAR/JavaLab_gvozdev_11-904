<html lang="en">
<body>

<table>
    <tr>
        <th>ID</th>
        <th>ownerID</th>
        <th>model</th>
    </tr>
    <#list cars as listCar>
        <tr>
            <td>${listCar.id}</td>
            <td>${listCar.ownerId}</td>
            <td>${listCar.model}</td>
        </tr>
    </#list>
</table>


</body>
</html>