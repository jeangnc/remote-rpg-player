import java.io.Serializable;
import java.util.UUID;

class Tabuleiro implements Serializable {
    private UUID uuid;

    Tabuleiro() {
        this.uuid = UUID.randomUUID();
    }

    UUID getUuid() {
        return uuid;
    }
}
