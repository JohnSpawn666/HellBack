package john.spawn.hellteam.hellback.control;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.UserLoginResponse;
import john.spawn.hellteam.hellback.service.IUserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${app.frontend-origin}")
@RestController
@RequestMapping("/api/user")
public class UserControl {

    private final IUserService service;

    public UserControl(IUserService service) {
        this.service = service;
    }

    @PostMapping
    public UserCreateResponse save(@RequestBody @Valid UserCreateRequest user) {
        return service.save(user);
    }

    @PostMapping("/auth/login")
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        return service.login(request);
    }

}
