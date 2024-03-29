package br.com.alura.adopet.api.validacoes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {
	
	@InjectMocks
	private ValidacaoPetDisponivel validacao;
	
	@Mock
	private PetRepository petRepository;
	
	@Mock
	private Pet pet;
	
	@Mock
	private SolicitacaoAdocaoDto dto;

	@Test
	@DisplayName("Deveria permitir a Solicitação do Pet disponível.")
	void deveriaPermitirSolicitacaoDeAdocaoPet() {
		
		BDDMockito
		.given(petRepository.getReferenceById(dto.idPet()))
		.willReturn(pet);
		
		BDDMockito
		.given(pet.getAdotado())
		.willReturn(false);
		
		// ASSERT + ACT
		assertDoesNotThrow(() -> validacao.validar(dto));
	}
	
	@Test
	@DisplayName("Não deveria permitir a Solicitação do Pet indisponível.")
	void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {
		//ARRANGE
		BDDMockito
		.given(petRepository.getReferenceById(dto.idPet()))
		.willReturn(pet);
		
		BDDMockito
		.given(pet.getAdotado())
		.willReturn(true);
		
		// ASSERT + ACT
		assertThrows(ValidacaoException.class,() -> validacao.validar(dto));
	}

}
