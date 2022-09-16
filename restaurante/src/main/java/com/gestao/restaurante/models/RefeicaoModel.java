package com.gestao.restaurante.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Component
@Getter
@Setter
@Table(name = "refeicao")
public class RefeicaoModel extends ProdutoModel implements Serializable {

    @Override
    public void registarProduto(){}

    @Override
    public void actualizarDados(){}

    @Override
    public void eliminarProduto(){}

}