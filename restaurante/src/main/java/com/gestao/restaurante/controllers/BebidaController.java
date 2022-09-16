package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.BebidaDto;
import com.gestao.restaurante.models.BebidaModel;
import com.gestao.restaurante.services.BebidaService;
import com.gestao.restaurante.utils.Files.FileStorageService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bebida")
public class BebidaController {

    final BebidaService bebidaService;
    final String vazio[] = null;
    
    @Autowired
    FileStorageService fileStorageService;

    public BebidaController(BebidaService bebidaService) {
        this.bebidaService = bebidaService;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> save(@RequestPart MultipartFile file, @RequestPart @Valid String bebidaDtoStr){
        
        BebidaDto bebidaDto = bebidaService.getJson(bebidaDtoStr);
        String fileDownloadUri = fileStorageService.storeFile(file);
        
        if (bebidaService.existsByDescricao(bebidaDto.getDescricao())){
            return ResponseEntity.status(HttpStatus.FOUND).body(vazio);
        }
        
        bebidaDto.setFoto(fileDownloadUri);
        BebidaModel bebidaModel = new BebidaModel();
        BeanUtils.copyProperties(bebidaDto, bebidaModel);
        return ResponseEntity.status(HttpStatus.OK).body(bebidaService.save(bebidaModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bebidaService.getAll());
    }

    @GetMapping("/{descricao}/pesquisa")
    public ResponseEntity<Object> getOne(@PathVariable(value = "descricao") String descricao){
        Optional<BebidaDto> optionalbebidaModel = bebidaService.getOne(descricao);
        if (optionalbebidaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Conflict:Esta bebida nao existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalbebidaModel.get());
    }
    
    @PatchMapping("/{descricao}")
    public ResponseEntity<Object> update(@PathVariable(value = "descricao") String descricao, @RequestBody
                                         @Valid BebidaDto bebidaDto){
        Optional<BebidaModel> optionalbebidaModel = bebidaService.findByDescricao(descricao);
        if (optionalbebidaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalbebidaModel.get());
        }
        BebidaModel bebidaModel = new BebidaModel();
        BeanUtils.copyProperties(bebidaDto, bebidaModel);
        return ResponseEntity.status(HttpStatus.OK).body(bebidaService.save(bebidaModel));
    }
    
    @DeleteMapping("/{descricao}")
    public ResponseEntity<Object> delete(@PathVariable(value = "descricao") String descricao){
        Optional<BebidaModel> optionalbebidaModel = bebidaService.findByDescricao(descricao);
        if (optionalbebidaModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Esta bebida nao existe");
        }
        BebidaModel bebidaModel = new BebidaModel();
        BeanUtils.copyProperties(optionalbebidaModel.get(), bebidaModel);
        bebidaService.delete(bebidaModel);
        return ResponseEntity.status(HttpStatus.OK).body("bebida eliminada com sucesso!");
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
