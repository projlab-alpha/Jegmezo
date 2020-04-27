package control;

import character.Eskimo;
import character.PolarBear;
import character.Researcher;
import field.*;
import item.*;
import snowstormStrategy.SnowstormStrategy;
import snowstormStrategy.SnowstormStrategyDefault;
import snowstormStrategy.SnowstormStrategyIgloo;
import snowstormStrategy.SnowstormStrategyTent;
import waterStrategy.WaterStrategy;
import waterStrategy.WaterStrategyDefault;
import waterStrategy.WaterStrategySuit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Singleton osztály, amely a játék állapotának követésért, a játék lebonyolításáért felelős.
 */
public class Control {

    /**
     * A játékos karakterek száma a játékmezőn.
     */
    private int PlayerCount;

    /**
     * A játékos karakterek listája.
     */
    private ArrayList<character.Character> characters = new ArrayList<character.Character>();

    /**
     * A játékban résztvevő jegesmedve referenciája.
     */
    private PolarBear polarBear;

    /**
     *  A játékmezőre mutató referencia.
     */
    private GameField gameField = new GameField();

    /**
     *  Az egyetlen példánya az osztálynak a Singleton működés érdekében.
     */
    private static Control control = null;

    /**
     *   Privát konstruktor a Singleton működés érdekében.
     */
    private Control(){}

