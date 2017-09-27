package com.nady.hrtool.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nady.hrtool.model.Offer;

@Repository("offerDao")
public class OfferDaoImpl extends AbstractDao<Integer, Offer> implements OfferDao {

	static final Logger logger = LoggerFactory.getLogger(OfferDaoImpl.class);

	public Offer findById(int id) {
		return getByKey(id);
	}

	public Offer findByOfferName(String offerName) {
		logger.info("offerName : {}", offerName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("offerName", offerName));
		Offer offer = (Offer) crit.uniqueResult();
		return offer;
	}

	@SuppressWarnings("unchecked")
	public List<Offer> findAllOffers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("offerName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
																		// duplicates.
		List<Offer> offers = (List<Offer>) criteria.list();

		return offers;
	}

	public void deleteByOfferName(String offerName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("offerName", offerName));
		Offer offer = (Offer) crit.uniqueResult();
		delete(offer);
	}

	public void saveOrUpdate(Offer offer) {
		saveUpdate(offer);
	}
}
