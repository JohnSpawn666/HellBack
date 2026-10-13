package john.spawn.hellteam.hellback.error;

public class FailLoginException extends RuntimeException {

    public FailLoginException(String pseudo) {
        super("Erreur de connection pour l'utilisateur " + pseudo);
    }

}
