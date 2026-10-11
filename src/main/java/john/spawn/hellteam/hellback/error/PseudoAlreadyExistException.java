package john.spawn.hellteam.hellback.error;

public class PseudoAlreadyExistException extends RuntimeException{

    public PseudoAlreadyExistException(String message){
        super("Le pseudo " + message + " existe déjà");
    }

}
