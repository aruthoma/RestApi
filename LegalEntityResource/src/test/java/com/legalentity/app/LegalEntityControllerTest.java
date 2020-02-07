package com.legalentity.app;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.legalentity.app.model.LegalEntityBean;
import com.legalentity.app.model.LegalEntityShareHolderBean;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LegalEntityControllerTest {
	
	//---set the type of response/request as APPLICATION_JSON
	public final MediaType JSON_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype()                   
            );
	
	//Static method to convert the POJO to Json
	private static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	//http://localhost:8081/createlegalentities/ URL to be called and just check the response
	public void createAllLegalEntities() throws Exception {
		 final String uri="/createlegalentities";//Create the Rest URL 
		 MvcResult mvcResult =this.mockMvc.perform(get(uri)).andDo(print()).andReturn();
		 int status = mvcResult.getResponse().getStatus();
		 assertEquals(200, status);//Check Status 200 for successful completion of the request
		 
	}

	@Test
	/*http://localhost:8081/getAlllegalentities/  URL to be called and check if the first element's entity name is 
	 * "Entity1" or not*/
	public void getAllLegalEntities() throws Exception {
		final String uri="/getAlllegalentities";
		
		this.mockMvc.perform(get(uri))
				.andExpect(status().isOk())
				.andExpect(content().contentType(JSON_TYPE))
                .andExpect(jsonPath("$[0].entityName", is("Entity1")));
	}
	
	/*http://localhost:8081//updatelegalentity/{entityname} URL with 
	 * entityName as "Entity1" and pass a new object with the countryOfIncorp as JPN
	 * and check the output is updated with the value contains JPN */
	@Test
	public void updateEntity() throws Exception {
		final String uri="/updatelegalentity/Entity1";
		
		//create entities- pre test condition
		this.mockMvc.perform(get("/createlegalentities"));
		
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US",5);
		LegalEntityShareHolderBean entity1SH2 = new LegalEntityShareHolderBean("Entity1ShareHolder2", "US",5);
		LegalEntityBean legalEntity=new LegalEntityBean("Entity1", new Date()
				, "JPN", 10,new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2)));
		
		this.mockMvc.perform(put(uri)
				.contentType(JSON_TYPE)
                .content(convertObjectToJsonBytes(legalEntity)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(JSON_TYPE))
                .andExpect(jsonPath("$[0].entityName", is("Entity1")))
                .andExpect(jsonPath("$[0].countryOfIncorp", is("JPN")));
	}
	
	
	/*Check the URL http://localhost:8081/addlegalentity/ and pass the object (Entity4) in POST request
	 * and check if the response contains "Entity4" , which is added just now*/
	@Test
	public void addEntity() throws Exception {
		
		//--setting the uri
		final String uri="/addlegalentity";
		
		LegalEntityShareHolderBean entity1SH1 = new LegalEntityShareHolderBean("Entity1ShareHolder1", "US",5);
		LegalEntityShareHolderBean entity1SH2 = new LegalEntityShareHolderBean("Entity1ShareHolder2", "US",5);
		LegalEntityBean legalEntity=new LegalEntityBean("Entity4", new Date()
				, "US", 10,new ArrayList<>(Arrays.asList(entity1SH1, entity1SH2)));
		
		MvcResult mvcResult = this.mockMvc.perform(post(uri)
				.contentType(JSON_TYPE)
                .content(convertObjectToJsonBytes(legalEntity)))
				.andDo(print()).andReturn();
				
		String value = mvcResult.getResponse().getContentAsString();
		Assert.assertThat(value, CoreMatchers.containsString("Entity4"));
		Assert.assertThat(value, CoreMatchers.containsString("Entity1ShareHolder2"));
	}
	
	/*http://localhost:8081//updatelegalentity/{entityname} URL in our case , we remove Entity2
	 * and check if the value is still there in the list of objects*/
	
	@Test
	public void deleteEntity() throws Exception {
		final String uri="/deletelegalentity/Entity1";
		
		//create entities- pre test condition
		this.mockMvc.perform(get("/createlegalentities"));
		
		this.mockMvc.perform(delete(uri))
				.andExpect(content().contentType(JSON_TYPE))
                .andExpect(jsonPath("$[0].entityName", is("Entity2")));
		
		MvcResult mvcResult = this.mockMvc.perform(delete(uri))
				.andExpect(content().contentType(JSON_TYPE))
				.andDo(print()).andReturn();
		
		String value = mvcResult.getResponse().getContentAsString();
		Assert.assertThat(value, CoreMatchers.containsString("Entity2"));

	}

}
