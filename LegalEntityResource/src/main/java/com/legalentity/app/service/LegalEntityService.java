package com.legalentity.app.service;

//*******************************************************************
//LegalEntityService.class
//This Service is called from the controller methods, all business logics and 
//calculations are present here
//*******************************************************************

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.legalentity.app.model.LegalEntityBean;
import com.legalentity.app.model.LegalEntityShareHolderBean;

@Service
public class LegalEntityService {

	// Created an empty list object from legalEntityResourceBean to do add, update,
	// get and delete operations
	List<LegalEntityBean> legalEntityList;
	//List<LegalEntityShareHolderBean> legalEntityOneShareHolders;//List of Beans created 
	//List<LegalEntityShareHolderBean> legalEntityTwoShareHolders;
	//List<LegalEntityShareHolderBean> legalEntityThreeShareHolders;

	/*
	 * This Service method is creating new objects of LegalEntityBean and add in the
	 * List of LegalEntityBean
	 */
	public List<LegalEntityBean> createLegalEntity() {
		
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US",5);
		LegalEntityShareHolderBean entity1SH2 = new LegalEntityShareHolderBean("Entity1ShareHolder2", "US",5);
		//legalEntityOneShareHolders = new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2));
		
		LegalEntityShareHolderBean entity2SH1 = new LegalEntityShareHolderBean("Entity2ShareHolder1", "UK",5);
		LegalEntityShareHolderBean entity2SH2 = new LegalEntityShareHolderBean("Entity2ShareHolder1", "UK",5);
		//legalEntityTwoShareHolders = new ArrayList<>(Arrays.asList(entity2SH1, entity2SH2));
		
		LegalEntityShareHolderBean entity3SH1 = new LegalEntityShareHolderBean("Entity3ShareHolder1", "CH",5);
		LegalEntityShareHolderBean entity3SH2 = new LegalEntityShareHolderBean("Entity3ShareHolder1", "CH",5);
		//legalEntityThreeShareHolders = new ArrayList<>(Arrays.asList(entity3SH1, entity3SH2));
		
		legalEntityList = new ArrayList<LegalEntityBean>();

		legalEntityList.add(new LegalEntityBean("Entity1", parseDate("2020-12-01"), "US", 10,new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2))));
		legalEntityList.add(new LegalEntityBean("Entity2", parseDate("2020-11-01"), "UK", 20,new ArrayList<>(Arrays.asList(entity2SH1, entity2SH2))));
		legalEntityList.add(new LegalEntityBean("Entity3", parseDate("2020-10-01"), "CH", 30,new ArrayList<>(Arrays.asList(entity3SH1, entity3SH2))));

		return legalEntityList;
	}

	// Return the contents of legalEntityList
	public List<LegalEntityBean> getAllLegalEntity() {
		return legalEntityList;
	}

	// This method uses Java8 features like Stream and filter to fetch the values
	// corresponding
	// to the input name
	public List<LegalEntityBean> getLegalEntityByName(String entityName) {

		return legalEntityList.stream().filter(p -> p.getEntityName().equals(entityName)).collect(Collectors.toList());

	}

	// Add the incoming object into the bean
	public void addLegalEntityByName(LegalEntityBean leagalEntityBean) {
		legalEntityList.add(leagalEntityBean);
	}

	// This method updates the legalEntityList , we use java 8 feature to update.
	public void updateLegalEntityByName(LegalEntityBean legalEntityBean, String entityName) {

		IntStream.range(0, legalEntityList.size())
				.filter(i -> legalEntityList.get(i).getEntityName().equals(entityName)).findFirst()
				.ifPresent(i -> legalEntityList.set(i, legalEntityBean));
	};

	/*
	 * Loop through the entire legaEntity list , and look for the Index of the value
	 * which has `Entity Name` as the input parameter. once we found the index ,
	 * remove the value
	 */
	public void deleteLegalEntityByName(String entityName) {

		IntStream.range(0, legalEntityList.size())
				.filter(i -> legalEntityList.get(i).getEntityName().equals(entityName)).findFirst()
				.ifPresent(i -> legalEntityList.remove(i));
	};

	// --Static method to convert String to Date type using Java 8 Date feature LocalDate 
	public static Date parseDate(String date) {
		try {
			LocalDate localDate = LocalDate.parse(date);
			return java.sql.Date.valueOf(localDate);
		} catch (Exception e) {
			return null;
		}
	}

}
