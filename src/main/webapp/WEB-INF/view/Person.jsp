<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person List</title>
</head>
<body>

<h2>Person List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Car</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="person" items="${peopleDTOList}">
        <tr>
            <td>${person.id}</td>
            <td><input type="text" id="name-${person.id}" value="${person.name}" disabled></td>
            <td><input type="number" id="age-${person.id}" value="${person.age}" disabled></td>
            <td><input type="text" id="car-${person.id}" value="${person.car.model}" disabled></td>

            <td>
                <button id="updateButton-${person.id}" onclick="toggleEditMode(${person.id})">Update</button>
                <button onclick="deletePerson(${person.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
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

<h3><a href="/cars/">Cars</a></h3>


<script>
    function toggleEditMode(personId) {
        var nameInput = document.getElementById('name-' + personId);
        var ageInput = document.getElementById('age-' + personId);
        var updateButton = document.getElementById('updateButton-' + personId);
        if (nameInput.disabled) {
            nameInput.disabled = false;
            ageInput.disabled = false;
            updateButton.textContent = 'Save';
        } else {
            nameInput.disabled = true;
            ageInput.disabled = true;
            updateButton.textContent = 'Update';

            var updatedPerson = {
                id: personId,
                name: nameInput.value,
                age: ageInput.value,
            };
            updatePerson(updatedPerson);
        }
    }

    function deletePerson(personId) {
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', '/people/' + personId, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('Person deleted successfully');
                    location.reload();
                } else {
                    alert('Error deleting person');
                }
            }
        };
        xhr.send();
    }

    function updatePerson(person) {
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', '/people/' + person.id, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('Person updated successfully');
                    location.reload();
                } else {
                    alert('Error updating person');
                }
            }
        };
        xhr.send(JSON.stringify(person));
    }
</script>

</body>
</html>


