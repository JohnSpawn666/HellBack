package john.spawn.hellteam.hellback.service;

import john.spawn.hellteam.hellback.bean.user.create.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.user.create.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.user.login.UserLoginResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserSelectResponse;
import john.spawn.hellteam.hellback.bean.user.select.UserUpdateResponse;
import john.spawn.hellteam.hellback.bean.user.update.UserUpdateRequest;
import john.spawn.hellteam.hellback.database.IUserRepository;
import john.spawn.hellteam.hellback.database.UserEntity;
import john.spawn.hellteam.hellback.error.FailLoginException;
import john.spawn.hellteam.hellback.error.NotFoundException;
import john.spawn.hellteam.hellback.error.PseudoAlreadyExistException;
import john.spawn.hellteam.hellback.error.SaveException;
import john.spawn.hellteam.hellback.mapper.IUserMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service

public class UserServiceImpl implements IUserService {

    private static final String TABLE = "USER";
    private final IUserRepository repository;
    private final IUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository repository, IUserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserCreateResponse save(UserCreateRequest request) {
        try {
            return mapper.mapToUserSaveResponse(repository.saveAndFlush(mapper.mapToUserEntity(request, passwordEncoder)));
        } catch (DataIntegrityViolationException ex) {
            if ("user.uni_user_pseudo".equalsIgnoreCase(getConstraintName(ex))) {
                throw new PseudoAlreadyExistException(request.pseudo());
            } else {
                throw new SaveException("DataIntegrityViolationException", TABLE);
            }
        } catch (Exception _) {
            throw new SaveException("Exception", TABLE);
        }
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {

        UserEntity entity = repository.findByPseudo(request.pseudo());

        if (entity == null || !passwordEncoder.matches(request.password(), entity.getPassword())) {
            throw new FailLoginException(request.pseudo());
        }
        return mapper.mapToUserLoginResponse(entity);
    }

    @Override
    public UserSelectResponse select(Long id) {
        return repository.findById(id)
                .map(mapper::mapToUserSelectResponse)
                .orElse(null);
    }

    @Override
    @Transactional
    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        Optional<UserEntity> optUser = repository.findById(id);

        if (optUser.isPresent()) {
            UserEntity userEntity = optUser.get();
            userEntity.setPassword(request.password());
            userEntity.setPseudo(request.pseudo());
            return mapper.mapToUserUpdateResponse(userEntity);
        } else {
            throw new NotFoundException(TABLE, String.valueOf(id));
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Récupère le nom de la contrainte via l'exception retournée
     *
     * @param ex Exception
     * @return Nom de la contrainte
     */
    private String getConstraintName(Throwable ex) {
        Throwable cause = ex;

        while (cause != null) {
            if (cause instanceof ConstraintViolationException cve) {
                return cve.getConstraintName();
            }
            cause = cause.getCause();
        }
        return null;
    }
}
