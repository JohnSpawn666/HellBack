package john.spawn.hellteam.hellback.service;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.UserLoginResponse;

public interface IUserService {

    UserCreateResponse save(@Valid UserCreateRequest request);

    UserLoginResponse login(@Valid UserLoginRequest request);
}
