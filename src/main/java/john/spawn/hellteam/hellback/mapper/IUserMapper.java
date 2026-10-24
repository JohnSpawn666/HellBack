package john.spawn.hellteam.hellback.mapper;

import john.spawn.hellteam.hellback.bean.user.create.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserSelectResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserUpdateResponse;
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

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "pseudo", source = "entity.pseudo")
    UserSelectResponse mapToUserSelectResponse(UserEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "pseudo", source = "entity.pseudo")
    UserUpdateResponse mapToUserUpdateResponse(UserEntity entity);
}
