package john.spawn.hellteam.hellback.service;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserSelectResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserUpdateResponse;
import john.spawn.hellteam.hellback.bean.user.update.UserUpdateRequest;

public interface IUserService {

    UserCreateResponse save(@Valid UserCreateRequest request);

    UserLoginResponse login(@Valid UserLoginRequest request);

    UserSelectResponse select(@Valid Long id);

    UserUpdateResponse update(@Valid Long id, @Valid UserUpdateRequest request);

    void delete(Long id);
}
