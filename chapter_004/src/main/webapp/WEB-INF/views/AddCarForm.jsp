<%@ page import="ru.job4j.car.entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.car.storage.CarStore" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>AddCar</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../css/main.css" type="text/css">
    <script>
        $(document).ready(function () {
            getBodyTypes();
            getEngineTypes();
            getTransTypes();
            getLocation();
        });
        function validateUserInput() {
            var name = document.getElementById("name").value;
            var body = document.getElementById("body").value;
            var engine = document.getElementById("engine").value;
            var transmission = document.getElementById("transmission").value;
            var location = document.getElementById("location").value;
            var price = document.getElementById("price").value;
            //var owner = document.getElementById("owner").value;

            if (name === "") {
                alert("Enter Car name");
                return false;
            } else if (body === "") {
                alert("Choose car body type");
                return false;
            } else if (engine === "") {
                alert("Chose car engine type");
                return false;
            } else if (transmission === "") {
                alert("Chose car transmission type");
                return false;
            } else if (location === "") {
                alert("Chose location");
                return false;
            } else if (price === "") {
                alert("Enter car price");
                return false;
            } /*else if (owner === "") {
                alert("Enter login name ");
                return false;
            }*/
            return true;
        }

        function getBodyTypes() {
            $.ajax({
                type: "GET",
                url: "getBody",
                success: function (data) {
                    console.log(data);
                    $("#body option").not(":first").remove();
                    $.each(data, function (indexInArray, value) {
                        $('#body option:last').after('<option>' + value + '</option>');
                    });

                }
            });
        }
        function addNewBodyType() {
            var newBody = document.getElementById("newBody").value;
            $('#body option:last').after('<option>' + newBody + '</option>');
            document.getElementById("newBody").value = "";
        }

        function getEngineTypes() {
            $.ajax({
                type: "GET",
                url: "getEngine",
                success: function (data) {
                    console.log(data);
                    $("#engine option").not(":first").remove();
                    $.each(data, function (indexInArray, value) {
                        $('#engine option:last').after('<option>' + value + '</option>');
                    });

                }
            });
        }

        function addNewEngineType() {
            var newEngine = document.getElementById("newEngine").value;
            $('#engine option:last').after('<option>' + newEngine + '</option>');
            document.getElementById("newEngine").value = "";
        }

        function getTransTypes() {
            $.ajax({
                type: "GET",
                url: "getTransmission",
                success: function (data) {
                    console.log(data);
                    $("#transmission option").not(":first").remove();
                    $.each(data, function (indexInArray, value) {
                        $('#transmission option:last').after('<option>' + value + '</option>');
                    });

                }
            });
        }

        function addNewTransType() {
            var newTransmission = document.getElementById("newTransmission").value;
            $('#transmission option:last').after('<option>' + newTransmission + '</option>');
            document.getElementById("newTransmission").value = "";
        }

        function getLocation() {
            $.ajax({
                type: "GET",
                url: "getLocation",
                success: function (data) {
                    console.log(data);
                    $("#location option").not(":first").remove();
                    $.each(data, function (indexInArray, value) {
                        $('#location option:last').after('<option>' + value + '</option>');
                    });

                }
            });
        }

        function addNewLocation() {
            var newLocation = document.getElementById("newLocation").value;
            $('#location option:last').after('<option>' + newLocation + '</option>');
            document.getElementById("newLocation").value = "";
        }
    </script>
</head>
<body>
<h2>ADD NEW CAR FOR SALE</h2>
<form:form method="post" action="addCar" modelAttribute="newCar">
    <label for="name">Car Name:</label>
    <form:input path="name"/><br>

    <label for="body">Body Type:</label>
    <form:select path="body" id="body" name="body" style="width: 13em">
        <form:option value="">--Select Body Type--</form:option>
    </form:select><br>
    <label for="newBody">&nbsp;</label>
    <input type="text" name="newBody" id="newBody">
    <input type="button" value="Add New Body Type" onclick="addNewBodyType()"> <br>


    <label for="engine">Engine Type:</label>
    <form:select path="engine" id="engine" name="engine" style="width: 13em">
        <form:option value="">--Select Engine Type--</form:option>
    </form:select><br>
    <label for="newEngine">&nbsp;</label>
    <input type="text" name="newEngine" id="newEngine">
    <input type="button" value="Add New Engine Type" onclick="addNewEngineType()"> <br>


    <label for="transmission">Transmission Type:</label>
    <form:select path="transmission" id="transmission" name="transmission" style="width: 13em">
        <form:option value="">--Select Transmission Type--</form:option>
    </form:select><br>
    <label for="newTransmission">&nbsp;</label>
    <input type="text" name="newTransmission" id="newTransmission">
    <input type="button" value="Add New Transmission Type" onclick="addNewTransType()"> <br>

    <label for="location">Car Location:</label>
    <form:select path="location" id="location" name="location" style="width: 13em">
        <form:option value="">--Select Location--</form:option>
    </form:select><br>
    <label for="newLocation">&nbsp;</label>
    <input type="text" name="newLocation" id="newLocation">
    <input type="button" value="Add New Location" onclick="addNewLocation()"> <br>

    <label for="price">Price:</label>
    <form:input path="sold" type="hidden" name="sold" value="false"/>
    <form:input path="price" type="text" name="price" id="price"/><br>

    <!--label for="owner">Owner</label-->
    <!--form:input path="user" name="owner" id="owner"/--><br><br>




    <label>&nbsp;</label>
    <input type='submit' value='ADD' onclick="return validateUserInput()">
    <br>
    <br>
    <c:if test="${not empty error}">
        <c:out value='${error}'></c:out>
    </c:if>
    <br>
    <br>
</form:form>

<label>&nbsp;</label>
<form method='get' action='${pageContext.servletContext.contextPath}/'>
    <input type='submit' value='Cancel'>
</form>
</body>
</html>
