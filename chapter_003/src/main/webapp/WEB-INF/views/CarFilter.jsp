<%@ page import="ru.job4j.entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.storage.CarStore" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Car List</title>
    <link rel="stylesheet" href="../../css/main.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).on('click', 'input[name=checkSold]', function () {
            var carId = $(this).attr('id');
            $.ajax({
                url: 'status.do',
                type: 'get',
                contentType: 'text/html',
                data: ({carId: carId, sold: $(this).is(':checked')})
            });
        });
        $(document).on('click', 'input[name=filterSold]', function () {
            //var carId = $(this).attr('id');
            $.ajax({
                url: 'contentFilter.do',
                type: 'get',
                contentType: 'text/html',
                data: ({sold: $(this).is(':checked')})
            });
        });
    </script>
</head>
<body>
<h2>ALL CARS</h2>
<table id="carTable">
    <tr>
        <th>Car Details</th>
        <th>Images</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <ul>
                    <li>Car Id: ${car.id}</li>
                    <li>Car name: ${car.name}</li>
                    <li>Body: ${car.body.bodyType}</li>
                    <li>Engine: ${car.engine.engineType}</li>
                    <li>Transmission: ${car.transmission.transmissionType}</li>
                    <li>Price: ${car.price}</li>
                    <li>Location: ${car.location}</li>
                    <li>Owner: ${car.user.name}</li>
                    <li>Sold Status:
                        <c:choose>
                            <c:when test="${car.sold == true && sessionScope.login == car.user.login}">
                                <input type="checkbox" name="checkSold" id="${car.id}" checked >
                            </c:when>
                            <c:when test="${car.sold == true && sessionScope.login != car.user.login}">
                                <input type="checkbox" name="checkSold" id="${car.id}" checked disabled>
                            </c:when>
                            <c:when test="${car.sold == false && sessionScope.login == car.user.login}">
                                <input type="checkbox" name="checkSold" id="${car.id}" >
                            </c:when>
                            <c:when test="${car.sold == false && sessionScope.login != car.user.login}">
                                <input type="checkbox" name="checkSold" id="${car.id}" disabled>
                            </c:when>
                        </c:choose>
                    </li>
                    <li>
                        <c:if test="${sessionScope.login == car.user.login}">
                            <form method="get" action="${pageContext.servletContext.contextPath}/imageUpload.do">
                                <input type="hidden" name="carId"  value="${car.id}">
                                <input type="submit" value="Upload Images">
                            </form>
                        </c:if>
                    </li>
                    <li>
                        <c:if test="${sessionScope.login == car.user.login}">
                            <form method="post" action="${pageContext.servletContext.contextPath}/status.do">
                                <input type="hidden" name="carId"  value="${car.id}">
                                <input type="submit" value="Delete Car">
                            </form>
                        </c:if>
                        <c:if test="${not empty error}">
                            <c:out value='${error}'></c:out>
                        </c:if>
                    </li>
                </ul>
            </td>
            <td>
                <c:forEach var="image" items="${car.images}">
                    <img src="data:image; base64,${image.base64Image}" width="240" height="300"/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>


<br>
<form method="get" action="${pageContext.servletContext.contextPath}/addCar.do">
    <input type='submit' value='Add New Car'>
</form>
<br>
<form method='get' action='${pageContext.servletContext.contextPath}/logout.do'>
    <input type='submit' value='Logout'>
</form>


</body>


</html>

