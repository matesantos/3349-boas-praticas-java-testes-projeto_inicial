package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

	@InjectMocks
	private PetService petService;

	@Mock
	private PetRepository petRepository;

	@Mock
	private Abrigo abrigo;
	
	@Mock
	private CadastroPetDto dto;

	@Test
	void deveriaRetornarOsPetsDisponiveis() {
		// ACT
		petService.buscarPetsDisponiveis();

		// ASSERT
		BDDMockito.then(petRepository).should().findAllByAdotadoFalse();
	}

	@Test
	void deveriaCadastrarPet() {
		// Act
		petService.cadastrarPet(abrigo, dto);

		// Assert
		BDDMockito.then(petRepository).should().save(new Pet(dto, abrigo));
	}

}
