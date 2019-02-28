<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/style/style-route.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>

    <title>Route</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            font-family: Helvetica, Arial, sans-serif;
        }

        /* Set a style for all buttons */
        button {
            background-color: #46b7ce;
            color: white;
            padding: 14px 20px;
            margin: 8px 26px;
            border: none;
            cursor: pointer;
            width: 90%;
            font-size: 20px;
        }

        button:hover {
            opacity: 0.8;
        }

        .button-edit {
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
            background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0.16, rgb(207, 207, 207)), color-stop(0.79, rgb(252, 252, 252)));
            background-image: linear-gradient(to top, rgb(207, 207, 207) 16%, rgb(252, 252, 252) 79%);
            padding: 4px;
            border: 1px solid #777777;
            color: black;
            text-decoration: none;
            width: 75px;
            height: 25px;
            font-size: 15px;
            margin: 2px 2px;
            margin-top: 10px;
        }

        /* Center the image and position the close button */
        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
            position: relative;
        }

        .avatar {
            width: 150px;
            height: 150px;
            border-radius: 50%;
        }

        /* The Modal (background) */
        .modal {
            display: none;
            position: fixed;
            z-index: 12;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        /* Modal Content Box */
        .modal-content {
            background-color: #fefefe;
            margin: 4% auto 15% auto;
            border: 1px solid #888;
            width: 40%;
            padding-bottom: 30px;
        }

        /* The Close Button (x) */
        .close {
            position: absolute;
            right: 25px;
            top: 0;
            color: #000;
            font-size: 35px;
            font-weight: bold;
        }

        .close:hover, .close:focus {
            color: red;
            cursor: pointer;
        }

        /* Add Zoom Animation */
        .animate {
            animation: zoom 0.6s
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        .custom-select {
            position: relative;
        }

        .custom-select select {
            display: none; /*hide original SELECT element:*/
        }

        .select-selected {
            background-color: #46b7ce;
        }

        /*style the arrow inside the select element:*/
        .select-selected:after {
            position: absolute;
            content: "";
            top: 14px;
            right: 10px;
            width: 0;
            height: 0;
            border: 6px solid transparent;
            border-color: #fff transparent transparent transparent;
        }

        /*point the arrow upwards when the select box is open (active):*/
        .select-selected.select-arrow-active:after {
            border-color: transparent transparent #fff transparent;
            top: 7px;
        }

        /*style the items (options), including the selected item:*/
        .select-items div, .select-selected {
            color: #ffffff;
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            border: 1px solid transparent;
            border-color: transparent transparent #46b7ce transparent;
            cursor: pointer;
            user-select: none;
        }

        /*style items (options):*/
        .select-items {
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            position: absolute;
            background-color: #46b7ce;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 99;
        }

        /*hide the items when the select box is closed:*/
        .select-hide {
            display: none;
        }

        .select-items div:hover, .same-as-selected {
            background-color: rgba(0, 0, 0, 0.1);
        }


    </style>

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

        <div id="navbar">
            <ul>
                <li>
                    <c:choose>
                        <c:when test="${route.getState() eq 'not started'}">
                            <a href="#">
                                <div class="icon">
                                    <i class="fas fa-forward fa-2x"></i>
                                    <i class="fas fa-forward fa-2x" title="Start route"
                                       onclick="document.getElementById('modal-content-start-route').style.display='block'"></i>
                                </div>
                            </a>
                        </c:when>
                        <c:when test="${route.getState() ne 'not started'}">
                            <a href="#">
                                <div class="icon-disabled">
                                    <i class="fas fa-forward fa-2x" title="You don't have privileges"></i>
                                </div>
                            </a>
                        </c:when>
                    </c:choose>
                </li>
                <li>
                    <c:choose>
                        <c:when test="${route.getState() eq 'not started'}">
                            <a href="#">
                                <div class="icon">
                                    <i class="fas fa-minus-square fa-2x"></i>
                                    <i class="fas fa-minus-square fa-2x" title="Delete"
                                       onclick="document.getElementById('modal-wrapper-deleteroute').style.display='block'"></i>
                                </div>
                            </a>
                        </c:when>
                        <c:when test="${route.getState() ne 'not started'}">
                            <a href="#">
                                <div class="icon-disabled">
                                    <i class="fas fa-minus-square fa-2x" title="You don't have privileges"></i>
                                </div>
                            </a>
                        </c:when>
                    </c:choose>


                </li>
            </ul>
        </div>

        <div class="route-table">

            <form id="edit-form" action="/route/update/${route.getId()}" method="post">

                <table id="example" class="display" style="width:100%">
                    <col width="300">

                    <tr>
                        <td>Promotion request name</td>
                        <td>${route.getName()}
                        </td>
                    </tr>
                    <tr>
                        <td>Owner</td>
                        <td>${route.getOwner().getUsername()}
                        </td>
                    </tr>
                    <tr>
                        <td>Promoted document</td>
                        <td><span class="link"><a href="#"
                                                  onclick="openPopup('/document/${item.getId()}${route.getDocumentBeingApproved().getId()}')">${route.getDocumentBeingApproved().getName()}</a></span>
                        </td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td>${route.getState()}
                        </td>
                    </tr>
                    <tr>
                        <td>Check due date</td>
                        <td><input type="text" class="edit-text" name="checkingDueDate"
                                   value="${route.getCheckingDueDate()}" readonly required>
                        </td>
                    </tr>
                    <tr>
                        <td>Person assigned to check</td>
                        <td><input type="text" class="edit-text" name="responsibleForChecking"
                                   value="${route.getResponsibleForChecking().getUsername()}" readonly required>
                        </td>
                    </tr>
                    <tr>
                        <td>Approve due date</td>
                        <td><input type="text" class="edit-text" name="deadline" value="${route.getDeadline()}"
                                   readonly
                                   required>
                        </td>
                    </tr>
                    <tr>
                        <td>Person assign to approve</td>
                        <td><input type="text" class="edit-text" name="responsibleForApproving"
                                   value="${route.getResponsibleForApproving().getUsername()}" readonly required>
                        </td>
                    </tr>
                    <tr>
                        <td>Comments</td>
                        <td><input type="text" class="edit-text" name="comments" value="${route.getComments()}"
                                   readonly required>
                        </td>
                    </tr>
                    <tr>
                        <td>Date of creation</td>
                        <td>${route.getCreationDate()}
                        </td>
                    </tr>
                    <tr>
                        <td>Finish date</td>
                        <td>${route.getFinishDate()}
                        </td>
                    </tr>

                    <script src="/jsscripts/editform.js"></script>

                </table>

                <c:if test="${route.getState() eq 'not started'}">
                    <button type="button" id="editButton" class="button-edit" style="visibility:visible"
                            onclick="edit()">
                        Edit
                    </button>
                    <button type="submit" id="saveButton" class="button-edit" style="visibility:hidden"
                            onclick="save()">
                        Save
                    </button>
                    <button type="button" id="cancelButton" class="button-edit" style="visibility:hidden"
                            onclick="cancel()">Cancel
                    </button>
                    <br>
                </c:if>

                <c:if test="${route.getState() eq 'checking' or route.getState() eq 'approving' or route.getState() eq 'completed'}">
                    <sec:authorize access="hasRole('ADMIN')">
                        <button type="button" id="editButton" class="button-edit" style="visibility:visible"
                                onclick="edit()">
                            Edit
                        </button>
                        <button type="submit" id="saveButton" class="button-edit" style="visibility:hidden"
                                onclick="save()">
                            Save
                        </button>
                        <button type="button" id="cancelButton" class="button-edit" style="visibility:hidden"
                                onclick="cancel()">Cancel
                        </button>
                        <br>
                    </sec:authorize>
                </c:if>

            </form>

        </div>

        <div class="route-stages">

            <ul class="progressbar">
                <c:choose>
                    <c:when test="${route.getState() eq 'not started'}">
                        <li><i class="fas fa-flag fa-3x"></i></li>
                        <li>Checking</li>
                        <li>Approving</li>
                        <li><i class="fas fa-flag-checkered fa-3x"></i></li>
                    </c:when>
                    <c:when test="${route.getState() ne 'checking'}">
                        <li class="active"><i class="fas fa-flag fa-3x"></i></li>
                        <li>Checking</li>
                        <li>Approving</li>
                        <li><i class="fas fa-flag-checkered fa-3x"></i></li>
                    </c:when>
                    <c:when test="${route.getState() ne 'approving'}">
                        <li class="active"><i class="fas fa-flag fa-3x"></i></li>
                        <li class="active">Checking</li>
                        <li>Approving</li>
                        <li><i class="fas fa-flag-checkered fa-3x"></i></li>
                    </c:when>
                    <c:when test="${route.getState() ne 'completed'}">
                        <li class="active"><i class="fas fa-flag fa-3x"></i></li>
                        <li class="active">Checking</li>
                        <li class="active">Approving</li>
                        <li class="active"><i class="fas fa-flag-checkered fa-3x"></i></li>
                    </c:when>
                </c:choose>
            </ul>

        </div>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <div id="modal-content-start-route" class="modal">

        <form class="modal-content animate" action="/route/start/${route.getId()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-content-start-route').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/start-route.png" alt="Document" class="avatar">
                <h1 style="text-align:center">Starting promotion request</h1>
            </div>

            <div class="container">
                <h3 style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">
                    Choose state to promote:
                </h3>
            </div>
            <div class="container">
                <div class="custom-select">
                    <select name="state" required>
                        <option value="">SELECT</option>
                        <option value="release">Release</option>
                        <option value="cancel">Cancel</option>
                    </select>
                </div>

                <div class="container"><h2
                        style="text-align:center; margin-left: 24px; padding-top: 35px; padding-bottom: 20px">New task
                    will be sent to ${route.getResponsibleForChecking().getUsername()}
                </h2></div>

                <button type="submit">Submit</button>
            </div>
        </form>

    </div>

    <div id="modal-wrapper-deleteroute" class="modal">

        <form class="modal-content animate" action="/route/delete/${route.getId()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-deleteroute').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/delete-route.jpeg" alt="Document" class="avatar">
                <h1 style="text-align:center">Delete route</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
                delete route nr ${route.getName()}
            </h3>

                <input type="hidden" name="routeId" value="${route.getId()}">

                <button type="submit">Complete</button>
            </div>
        </form>

    </div>

    <script>
        // If user clicks anywhere outside of the modal, Modal will close

        var modal = document.getElementById('modal-content-start-route');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

    <script>
        // If user clicks anywhere outside of the modal, Modal will close

        var modal = document.getElementById('modal-wrapper-deleteroute');
        window.onclick = function (event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

    <script src="/jsscripts/popup.js"></script>
    <script src="/jsscripts/dropdownmenu.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            // Setup - add a text input to each footer cell
            $('#example tfoot th').each(function () {
                var title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
            });

            // DataTable
            var table = $('#example').DataTable({
                "lengthMenu": [[10, 20], [10, 20]]
            });
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#example').DataTable();

            $('#example_filter').hide(); // Hide default search datatables where example is the ID of table

            $('#txtSearch').on('keyup', function () {
                $('#example')
                    .DataTable()
                    .search($('#txtSearch').val(), false, true)
                    .draw();
            });
        });
    </script>

</div>

</body>
</html>
