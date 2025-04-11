<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="CSS/style_updateP.css"/>
    <!-- CKEditor -->
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
</head>
<body>
    <%@include file="/Views/headerAdmin.jsp" %>
    <c:set var="c" value="${requestScope.dataProduct}" />
    <c:if test="${not empty requestScope.error}">
    <center style="color: red" id="error-message">
        ${requestScope.error}
    </center>
    </c:if>
    <form action="updateProducts" method="post" enctype="multipart/form-data">
        <h2 style="text-align: center">Update Product</h2>
        <table>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" value="${c.name}" required></td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><input type="number" name="quantity" value="${c.quanity}" required></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" name="price" value="${c.price}" required></td>
            </tr>
            <tr>
                <td>Release Date:</td>
                <td><input type="date" name="releaseDate" value="${c.releaseDate}" required></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    <textarea id="description" name="description" rows="4" cols="36" style="resize: none;" required>${c.describe}</textarea>
                </td>
            </tr>
            <tr>
                <td>Current Image:</td>
                <td>
                    <img src="${c.image}" alt="Current Image" style="max-width: 100px; max-height: 100px;">
                    <input type="hidden" name="oldimg" value="${c.image}">
                </td>
            </tr>
            <tr>
                <td>New Image:</td>
                <td>
                    <input type="file" name="image" id="imageUpload" accept="image/*">
                    <br>
                    <img id="previewImage" src="#" alt="Preview" style="max-width: 100px; max-height: 100px; display: none;">
                </td>
            </tr>
            <tr>
                <td><label>Category:</label></td>
                <td>
                    <select name="typename" required>
                        <c:forEach items="${requestScope.categoryID}" var="category">
                            <option value="${category.id}" ${category.id == c.category.id ? 'selected' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>

        <input type="hidden" name="id" value="${c.id}">
        <input type="submit" value="Update Product">
        <button type="button" class="button-back" onclick="window.location.href = 'managerProduct'">Back</button>
    </form>

    <script>
        // Xử lý xem trước hình ảnh mới
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
        
        // Khởi tạo CKEditor cho phần Description
        CKEDITOR.replace('description');
    </script>

    <script src="JS/app.js"></script>
    <br>
    <%-- Include footer --%>
    <%@include file="/Views/footer.jsp" %>
</body>
</html>
