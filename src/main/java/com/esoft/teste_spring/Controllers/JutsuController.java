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

import com.esoft.teste_spring.DTOs.JutsuDTO;
import com.esoft.teste_spring.Services.JutsuService;

@RestController
@RequestMapping("/jutsus")
public class JutsuController {

    @Autowired
    private JutsuService jutsuService;

    @GetMapping
    public List<JutsuDTO> listar() {
        return jutsuService.listar();
    }

    @PostMapping
    public JutsuDTO criar(@RequestBody JutsuDTO jutsu) {
        return jutsuService.salvar(jutsu);
    }

    @GetMapping("/{id}")
    public JutsuDTO buscarPorId(@PathVariable Long id) {
        return jutsuService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public JutsuDTO atualizar(@PathVariable Long id, @RequestBody JutsuDTO jutsu) {
        return jutsuService.atualizar(id, jutsu);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        jutsuService.remover(id);
    }
}