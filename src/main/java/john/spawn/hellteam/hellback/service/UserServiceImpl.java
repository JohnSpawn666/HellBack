package john.spawn.hellteam.hellback.service;

import john.spawn.hellteam.hellback.bean.UserCreateRequest;
import john.spawn.hellteam.hellback.bean.UserCreateResponse;
import john.spawn.hellteam.hellback.bean.UserLoginRequest;
import john.spawn.hellteam.hellback.bean.UserLoginResponse;
import john.spawn.hellteam.hellback.database.IUserRepository;
import john.spawn.hellteam.hellback.database.UserEntity;
import john.spawn.hellteam.hellback.error.FailLoginException;
import john.spawn.hellteam.hellback.error.PseudoAlreadyExistException;
import john.spawn.hellteam.hellback.error.SaveException;
import john.spawn.hellteam.hellback.mapper.IUserMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
