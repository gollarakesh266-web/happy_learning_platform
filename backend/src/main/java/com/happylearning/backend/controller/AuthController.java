package com.happylearning.backend.controller;

import com.happylearning.backend.dto.LoginRequest;
import com.happylearning.backend.dto.LoginResponse;
import com.happylearning.backend.entity.Admin;
import com.happylearning.backend.entity.Student;
import com.happylearning.backend.entity.Teacher;
import com.happylearning.backend.repository.AdminRepository;
import com.happylearning.backend.repository.StudentRepository;
import com.happylearning.backend.repository.TeacherRepository;
import com.happylearning.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Check Student table first
        Optional<Student> student = studentRepository.findByEmail(request.getEmail());
        if (student.isPresent() && student.get().getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(student.get().getEmail(), "STUDENT", student.get().getId());
            return ResponseEntity.ok(new LoginResponse(token, "STUDENT", student.get().getStudentName(), student.get().getId()));
        }

        // Check Teacher table
        Optional<Teacher> teacher = teacherRepository.findByEmail(request.getEmail());
        if (teacher.isPresent() && teacher.get().getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(teacher.get().getEmail(), "TEACHER", teacher.get().getId());
            return ResponseEntity.ok(new LoginResponse(token, "TEACHER", teacher.get().getTeacherName(), teacher.get().getId()));
        }

        // Check Admin table
        Optional<Admin> admin = adminRepository.findByEmail(request.getEmail());
        if (admin.isPresent() && admin.get().getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(admin.get().getEmail(), "ADMIN", admin.get().getId());
            return ResponseEntity.ok(new LoginResponse(token, "ADMIN", admin.get().getAdminName(), admin.get().getId()));
        }

        return ResponseEntity.status(401).body("Invalid email or password");
    }
}
