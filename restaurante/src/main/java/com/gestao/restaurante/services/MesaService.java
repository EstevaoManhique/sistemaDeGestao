package com.gestao.restaurante.services;

import com.gestao.restaurante.models.MesaModel;
import com.gestao.restaurante.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MesaService {
    final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository){
        this.mesaRepository = mesaRepository;
    }

    public Object save(MesaModel mesaModel) {
        return mesaRepository.save(mesaModel);
    }

    public ArrayList<MesaModel> saveVarious(ArrayList<MesaModel> mesaModels){
        return (ArrayList<MesaModel>) mesaRepository.saveAll(mesaModels);
    }

    public Object findAll() {
        return mesaRepository.findAll();
    }
    public Optional<MesaModel> findById(Long id) {
        return mesaRepository.findById(id);
    }

    public boolean existsByQr(String qr_mesa) {
        return mesaRepository.existsByQr(qr_mesa);
    }

    public void delete(MesaModel mesaModel) {
        mesaRepository.delete(mesaModel);
    }

    public boolean existsById(Long id) {
        return mesaRepository.existsById(id);
    }
}