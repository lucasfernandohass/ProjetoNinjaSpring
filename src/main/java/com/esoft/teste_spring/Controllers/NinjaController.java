package com.esoft.teste_spring.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esoft.teste_spring.DTOs.NinjaDTO;
import com.esoft.teste_spring.Services.NinjaService;
import com.esoft.teste_spring.models.Ninja;

import jakarta.websocket.server.PathParam;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    NinjaService ninjaService;

    @GetMapping
    public List<NinjaDTO> listar() {
        return ninjaService.listar();
    }

    @PostMapping
    public NinjaDTO criar(@RequestBody NinjaDTO ninja) throws Exception {
        return ninjaService.salvar(ninja);
    }

    @GetMapping("/{id}")
    public NinjaDTO buscarPorId(@PathVariable Long id) {
        return ninjaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public NinjaDTO putMethodName(@PathVariable Long id, @RequestBody NinjaDTO ninja) {
        return ninjaService.salvar(id, ninja);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        ninjaService.deletar(id);
    }

}
