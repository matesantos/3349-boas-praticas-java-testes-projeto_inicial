package br.com.alura.adopet.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.adopet.api.service.TutorService;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Mock
	private TutorService tutorService;
	
	@Test
	void deveriaRetornar400() throws Exception {
		//Arrange
		String json = "{}";
		
		//Act
		var response = mock.perform(
				post("/abrigos")
				.contentType(json)
				.contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();
		
		assertEquals(400, response.getStatus());
	}

}
