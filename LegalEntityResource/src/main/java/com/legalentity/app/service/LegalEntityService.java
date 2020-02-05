package com.legalentity.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.legalentity.app.model.LegalEntityBean;

@Service
public class LegalEntityService {

	// Created an empty list object from legalEntityResourceBean to do add, update, get and delete operations 
	List<LegalEntityBean> legalEntity = new ArrayList<LegalEntityBean>();

	public List<LegalEntityBean> createLegalEntity() {

		legalEntity.add(new LegalEntityBean("Entity1", parseDate("2020-12-01"), "US", 10));
		legalEntity.add(new LegalEntityBean("Entity2", parseDate("2020-11-01"), "UK", 20));
		legalEntity.add(new LegalEntityBean("Entity3", parseDate("2020-10-01"), "CH", 30));

		return legalEntity;
	}

	public List<LegalEntityBean> getAllLegalEntity() {
		return legalEntity;
	}

	public LegalEntityBean getLegalEntityByName(String name) {
		//--- Adding Optional(Java 8 Feature) to avoid crash of the program.
		Optional<LegalEntityBean> legalEntityFound = legalEntity.stream().filter(p -> p.getEntityName().equals(name))
				.findFirst();
		if (legalEntityFound.isPresent())//Check if the we have found any value or not
			return legalEntityFound.get();// Return the found value
		else
			return null;//No value is found , so returning NULL
	}

	public void addLegalEntityByName(LegalEntityBean leagalEntity) {
		legalEntity.add(leagalEntity);
	}
    
	public void updateLegalEntityByName(LegalEntityBean legalEntityBean, String entityname) {
		
		for(int i = 0; i < legalEntity.size() ; i++) {
			LegalEntityBean l = legalEntity.get(i);
			if(l.getEntityName().equals(entityname)) {
				legalEntity.set(i, legalEntityBean);
				return;
			}
		}	
	   
		legalEntity.stream().map(p ->  p.getEntityName().equals(entityname) ? legalEntityBean : p );
	};

	// --Static method to convert String to Date type using Java 8 Date apis
	public static Date parseDate(String date) {
		try {
			LocalDate localDate = LocalDate.parse(date);
			return java.sql.Date.valueOf(localDate);
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteLegalEntityByName(String entityname) {
		// TODO Auto-generated method stub
		for(int i = 0; i < legalEntity.size() ; i++) {
			LegalEntityBean l = legalEntity.get(i);
			if(l.getEntityName().equals(entityname)) {
				legalEntity.remove(i);
				return;
			}
		}	
		
	}

}
