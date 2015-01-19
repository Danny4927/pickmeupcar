package controllers;

import models.Offer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.OfferFormData;
import views.formdata.SearchOfferFormData;
import views.html.lastoffers;

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
            for (Offer offer : Offer.getAllOffers()) {
                offers.add(offer.getOfferFormData());
            }

            // Don't call formData.get() when there are errors, pass 'null' to helpers instead.
            flash("error", "Bitte beheben sie zuerst alle Fehler");
            return badRequest(lastoffers.render(offers, formData));
        } else {
            List<OfferFormData> offers = new ArrayList<>();
            for (Offer offer : Offer.getAllOffers()) {
                offers.add(offer.getOfferFormData());
            }
            return ok(lastoffers.render(offers, formData));
        }
    }

}
