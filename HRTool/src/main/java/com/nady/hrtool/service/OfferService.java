package com.nady.hrtool.service;

import java.util.List;

import com.nady.hrtool.model.Offer;


public interface OfferService {
	
	Offer findById(int id);
	
	Offer findByOfferName(String offerName);
	
	void saveOrUpdateOffer(Offer offer);
	
	void updateOffer(Offer offer);
	
	void deleteOfferByOfferName(String offerName);

	List<Offer> findAllOffers(); 
	
	boolean isOfferNameUnique(Integer id, String offerName);

}