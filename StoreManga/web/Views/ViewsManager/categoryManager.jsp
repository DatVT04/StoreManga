

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
        
        <link rel="stylesheet" href="CSS/style_productsM.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

    </head>
    <body>
        <%@include file="/Views/headerAdmin.jsp"%>

        <script>
              var error = "${requestScope.error}";
              if (error) {
                  alert(error);
                  window.location.href = "category";
              }
        </script>
        <div class="container">
            <div id="content-wrapper">

                <h3 style="text-align: center">DANH SÁCH CÁC THỂ LOẠI TRUYỆN</h3>
                <button type="button" class="search-btn btn btn-primary btn-add floating-add-button" onclick="location.href = 'addCategory'">
                    <i class="fa fa-plus"></i> Add
                </button>

                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Option</th>
                        </tr>
                    </thead>

                    <c:forEach items="${requestScope.category}" var="a">

                        <tr>
                            <td>${a.name}</td>
                            <td>${a.describe}</td>

                            <td>
                                <a class="btn-dle btn btn-danger" href="#" onclick="HandleDelete('${a.id}', '${a.name}')">
                                    <i class="fa fa-trash"></i> Delete
                                </a>
                                <a class="btn-edit btn btn-success" href="updateCategory?id=${a.id}">
                                    <i class="fa fa-pencil-alt"></i> Update
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
                                        if (confirm("Do you want to delete Category: " +name + "?")) {
                                            window.location = "deleteCategory?id=" + id;
                                        }
                                    }
        </script>

        <br>
        <%-- Include footer --%>
        <%@include file="/Views/footer.jsp" %>
    </body>
</html>
