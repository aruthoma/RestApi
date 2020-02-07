package com.legalentity.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.*;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class LegalEntityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void createAllLegalEntitiesTest() throws Exception {
		 final String uri="/createlegalentities";
		 MvcResult mvcResult =this.mockMvc.perform(get(uri)).andDo(print()).andReturn();
		 int status = mvcResult.getResponse().getStatus();
		 assertEquals(200, status);
	}

	@Test
	public void getAllLegalEntitiesTest() throws Exception {
		final String uri="/getAlllegalentities";
		MvcResult mvcResult =this.mockMvc.perform(get(uri)).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		String value = mvcResult.getResponse().getContentAsString();
		System.out.print("hello world "+value);
		Assert.assertThat(value, CoreMatchers.containsString("Entity1"));
		assertEquals(200, status);
				
	}
	
	/*@Test
	public void deleteLegalEntityTest() throws Exception {
		final String uri="/deletelegalentity/Entity1";
		MvcResult mvcResult =this.mockMvc.perform(delete(uri)).contentType(MediaType.APPLICATION_JSON))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
		int status = mvcResult.getResponse().getStatus();
		int value = mvcResult.getResponse().getContentLength();
		System.out.println("Arun "+value);
		assertEquals(200, status);
				
	}*/

}
