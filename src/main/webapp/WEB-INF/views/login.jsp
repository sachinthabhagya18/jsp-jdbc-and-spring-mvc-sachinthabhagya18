<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Login</title></head>
<body>
    <h2>Login</h2>
    <form method="post" action="login">
        Email: <input type="text" name="email"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" value="Login"/>
    </form>
    <p style="color:red">${error}</p>
</body>
</html>
