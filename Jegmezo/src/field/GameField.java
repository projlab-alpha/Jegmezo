package field;

import java.util.ArrayList;
import java.util.Random;

/**
 * Ez az ostály reprezentálja a játékmezőt, amelyen a különböző jégtáblák elhelyezkednek.
 * Felelős ezek nyilvántartásáért, valamint képes hóvihart szimulálni.
 */
public class GameField {

    /**
     * Heterogén kollekció a játékmezőn található táblákról.
     */
    private ArrayList<AbstractField> floes;

    /**
     * Megadja, mekkora az esélye annak, hogy egy
     * mezőn hóvihar támad.
     */
    private final double SnowstormChance = 0.2;

    /**
     * Konstruktor. Beállítja a SnowstormChance értékét.
     */
    public GameField(int width, int height) {
        floes = new ArrayList<>(width * height);
        Random rng = new Random();
        for(int i = 0; i < width * height; ++i) {
            AbstractField newField;
            if(rng.nextDouble() < 0.6) {
                //newField = new Floe();
            }
        }
    }
 
    /**
     * Hozzáad egy jégmezőt a jégtáblához.
     * @param f a hozzáadandó jégmező
     */
    public void addField(AbstractField f) {     //TODO: Dokumentációról lemaradt
        floes.add(f);
    }

    /**
     * A metódus végigmegy a floes kollekció összes elemén, majd
     * minden elemhez generál egy pszeudorandom valós számot 0.0 és 1.0 között. Ha ez a
     * szám kisebb a SnowstormChance tagváltozó értékénél, akkor meghívja az esedékes
     * mező SnowStormHit() metódusát.
     */
    public void SnowStorm(){
        //TODO
    }

    /**
     * Visszatér a játékmezőn lévő jégtáblákat tartalmazó tömbbel.
     * Prototípus működése érdekében.
     * @return a jégtáblákat tartalmazó tömb.
     */
    public ArrayList<AbstractField> getFloes() {
        return floes;
    }

    public AbstractField getFloeAt(int idx) {
        if(idx < 0 || idx > floes.size())
            return null;
        return floes.get(idx);
    }
}
