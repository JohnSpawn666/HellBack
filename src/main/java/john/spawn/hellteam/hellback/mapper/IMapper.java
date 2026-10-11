package john.spawn.hellteam.hellback.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface IMapper {

    @Named("encode")
    default String encode(String rawPassword, @Context PasswordEncoder passwordEncoder){
        return passwordEncoder.encode(rawPassword);
    }

}
