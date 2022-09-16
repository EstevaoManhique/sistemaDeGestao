package com.gestao.restaurante.utils.Files;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Component
public class FileResponse {
    
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
