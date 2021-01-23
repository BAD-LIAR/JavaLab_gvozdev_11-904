<html lang="en">
<body>
    <form action="${form.action!"missed"}" method="${form.method!"missed"}">
        <#list form.fieldList as listField>
            <input
                    type="${listField.type}"
                    name="${listField.name}"
                    placeholder="${listField.placeholder}">
        </#list>
    </form>
</body>
</html>