package controllers;

import models.Offer;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.OfferFormData;
import views.formdata.SearchOfferFormData;
import views.html.createoffer;
import views.html.lastoffers;
import views.html.searchOffers;
import views.html.searchresults;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

/**
 * Created by ru4262 on 01.10.2014.
 */
public class OfferController extends Controller {

    /**
     * Returns the page where the form is filled by the user whose id is passed, or an empty form
     * if the id is 0.
     * @return The page containing the form and data.
     */

    public static Result getLastOffers(){

        SearchOfferFormData offerData =  new SearchOfferFormData();
        Form<SearchOfferFormData> formData = form(SearchOfferFormData.class).fill(offerData);

        List <OfferFormData> offers = new ArrayList<>();
        for (Offer offer: Offer.getAllOffers()){
            offers.add(offer.getOfferFormData());
        }

        return ok(lastoffers.render(offers, formData));
    }

    public static Result postSearchRasults() {
        // Get the submitted form data from the request object, and run validation.
        Form<SearchOfferFormData> formData = form(SearchOfferFormData.class).bindFromRequest();
        if (formData.hasErrors()) {
            List<OfferFormData> offers = new ArrayList<>();

            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(searchOffers.render(formData));
        } else {
            List<OfferFormData> offers = new ArrayList<>();
            for (Offer offer : Offer.getAllOffers()) {
                if (offer.getDate().equals(formData.get().getDate()) &&
                    offer.getStartPoint().equals(formData.get().getStartPoint()) &&
                    offer.getTargetPoint().equals(formData.get().getTargetPoint())){
                        offers.add(offer.getOfferFormData());
                }
            }
            return ok(searchresults.render(offers,formData));
        }
    }

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
            User currentuser = User.getUserById(Long.parseLong(session().get("id")));
            if (currentuser != null) {
                offer.setUser(currentuser);
            }
            flash("success", "Ihr Angebot von " + offer.getStartPoint() + " nach " + offer.getTargetPoint() + " wurde gespeichert" );
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

}
