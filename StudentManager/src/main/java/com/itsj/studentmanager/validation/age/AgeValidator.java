package com.itsj.studentmanager.validation.age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<ValidAge, Date> {
    @Override
    public boolean isValid(Date birthday, ConstraintValidatorContext context) {
        Integer year = Year.now().getValue();

        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        Integer birthYear = cal.get(Calendar.YEAR);

        Integer age = year - birthYear;
        if(age > 17 && age <80)
        {
            return true;
        }else
        {
            return false;
        }
    }
}
