<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <tiles:insertAttribute name="includeHeader"/>
</head>

<body class="fuelux">
<tiles:insertAttribute name="bodyHeader"/>
<tiles:insertAttribute name="menu"/>
<tiles:insertAttribute name="breadcrumb"/>
<div class="container mainPart">
    <div class="row" style="margin: 0">
        <div class="col-md-12 col-sm-9" style="padding: 0">
            <tiles:insertAttribute name="main"/>
        </div>
        <div class="clear" style="clear: both"></div>
    </div>
</div>
<tiles:insertAttribute name="bodyFooter"/>
</body>
<tiles:insertAttribute name="includeFooter"/>
</html>