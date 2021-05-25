package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatConstrainValidator implements ConstraintValidator<DateFormat, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        String parseDateToString = localDate.toString();
        Pattern pattern = Pattern.compile(parseDateToString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("/\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])*/g");
        boolean result = matcher.find();
        return result;
    }
}
