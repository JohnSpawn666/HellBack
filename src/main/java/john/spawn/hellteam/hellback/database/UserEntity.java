package john.spawn.hellteam.hellback.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(
        name = "user",
        uniqueConstraints = @UniqueConstraint(
                name = "uni_user_pseudo",
                columnNames = "pseudo"
        ))
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String pseudo;

    @Column(nullable = false)
    private String password;

}
