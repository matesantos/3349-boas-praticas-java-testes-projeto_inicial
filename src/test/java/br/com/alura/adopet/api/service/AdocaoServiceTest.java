package br.com.alura.adopet.api.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoPetDisponivel;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {
	
	@InjectMocks
	private AdocaoService adocaoService;
	
	@Mock
	private AdocaoRepository adocaoRepository;

	@Mock
    private PetRepository petRepository;
	
	@Mock
    private TutorRepository tutorRepository;
	
	private SolicitacaoAdocaoDto dto;
	
	@Mock
	private EmailService emailService;
	
	@Spy
	private List<ValidacaoSolicitacaoAdocao> validadores = new ArrayList<>();
	
	@Mock
	private ValidacaoSolicitacaoAdocao validacao1;
	
	@Mock
	private ValidacaoSolicitacaoAdocao validacao2;
	
	@Mock
	private ValidacaoPetDisponivel validacaoPetDisponivel;
	
	@Mock
	private Pet pet;
	
	@Mock
	private Tutor tutor;
	
	@Mock
	private Abrigo abrigo;
	
	@Captor
	private ArgumentCaptor<Adocao> adocaoCaptor;

	@Test
	void deveriaSalvarAdocaoAoSolicitar() {
		//ARRANGE
		this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo qualquer");
		BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
		BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
		BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);
		
		//ACT
		adocaoService.solicitar(dto);
		
		//ASSERT
		BDDMockito.then(adocaoRepository).should().save(adocaoCaptor.capture());
		var adocaoSalva = adocaoCaptor.getValue();
		assertEquals(pet, adocaoSalva.getPet());
		assertEquals(tutor, adocaoSalva.getTutor());
		assertEquals(dto.motivo(), adocaoSalva.getMotivo());
	}
	
	@Test
	void deveriaChamarValidadesDeAdocaoAoSolicitar() {
		//ARRANGE
		this.dto = new SolicitacaoAdocaoDto(10l, 20l, "motivo qualquer");
		BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
		BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
		BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);
		
		validadores.add(validacao1);
		validadores.add(validacao2);
		
		//ACT
		adocaoService.solicitar(dto);
		
		//ASSERT
		BDDMockito.then(validacao1).should().validar(dto);
		BDDMockito.then(validacao2).should().validar(dto);
		
	}

}
