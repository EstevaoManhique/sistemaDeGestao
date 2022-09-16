package com.gestao.restaurante.utils.Files;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class FileStorageService {

    private Path fileStorageLocation;

    //@Autowired. Criacao do diretorio para guardar os ficheiros
    public FileStorageService (FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.uploadDir()).toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch(Exception e){
            throw new FileStorageException("could not create the directory to upload");
        }
    }

    //function to store the file
    public String storeFile(MultipartFile file){
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/refeicao/")
            .path(fileName)
            .toUriString();
        try{
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileDownloadUri;
        }catch(IOException ex){
            throw new FileStorageException("could not store file "+fileName+". Pleasse try again!");
        }
    }
    
    //function to load the file
    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new MyFileNotFoundException("File not found "+ fileName);
            }
        }catch(MalformedURLException ex){
            throw new MyFileNotFoundException("File not found "+ fileName);
        }
    }
}