    /**
     * Visszatér az osztály egyetlen példányával ha van,
     * inicializálja a példányt és visszatér vele, ha nincs.
     * @return Az osztály példánya
     */
    public static Control getInstance(){
        if (control == null)
            control = new Control();
        return control;
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy
     * egy játékos karakter valamilyen okból meghalt, és így a játéknak véget kell vetni.
     */
    public void CharacterDied(){
        System.out.println("Game Over");
    }

    /**
     * E metódus meghívásával jelezhetik a játék objektumok, hogy sikerült
     * összerakni és elsütni a jelző pisztolyt, és így játéknak véget kell vetni.
     */
    public void Win(){
        System.out.println("Congratulations! You won!");
    }


    //// COMMANDS ////

    private Item convertItem(String item) {
        Item i;
        if(item.equalsIgnoreCase("Cartridge"))
            i = new Cartridge();
        else if (item.equalsIgnoreCase("Divingsuit"))
            i = new Divingsuit();
        else if (item.equalsIgnoreCase("Flare"))
            i = new Flare();
        else if (item.equalsIgnoreCase("Food"))
            i = new Food();
        else if (item.equalsIgnoreCase("FragileShovel"))
            i = new FragileShovel();
        else if (item.equalsIgnoreCase("Pistol"))
            i = new Pistol();
        else if (item.equalsIgnoreCase("Rope"))
            i = new Rope();
        else if (item.equalsIgnoreCase("Shovel"))
            i = new Shovel();
        else if (item.equalsIgnoreCase("Tent"))
            i = new Tent();
        else i = null;
        return i;
    }


    public int Load(String filename) {
    	try{
    	boolean exit = false;
    	File f=new File(filename);
    	FileReader fr = new FileReader(f);
    	BufferedReader scanner=new BufferedReader(fr);
        while(!exit) {
        	if(scanner.ready()) {
        		String[] input = scanner.readLine().split(" ");
        		int res = Prototype.parseInput(input);
        		System.out.println(res);
        	}
        	else
        		exit = true;
            
        }
        scanner.close();
        }
    	catch(Exception e) {System.out.println(e.getMessage());return -1;}//TODO
        return 0;
    }       

    public int Save(String filename) {       //TODO
        return -1;
    }       //TODO

    
    
    
    
    public int Init() {
        return Init(5);
    }

    public int Init(int size) {
        if(size <= 0)
            return -1;

        this.gameField.getFloes().clear();

        for(int i = 0; i < size * size; ++i) {
            int rand_snowcount = new Random().nextInt(4) + 1;     //random int between 1 and 4 (inclusive)
            Floe f = new Floe(null, 10, rand_snowcount);
            this.gameField.addField(f);
        }
        System.out.println(this.gameField.getFloes().size());
        return 0;
    }

    public int SetNeighbor(String floename1, String floename2) {
        int index1 = floename1.charAt(4);
        int index2 = floename1.charAt(4);
        AbstractField floe1 = this.gameField.getFloeAt(index1);
        AbstractField floe2 = this.gameField.getFloeAt(index2);
        //TODO: Honnan tudjuk, melyik irányban szomszédosak?
        return -1;
    }

    public int AddChar(String chartype, String floename) {
        character.Character ch;
        if(chartype.equalsIgnoreCase("Eskimo")) {
            ch = new Eskimo();
        } else if (chartype.equalsIgnoreCase("Researcher")) {
            ch = new Researcher();
        } else if (chartype.equalsIgnoreCase("polarbear"))
            ch = new PolarBear();
        else return -1;
        try {
            int idx = floename.charAt(4) - '0';
            this.characters.add(ch);
            this.PlayerCount += 1;
            this.gameField.getFloeAt(idx).Accept(ch);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }


    public int MoveChar(int chara, String floename) {        //TODO: 08 - Részletes tervek: 8.2.7 - 'Down' argumentum helyett floe# kell
        try {
            int idx = floename.charAt(4) - '0';
            AbstractField f = this.gameField.getFloeAt(idx);
            character.Character c = this.characters.get(chara - 1);
            c.setField(f);
            f.Accept(c);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }
    
    
    public int ShowFloeDetails(String floea, String attr) {
    	int idx = Integer.parseInt(floea.substring(4, 5));
    	if(attr.equalsIgnoreCase("snow")) {
    		int snow= this.gameField.getFloeAt(idx).ChangeSnow(0);
    		System.out.println("Floe: "+ floea+"\n"+ attr + ":" + snow);
    		return 0;    
    	
    	}
    	else if(attr.equalsIgnoreCase("capacity")) {
    		int snow= this.gameField.getFloeAt(idx).FindCapacity();
    		System.out.println("Floe: "+ floea+"\n"+ attr + ":" + snow);
    		return 0;
    	} 
    	else if(attr.equalsIgnoreCase("snowstormstartegy")) {
    		String snow= this.gameField.getFloeAt(idx).ChangeSnowStrategy(null).getClass().getSimpleName();
    		System.out.println("Floe: "+ floea+"\n"+ attr + ":" + snow);
    	}
    	else if(attr.equalsIgnoreCase("type")) {
    		String snow= this.gameField.getFloeAt(idx).getClass().getSimpleName();
    		System.out.println("Floe: "+ floea+"\n"+ attr + ":" + snow);
    		return 0;
    	}
		
    	
    	return -1;
    	
    	
    	
    	
    	
    }

    public int ShowCharDetails(int chara, String attr) {
        try {
            character.Character ch = this.characters.get(chara - 1);
            System.out.println(ch.getClass().getSimpleName()+" : "+chara);

            if (attr.equalsIgnoreCase("pos")) {                    //pos parancs
                AbstractField floe = ch.getField();
                for (int i = 0; i < this.gameField.getFloes().size(); ++i) {
                    AbstractField f = this.gameField.getFloeAt(i);
                    if (floe == f) {
                        System.out.println("\tPosition: floe" + i);
                        return 0;
                    }
                }
                return -1;
            }
            else {
                String[] attributes = ch.getAttributes();
                if (attr.equalsIgnoreCase("ap")) {                  //ap parancs
                    System.out.println("\tActionpoints: " + attributes[0]);
                    return 0;
                } else if (attr.equalsIgnoreCase("items")) {        //Items parancs
                    System.out.println("\tItems: " + attributes[1]);
                    return 0;
                } else if (attr.equalsIgnoreCase("warmth")) {       //warmth parancs
                    System.out.println("\tWarmth: " + attributes[2]);
                    return 0;
                } else if (attr.equalsIgnoreCase("ws")) {           //ws parancs
                    System.out.println("\tWater strat: " + attributes[3]);
                    return 0;
                } else return -1;
            }
        } catch(Exception e) {
            return -1;
        }
    }

    public int Dig(int chara) {
        try {
            this.characters.get(chara - 1).Dig();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int Pickup(int chara) {
        try {
            this.characters.get(chara - 1).PickUpItem();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int UseItem(int chara, String item) { 
    	character.Character ch = this.characters.get(chara - 1);  //TODO
        if( ch.HasItem(item)){
        	Item i =convertItem(item);
        	i.UseItem(ch);
        	return 0;
        };
    	return -1;

        //try {
        //    this.characters.get(chara).UseItem(-1);      //TODO: item névből index? 
        //} catch(Exception e) {
        //    return -1;
        //}
        //return 0;
    }

    public int UseAbility(int chara, String floe) {
    	character.Character ch = this.characters.get(chara - 1);
    	String type=ch.getClass().getSimpleName();
    	ch.ChActionpoint(-1);
    	if(type.equalsIgnoreCase("Eskimo")) {
    		snowstormStrategy.SnowstormStrategy s=new SnowstormStrategyIgloo();
    		ch.getField().ChangeSnowStrategy(s);
    		return 0;
    	}
    	if(type.equalsIgnoreCase("Researcher")) {
    		int idx = Integer.parseInt(floe.substring(4, 5));
    		int capacity=this.gameField.getFloeAt(idx).FindCapacity();
    		System.out.println("Floe: floe"+ idx);
    		System.out.println("	capacity: " + capacity);
    		return 0;
    		
    	}
        return -1;
    }

    public int SnowStorm(String floename) {
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloeAt(idx).SnowStormHit();
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int Push(String filename) {       //TODO
        return -1;
    }

    public int CharAddItem(int chara, String item) {         //TODO: Prototípus concepcióban az argumentumnak Item helyett nem Stringnek kéne lennie?
        try {
            character.Character ch = this.characters.get(chara - 1);
            Item i = convertItem(item);
            if(i == null)
                return -1;
            ch.addItem(i);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public int FloeAddItem(String floename, String item) {
        Item i = convertItem(item);
        if(i == null)
            return -1;
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloeAt(idx).setItem(i);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public int CharWaterStrategy(int chara, String ws) {
        WaterStrategy w;
        if(ws.equalsIgnoreCase("Default"))
            w = new WaterStrategyDefault();
        else if(ws.equalsIgnoreCase("DivingSuit") || ws.equalsIgnoreCase("Suit"))
            w = new WaterStrategySuit();
        else return -1;
        try {
            this.characters.get(chara - 1).ChangeStrategy(w);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int FloeSnowStormStrategy(String floename, String sss) {
        SnowstormStrategy s;
        if(sss.equalsIgnoreCase("default"))
            s = new SnowstormStrategyDefault();
        else if(sss.equalsIgnoreCase("igloo"))
            s = new SnowstormStrategyIgloo();
        else if(sss.equalsIgnoreCase("tent"))
            s = new SnowstormStrategyTent();
        else return -1;
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloeAt(idx).ChangeSnowStrategy(s);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int CharActionPoints(int chara, int ap) {
        try {
            this.characters.get(chara - 1).setActionpoint(ap);
            return 0;
        } catch(Exception e) {
            return -1;
        }
    }

    public int CharWarmth(int chara, int warmth) {
        try {
            this.characters.get(chara - 1).setWarmth(warmth);
            return 0;
        } catch(Exception e) {
            return -1;
        }
    }

    public int AddUnstableFloe(String floename, int capacity) {
        int rand_snowcount = new Random().nextInt(4) + 1;
        UnstableFloe uf = new UnstableFloe(null, capacity, rand_snowcount);
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloes().set(idx, uf);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int AddHole(String floename) {
        int rand_snowcount = new Random().nextInt(4) + 1;
        Hole h = new Hole(null, rand_snowcount);
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloes().set(idx, h);
        } catch(Exception e) {
            return -1;
        }
        return 0;
    }

    public int SetSnow(String floename, int snow) {
        try {
            int idx = floename.charAt(4) - '0';
            this.gameField.getFloeAt(idx).setSnowCount(snow);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
