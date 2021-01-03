package skamila.kapj.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import skamila.kapj.domain.AppUser;

public class AppUserValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUser.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "pesel.PESEL", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "error.field.required");

        AppUser appUser = (AppUser) o;
        validateEmail(appUser, errors);
        validatePassword(appUser, errors);
    }

    private void validateEmail(AppUser appUser, Errors errors) {
        String userEmail = appUser.getEmail();
        if (!StringUtils.hasText(userEmail)) {
            errors.rejectValue("email", "error.field.required");
        } else if (!emailValidator.isValid(userEmail)) {
            errors.rejectValue("email", "error.email.invalid");
        }
    }

    private void validatePassword(AppUser appUser, Errors errors) {
        String userPassword = appUser.getPassword();
        if (!StringUtils.hasText(userPassword)) {
            errors.rejectValue("password", "error.field.required");
        } else if (userPassword.length() < 3 || 30 > userPassword.length()) {
            errors.rejectValue("password", "error.password");
        }
    }

}
