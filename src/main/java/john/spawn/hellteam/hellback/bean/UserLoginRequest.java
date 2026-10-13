package john.spawn.hellteam.hellback.bean;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(

        @NotNull(message = "Le pseudo de l'utilisateur est obligatoire")
        String pseudo,

        @NotNull(message = "Le mot de passe de l'utilisateur est obligatoire")
        String password
) {
}
