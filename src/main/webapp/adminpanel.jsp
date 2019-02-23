<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="style/style.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <title>Admin panel</title>
</head>
<body>

<div id="container">

    <div id="logo">
        <span style="color:#c34f4f">Data</span> Management System
    </div>

    <div class="menu">

        <div class="topmenu">
            <label></label>
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
                    <a href="#" onclick="document.getElementById('usershow').submit()">Witaj <sec:authentication property="principal.username"/>
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
    <div style="clear:both"></div>

    <div id="sidebar">
        <div class="optionL"><a href="/documents">Documents</a></div>
        <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
            <div class="optionL"><a href="/routeslist">Routes</a></div>
            <div class="optionL"><a href="/tasks">Tasks</a></div>
        </sec:authorize>

        <sec:authorize access="hasRole('ADMIN')">
            <div class="optionL"><a href="/adminpanel">Admin Panel</a></div>
        </sec:authorize>

        <div style="clear: both"></div>
    </div>

    <div id="content">
        <div class="square">
            <div class="tile1"><H1><a href="/registration" class="tilelink">Create new user</a> </H1></div>
            <div class="tile1"><H1><a href="/all/users" class="tilelink">Show all users</a> </H1><</div>
            <div style="clear: both"></div>

            <div class="tile2"><H1><a href="#" class="tilelink">Delete user</a></H1></div>
            <div class="tile3"><H1><a href="#" class="tilelink">Create workspace</a></H1></div>
            <div style="clear: both"></div>

            <div class="tile4">4</div>

        </div>
        <div class="square">
            <div class="tile5">5</div>

            <div class="tile6">6</div>
            <div class="tile7">7</div>
            <div class="tile8">8</div>
            <div class="tile9">9</div>
            <div style="clear: both"></div>
        </div>
        <div style="clear: both"></div>
    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

</div>

</body>
</html>
