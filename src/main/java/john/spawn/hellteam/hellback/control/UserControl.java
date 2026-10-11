package john.spawn.hellteam.hellback.control;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.UserRequest;
import john.spawn.hellteam.hellback.bean.UserResponse;
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
    public UserResponse save(@RequestBody @Valid UserRequest user) {
        return service.save(user);
    }

}
