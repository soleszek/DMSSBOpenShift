<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Information about user</title>
    <link rel="stylesheet" href="/style/documents-view.css" type="text/css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

    <script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>

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

        .file {
            background-color: #46b7ce;
            color: white;
            padding: 14px 20px;
            margin-left: 24px;
            margin-right: 50px;
            border: none;
            cursor: pointer;
            font-size: 15px;
            width: 85%;
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

    <div id="search">

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
        <div class="optionL"><a href="/messages/unread">Unread (${newMessagesCount})</a></div>
        <div class="optionL"><a href="/messages/received">Received</a></div>
        <div class="optionL"><a href="/messages/sent">Sent</a></div>
        <div class="optionL"><a href="/messages/deleted">Deleted</a></div>
        <div style="clear: both"></div>
    </div>

    <div id="content">

        <div id="navbar">
            <ul>
                <li>
                    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                        <a href="#">
                            <div class="icon">
                                <i class="far fa-comments fa-2x"></i>
                                <i class="far fa-comments fa-2x" title="Create new message"
                                   onclick="document.getElementById('modal-wrapper-newmessage').style.display='block'"></i>
                            </div>
                        </a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                        <a href="#">
                            <div class="icon">
                                <i class="fas fa-trash-alt fa-2x"></i>
                                <i class="fas fa-trash-alt fa-2x" title="Move to trash"
                                   onclick="document.getElementById('modal-wrapper-movemessagetotrash').style.display='block'"></i>
                            </div>
                        </a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="hasAnyRole('MANAGER','CONTRIBUTOR','ADMIN')">
                        <a href="#">
                            <div class="icon">
                                <i class="fas fa-user-plus fa-2x"></i>
                                <i class="fas fa-user-plus fa-2x" title="Change your role"
                                   onclick="document.getElementById('modal-wrapper-deactivateuser').style.display='block'"></i>
                            </div>
                        </a>
                    </sec:authorize>
                </li>
            </ul>
        </div>

            <table id="example" class="display" style="width:100%">
                <col width="220">

                <tr>
                    <td>Name</td>
                    <td>${oneMessage.getName()}
                    </td>
                </tr>
                <tr>
                    <td>Title</td>
                    <td>${oneMessage.getTitle()}</td>
                </tr>
                <tr>
                    <td>Content</td>
                    <td><textarea class="noedit-text" rows="4" cols="50" name="content"
                                  style="resize:none" disabled>${oneMessage.getContent()}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>Sender</td>
                    <td>${oneMessage.getSender().getUsername()}</td>
                </tr>
                <tr>
                    <td>Sending date</td>
                    <td>${oneMessage.getSendingDate()}
                    </td>
                </tr>
                <tr>
                    <td>Receiving date</td>
                    <td>${oneMessage.getReceivingDate()}
                    </td>
                </tr>

            </table>

    </div>

    <div id="footer">
        Sylwester Oleszek 2018 &copy;
    </div>

    <div id="modal-wrapper-deleteuser" class="modal">

        <form class="modal-content animate" action="/delete/user/${user.getUser_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-deleteuser').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/delete-user.png" alt="Document" class="avatar">
                <h1 style="text-align:center">Delete user</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
                delete ${user.getUsername()}
            </h3>
                <button type="submit">Complete</button>
            </div>
        </form>

    </div>

    <div id="modal-wrapper-movemessagetotrash" class="modal">

        <form class="modal-content animate" action="/trash/message/${oneMessage.getMessage_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-movemessagetotrash').style.display='none'" class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/trash.png" alt="Trash" class="avatar">
                <h1 style="text-align:center">Move message to trash</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">Are you sure you want to move this message to the trash?
            </h3>
                <button type="submit">Confirm</button>
            </div>
        </form>
    </div>

    <div id="modal-wrapper-deactivateuser" class="modal">

        <form class="modal-content animate" action="/status/user/${user.getUser_id()}" method="get">

            <div class="imgcontainer">
                <span onclick="document.getElementById('modal-wrapper-deactivateuser').style.display='none'"
                      class="close"
                      title="Close PopUp">&times;</span>
                <img src="/style/deactivate-user.png" alt="Document" class="avatar">
                <c:choose>
                <c:when test="${user.getEnabled() eq '1'}">
                <h1 style="text-align:center">Deactivate user</h1>
            </div>

            <div class="container"><h3
                    style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
                deactivate ${user.getUsername()}
            </h3>
                <button type="submit">Complete</button>
            </div>
            </c:when>
            <c:otherwise>
            <h1 style="text-align:center">Activate user</h1>
    </div>

    <div class="container"><h3
            style="text-align:left; margin-left: 24px; padding-top: 35px; padding-bottom: 15px">You are about to
        activate ${user.getUsername()}
    </h3>
        <button type="submit">Complete</button>
    </div>
    </c:otherwise>
    </c:choose>
    </form>

</div>

<script>
    // If user clicks anywhere outside of the modal, Modal will close

    var modal = document.getElementById('modal-wrapper-movemessagetotrash');
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

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
