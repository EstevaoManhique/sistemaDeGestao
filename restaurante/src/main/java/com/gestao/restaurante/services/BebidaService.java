package com.gestao.restaurante.services;

import com.gestao.restaurante.dtos.BebidaDto;
import com.gestao.restaurante.models.BebidaModel;
import com.gestao.restaurante.repository.BebidaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    final BebidaRepository bebidaRepository;

    public BebidaService (BebidaRepository bebidaRepository){
        this.bebidaRepository = bebidaRepository;
    }
    @Transactional
    public BebidaModel save(BebidaModel bebidaModel){
        return bebidaRepository.save(bebidaModel);
    }

    public boolean existsByDescricao(String descricao) {
        return bebidaRepository.existsByDescricao(descricao);
    }

    public List<BebidaDto> getAll() {
        return bebidaRepository.getAll();
    }

    public Optional<BebidaDto> getOne(String descricao){
        return bebidaRepository.getOne(descricao);
    }

    public Optional<BebidaModel> findByDescricao(String descricao) {
        return bebidaRepository.findByDescricao(descricao);
    }

    public void delete(BebidaModel bebidaModel) {
        bebidaRepository.delete(bebidaModel);
    }

    public BebidaDto getJson(String bebidaDtoStr){
        BebidaDto bebidaDtoJson = new BebidaDto();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            bebidaDtoJson = objectMapper.readValue(bebidaDtoStr, BebidaDto.class);
        }catch(IOException err){
            System.out.println(err);
        }

        return bebidaDtoJson;
    }
}
