function validateUserInput() {
    var name = document.getElementById("name").value;
    var login = document.getElementById("login").value;
    var email = document.getElementById("email").value;
    var pass = document.getElementById("password").value;

    if (name === "") {
        alert("Enter User name");
        return false;
    } else if (login === "") {
        alert("Enter User login name");
        return false;
    } else if (email === "") {
        alert("Enter email address");
        return false;
    } else if (pass === "") {
        alert("Enter password");
        return false;
    }
    return true;
}