package app.SpringBoot.services;

import app.SpringBoot.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageServiceInter {
    ResponseEntity<String> uploadImage(MultipartFile file , Long idUser) throws IOException;

    ResponseEntity<Image> getImageByUseId(Long idUser);

    ResponseEntity<String> updateImage(MultipartFile file, Long idUser) throws IOException;


    ResponseEntity<String> deleteImage(Long idUser);
}
