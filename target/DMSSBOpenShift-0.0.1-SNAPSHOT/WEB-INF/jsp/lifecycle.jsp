<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/style/style.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <title>Lifecycle</title>
</head>
<body>

<div id="container">

    <div id="logo">
        <span style="color:#c34f4f">Data</span> Management System
    </div>

    <div class="menu">

        <div class="topmenu">
            <label><c:out value="${document.getName()}"/></label>
        </div>
        <div id="search">
            <ul class="sliding-icons">
                <li>
                    <a href="/advancedsearch">
                        <div class="icon">
                            <i class="fas fa-search fa-2x"></i>
                            <i class="fas fa-search fa-2x" title="Advanced search"></i>
                        </div>
                    </a>
                </li>
            </ul>
            <form class="thing" action="/quicksearch" method="get">
                <label for="ddd" class="thing-label">
                    Type to search...
                </label>
                <input type="text" name="phrase" id="ddd" class="thing-text">
                <input type="submit" value="search" class="thing-btn">
            </form>
            <div style="clear: both"></div>
        </div>

        <div class="topmenu">
            <div class="optionSO">
                <form action="/logout" method="get">
                    <input type="hidden" name="login" value="<c:out value="${sessionScope.login}"/>">
                    <input type="submit" name="menu" value="Sign out">
                </form>
            </div>
            <div class="option">
                <form id="usershow" action="/userdetails" method="get">
                    <a href="#" onclick="document.getElementById('usershow').submit()">Witaj <sec:authentication
                            property="principal.username"/>
                    </a>
                </form>
            </div>
            <div class="optionSO">
                <a href="/dashboard" id="home"><i class="fas fa-play fa-lg" title="Home"></i></a>
            </div>
            <div style="clear: both"></div>

        </div>
        <div style="clear:both;"></div>

    </div>
    <div style="clear:both;"></div>

    <div id="sidebar">
        <div class="optionL"><a href="/document/${document.getId()}">Properties</a></div>
        <div class="optionL"><a href="/document/${document.getId()}/revisions">Revisions</a></div>

        <c:if test="${role ne 'viewer'}">
            <div class="optionL"><a href="/document/${document.getId()}/routes">Routes</a></div>
        </c:if>

        <div class="optionL"><a href="/document/${document.getId()}/lifecycle">Lifecycle</a></div>
        <c:if test="${document.getType() eq 'drawing'}">
            <div class="optionL"><a href="/document/${document.getId()}/viewer">Viewer</a></div>
        </c:if>

        <div style="clear: both"></div>
    </div>

    <div id="content">

        <ul class="lifecycle">

            <c:choose>
                <c:when test="${document.getState() eq 'in work'}">
                    <li class="active">In work</li>
                </c:when>
                <c:when test="${document.getState() ne 'in work'}">
                    <li>In work</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'frozen'}">
                    <li class="active">Frozen</li>
                </c:when>
                <c:when test="${document.getState() ne 'frozen'}">
                    <li>Frozen</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'released'}">
                    <li class="active">Released</li>
                </c:when>
                <c:when test="${document.getState() ne 'released'}">
                    <li>Released</li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${document.getState() eq 'cancelled'}">
                    <li class="active">Canceled</li>
                </c:when>
                <c:when test="${document.getState() ne 'cancelled'}">
                    <li>Canceled</li>
                </c:when>
            </c:choose>
        </ul>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

</body>
</html>
