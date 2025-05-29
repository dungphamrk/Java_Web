
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head><title><spring:message code="finance.add"/></title></head>
<body>
<h2><spring:message code="finance.add"/></h2>
<form:form method="POST" action="/finance/add" modelAttribute="transaction">
  <div>
    <label><spring:message code="finance.username"/>:</label>
    <input type="text" name="username" value="${username != null ? username : ''}"/>
  </div>
  <div>
    <label><spring:message code="finance.description"/>:</label>
    <form:input path="description"/>
    <form:errors path="description" cssClass="error"/>
  </div>
  <div>
    <label><spring:message code="finance.amount"/>:</label>
    <form:input path="amount"/>
    <form:errors path="amount" cssClass="error"/>
  </div>
  <div>
    <label><spring:message code="finance.type"/>:</label>
    <form:select path="type">
      <form:option value="">--Chọn--</form:option>
      <form:option value="income"><spring:message code="finance.income"/></form:option>
      <form:option value="expense"><spring:message code="finance.expense"/></form:option>
    </form:select>

    <form:errors path="type" cssClass="error"/>
  </div>
  <button type="submit"><spring:message code="finance.add"/></button>
</form:form>
<a href="${pageContext.request.contextPath}/finance/list"><spring:message code="finance.list"/></a>
<br/>
<a href="${pageContext.request.contextPath}/finance/form?lang=vi">Tiếng Việt</a> |
<a href="${pageContext.request.contextPath}/finance/form?lang=en">English</a>


</body>
</html>