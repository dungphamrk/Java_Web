
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head><title>Chi tiết xe</title></head>
<body>
<h2>Xe: ${bus.licensePlate} - Loại: ${bus.busType}</h2>
<img src="${bus.image}" width="200"/>
<table border="1">
    <c:forEach var="r" begin="1" end="${bus.rowSeat}">
        <tr>
            <c:forEach var="c" begin="1" end="${bus.colSeat}">
                <c:set var="seatName" value="${fn:substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ', r-1, r)}${c}"/>
                <c:set var="seat" value="${seats.stream().filter(s -> s.nameSeat == seatName).findFirst().orElse(null)}"/>
                <td>
                    <c:choose>
                        <c:when test="${seat != null}">
                            ${seat.nameSeat} <br/>
                            ${seat.price} <br/>
                            ${seat.status}
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/bus">Quay lại</a>
</body>
</html>

