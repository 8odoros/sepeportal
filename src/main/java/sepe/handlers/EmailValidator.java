package sepe.handlers;

import org.springframework.stereotype.Component;
import sepe.config.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator {
    private static Pattern pattern;
    private static Matcher matcher;


    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(Constants.EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

