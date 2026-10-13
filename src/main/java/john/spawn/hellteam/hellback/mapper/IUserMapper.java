package john.spawn.hellteam.hellback.mapper;

import john.spawn.hellteam.hellback.bean.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.UserLoginResponse;
import john.spawn.hellteam.hellback.database.UserEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IMapper {

    @Mapping(target = "pseudo", source = "request.pseudo")
    @Mapping(target = "password", source = "request.password", qualifiedByName = "encode")
    UserEntity mapToUserEntity(UserCreateRequest request, @Context PasswordEncoder passwordEncoder);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "pseudo", source = "entity.pseudo")
    UserCreateResponse mapToUserSaveResponse(UserEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "pseudo", source = "entity.pseudo")
    UserLoginResponse mapToUserLoginResponse(UserEntity entity);
}
