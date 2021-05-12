package com.itsj.studentmanager.service.impl;

import com.itsj.studentmanager.model.Student;
import com.itsj.studentmanager.repository.StudentRepo;
import com.itsj.studentmanager.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Override
    public Student saveStudent(Student student){
        studentRepo.save(student);
        return student;
    }

    @Override
    public List<Student> getAllStudents(int pageNumber){
        return studentRepo.findAll(PageRequest.of(pageNumber, 2)).toList();
    }

    @Override
    public Optional<Student> getStudentsById(long studentId){
        return studentRepo.findById(studentId);
    }

    @Override
    public Student deleteStudent(Long studentId){
        studentRepo.deleteById(studentId);
        return null;
    }

    @Override
    public Page<Student> findStudentByNameAndEmailAndAddressAndBirthday(String name, String email, String address, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByNameAndEmailAndAddressAndBirthday(name, email, address, birthday,PageRequest.of(pageNumber, 5));
    }
    @Override
    public Page<Student> findByName(String name, int pageNumber) throws Exception{
        return studentRepo.findByName(name,PageRequest.of(pageNumber, 5));
    }
    @Override
    public Page<Student> findByEmail(String email, int pageNumber) throws Exception{
        return studentRepo.findByEmail(email,PageRequest.of(pageNumber, 5));
    }
    @Override
    public Page<Student> findByAddress(String address, int pageNumber) throws Exception{
        return studentRepo.findByAddress(address,PageRequest.of(pageNumber, 5));
    }
    @Override
    public Page<Student> findByBirthday(Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByBirthday(birthday,PageRequest.of(pageNumber, 5));
    }
    @Override
    public Page<Student> findByNameAndEmail(String name, String email, int pageNumber) throws Exception{
        return studentRepo.findByNameAndEmail(name, email,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByNameAndAddress(String name, String address, int pageNumber) throws Exception{
        return studentRepo.findByNameAndAddress(name, address,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByNameAndBirthday(String name, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByNameAndBirthday(name, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByEmailAndAddress(String email, String address, int pageNumber) throws Exception{
        return studentRepo.findByEmailAndAddress(email,address,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByEmailAndBirthday(String email, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByEmailAndBirthday(email, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByAddressAndBirthday(String address, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByAddressAndBirthday(address, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByNameAndEmailAndAddress(String name, String email, String address, int pageNumber) throws Exception{
        return studentRepo.findByNameAndEmailAndAddress(name, email, address,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByNameAndEmailAndBirthday(String name, String email, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByNameAndEmailAndBirthday(name, email, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByAddressAndEmailAndBirthday(String address, String email, Date birthday, int pageNumber) throws Exception{
        return  studentRepo.findByAddressAndEmailAndBirthday(address, email, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByAddressAndNameAndBirthday(String address, String name, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByAddressAndNameAndBirthday(address, name, birthday,PageRequest.of(pageNumber, 5));
    }

    @Override
    public Page<Student> findByNameAndEmailAndAddressAndBirthday(String name, String email, String address, Date birthday, int pageNumber) throws Exception{
        return studentRepo.findByNameAndEmailAndAddressAndBirthday(name, email, address, birthday,PageRequest.of(pageNumber, 5));
    }

}
