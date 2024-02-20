package br.com.alura.adopet.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.adopet.api.service.PetService;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PetService petSevice;

	@Test
	void deveriaDevolverCodigo200ParaSolicitacaoDeListaPet() throws Exception {
		//Act
		var response = mockMvc.perform(
				get("/pets")
				.contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();
		
		//asserts
		assertEquals(200, response.getStatus());
		
	}

}
