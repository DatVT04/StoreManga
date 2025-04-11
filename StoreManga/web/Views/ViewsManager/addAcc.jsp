

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Account</title>
        <!-- Bootstrap CSS -->
        
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
          <%@include file="/Views/headerAdmin.jsp" %>
        <div class="container">

            <c:if test="${not empty requestScope.error}">
                <center  style="color: red" id="error-message">
                    ${requestScope.error}
                </center>
            </c:if>
              <script src="JS/app.js"></script>
            <form action="addAccount" method="post">
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div class="card mt-5">
                            <div class="card-body">
                                <h4 class="card-title text-center">Add Account</h4>
                                <form action="signup" method="post"> 
                                    <table>
                                        <tr>
                                            <td>Username</td>
                                            <td><input id="signup-user" type="text" class="form-control" name="signup-user" required></td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td><input id="signup-pass" type="password" class="form-control" name="signup-pass" data-type="password" required></td>
                                        </tr>
                                        <tr>
                                            <td>Repeat Password</td>
                                            <td><input id="repeat-pass" type="password" class="form-control" name="repeat-pass" data-type="password" required></td>
                                        </tr>
                                        <tr>
                                            <td>Email Address</td>
                                            <td><input id="email" type="email" class="form-control" name="email" required></td>
                                        </tr>
                                        <tr>
                                            <td>Role</td>
                                            <td>
                                                <select class="form-control" name="role">                             
                                                    <option value="2" ${a.getRole().getRoleID() == 2 ? "selected" : ""}>User</option>
                                                    <option value="1" ${a.getRole().getRoleID() == 1 ? "selected" : ""}>Admin</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <button type="submit" class="btn btn-primary btn-block">Submit</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <button type="button" class="btn btn-secondary float-left" onclick="window.location.href = 'account'">Back to Home</button>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <input type="hidden" name="id" value="${c.userID}">
        </div>


        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="JS/app.js"></script>
          <br>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
    </body>

    <style>

        .success-message {
            color: green;
            font-weight: bold;
            margin-bottom: 10px;
        }

        /* Style for error message */
        .error-message {
            color: red;
            font-weight: bold;
            margin-bottom: 10px;
        }

        /* Style for form inputs */
        .form-control {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* Style for form buttons */
        .btn {
            padding: 10px 20px;
            margin-bottom: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
        }

        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }

        /* Style for card */
        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            border-radius: 5px;
            padding: 20px;
        }

        /* Style for table */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

    </style>
</html>
