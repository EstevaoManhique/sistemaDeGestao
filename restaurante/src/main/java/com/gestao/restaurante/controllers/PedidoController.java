package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.PedidoDto;
import com.gestao.restaurante.models.PedidoModel;
import com.gestao.restaurante.services.ItemPedidoService;
import com.gestao.restaurante.services.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PedidoDto pedidoDto) {
        if (pedidoService.existsById(pedidoDto.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este pedido ja foi feito");
        }
        PedidoModel pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDto, pedidoModel);

        itemPedidoService.save(pedidoModel.getItems_Pedidos());

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id){
        Optional<PedidoModel> optionalPedidoModel = pedidoService.findById(id);
        if (optionalPedidoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este pedido nao existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalPedidoModel.get());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updateOne(@PathVariable(value = "id") Long id,
                                            @RequestBody @Valid PedidoDto pedidoDto){
        Optional<PedidoModel> optionalPedidoModel = pedidoService.findById(id);
        if (optionalPedidoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido nao encontrado");
        }
        PedidoModel pedidoModel = new PedidoModel();
        BeanUtils.copyProperties(pedidoDto, pedidoModel);
        pedidoModel.setId(pedidoDto.getId());
        pedidoModel.setData(pedidoDto.getData());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") Long id){
        Optional<PedidoModel> optionalPedidoModel = pedidoService.findById(id);
        if (optionalPedidoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este pedido nao existe");
        }
        pedidoService.delete(optionalPedidoModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pedido eliminado com sucesso!");
    }
}