package views.formdata;

import models.User;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ru4262 on 20.10.2014.
 */
public class UserFormData {

    public String name = "";
    public String password = "";
    public String email = "";
    private String username;


    public void addUser(User user) {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
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

        if (password == null || password.length() == 0) {
            errors.add(new ValidationError("password", "Kein Password eingegeben"));
        }

        if(errors.size() > 0)
            return errors;

        return null;
    }
}
