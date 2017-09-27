package com.nady.hrtool.dao;

import java.util.List;

import com.nady.hrtool.model.Offer;

public interface OfferDao {

	Offer findById(int id);

	Offer findByOfferName(String offerName);

	void saveOrUpdate(Offer offer);

	void deleteByOfferName(String offerName);

	List<Offer> findAllOffers();

}
