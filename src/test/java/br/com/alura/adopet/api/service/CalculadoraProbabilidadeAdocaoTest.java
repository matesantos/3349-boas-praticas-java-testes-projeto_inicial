package br.com.alura.adopet.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;

class CalculadoraProbabilidadeAdocaoTest {

	@Test
	@DisplayName("Probabilidade alta para gatos jovens com peso baixo")
	void deveriaRetornarProbabilidadeAlta() {
		//idade 4 anos e 4kg - ALTA
		 Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
	                "Abrigo feliz",
	                "94999999999",
	                "abrigofeliz@email.com.br"
	        ));
	        Pet pet = new Pet(new CadastroPetDto(
	                TipoPet.GATO,
	                "Miau",
	                "Siames",
	                4,
	                "Cinza",
	                4.0f
	        ), abrigo);
		var cal = new CalculadoraProbabilidadeAdocao();
		var probabilidade = cal.calcular(pet);
		
		assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
	}
	
	@Test
    @DisplayName("Probabilidade média para gatos idosos com peso baixo")
	void deveriaRetornarProbabilidadeMedia() {
		//idade 15 anos e 4kg - ALTA
		 Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
	                "Abrigo feliz",
	                "94999999999",
	                "abrigofeliz@email.com.br"
	        ));
	        Pet pet = new Pet(new CadastroPetDto(
	                TipoPet.GATO,
	                "Miau",
	                "Siames",
	                15,
	                "Cinza",
	                4.0f
	        ), abrigo);
		var cal = new CalculadoraProbabilidadeAdocao();
		var probabilidade = cal.calcular(pet);
		
		assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
	}

}
