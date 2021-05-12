package com.itsj.studentmanager.model;

import com.itsj.studentmanager.validation.email.ValidEmail;
import com.itsj.studentmanager.validation.age.ValidAge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "STUDENT")
@ApiModel(value = "All details about the student.")
public class Student extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated Student ID")
    private Long id;
    private String name;

    @NotEmpty(message = "Please enter email")
    @ValidEmail
    @ApiModelProperty(notes = "The student email")
    private String email;

    @ApiModelProperty(notes = "The student address")
    private String address;

    @ValidAge
    @ApiModelProperty(notes = "The student birthday")
    private Date birthday;

    public Student(String name, String email, String address, Date birthday) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }


}
