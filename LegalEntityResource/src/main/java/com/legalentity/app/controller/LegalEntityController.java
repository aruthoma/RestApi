package com.legalentity.app.controller;

//*******************************************************************
//LegalEntityController.class
// This Controller handles the incoming HTTP requests and call the service methods to fetch the information
//  and returns the value accordingly
//*******************************************************************

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.legalentity.app.model.LegalEntityBean;
import com.legalentity.app.service.LegalEntityService;

@RestController
public class LegalEntityController {

	//Inject the service -Business Logic
	@Autowired
	private LegalEntityService legalEntityService;

	/*http://localhost:8081/createlegalentities/  URL 
	 * will create three entity objects */
	@RequestMapping("/createlegalentities")
	public List<LegalEntityBean> createLegalEntities() {
		return legalEntityService.createLegalEntity();
	}

	/*http://localhost:8081/getAlllegalentities/  URL 
	 * will show all the entity objects in the bean object*/
	@RequestMapping("/getAlllegalentities")
	public List<LegalEntityBean> getAllLegalEntities() {
		return legalEntityService.getAllLegalEntity();
	}

	/*http://localhost:8081/getlegalentity/{entityname}  URL 
	 * will find a specific entity we are searching for .
	we can pass the `Entity Name` as the parameter,if the entityName is not present, this api will return null */
	@RequestMapping(method=RequestMethod.GET,value="/getlegalentity/{entityName}")
	public List<LegalEntityBean> getLegalEntityByName(@PathVariable String entityName) {
		return legalEntityService.getLegalEntityByName(entityName);
	}

	/*http://localhost:8081/addlegalentity URL 
	 * will add a new object in the bean object and return the bean object.
	So that we can verify if the value is added or not. This is a POST request - We need some rest client to Test this */
	@RequestMapping(method=RequestMethod.POST,value="/addlegalentity")
	public List<LegalEntityBean> addLegalEntityByName(@RequestBody LegalEntityBean legalEntity) {
		legalEntityService.addLegalEntityByName(legalEntity);
		return legalEntityService.getAllLegalEntity();
	}

	/*http://localhost:8081//updatelegalentity/{entityname} URL 
	 * will update in the bean object and return the bean object.
	So that we can verify if the value is updated or not. This is a PUT request - We need some rest client to Test this.
	 We should pass the `entityName` which needs to be updated and in the request body, we should pass the JSON value , which will replace the existing value for the `Entity Name` */
	@RequestMapping(method=RequestMethod.PUT,value="/updatelegalentity/{entityName}")
	public List<LegalEntityBean> updateLegalEntityByName(@RequestBody LegalEntityBean legalEntityBean,@PathVariable String entityName) {
		legalEntityService.updateLegalEntityByName(legalEntityBean, entityName);
		return legalEntityService.getAllLegalEntity();
	}

	/*http://localhost:8081//deletelegalentity/{entityname} URL 
	 * will delete a specific object from the bean object and return the bean object.
	So that we can verify if the value is deleted or not. This is a DELETE request */
	@RequestMapping(method=RequestMethod.DELETE,value="/deletelegalentity/{entityName}")
	public List<LegalEntityBean> deleteLegalEntityByName(@PathVariable String entityName) {
		legalEntityService.deleteLegalEntityByName( entityName);;
		return legalEntityService.getAllLegalEntity();
	}
	

	
	

}
