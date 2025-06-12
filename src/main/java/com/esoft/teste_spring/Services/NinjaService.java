package com.esoft.teste_spring.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esoft.teste_spring.DTOs.NinjaDTO;
import com.esoft.teste_spring.Exceptions.NaoEncontradoException;
import com.esoft.teste_spring.models.Jutsu;
import com.esoft.teste_spring.models.Missao;
import com.esoft.teste_spring.models.Ninja;
import com.esoft.teste_spring.models.Vila;
import com.esoft.teste_spring.repositories.JutsuRepository;
import com.esoft.teste_spring.repositories.MissaoRepository;
import com.esoft.teste_spring.repositories.NinjaRepository;
import com.esoft.teste_spring.repositories.VilaRepository;

import jakarta.transaction.Transactional;

@Service
public class NinjaService{

    private final NinjaRepository ninjaRepository;
    private final MissaoRepository missaoRepository;
    private final VilaRepository vilaRepository;
    private final JutsuRepository jutsuRepository;

    public NinjaService(NinjaRepository ninjaRepository, MissaoRepository missaoRepository, VilaRepository vilaRepository, JutsuRepository jutsuRepository){
        this.ninjaRepository = ninjaRepository;
        this.missaoRepository = missaoRepository;
        this.vilaRepository = vilaRepository;
        this.jutsuRepository = jutsuRepository;
    }

    public List<NinjaDTO> listar(){
        return ninjaRepository.findAll().stream().map(ninja -> new NinjaDTO(ninja)).toList();
    }
    
    @Transactional
    public NinjaDTO salvar(NinjaDTO ninjaDTO){
        Ninja ninjaEntity = new Ninja();
        ninjaEntity.setNome(ninjaDTO.nome());
        ninjaEntity.setIdade(ninjaDTO.idade());
        ninjaEntity.setCla(ninjaDTO.cla());

        if(ninjaDTO.vilaId() != null){
            Vila vila = vilaRepository.findById(ninjaDTO.vilaId())
                .orElseThrow(() -> new NaoEncontradoException("Vila não encontrada"));
            ninjaEntity.setVila(vila);
        }

        if(ninjaDTO.missaoId() != null){
            Missao missao = missaoRepository.findById(ninjaDTO.missaoId())
                .orElseThrow(() -> new NaoEncontradoException("Missão não encontrada"));
            ninjaEntity.setMissao(missao);
        }

        ninjaEntity.setJutsu(null);
        ninjaEntity.setJutsu(null); // pode ser omitido
        Ninja salvo = ninjaRepository.saveAndFlush(ninjaEntity); // <- força o flush, salva imediatamente no banco

        if(ninjaDTO.jutsuIds() != null && !ninjaDTO.jutsuIds().isEmpty()){
            List<Jutsu> jutsus = jutsuRepository.findAllById(ninjaDTO.jutsuIds());
            salvo.setJutsu(jutsus);
            salvo = ninjaRepository.save(salvo);
        }

        return new NinjaDTO(salvo);
    }

    @Transactional
    public NinjaDTO salvar(Long id, NinjaDTO ninjaDTO) {
        Ninja ninjaEntity = ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!"));

        ninjaEntity.setNome(ninjaDTO.nome());
        ninjaEntity.setIdade(ninjaDTO.idade());
        ninjaEntity.setCla(ninjaDTO.cla());

        if (ninjaDTO.vilaId() != null) {
            Vila vila = vilaRepository.findById(ninjaDTO.vilaId())
                .orElseThrow(() -> new NaoEncontradoException("Vila não encontrada"));
            ninjaEntity.setVila(vila);
        } else {
            throw new IllegalArgumentException("Vila não pode ser nula");
        }

        if (ninjaDTO.missaoId() != null) {
            Missao missao = missaoRepository.findById(ninjaDTO.missaoId())
                .orElse(null);
            ninjaEntity.setMissao(missao);
        } else {
            ninjaEntity.setMissao(null);
        }

        if (ninjaDTO.jutsuIds() != null && !ninjaDTO.jutsuIds().isEmpty()) {
            var jutsus = jutsuRepository.findAllById(ninjaDTO.jutsuIds());
            ninjaEntity.setJutsu(jutsus);
        } else {
            ninjaEntity.setJutsu(null);
        }

        ninjaEntity = ninjaRepository.save(ninjaEntity);

        return new NinjaDTO(ninjaEntity);
    }

    public NinjaDTO buscarPorId(Long id){
        return new NinjaDTO(ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!")));
    }

    public void deletar(Long id){
        ninjaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ninja com id " + id + " não foi encontrado!"));
        ninjaRepository.deleteById(id);
    }

}
