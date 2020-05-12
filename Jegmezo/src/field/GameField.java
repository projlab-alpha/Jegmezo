package field;

import character.PolarBear;
import control.Direction;
import item.Item;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final double SnowstormChance = 0.1;

    private void addRandom(character.Character c) {
        Random rng = new Random();
        AbstractField floe = floes.get(rng.nextInt(floes.size()));
        if(c instanceof PolarBear) {
            floe.Accept(c);
            return;
        }
        while(!(floe instanceof Floe)) {
            floe = floes.get(rng.nextInt(floes.size()));
        }
        floe.Accept(c);
    }

    private void addRandom(Item i) {
        Random rng = new Random();
        int ranNum = rng.nextInt(floes.size());
        while(floes.get(ranNum).hasItem()) {
            ranNum = rng.nextInt(floes.size());
        }
        floes.get(ranNum).setItem(i);
    }

    /**
     * Konstruktor. Beállítja a SnowstormChance értékét.
     */
    public GameField(int width, int height, ArrayList<character.Character> chars, PolarBear polarBear) {
        final int size = width * height;

        Random rng = new Random();
        floes = new ArrayList<>(size);

        //initialize into 2d array for easier neighbor settings
        AbstractField[][] tempFloes = new AbstractField[width][height];
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                AbstractField newField;
                double typeChance = rng.nextDouble();
                if (typeChance < 0.15) {
                    newField = new Hole(null, rng.nextInt(4));
                } else if (typeChance < 0.55) {
                    newField = new UnstableFloe(null, rng.nextInt(chars.size()) + 1, rng.nextInt(4));
                } else {
                    newField = new Floe(null, chars.size() + 2, rng.nextInt(4));
                }
                tempFloes[i][j] = newField;
            }
        }
        //Guarantee that at least one field is a normal Floe
        tempFloes[rng.nextInt(width)][rng.nextInt(height)] = new Floe(null, chars.size() + 2, rng.nextInt(4));
        //set neighbors
        for(int i = 0; i < width; ++i) {
            for(int j = 0; j < height; ++j) {
                if(i - 1 >= 0)
                    tempFloes[i][j].setNeighbour(Direction.WEST, tempFloes[i-1][j]);
                if(i + 1 < width)
                    tempFloes[i][j].setNeighbour(Direction.EAST, tempFloes[i+1][j]);
                if(j - 1 >= 0)
                    tempFloes[i][j].setNeighbour(Direction.NORTH, tempFloes[i][j-1]);
                if(j + 1 < height)
                    tempFloes[i][j].setNeighbour(Direction.SOUTH, tempFloes[i][j+1]);
            }
        }
        //add floes to real array
        for(int i = 0; i < width; ++i) {
            floes.addAll(Arrays.asList(tempFloes[i]).subList(0, height));
        }

        //places polar bear and characters
        addRandom(polarBear);
        for(character.Character c : chars)
            addRandom(c);

        //TODO: add items here


        ////add win condition items
        //int x = rng.nextInt(width);
        //int y = rng.nextInt(height);
        //while(tempFloes[x][y].hasItem()) {
        //    x = rng.nextInt(width);
        //    y = rng.nextInt(height);
        //}
        //tempFloes[x][y].setItem(new Cartridge());
        //while(tempFloes[x][y].hasItem()) {
        //    x = rng.nextInt(width);
        //    y = rng.nextInt(height);
        //}
        //tempFloes[x][y].setItem(new Flare());
        //while(tempFloes[x][y].hasItem()) {
        //    x = rng.nextInt(width);
        //    y = rng.nextInt(height);
        //}
        //tempFloes[x][y].setItem(new Pistol());
//
        ////add usable items
        //int itemsAdded = 0;
        //while(itemsAdded < itemcount) {
        //    x = rng.nextInt(width);
        //    y = rng.nextInt(height);
        //    if(!tempFloes[x][y].hasItem()) {
        //        Item i;
        //        double itemChance = rng.nextDouble();
        //
        //    }
        //}
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
