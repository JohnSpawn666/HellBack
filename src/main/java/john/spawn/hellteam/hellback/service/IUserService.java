package john.spawn.hellteam.hellback.service;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.UserRequest;
import john.spawn.hellteam.hellback.bean.UserResponse;

public interface IUserService {

    UserResponse save(@Valid UserRequest user);
}
