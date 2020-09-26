package com.joao.takayama.Carros.domain.dto;

import com.joao.takayama.Carros.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {

    private Long id;

    private String nome;

    public CarroDTO(Carro carro){
        this.id = carro.getId();
        this.nome = carro.getNome();
    }
}
