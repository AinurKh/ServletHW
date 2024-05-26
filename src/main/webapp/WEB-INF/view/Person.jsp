%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>People List</title>
    <script>
        function sendRequest(url, method, data) {
            fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            }).then(response => {
                if (response.ok) {
                    location.reload();
                } else {
                    alert('Error: ' + response.statusText);
                }
            });
        }

        function updatePerson(personId) {
            var name = document.getElementById('name-' + personId).value;
            var age = document.getElementById('age-' + personId).value;

            var xhr = new XMLHttpRequest();
            xhr.open('PUT', '/your-servlet-url/' + personId, true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        alert('Person updated successfully');
                        // Обновляем список персон или выполняем другие действия, если необходимо
                    } else {
                        alert('Error updating person');
                    }
                }
            };
            xhr.send('name=' + encodeURIComponent(name) + '&age=' + encodeURIComponent(age));
        }

        function deletePerson(id) {
            sendRequest('${pageContext.request.contextPath}/people/' + id, 'DELETE');
        }
    </script>
</head>
<body>
<h2>People List</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Car</th>
        <th>Gas Stations</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="person" items="${peopleDTOList}">
        <tr>
            <td>${person.id}</td>
            <td><input type="text" id="name-${person.id}" value="${person.name}" /></td>
            <td><input type="number" id="age-${person.id}" value="${person.age}" /></td>
            <td>${person.car.model}</td>
            <td>
                <c:forEach var="gasStation" items="${person.stationList}">
                    ${gasStation.name} (${gasStation.number})<br/>
                </c:forEach>
            </td>
            <td>
                <button onclick="updatePerson(${person.id})">Update</button>
                <button onclick="deletePerson(${person.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<hr>
<h2>Add New Person</h2>
<form action="${pageContext.request.contextPath}/people" method="post">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required />
    <label for="age">Age:</label>
    <input type="number" name="age" id="age" required />
    <button type="submit">Add Person</button>
</form>
</body>
</html>

