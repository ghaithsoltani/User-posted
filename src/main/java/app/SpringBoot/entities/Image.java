package app.SpringBoot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String name;
    @Lob
    @Column(name = "picByte",columnDefinition = "LONGBLOB")
    byte[] picByte;
    @OneToOne
    @JoinTable(name = "user_image_asotiation")
    private UserEntity user;
}
