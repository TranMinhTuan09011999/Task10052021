package com.itsj.studentmanager.repository;

import com.itsj.studentmanager.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student,Long> {
    Page<Student> findByName(String name, Pageable pageable);
    Page<Student> findByEmail(String email, Pageable pageable);
    Page<Student> findByAddress(String address, Pageable pageable);
    Page<Student> findByBirthday(Date birthday, Pageable pageable);
    Page<Student> findByNameAndEmail(String name, String email, Pageable pageable);
    Page<Student> findByNameAndAddress(String name, String address, Pageable pageable);
    Page<Student> findByNameAndBirthday(String ename, Date birthday, Pageable pageable);
    Page<Student> findByEmailAndAddress(String email, String address, Pageable pageable);
    Page<Student> findByEmailAndBirthday(String email, Date birthday, Pageable pageable);
    Page<Student> findByAddressAndBirthday(String address, Date birthday, Pageable pageable);
    Page<Student> findByNameAndEmailAndAddress(String name, String email, String address, Pageable pageable);
    Page<Student> findByNameAndEmailAndBirthday(String name, String email, Date birthday, Pageable pageable);
    Page<Student> findByAddressAndEmailAndBirthday(String address, String email, Date birthday, Pageable pageable);
    Page<Student> findByAddressAndNameAndBirthday(String address, String name, Date birthday, Pageable pageable);
    Page<Student> findByNameAndEmailAndAddressAndBirthday(String name, String email, String address, Date birthday, Pageable pageable);

    Optional<Student> findByEmail(String email);
}
