package com.university.controller;

import com.university.dao.UserDAO;
import com.university.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String showCourses(Model model) {
        List<Course> courseList = UserDAO.getAllCourses();
        model.addAttribute("courses", courseList);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("user");
        UserDAO.registerCourse(email, courseId);
        return "success";
    }
}
