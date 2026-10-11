package john.spawn.hellteam.hellback.mapper;

import javax.annotation.processing.Generated;
import john.spawn.hellteam.hellback.bean.UserRequest;
import john.spawn.hellteam.hellback.bean.UserResponse;
import john.spawn.hellteam.hellback.database.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-11T16:09:55+0200",
    comments = "version: 1.7.0.Beta2, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity mapToUserEntity(UserRequest request, PasswordEncoder passwordEncoder) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setPseudo( request.pseudo() );
        userEntity.setPassword( encode( request.password(), passwordEncoder ) );

        return userEntity;
    }

    @Override
    public UserResponse mapToUserSaveResponse(UserEntity response) {
        if ( response == null ) {
            return null;
        }

        Long id = null;
        String pseudo = null;
        String password = null;

        id = response.getId();
        pseudo = response.getPseudo();
        password = response.getPassword();

        UserResponse userResponse = new UserResponse( id, pseudo, password );

        return userResponse;
    }
}
