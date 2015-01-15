package views.formdata;

import models.User;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ru4262 on 14.01.2015.
 */
public class LoginFormData {
    public String email;
    public String password;

    /** Required for form instantiation. */
    public LoginFormData() {
    }

    public LoginFormData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Validates Form<StudentFormData>.
     * Called automatically in the controller by bindFromRequest().
     *
     * Validation checks include:
     * <ul>
     * <li> Name must be non-empty.
     * <li> Password must be at least five characters.
     * <li> Hobbies (plural) are optional, but if specified, must exist in database.
     * <li> Grade Level is required and must exist in database.
     * <li> GPA is required and must exist in database.
     * <li> Majors (plural) are optional, but if specified, must exist in database.
     * </ul>
     *
     * @return Null if valid, or a List[ValidationError] if problems found.
     */
    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<ValidationError>();

        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "Keine Emailadresse eingegeben"));
        }
        User currentUser = User.getUserByEmail(email);
        if (currentUser == null ) {
            errors.add(new ValidationError("email", "Ihre Emailadresse wurde nicht im System gefunden"));
        }

        else if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "Kein Password eingegeben"));
        }

        else if (!currentUser.getPassword().equals(password)){
            errors.add(new ValidationError("password", "Falsche Passwort eingegeben"));
        }

        if(errors.size() > 0) {
            return errors;
        }


        return null;
    }


}
