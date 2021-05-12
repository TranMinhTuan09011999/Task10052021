package com.itsj.studentmanager.service;

import com.itsj.studentmanager.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents(int pageNumber);
    /*public List<Student>getAllStudents(int pageNo);*/
    public Optional<Student> getStudentsById(long studentId);
    public Student deleteStudent(Long studentId);
    public Page<Student> findStudentByNameAndEmailAndAddressAndBirthday(String name, String email, String address, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByName(String name, int pageNumber) throws Exception;
    public Page<Student> findByEmail(String email, int pageNumber) throws Exception;
    public Page<Student> findByAddress(String address, int pageNumber) throws Exception;
    public Page<Student> findByBirthday(Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByNameAndEmail(String name, String email, int pageNumber) throws Exception;
    public Page<Student> findByNameAndAddress(String name, String address, int pageNumber) throws Exception;
    public Page<Student> findByNameAndBirthday(String name, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByEmailAndAddress(String email, String address, int pageNumber) throws Exception;
    public Page<Student> findByEmailAndBirthday(String email, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByAddressAndBirthday(String address, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByNameAndEmailAndAddress(String name, String email, String address, int pageNumber) throws Exception;
    public Page<Student> findByNameAndEmailAndBirthday(String name, String email, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByAddressAndEmailAndBirthday(String address, String email, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByAddressAndNameAndBirthday(String address, String name, Date birthday, int pageNumber) throws Exception;
    public Page<Student> findByNameAndEmailAndAddressAndBirthday(String name, String email, String address, Date birthday, int pageNumber) throws Exception;
}
