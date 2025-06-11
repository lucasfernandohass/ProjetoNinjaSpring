package com.esoft.teste_spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esoft.teste_spring.DTOs.VilaDTO;
import com.esoft.teste_spring.Services.VilaService;


@RestController
@RequestMapping("/vilas")
public class VilaController {

    @Autowired
    private VilaService vilaService;

    @GetMapping
    public List<VilaDTO> listar() {
        return vilaService.listar();
    }

    @PostMapping
    public VilaDTO criar(@RequestBody VilaDTO vila) {
        return vilaService.salvar(vila);
    }

    @GetMapping("/{id}")
    public VilaDTO buscarPorId(@PathVariable Long id) {
        return vilaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public VilaDTO atualizar(@PathVariable Long id, @RequestBody VilaDTO vila) {
        return vilaService.atualizar(id, vila);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        vilaService.remover(id);
    }
}