package app.SpringBoot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @Temporal(TemporalType.DATE)
    private Date created;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")
    private  UserEntity user;


@JsonIgnore
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> commensts =new HashSet<>();
}
