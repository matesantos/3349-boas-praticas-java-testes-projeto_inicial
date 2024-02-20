package br.com.alura.adopet.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.adopet.api.service.AdocaoService;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private AdocaoService adocaoService;

	@Test
	@DisplayName("Tem que devolver um 400")
	void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
		//ARRANGE
		String json = "{}";
		
		//ACT
		var response = mock.perform(
			post("/adocoes")
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();
		
		//asserts
		assertEquals(400, response.getStatus());
		
	}
	
	@Test
	@DisplayName("Tem que devolver um 200")
	void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
		//ARRANGE
		 String json = """
		            {
		                "idPet": 1,
		                "idTutor": 1,
		                "motivo": "Motivo qualquer"
		            }
		            """;
		
		//ACT
		var response = mock.perform(
			post("/adocoes")
			.content(json)
			.contentType(MediaType.APPLICATION_JSON)
		).andReturn().getResponse();
		
		//asserts
		assertEquals(200, response.getStatus());
		
	}

}
