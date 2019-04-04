<%@ page import="ru.job4j.car.entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.car.storage.CarStore" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Car List</title>
    <!--link rel="stylesheet" href="../../css/main.css" type="text/css"-->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).on('click', 'input[name=checkSold]', function () {
            var carId = $(this).attr('id');
            $.ajax({
                url: 'updateDelete',
                type: 'get',
                contentType: 'text/html',
                data: ({carId: carId, sold: $(this).is(':checked')})
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
                        <form method="get" action="${pageContext.servletContext.contextPath}/imageUpload">
                            <input type="hidden" name="carIdForImage"  value="${car.id}">
                            <input type="submit" value="Upload Images">
                        </form>
                    </li>
                    <li>
                        <form method="post" action="${pageContext.servletContext.contextPath}/updateDelete">
                            <input type="hidden" name="carId"  value="${car.id}">
                            <input type="submit" onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false" value="Delete Car">
                        </form>
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
<form method="get" action="${pageContext.servletContext.contextPath}/contentFilter">
    <label>With Images</label>
    <input type="checkbox" name="filterImage" value="image">
    <br>
    <label>Only cars fo sale: </label>
    <input type="checkbox" name="filterSold" value="sold">
    <br>
    <label>With Car Name</label>
    <input type="text" name="filterName">
    <br>
    <input type="submit" value="Filter" >
</form>


<br>
<form method="get" action="${pageContext.servletContext.contextPath}/addCarForm">
    <input type='submit' value='Add New Car'>
</form>
<br>
<!--form method='get' action='${pageContext.servletContext.contextPath}/logout.do'>
    <input type='submit' value='Logout'>
</form-->


</body>


</html>
