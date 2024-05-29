<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.05.2024
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car List</title>
</head>
<body>

<h2>Car List</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Person_id</th>
        <th>Model</th>
        <th>HorsePower</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="car" items="${carDTOS}">
        <tr>
            <td>${car.id}</td>
            <td><input type="number" id="personId-${car.id}" value="${car.personId}" disabled></td>
            <td><input type="text" id="model-${car.id}" value="${car.model}" disabled></td>
            <td><input type="number" id="horsePower-${car.id}" value="${car.horsePower}" disabled></td>

            <td>
                <button id="updateButton-${car.id}" onclick="toggleEditMode(${car.id})">Update</button>
                <button onclick="deletePerson(${car.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<hr>

<h2>Add New Car</h2>
<form action="${pageContext.request.contextPath}/cars" method="post">
    <label for="personId">PersonId:</label>
    <input type="number" name="personId" id="personId" required />

    <label for="model">Model:</label>
    <input type="text" name="model" id="model" required />

    <label for="horsePower">Horse Power:</label>
    <input type="number" name="horsePower" id="horsePower" required />

    <button type="submit">Add Car</button>
</form>

<h3><a href="/people">People</a></h3>

<script>
    function toggleEditMode(carId) {
        var personIdInput = document.getElementById('personId-' + carId);
        var modelInput = document.getElementById('model-' + carId);
        var horsePowerInput=document.getElementById('horsePower-'+ carId)
        var updateButton = document.getElementById('updateButton-' + carId);
        if (modelInput.disabled) {
            modelInput.disabled = false;
            horsePowerInput.disabled=false;
            updateButton.textContent = 'Save';
        } else {
            modelInput.disabled = true;
            horsePowerInput.disabled=true;
            updateButton.textContent = 'Update';

            var updatedCar = {
                id: carId,
                personId: personIdInput.value,
                model:modelInput.value,
                horsePower: horsePowerInput.value,
            };
            updateCar(updatedCar);
        }
    }

    function deleteCar(carId) {
        var xhr = new XMLHttpRequest();
        xhr.open('DELETE', '/cars/' + carId, true);
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

    function updateCar(car) {
        var xhr = new XMLHttpRequest();
        xhr.open('PUT', '/cars/' + car.id, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('Car updated successfully');
                    location.reload();
                } else {
                    alert('Error updating car');
                }
            }
        };
        xhr.send(JSON.stringify(car));
    }
</script>

</body>
</html>

