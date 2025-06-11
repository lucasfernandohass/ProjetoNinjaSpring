package com.esoft.teste_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.VilaDTO;
import com.esoft.teste_spring.Exceptions.DeleteNegadoException;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Vila;
import com.esoft.teste_spring.repositories.VilaRepository;

import jakarta.transaction.Transactional;

@Service
public class VilaService{

    private final VilaRepository vilaRepository;

    public VilaService(VilaRepository vilaRepository){
        this.vilaRepository = vilaRepository;
    }

    public VilaDTO salvar(VilaDTO vilaDTO){
        Vila vila = new Vila(vilaDTO);
        return new VilaDTO(vilaRepository.save(vila));
    }

    public VilaDTO atualizar(Long id, VilaDTO vilaDTO){
        Vila existente = vilaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não encontrada."));

        existente.setNome(vilaDTO.nome());
        existente.setHabitantes(vilaDTO.habitantes());

        return new VilaDTO(vilaRepository.save(existente));
    }

    public List<VilaDTO> listar(){
        return vilaRepository.findAll().stream().map(VilaDTO::new).toList();
    }

    public VilaDTO buscarPorId(Long id){
        return new VilaDTO(vilaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não encontrada.")));
    }

    @Transactional
    public void remover(Long id){
        Vila vila = vilaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Vila com id " + id + " não encontrada."));

        if(vila.getNinjas() != null && !vila.getNinjas().isEmpty()){
            throw new DeleteNegadoException("Não é possível excluir uma vila com ninjas associados.");
        }

        vilaRepository.deleteById(id);
    }
}