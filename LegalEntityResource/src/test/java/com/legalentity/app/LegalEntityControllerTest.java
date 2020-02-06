package com.legalentity.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.legalentity.app.controller.LegalEntityController;
import com.legalentity.app.model.LegalEntityBean;
import com.legalentity.app.model.LegalEntityShareHolderBean;
import com.legalentity.app.service.LegalEntityService;


@RunWith(MockitoJUnitRunner.class)
public class LegalEntityControllerTest {
	
	@InjectMocks
	private LegalEntityController legalEntityController;

	@Mock
	private LegalEntityService legalEntityService;

	@Test
	public void retrieveAllItems_basic() {
		
		List<LegalEntityBean> legalEntityList;
		
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


		
		
		when(legalEntityService.createLegalEntity()).thenReturn(legalEntityList);
		List<LegalEntityBean> legalEntitiesValues = legalEntityService.createLegalEntity();
		
		assertEquals("Entity1", legalEntitiesValues.get(0).getEntityName());
		assertEquals("Entity3", legalEntitiesValues.get(2).getEntityName());
	}
	
	// --Static method to convert String to Date type 
			public static Date parseDate(String date) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
				 
				String dateInString = date;
				try {
					Date dateToSend = formatter.parse(dateInString);
					return dateToSend;
				} catch (ParseException e) {
					return null;
				}
		
	};

}
