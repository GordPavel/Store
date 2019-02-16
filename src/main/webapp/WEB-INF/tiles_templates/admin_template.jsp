<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <tiles:insertAttribute name="includeHeader"/>
</head>

<body class="fuelux">
<tiles:insertAttribute name="bodyHeader"/>
<div class="container mainPart">
    <tiles:insertAttribute name="main"/>
</div>
</body>
<tiles:insertAttribute name="includeFooter"/>
</html>