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
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;

@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComAdocaoEmAndamentoTest {

	@InjectMocks
	private ValidacaoTutorComAdocaoEmAndamento validacao;
	
	@Mock
	private SolicitacaoAdocaoDto dto;
	
	@Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private TutorRepository tutorRepository;
    	
    @Mock
    private Adocao adocao;
    
    @Mock
    private Tutor tutor;
    
	@Test
	@DisplayName("Não deveria permitir adoção.")
	void naoDeveriaPermitirTutorAdotarPetComAdocaoEmAndamento() {
		//ARRANGE
		BDDMockito.given(
				adocaoRepository
				.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
		.willReturn(true);
		
		//ACT + ASSERT
		assertThrows(ValidacaoException.class,() -> validacao.validar(dto));
	}
	
	@Test
	@DisplayName("Deveria permitir adoção.")
	void deveriaPermitirTutorAdotarPetComAdocaoEmAndamento() {
		//ARRANGE
		BDDMockito.given(
				adocaoRepository
				.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
		.willReturn(false);
		
		
		//ACT + ASSERT
		assertDoesNotThrow(() -> validacao.validar(dto));
	}

}
