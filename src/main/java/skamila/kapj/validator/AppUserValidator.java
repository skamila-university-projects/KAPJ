package skamila.kapj.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import skamila.kapj.domain.AppUser;

public class AppUserValidator implements Validator {

    private EmailValidator emailValidator = EmailValidator.getInstance();
    private RegexValidator telephoneValidator = new RegexValidator("^\\+[0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3}$");

    @Override
    public boolean supports(Class<?> aClass) {
        return AppUser.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "firstName", "error.field.required");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.field.required");

        String userEmail = ((AppUser) o).getEmail();
        if (!StringUtils.hasText(userEmail)) {
            errors.rejectValue("email", "error.field.required");
        } else if (!emailValidator.isValid(userEmail)) {
            errors.rejectValue("email", "error.email.invalid");
        }

        String userTelephone = ((AppUser) o).getTelephone();
        if (!StringUtils.hasText(userTelephone)) {
            errors.rejectValue("telephone", "error.field.required");
        } else if (!telephoneValidator.isValid(userTelephone)) {
            errors.rejectValue("telephone", "error.telephone.invalid");
        }

    }
}
