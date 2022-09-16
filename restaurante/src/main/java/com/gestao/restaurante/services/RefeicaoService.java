package com.gestao.restaurante.services;

import com.gestao.restaurante.dtos.RefeicaoDto;
import com.gestao.restaurante.models.RefeicaoModel;
import com.gestao.restaurante.repository.RefeicaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {
    final RefeicaoRepository refeicaoRepository;

    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

    @Transactional
    public RefeicaoModel save(RefeicaoModel refeicaoModel) {
        return refeicaoRepository.save(refeicaoModel);
    }

    public boolean existsByDescricao(String descricao) {
        return refeicaoRepository.existsByDescricao(descricao);
    }

    public Object findAll() {
        return refeicaoRepository.findAll();
    }

    public Optional<RefeicaoModel> findByDescricao(String descricao) {
        return refeicaoRepository.findByDescricao(descricao);
    }

    public void delete(RefeicaoModel refeicaoModel) {
        refeicaoRepository.delete(refeicaoModel);
    }

    public List<RefeicaoDto> getAll(){
        return refeicaoRepository.getAll();
    }

    public RefeicaoDto getJson(String refeicaoDtoStr){
        RefeicaoDto refeicaoDtoJson = new RefeicaoDto();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            refeicaoDtoJson = objectMapper.readValue(refeicaoDtoStr, RefeicaoDto.class);
        }catch(IOException err){
            System.out.println(err);
        }

        return refeicaoDtoJson;
    }

    public Optional<RefeicaoDto> getOne(String descricao){
        return this.refeicaoRepository.getOne(descricao);
    }
}
