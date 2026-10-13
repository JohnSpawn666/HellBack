package john.spawn.hellteam.hellback.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByPseudo(String pseudo);

}
