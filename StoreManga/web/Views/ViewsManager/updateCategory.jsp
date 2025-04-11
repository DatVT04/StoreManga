<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
        <link rel="stylesheet" href="CSS/style_updateP.css"/>
        <!-- CKEditor -->
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    </head>
    <body>

        <%@include file="/Views/headerAdmin.jsp" %>

        <c:if test="${not empty requestScope.error}">
        <center  style="color: red" id="error-message">
            ${requestScope.error}
        </center>
    </c:if>
    <form action="updateCategory" method="post">
        <c:set value="${requestScope.category}" var="c"/>
        <h2 style="text-align: center">Add New Category</h2>
        <table>

            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"  value="${c.name}"required></td>
            </tr>


            <tr>
                <td>Description:</td>
                <td>
                    <textarea id="description" name="description" rows="4" cols="36" style="resize: none;" required>${c.describe}</textarea>
                </td>
            </tr>

        </table>
        <input type="hidden" name="id" value="${c.id}">
        <input type="submit" value="Update Category">
        <button type="button" class="button-back" onclick="window.location.href = 'category'">Back</button>
    </form>

    <script>// Khởi tạo CKEditor cho phần Description
        CKEDITOR.replace('description');
    </script>
    <script src="JS/app.js"></script>
    <br>
    <%-- Include footer --%>
    <%@include file="/Views/footer.jsp" %>
</body>
</html>