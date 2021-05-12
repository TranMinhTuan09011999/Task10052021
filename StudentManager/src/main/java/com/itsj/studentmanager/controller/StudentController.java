package com.itsj.studentmanager.controller;

import com.itsj.studentmanager.exception.StudentAlreadyExistedException;
import com.itsj.studentmanager.exception.StudentNotFoundException;
import com.itsj.studentmanager.model.Student;
import com.itsj.studentmanager.repository.StudentRepo;
import com.itsj.studentmanager.service.StudentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "api/student")
@Api(value="Student manager", description="Operations pertaining to student in Student Manager")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;

    @ApiOperation(value = "Add an student")
    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@ApiParam(value = "Student object in database table", required = true) @Valid @RequestBody Student student) {
        String email = student.getEmail();
        System.out.println(email);
        Optional<Student> student1 = studentRepo.findByEmail(email);
        if (student1.isPresent()) {
            throw new StudentAlreadyExistedException("Email has been already existed!!!");
        }
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
    }

    @ApiOperation(value = "View a list of student", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/getAll/{pageNumber}")
    public ResponseEntity<List<Student>> getALLStudent(@PathVariable int pageNumber) {
        List<Student> students = studentService.getAllStudents(pageNumber);
        if(students.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<> (students,HttpStatus.OK);
    }

    @ApiOperation(value = "Get an student by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentsById(@ApiParam(name = "id",
            value = "Student id from which student object will retrieve",
            example = "1",
            required = true) @PathVariable("id") Long studentId){
        Optional<Student> studentData = Optional.ofNullable(studentService.getStudentsById(studentId).orElseThrow(() -> new StudentNotFoundException("Not found Student with id " + studentId)));
        return studentData.map(student -> new ResponseEntity<>(student,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Update an student")
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@ApiParam(name = "id",
            value = "Student Id to update student object",
            example = "1",
            required = true) @PathVariable Long id,@ApiParam(value = "Update student object", required = true) @Valid @RequestBody Student student) {
        Optional<Student> studentData = Optional.ofNullable(studentService.getStudentsById(id).orElseThrow(() -> new StudentNotFoundException("Not found Student with id " + id)));
        return studentData.map(student1 -> {
            student.setId(student1.getId());
            return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Delete an student")
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@ApiParam(name = "id",
            value = "Student Id from which student object will delete from database table",
            example = "1",
            required = true) @PathVariable Long id) {
        //Student studentData = studentService.getStudentsById(id).orElseThrow(() -> new StudentNotFoundException("Not found Student with id " + id));
        studentService.deleteStudent(id);
    }

    @ApiOperation(value = "View a list of student by", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping("/getStudentBy/{pageNumber}")
    public ResponseEntity<?> getStudentBy(@ApiParam(value = "Request to get student", required = true) @RequestBody HashMap<String,String> getStudentRequest, @PathVariable int pageNumber) {
        try {
            String name = getStudentRequest.get("name");
            String email = getStudentRequest.get("email");
            String address = getStudentRequest.get("address");
            String birthdayStr = getStudentRequest.get("birthday");
            Date birthday = null;
            if(birthdayStr != null)
            {
                birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
            }

            Page<Student> obj = null;
            if(address == null && email == null && birthday == null && name != null)
            {
                obj = studentService.findByName(name,pageNumber);
            }
            if(address == null && name == null && birthday == null && email != null)
            {
                obj = studentService.findByEmail(email,pageNumber);
            }
            if(email == null && name == null && birthday == null && address != null)
            {
                obj = studentService.findByAddress(address,pageNumber);
            }
            if(email == null && name == null && address == null && birthday != null)
            {
                obj = studentService.findByBirthday(birthday,pageNumber);
            }
            if(birthday == null && address == null && name != null & email != null)
            {
                obj = studentService.findByNameAndEmail(name, email,pageNumber);
            }
            if(email == null && birthday == null && name != null && address != null)
            {
                obj = studentService.findByNameAndAddress(name,address,pageNumber);
            }
            if(email == null && address == null && name != null & birthday != null)
            {
                obj = studentService.findByNameAndBirthday(name,birthday,pageNumber);
            }
            if(name == null && birthday == null && email != null && address != null)
            {
                obj = studentService.findByEmailAndAddress(email,address,pageNumber);
            }
            if(name == null && address == null && email != null && birthday != null)
            {
                obj = studentService.findByEmailAndBirthday(email, birthday,pageNumber);
            }
            if(name == null && email == null && address != null && birthday != null){
                obj = studentService.findByAddressAndBirthday(address,birthday,pageNumber);
            }
            if(name == null && address != null && email != null && birthday != null){
                obj = studentService.findByAddressAndEmailAndBirthday(address,email,birthday,pageNumber);
            }
            if(email == null && address != null && name != null && birthday != null){
                obj = studentService.findByAddressAndNameAndBirthday(address,name,birthday,pageNumber);
            }
            if(address == null && name != null && email != null && birthday != null){
                obj = studentService.findByNameAndEmailAndBirthday(name,email,birthday,pageNumber);
            }
            if(birthday == null && address != null && email != null && name != null){
                obj = studentService.findByNameAndEmailAndAddress(name,email,address,pageNumber);
            }
            if(name != null && email != null && address != null)
            {
                obj = studentService.findByNameAndEmailAndAddressAndBirthday(name,email,address,birthday,pageNumber);
            }
            if(name == null && email == null && address == null && birthday == null)
            {
                /*obj = studentService.getAllStudents(pageNumber);*/
            }
            return new ResponseEntity<> (obj,HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
