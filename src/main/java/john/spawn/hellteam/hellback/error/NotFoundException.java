package john.spawn.hellteam.hellback.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String table, String value) {
        super("Aucune valeur dans la table " + table + " pour la valeur " + value);
    }

}
