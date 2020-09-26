package com.joao.takayama.Carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepository<Carro,Long> ele retorna iterable mas o estende dele e retorna uma lista direto ja
public interface CarroREpository extends JpaRepository<Carro,Long> {

    List<Carro> findByTipo(String tipo);
}
