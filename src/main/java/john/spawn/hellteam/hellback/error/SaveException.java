package john.spawn.hellteam.hellback.error;

public class SaveException extends RuntimeException{

    public SaveException(String cause, String bean){
        super("Erreur de " + cause + " lors de l'enregistrement de " + bean);
    }

}
