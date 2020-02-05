package com.legalentity.app.controller;

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

	@Autowired
	private LegalEntityService legalEntityService;

	@RequestMapping("/createlegalentities")
	public List<LegalEntityBean> createLegalEntities() {
		return legalEntityService.createLegalEntity();
	}

	@RequestMapping("/getAlllegalentities")
	public List<LegalEntityBean> getAllLegalEntities() {
		return legalEntityService.getAllLegalEntity();
	}
	
	@RequestMapping("/getlegalentity/{entityname}")
	public LegalEntityBean getLegalEntityByName(@PathVariable String entityName) {
		return legalEntityService.getLegalEntityByName(entityName);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addlegalentity")
	public List<LegalEntityBean> addLegalEntityByName(@RequestBody LegalEntityBean legalEntity) {
		legalEntityService.addLegalEntityByName(legalEntity);
		return legalEntityService.getAllLegalEntity();
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updatelegalentity/{entityname}")
	public List<LegalEntityBean> updateLegalEntityByName(@RequestBody LegalEntityBean legalEntityBean,@PathVariable String entityname) {
		legalEntityService.updateLegalEntityByName(legalEntityBean, entityname);;
		return legalEntityService.getAllLegalEntity();
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deletelegalentity/{entityname}")
	public List<LegalEntityBean> deleteLegalEntityByName(@PathVariable String entityname) {
		legalEntityService.deleteLegalEntityByName( entityname);;
		return legalEntityService.getAllLegalEntity();
	}
	

	
	

}
