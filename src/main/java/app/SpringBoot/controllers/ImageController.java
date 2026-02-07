package app.SpringBoot.controllers;

import app.SpringBoot.entities.Image;
import app.SpringBoot.services.ImageServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
@Autowired
    ImageServiceInter imageServiceInter;

    @PostMapping("/upload/{idUser}")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file , @PathVariable Long idUser) throws IOException {

        return imageServiceInter.uploadImage(file,idUser);
    }

    @GetMapping("/get/{idUser}")

    public ResponseEntity<Image> getImageByUseId(@PathVariable Long idUser){
        return imageServiceInter.getImageByUseId(idUser);
    }

    @PutMapping("/update/{idUser}")
    public ResponseEntity<String> updateImage (@RequestParam("imageFile") MultipartFile file , @PathVariable Long idUser) throws IOException {
        return imageServiceInter.updateImage(file,idUser);

    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<String> deleteImage(@PathVariable Long idUser){
        return imageServiceInter.deleteImage(idUser);
    }

}
