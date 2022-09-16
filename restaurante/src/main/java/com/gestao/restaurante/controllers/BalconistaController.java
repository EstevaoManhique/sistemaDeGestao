package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.EnvioEmailDto;
import com.gestao.restaurante.dtos.UsuarioDto;
import com.gestao.restaurante.models.BalconistaModel;
import com.gestao.restaurante.models.UsuarioModel;
import com.gestao.restaurante.services.UserService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/balconista")
public class BalconistaController {

    private final UserService userService;
    private final String inactivo = "inactivo";
    private final String activo = "activo";
    

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BalconistaController(UserService userService){
        this.userService = userService;
    }

    {/*Registro do balconista, que e feito pelo administrador.*/}
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UsuarioDto userDto){
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.OK).body("Ese usuario ja existe!");
        }
        BalconistaModel balconistaModel = new BalconistaModel();
        BeanUtils.copyProperties(userDto, balconistaModel);
        {/*Ao registrar o balconista, ele fica com estado inactivo, e so entra para o estado
        activo depois de autenticar a conta.*/}
        balconistaModel.setEstado(inactivo);
        
        /*Envio da mensagem para o servico de email*/
        EnvioEmailDto envioEmailDto = new EnvioEmailDto();
        envioEmailDto.setEmail(userDto.getEmail());
		rabbitTemplate.convertAndSend("registro.balconista", "", envioEmailDto);
        /*Fim do envio da mensagem para o servico de email*/
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(balconistaModel));
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    {/*O balconista so pode fazer login depois de autenticar a conta.*/}
    @GetMapping("/{email}/{senha}/login")
    public ResponseEntity<Object> getOne(@PathVariable(value = "email") String email,
                                    @PathVariable(value = "senha") String senha){
        
        Optional<UsuarioModel> optionalBalconistaModel = userService.findByEmailAndSenha(email, senha);
        if (optionalBalconistaModel.isEmpty() || optionalBalconistaModel.get().getEstado().equals(inactivo)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existente!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalBalconistaModel.get());
    }
    
    @GetMapping("/{senha}/autenticarconta")
    public ResponseEntity<Object> getOneToAutenticate(@PathVariable(value = "senha") String senha){
        Optional<UsuarioModel> optionalBalconistaModel = userService.findBySenha(senha);
        if (optionalBalconistaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existente!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalBalconistaModel.get());
    }

    {/*A principio este metodo usa-se para activar a conta do usuario*/}
    @PatchMapping("/{email}/{senha}")
    public ResponseEntity<Object> updateGestor(@PathVariable(value = "email") String email,
                                               @RequestBody @Valid UsuarioDto userDto, 
                                               @PathVariable(value = "senha") String senha){

        Optional<UsuarioModel> optionalBalconistaModel = userService.findByEmailAndSenha(email, senha);
        if (optionalBalconistaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existente");
        }
        BalconistaModel balconistaModel = new BalconistaModel();
        BeanUtils.copyProperties(userDto, balconistaModel);
        balconistaModel.setEmail(optionalBalconistaModel.get().getEmail());
        balconistaModel.setEstado(activo);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(balconistaModel));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> deleteGestor(@PathVariable(value = "email") String email){
        Optional<UsuarioModel> optionalBalconistaModel = userService.findByEmail(email);
        if (optionalBalconistaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Usuario nao existe");
        }
        userService.delete(optionalBalconistaModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado com sucesso!");
    }

}
