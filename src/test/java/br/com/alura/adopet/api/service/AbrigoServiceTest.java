package br.com.alura.adopet.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

	@InjectMocks
	private AbrigoService abrigoService;
	
	@Mock
	private AbrigoRepository abrigoRepository;
	
	@Mock
	private Abrigo abrigo;

	@Mock
	private PetRepository petRepository;
	
	@Mock
	private CadastroAbrigoDto dto;
	
	@Captor
	private ArgumentCaptor<Abrigo> abrigoCaptor;

	@Test
	void deveriaCadastrarUmAbrigo() {
		//arrange
		this.dto = new CadastroAbrigoDto("abrigo","83987350305","abrigo@abrigo.com");
		BDDMockito
			.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
			.willReturn(false);
		
		//act
		abrigoService.cadastrar(dto);
		
		//assert
		BDDMockito.then(abrigoRepository).should().save(abrigoCaptor.capture());
		var abrigoCadastrado = abrigoCaptor.getValue();
		assertEquals("abrigo", abrigoCadastrado.getNome());
		assertEquals("83987350305", abrigoCadastrado.getTelefone());
		assertEquals("abrigo@abrigo.com", abrigoCadastrado.getEmail());
	}
	
	@Test
	void naoDeveriaCadastrarUmAbrigo() {
		//arrange
		BDDMockito
			.given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
			.willReturn(true);
		
		//act + assert
		assertThrows(ValidacaoException.class, ()-> abrigoService.cadastrar(dto));
	}
	
	@Test
	void deveriaListarAbrigo() {
		//act
		abrigoService.listar();
		
		//ASSERT
		BDDMockito.then(abrigoRepository).should().findAll();
	}
	
	@Test
	void deveriaChamarUmaLisDePetsDoAbrigoPeloNome() {
		String nome = "Ceara";
		
		//arrange
		BDDMockito
			.given(abrigoRepository.findByNome(nome))
			.willReturn(Optional.of(abrigo));
		
		//act
		abrigoService.listarPetsDoAbrigo(nome);
		
		//ASSERT
		BDDMockito.then(abrigoRepository).should().findByNome(nome);
	}
	
	

}
