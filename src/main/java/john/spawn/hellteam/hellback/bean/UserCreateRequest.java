package john.spawn.hellteam.hellback.bean;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(

        @NotNull(message = "Le pseudo de l'utilisateur est obligatoire")
        @Size(min = 5, max = 20, message = "Le pseudo doit être compris entre 5 et 20 caractères")
        String pseudo,

        @NotNull
        @Size(min = 5, max = 50, message = "Le mot de passe doit être compris entre 5 et 50 catactères")
        String password
) {
}
