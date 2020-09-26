package com.joao.takayama.Carros.api;

import com.joao.takayama.Carros.domain.Carro;
import com.joao.takayama.Carros.domain.CarroService;
import com.joao.takayama.Carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping()
    public ResponseEntity getCarros(){
        //Pare retornar um status junto com a resposta
        //return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
        //Ou da maneira encurtada
        return ResponseEntity.ok(carroService.getCarros());
    }

    @GetMapping("/dto")
    public ResponseEntity getCarrosDto(){
        //Pare retornar um status junto com a resposta
        //return new ResponseEntity<>(carroService.getCarros(), HttpStatus.OK);
        //Ou da maneira encurtada
        return ResponseEntity.ok(carroService.getCarrosDto());
    }


    //Outra maneira de utilizar o status
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarro(@PathVariable("id") Long id){
        Optional<CarroDTO> carro = carroService.getCarroById(id);
        //Com lambdas, só é chamada apenas se existir o objeto
        return carro.map(c -> ResponseEntity.ok(c)).orElse(ResponseEntity.notFound().build());
        //é a mesma coisa
        //return carro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        //OU
       // return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
        //OU
//        if(carro.isPresent()){
//            //Como esta passando um Objeto nao precisa dar build
//            return ResponseEntity.ok(carro.get());
//        }else{
//            return ResponseEntity.notFound().build();
//        }
    }

    @GetMapping("/tipo/{tipo}")
        public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo){
        List<Carro> carros = carroService.getCarByType(tipo);
        return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
    }

    @PostMapping("/carro")
    public String addCar(@RequestBody Carro car) {
        Carro carro = carroService.addCar(car);
        return "Carro adicionado com success" + carro.getId();
    }

    @PutMapping("/carro/{id}")
    public String updateCar(@RequestBody Carro car,
                            @PathVariable("id") Long id){
        Carro carro = carroService.updateCar(car, id);
        return "Carro atualizado com success " + carro.getId();
    }

    @DeleteMapping("carro/{id}")
    public void deleteCar(@PathVariable("id") Long id){
        carroService.deleteCar(id);
    }
}
