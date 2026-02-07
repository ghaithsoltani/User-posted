package app.SpringBoot.services;

import app.SpringBoot.entities.Image;
import app.SpringBoot.entities.UserEntity;
import app.SpringBoot.repository.ImageRepository;
import app.SpringBoot.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageServiceImpl implements ImageServiceInter {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;
    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file, Long idUser) throws IOException {

        Optional<UserEntity> user= userRepository.findById(idUser);

        if(user.isPresent()){

            if(user.get().getUserImage()!=null)
            {
                return ResponseEntity.badRequest().body("User already has an image");
            }

            Image img= new Image();
            img.setName(file.getOriginalFilename());
            img.setPicByte(compressBytes( file.getBytes()));
            img.setUser(user.get());
            imageRepository.save(img);
            return ResponseEntity.ok("Image ( "+img.getName()+"' ) added to user : "+img.getUser().getUsername() );
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @Override
    public ResponseEntity<Image> getImageByUseId(Long idUser) {
      Optional<Image> image=imageRepository.findByUserId(idUser);
      if(image.isPresent()){
          Image img = image.get();
img.setPicByte(decompressBytes(img.getPicByte()));
          return ResponseEntity.ok(img);

      }else{
          return  ResponseEntity.notFound().build();
      }


    }

    @Override
    public ResponseEntity<String> updateImage(MultipartFile file, Long idUser) throws IOException {
        Optional<UserEntity> user = userRepository.findById(idUser);

        if(user.isPresent()){

            if(user.get().getUserImage()==null)
            {
                return ResponseEntity.badRequest().body("NO image");
            }


            UserEntity usr= user.get();
            Image image=usr.getUserImage();
            image.setName(file.getOriginalFilename());
            image.setPicByte(compressBytes( file.getBytes()));
            imageRepository.save(image);
            return ResponseEntity.ok("updated");

        }else{
            return ResponseEntity.notFound().build();
        }


    }

    @Override
    public ResponseEntity<String> deleteImage(Long idUser) {
      Optional<UserEntity> user =userRepository.findById(idUser);
       if(user.isPresent()){
           Image img=user.get().getUserImage();
       if (img !=null){
           imageRepository.delete(img);
           return ResponseEntity.ok("Deleted");
       }

       }
        return null;
    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }




}
