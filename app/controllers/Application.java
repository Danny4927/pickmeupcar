package controllers;

import models.Offer;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.LoginFormData;
import views.formdata.OfferFormData;
import views.html.createoffer;
import views.html.index;
import views.html.login2;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index(String s) {
        return ok(index.render(s));
    }

    public static Result login() {
        return ok(
                //login2.render(form(Login.class))
        );
    }

//    public static class Login {
//
//        public String email;
//        public String password;
//
//        public String validate() {
//            if (User.authenticate(email, password) == null) {
//                return "Invalid user or password";
//            }
//            return null;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//    }
//    public static Result authenticate() {
//        Form<Login> loginForm = form(Login.class).bindFromRequest();
//        if (loginForm.hasErrors()) {
//            return badRequest(login.render(loginForm));
//        } else {
//            session().clear();
//            session("email", loginForm.get().email);
//            return redirect(routes.Application.index()
//
//            );
//        }
//    }

    /**
     * Process a form submission.
     * First we bind the HTTP POST data to an instance of OfferFormData.
     * The binding process will invoke the OfferFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     * @return The index page with the results of validation.
     */
    public static Result postOfferForm() {

        // Get the submitted form data from the request object, and run validation.
        Form<OfferFormData> formData = form(OfferFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(createoffer.render(formData));
        }
        else {
            // Convert the formData into a offer model instance.
            Offer offer = Offer.makeInstance(formData.get());
            flash("success", "Ihr Angebot von " + offer.getStartPoint() + " nach " + offer.getTargetPoint() + " wurde gespeichert");
            return ok(createoffer.render(formData));
        }
    }

    /**
     * Returns the page where the form is filled by the user whose id is passed, or an empty form
     * if the id is 0.
     * @param id The id of the Student whose data is to be shown.  0 if an empty form is to be shown.
     * @return The page containing the form and data.
     */

    public static Result getOfferForm(long id) {
        OfferFormData offerData = (id == 0) ? new OfferFormData() : Offer.makeOfferFormData(id);
        Form<OfferFormData> formData = form(OfferFormData.class).fill(offerData);
        return ok(createoffer.render(formData));
    }


    /**
     * Process a form submission.
     * First we bind the HTTP POST data to an instance of OfferFormData.
     * The binding process will invoke the OfferFormData.validate() method.
     * If errors are found, re-render the page, displaying the error data.
     * If errors not found, render the page with the good data.
     *
     * @return The index page with the results of validation.
     */
    public static Result postLoginForm() {

        // Get the submitted form data from the request object, and run validation.
        Form<LoginFormData> formData = form(LoginFormData.class).bindFromRequest();

        if (formData.hasErrors()) {
            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(login2.render(formData));
        } else {
            // Convert the formData into a offer model instance.
            User  current = User.getUserByEmail(formData.get().getEmail());
            session().clear();
            session().put("id", String.valueOf(current.getId()));
            session().put("username", String.valueOf(current.getUsername()));
            session().put("connected", "true");
//            return ok(createoffer.render(formData));
            flash("success", "Die Anmeldung  war erfolgreich");
            return ok(index.render("Willkommen zurück "+current.getUsername()));
        }
    }

    public static Result getLoginForm(long id) {
        LoginFormData loginData = new LoginFormData();
        Form<LoginFormData> formData = form(LoginFormData.class).fill(loginData);
//        return ok(createlogin.render(formData));
        return ok(login2.render(formData));
    }


    public static Result logout() {

        session().clear();
        return ok(index.render("Auf Wiedersehen"));
    }
}
