package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.UsuarioDto;
import com.gestao.restaurante.models.GestorModel;
import com.gestao.restaurante.models.UsuarioModel;
import com.gestao.restaurante.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/gestor")
public class GestorController {

    final UserService userService;

    public GestorController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UsuarioDto userDto){
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.OK).body("Ese usuario ja existe!");
        }
        UsuarioModel gestorModel = new GestorModel();
        BeanUtils.copyProperties(userDto, gestorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(gestorModel));
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "email") String email){
        Optional<UsuarioModel> optionalGestorModel = userService.findByEmail(email);
        if (optionalGestorModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existente!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalGestorModel.get());
    }
    @PostMapping("/{email}")
    public ResponseEntity<Object> updateGestor(@PathVariable(value = "email") String email,
                                                   @RequestBody @Valid UsuarioDto userDto){

        Optional<UsuarioModel> optionalGestorModel = userService.findByEmail(email);
        if (optionalGestorModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existente");
        }
        GestorModel gestorModel = new GestorModel();
        BeanUtils.copyProperties(userDto, gestorModel);
        gestorModel.setEmail(optionalGestorModel.get().getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(gestorModel));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteGestor(@PathVariable(value = "email") String email){
        Optional<UsuarioModel> optionalGestorModel = userService.findByEmail(email);
        if (optionalGestorModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existe");
        }
        userService.delete(optionalGestorModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado com sucesso!");
    }
}