package com.esoft.teste_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.JutsuDTO;
import com.esoft.teste_spring.Exceptions.DeleteNegadoException;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Jutsu;
import com.esoft.teste_spring.repositories.JutsuRepository;

import jakarta.transaction.Transactional;

@Service
public class JutsuService {

    private final JutsuRepository jutsuRepository;

    public JutsuService(JutsuRepository jutsuRepository) {
        this.jutsuRepository = jutsuRepository;
    }

    public JutsuDTO salvar(JutsuDTO jutsuDTO) {
        Jutsu jutsu = new Jutsu(jutsuDTO);
        return new JutsuDTO(jutsuRepository.save(jutsu));
    }

    public JutsuDTO atualizar(Long id, JutsuDTO jutsuDTO) {
        Jutsu existente = jutsuRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Jutsu com id " + id + " não encontrado."));

        existente.setNome(jutsuDTO.nome());
        existente.setDescricao(jutsuDTO.descricao());
        existente.setDificuldade(jutsuDTO.dificuldade());

        return new JutsuDTO(jutsuRepository.save(existente));
    }

    public List<JutsuDTO> listar() {
        return jutsuRepository.findAll().stream().map(JutsuDTO::new).toList();
    }

    public JutsuDTO buscarPorId(Long id) {
        return new JutsuDTO(jutsuRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Jutsu com id " + id + " não encontrado.")));
    }

    @Transactional
    public void remover(Long id) {
        Jutsu jutsu = jutsuRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Jutsu com id " + id + " não encontrado."));

        if (jutsu.getNinjas() != null && !jutsu.getNinjas().isEmpty()) {
            throw new DeleteNegadoException("Não é possível excluir um jutsu com ninjas associados.");
        }

        jutsuRepository.deleteById(id);
    }
}