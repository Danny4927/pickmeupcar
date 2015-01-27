package views.formdata;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danny on 29.09.2014.
 */
public class SearchOfferFormData {

    public String startPoint = "";
    public String targetPoint = "";
    public String date = "";



    /** Required for form instantiation. */
    public SearchOfferFormData() {
    }

    public SearchOfferFormData(String startPoint, String targetPoint, String date, String time) {
        this.startPoint = startPoint;
        this.targetPoint = targetPoint;
        this.date = date;
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

        if (startPoint == null || startPoint.length() == 0) {
            errors.add(new ValidationError("startPoint", "Kein gültiger Startpunkt angegeben."));
        }

        if (targetPoint == null || targetPoint.length() == 0) {
            errors.add(new ValidationError("targetPoint", "Kein gültiges Ziel angegeben."));

        }

        // Datum validieren
        if (date == null || date.length() == 0) {
            errors.add(new ValidationError("date", "Kein gültiges Datum angegeben."));

        }




        if(errors.size() > 0)
            return errors;

        return null;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
