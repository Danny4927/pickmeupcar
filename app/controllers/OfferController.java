package controllers;

import models.Offer;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.OfferFormData;
import views.html.lastoffers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ru4262 on 01.10.2014.
 */
public class OfferController extends Controller {

    public static Result getLastOffers(){
        List <OfferFormData> offers = new ArrayList<>();
        for (Offer offer: Offer.getAllOffers()){
            offers.add(offer.getOfferFormData());
        }
        return ok(lastoffers.render(offers));
    }

}
