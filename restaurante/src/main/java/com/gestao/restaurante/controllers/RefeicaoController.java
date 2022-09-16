package com.gestao.restaurante.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gestao.restaurante.dtos.RefeicaoDto;
import com.gestao.restaurante.models.RefeicaoModel;
import com.gestao.restaurante.services.RefeicaoService;
import com.gestao.restaurante.utils.Files.FileStorageService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/refeicao")
public class RefeicaoController {

    final RefeicaoService refeicaoService;
    final String vazio[] = null;
    
    @Autowired
    FileStorageService fileStorageService;

    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> save(@RequestPart MultipartFile file, @RequestPart @Valid String refeicaoDtoStr){
        
        RefeicaoDto refeicaoDto = refeicaoService.getJson(refeicaoDtoStr);
        String fileDownloadUri = fileStorageService.storeFile(file);
        
        if (refeicaoService.existsByDescricao(refeicaoDto.getDescricao())){
            return ResponseEntity.status(HttpStatus.FOUND).body(vazio);
        }
        refeicaoDto.setFoto(fileDownloadUri);
        
        RefeicaoModel refeicaoModel = new RefeicaoModel();
        BeanUtils.copyProperties(refeicaoDto, refeicaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.save(refeicaoModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.getAll());
    }

    @GetMapping("/{descricao}/pesquisa")
    public ResponseEntity<Object> getOne(@PathVariable(value = "descricao") String descricao){
        Optional<RefeicaoDto> optionalRefeicaoModel = refeicaoService.getOne(descricao);
        if (optionalRefeicaoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Conflict:Esta refeicao nao existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalRefeicaoModel.get());
    }
    
    @PatchMapping("/{descricao}")
    public ResponseEntity<Object> update(@PathVariable(value = "descricao") String descricao, @RequestBody
                                         @Valid RefeicaoDto refeicaoDto){
        Optional<RefeicaoModel> optionalRefeicaoModel = refeicaoService.findByDescricao(descricao);
        if (optionalRefeicaoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalRefeicaoModel.get());
        }
        RefeicaoModel refeicaoModel = new RefeicaoModel();
        BeanUtils.copyProperties(refeicaoDto, refeicaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(refeicaoService.save(refeicaoModel));
    }
    
    @DeleteMapping("/{descricao}")
    public ResponseEntity<Object> delete(@PathVariable(value = "descricao") String descricao){
        Optional<RefeicaoModel> optionalRefeicaoModel = refeicaoService.findByDescricao(descricao);
        if (optionalRefeicaoModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Esta refeicao nao existe");
        }
        RefeicaoModel refeicaoModel = new RefeicaoModel();
        BeanUtils.copyProperties(optionalRefeicaoModel.get(), refeicaoModel);
        refeicaoService.delete(refeicaoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Refeicao eliminada com sucesso!");
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch(IOException ex){
            System.out.println("cound not determine file type");
        }

        if(contentType==null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .body(resource);
    }
}