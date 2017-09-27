package com.nady.hrtool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nady.hrtool.dao.OfferDao;
import com.nady.hrtool.model.Offer;

@Service("offerService")
@Transactional
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferDao dao;

	public Offer findById(int id) {
		return dao.findById(id);
	}

	public Offer findByOfferName(String offerName) {
		Offer offer = dao.findByOfferName(offerName);
		return offer;
	}


	public void updateOffer(Offer offer) {
		Offer entity = dao.findById(offer.getId());
		if (entity != null) {
			entity.setOfferName(offer.getOfferName());
			entity.setOfferDescription(offer.getOfferDescription());

		}
	}

	@Override
	public boolean isOfferNameUnique(Integer id, String offerName) {
		Offer offer = findByOfferName(offerName);
		return (offer == null || ((id != null) && (offer.getId() == id)));
	}

	public void deleteOfferByOfferName(String offerName) {
		dao.deleteByOfferName(offerName);
	}

	public List<Offer> findAllOffers() {
		return dao.findAllOffers();
	}

	public void saveOrUpdateOffer(Offer offer) {
		dao.saveOrUpdate(offer);
	}
}
