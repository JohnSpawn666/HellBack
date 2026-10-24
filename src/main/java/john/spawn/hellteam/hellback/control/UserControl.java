package john.spawn.hellteam.hellback.control;

import jakarta.validation.Valid;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserSelectResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserUpdateResponse;
import john.spawn.hellteam.hellback.bean.user.update.UserUpdateRequest;
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
    public UserCreateResponse create(@RequestBody @Valid UserCreateRequest user) {
        return service.save(user);
    }

    @PostMapping("/auth/login")
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        return service.login(request);
    }

    @GetMapping("/{id}")
    public UserSelectResponse select(@PathVariable Long id) {
        return service.select(id);
    }

    @PutMapping("/{id}")
    public UserUpdateResponse update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
