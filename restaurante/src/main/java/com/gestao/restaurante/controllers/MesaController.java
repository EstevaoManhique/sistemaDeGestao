package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.MesaDto;
import com.gestao.restaurante.models.MesaModel;
import com.gestao.restaurante.services.MesaService;
import com.gestao.restaurante.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/mesa")
public class MesaController {

    
    final MesaService mesaService;

    public MesaController(MesaService mesaService){
        this.mesaService = mesaService;
    }

    @PostMapping
    public ResponseEntity<Object> saveMesa(@RequestBody @Valid MesaDto mesaDto){

        if(mesaService.existsById(mesaDto.getId()) || mesaService.existsByQr(mesaDto.getQr())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Essa mesa ja foi registrada");
        }
        MesaModel mesaModel = new MesaModel();
        BeanUtils.copyProperties(mesaDto, mesaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.save(mesaModel));
    }

    @PostMapping("varias/{quantidade}")
    public ResponseEntity<Object> saveVarious(@PathVariable(value = "quantidade") int quantidade){
        return ResponseEntity.status(HttpStatus.OK).body(mesaService.saveVarious(Util.gerarMesas(quantidade)));
    }

    @GetMapping
    public ResponseEntity<Object> getAllMesa(){
        return ResponseEntity.status(HttpStatus.OK).body(mesaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOndeMesa(@PathVariable(value = "id") Long id){
        Optional<MesaModel> mesaOptional = mesaService.findById(id);
        if (mesaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Mesa not exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mesaOptional.get());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateMesa (@PathVariable(value = "id") Long id,
                                                  @RequestBody @Valid MesaDto mesaDto){
        Optional<MesaModel> mesaOptional = mesaService.findById(id);
        if (mesaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Mesa not exists");
        }

        MesaModel mesaModel = new MesaModel();
        BeanUtils.copyProperties(mesaDto, mesaModel);
        mesaModel.setId(mesaOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(mesaService.save(mesaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMesa(@PathVariable(value = "id") Long id){
        Optional<MesaModel> mesaOptional = mesaService.findById(id);
        if (!mesaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Mesa not found");
        }
        mesaService.delete(mesaOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Mesa deleted successfully!");
    }
}
