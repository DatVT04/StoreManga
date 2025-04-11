/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function () {
    var errorMessage = document.getElementById('error-message');
    if (errorMessage) {
        setTimeout(function () {
            errorMessage.parentNode.removeChild(errorMessage);
        }, 5000);
    }
});


function HandleDelete(id) {
    if (confirm("Do you want to delete ID = " + id + "?")) {
        window.location = "delete?id=" + id;
    }

}

