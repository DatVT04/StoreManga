

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        
        <link rel="stylesheet" href="CSS/style_productsM.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    </head>
    <body>
        
          <script>
              var error = "${requestScope.error}";
              if (error) {
                  alert(error);
                  window.location.href = "account";
              }
        </script>
        <%@include file="/Views/headerAdmin.jsp"%>
        <div class="container">
            <div id="content-wrapper">

                <h3 style="text-align: center">DANH SÁCH TÀI KHOẢN</h3>
                <button type="button" class="search-btn btn btn-primary btn-add floating-add-button" onclick="location.href = 'addAccount'">
                    <i class="fa fa-plus"></i> Add
                </button>

                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email</th>
                            <th>Role</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;Option</th>
                        </tr>
                    </thead>

                    <c:forEach items="${requestScope.account1}" var="a">

                        <tr>
                            <td>${a.userID}</td>
                            <td>${a.username}</td>
                            <td>${a.email}</td>
                            <td>
                                ${a.getRole().getRoleID()== 1 ? "Admin" : "User"}
                            </td>
                            <td>
                                <a class="btn-edit btn btn-warning" href="updateAccount?id=${a.userID}">
                                    <i class="fa fa-pencil-alt"></i> Edit
                                </a>
                                <a class="btn-dle btn btn-danger" href="#" onclick="HandleDelete('${a.userID}', '${a.username}')">
                                    <i class="fa fa-trash"></i> Delete
                                </a>
                            </td>
                        </tr>

                    </c:forEach>

                </table>
                <c:if test="${not empty requestScope.error}">
                    <p class="text-center"  style="color: red" id="error-message">
                        ${requestScope.error}
                    </p>
                </c:if>
            </div>

        </div>
        <script src="JS/app.js"></script>

        <script>
                                          function HandleDelete(id, name) {
                                              if (confirm("Do you want to delete Account: " + name + "?")) {
                                                  window.location = "deleteAccount?id=" + id + "&name=" + encodeURIComponent(name);
                                              }
                                          }
        </script>
        <br>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
    </body>
</html>
