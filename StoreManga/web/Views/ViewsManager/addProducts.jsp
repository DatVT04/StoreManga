<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <link rel="stylesheet" href="CSS/style_updateP.css"/>
        
    </head>
    <body>

        <%@include file="/Views/headerAdmin.jsp" %>

        <c:if test="${not empty requestScope.error}">
        <center  style="color: red" id="error-message">
            ${requestScope.error}
        </center>
    </c:if>
   
    <form action="addProducts" method="post" enctype="multipart/form-data">

        <h2 style="text-align: center">Add New Product</h2>
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" name="quantity" required></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" name="price" required></td>
            </tr>
            <tr>
                <td>Release Date:</td>
                <td><input type="date" name="releaseDate" required ></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" rows="4" cols="36" style="resize: none;" required></textarea></td>
            </tr>

            <tr>
                <td>New Image:</td>
                <td>
                    <input type="file" name="image" id="imageUpload" accept="image/*" required>
                    <br>
                    <img id="previewImage" src="#" alt="Preview" style="max-width: 100px; max-height: 100px; display: none;">
                </td>
            </tr>
            <tr>
                <td><label>Category:</label></td>
                <td>
                    <select name="typename" required>

                        <c:forEach items="${requestScope.product1}" var="x">
                            <option value="${x.id}" ${x.id == c.category.id ? 'selected' : ''}>${x.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Add Product">
        <button type="button" class="button-back" onclick="window.location.href = 'managerProduct'">Back</button>
    </form>
    <script>
        document.getElementById('imageUpload').onchange = function (e) {
            var file = e.target.files[0];


            if (file.size > 5 * 1024 * 1024) {
                alert('Please select a file smaller than 5MB.');
                this.value = '';
            } else {
                var reader = new FileReader();

                reader.onload = function (event) {
                    document.getElementById('previewImage').style.display = 'block';
                    document.getElementById('previewImage').src = event.target.result;
                };

                reader.readAsDataURL(file);
            }
        };
    </script>
    <script src="JS/app.js"></script>
    <br>
    <%-- Include footer --%>
    <%@include file="/Views/footer.jsp" %>
</body>
</html>