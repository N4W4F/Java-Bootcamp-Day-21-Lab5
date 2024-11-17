package com.example.studentsystem.Controller;

import com.example.studentsystem.ApiResponse.ApiResponse;
import com.example.studentsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class studentController {

    ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/create-student")
    public ApiResponse createStudent(@RequestBody Student student) {
        students.add(student);

        return new ApiResponse("Student has been created successfully");
    }

    @GetMapping("/display-students")
    public ArrayList<Student> displayStudents() {
        return students;
    }

    @PutMapping("/update-student/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student student) {
        students.set(index, student);

        return new ApiResponse("Student at index " + index + " has been updated successfully");
    }

    @DeleteMapping("/delete-student/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        students.remove(index);

        return new ApiResponse("Student at index " + index + " has been deleted successfully");
    }

    @GetMapping("/classify-students")
    public ApiResponse classifyStudents() {
        ArrayList<Student> firstHonor = new ArrayList<>();
        ArrayList<Student> secondHonor = new ArrayList<>();
        ArrayList<Student> thirdHonor = new ArrayList<>();

        for (Student student : students) {
            if (student.getGPA() >= 4.75) {
                firstHonor.add(student);
            } else if (student.getGPA() >= 4.5) {
                secondHonor.add(student);
            } else if (student.getGPA() >= 4) {
                thirdHonor.add(student);
            }
        }

        String classification = String.format(
                "First Honor Students: %s, Second Honor Students: %s, Third Honor Students: %s",
                firstHonor, secondHonor, thirdHonor
        );

        return new ApiResponse(classification);
    }

    @GetMapping("/get-above-average")
    public ArrayList<Student> getAboveAverage() {
        ArrayList<Student> aboveAverage = new ArrayList<>();
        for (Student student : students)
            if (student.getGPA() > getAverage())
                aboveAverage.add(student);

        return aboveAverage;
    }

    // Helper method for getAboveAverage()
    public double getAverage() {
        double avg = 0;
        for (Student student : students)
            avg += student.getGPA();

        return avg / students.size();
    }
}
