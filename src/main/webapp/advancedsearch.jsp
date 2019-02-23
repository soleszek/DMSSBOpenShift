<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/style/documents-view.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>

    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">

    <title>Advanced search</title>

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

        <select id="object">
            <option selected>Select type of object</option>
            <option value="div1">Documents</option>
            <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                <option value="div2">Routes</option>
                <option value="div3">Tasks</option>
                <option value="div4">Users</option>
            </sec:authorize>
        </select>
        <div class="form-div1" id="div1">
            <form class="edit-form" action="/advancedsearch/document" method="post">

                <table id="example1" class="display" style="width:100%">
                    <col width="220">

                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="">
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="title" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Type</td>
                        <td><input type="text" id=doctype name="doctypeinput" value="" readonly>
                            <select name="doctype" id="select-doctype" onchange="replaceValueDocType(event)">
                                <option value="drawing">drawing</option>
                                <option value="document">document</option>
                                <option value="image">image</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Revision</td>
                        <td><input type="text" name="revision" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" id="docstate" name="docstateinput" value="" readonly>
                            <select name="docstate" id="select-docstate" onchange="replaceValueDocState(event)">
                                <option value="in work">in work</option>
                                <option value="frozen">frozen</option>
                                <option value="released">released</option>
                                <option value="canceled">canceled</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td><input type="text" name="owner" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Creation date</td>
                        <td><input type="text" name="creationDate" value="" class="datepicker" id="creationDate"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('creationDate').value = ''"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Last modified</td>
                        <td><input type="text" name="lastModified" value="" class="datepicker" id="lastModificationDate"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('lastModificationDate').value = ''"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" name="description" value="">
                        </td>
                    </tr>

                </table>

                <input type="submit" name="" value="search">

            </form>
        </div>
        <div class="form-div2" id="div2">
            <form id="edit-form" action="/advancedsearch/route" method="post">

                <table id="example2" class="display" style="width:100%">
                    <col width="300">

                    <tr>
                        <td>Promotion request name</td>
                        <td><input type="text" name="name" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td><input type="text" name="owner" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Promoted document</td>
                        <td><input type="text" name="documentBeingApproved" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" id="routestate" name="routestate" value="" readonly>
                            <select name="routestate" id="select-routestate" onchange="replaceValueRouteState(event)">
                                <option value="not started">not started</option>
                                <option value="checking">checking</option>
                                <option value="approving">approving</option>
                                <option value="completed">completed</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Check due date</td>
                        <td><input type="text" name="checkingDueDate" value="" id="checkDueDate" class="datepicker"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('checkDueDate').value = ''"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Person assigned to check</td>
                        <td><input type="text" name="responsibleForChecking" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Approve due date</td>
                        <td><input type="text" name="deadline" value="" id="approveDueDate" class="datepicker"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('approveDueDate').value = ''"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Person assigned to approve</td>
                        <td><input type="text" name="responsibleForApproving" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Comments</td>
                        <td><input type="text" name="comments" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Date of creation</td>
                        <td><input type="text" name="creationDate" value="" id="dateOfCreation" class="datepicker"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('dateOfCreation').value = ''"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Finish date</td>
                        <td><input type="text" name="finishDate" value="" id="finishDate" class="datepicker"
                                   readonly><input type="button" value="clear"
                                                   onclick="document.getElementById('finishDate').value = ''"/>
                        </td>
                    </tr>

                </table>

                <input type="submit" name="" value="search">

            </form>
        </div>
        <div class="form-div3" id="div3">
            <form class="edit-form" action="/advancedsearch/task" method="post">
                <table id="example3" class="display" style="width:100%">
                    <col width="300">

                    <tr>
                        <td>Task name</td>
                        <td><input type="text" name="name" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td><input type="text" name="owner" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Assigned to</td>
                        <td><input type="text" name="assignedTo" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Promoted document</td>
                        <td><input type="text" name="processedDocument" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" id="state" name="state" value="" readonly>
                            <select name="state" id="select-state" onchange="replaceValueState(event)">
                                <option value="active">Active</option>
                                <option value="completed">Completed</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Due date</td>
                        <td><input type="text" name="dueDate" value="" class="datepicker">
                        </td>
                    </tr>
                    <tr>
                        <td>Completion date</td>
                        <td><input type="text" name="completionDate" value="" class="datepicker">
                        </td>
                    </tr>
                    <tr>
                        <td>Task comment</td>
                        <td><input type="text" name="comment" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>Parent route</td>
                        <td><input type="text" name="parentRoute" value="">
                        </td>
                    </tr>

                </table>

                <input type="submit" name="" value="search">

            </form>
        </div>
        <div class="form-div4" id="div4">
            <form <%--id="edit-form"--%> action="/advancedsearch/user" method="post">

                <table id="example4" class="display" style="width:100%">
                    <col width="220">

                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="">
                        </td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" name="firstName" value=""></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" name="lastName" value=""></td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td><input type="text" id="role" name="role" value="" readonly>
                            <select name="role" id="select-role" onchange="replaceValue(event)">
                                <option value="viewer">Viewer</option>
                                <option value="contributor">Contributor</option>
                                <option value="manager">Manager</option>
                                <option value="admin">Admin</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="username" value="">
                        </td>
                    </tr>

                </table>

                <input type="submit" name="" value="search">

            </form>
        </div>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <script type="text/javascript">
        $(document).ready(function () {

            // Setup - add a text input to each footer cell
            $('#example1 tfoot th').each(function () {
                var title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            });

            // DataTable
            var table = $('#example1').DataTable({
                "lengthMenu": [[10, 20], [10, 20]]
            });

        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {

            // Setup - add a text input to each footer cell
            $('#example2 tfoot th').each(function () {
                var title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            });

            // DataTable
            var table = $('#example2').DataTable({
                "lengthMenu": [[10, 20], [10, 20]]
            });

        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {

            // Setup - add a text input to each footer cell
            $('#example3 tfoot th').each(function () {
                var title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            });

            // DataTable
            var table = $('#example3').DataTable({
                "lengthMenu": [[10, 20], [10, 20]]
            });

        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {

            // Setup - add a text input to each footer cell
            $('#example4 tfoot th').each(function () {
                var title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            });

            // DataTable
            var table = $('#example4').DataTable({
                "lengthMenu": [[10, 20], [10, 20]]
            });

        });
    </script>

    <script>
        $(function () {
            $(".datepicker").datepicker({
                dateFormat: "yy-mm-dd"
            });
        });
    </script>

    <script src="/jsscripts/advancedsearch.js"></script>
    <script src="/jsscripts/dropdownToInput.js"></script>
    <script src="/jsscripts/dropdownToInputState.js"></script>
    <script src="/jsscripts/dropdownToInputRouteState.js"></script>
    <script src="/jsscripts/dropdownToInputDocState.js"></script>
    <script src="/jsscripts/dropdownToInputDocType.js"></script>
    <script src="/jsscripts/resetcreationdate.js"></script>
    <script src="/jsscripts/resetlastmodificationdate.js"></script>


</div>

</body>

</html>
