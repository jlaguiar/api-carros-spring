package com.joao.takayama.Carros.domain;

import com.joao.takayama.Carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroREpository carroREpository;

    public List<Carro> getCarros() {
        return carroREpository.findAll();
    }

    public List<CarroDTO> getCarrosDto() {
        List<Carro> carros = carroREpository.findAll();

        ///Lambadas
        List<CarroDTO> list = carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
        return list;
        //OU
//        List<CarroDTO> list = new ArrayList<>();
//        for (Carro c : carros){
//            list.add(new CarroDTO(c));
//        }
//        return list;
    }

    public Optional<CarroDTO> getCarroById(Long id) {
        return carroREpository.findById(id).map(CarroDTO::new);
    }

    public List<Carro> getCarByType(String tipo) {
        return carroREpository.findByTipo(tipo);
    }

    public Carro addCar(Carro car) {
        return carroREpository.save(car);
    }

    public Carro updateCar(Carro car, Long id) {
        Assert.notNull(id,"Id null");
        Optional<Carro> optional = carroREpository.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            //Copiar as propriedades
            db.setNome(car.getNome());
            db.setTipo(car.getTipo());
            System.out.println("Carro id " + db.getId());

            return carroREpository.save(db);

        }else{
            throw new RuntimeException("Não foi possivel atualizar registro");
        }
    }

    public void deleteCar(Long id) {
        Optional<Carro> carro = carroREpository.findById(id);
        if(carro.isPresent()){
            carroREpository.deleteById(id);
        }
    }


//    public List<Carro> getCarrosFake() {
//        List<Carro> carros = new ArrayList<>();
//        carros.add(new Carro(1L,"Fusca"));
//        carros.add(new Carro(2L,"Fiat"));
//        carros.add(new Carro(3L,"Gol"));
//
//        return carros;
//    }


}

