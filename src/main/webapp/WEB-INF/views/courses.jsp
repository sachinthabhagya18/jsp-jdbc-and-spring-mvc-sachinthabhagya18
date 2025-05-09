<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Available Courses</title></head>
<body>
    <h2>Available Courses</h2>
    <c:forEach var="course" items="${courses}">
        <p>
            <strong>${course.name}</strong> by ${course.instructor} (${course.credits} credits)
            <form method="post" action="register/${course.courseId}">
                <button type="submit">Register</button>
            </form>
        </p>
    </c:forEach>
</body>
</html>
