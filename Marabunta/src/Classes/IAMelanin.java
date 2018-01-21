/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;



import java.util.*;

public class IAMelanin
{
	private static boolean isAnt;
	private static Ant ant;
	private static Nest nest;
	private static ArrayList<Target> targets = null;
	private static ArrayList<String> sorties = null;

    private static int nbrFourmis = 0;
    private static int nbrdehors = 0;
    private static int food = 200;
    private static String stream = "";
    private static int scenarioId = 1;
    private static ArrayList<Integer> antList = new ArrayList<Integer>();
    private static String[] reception;
    private static Game game;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
       game = new Game(0,0);
    	
    	while(true){
    		ArrayList<String> entries = recoie(); 
    		System.out.println(""+entries.size());
    		/*for(String str: entries) {
    			/*switch(str) {
    			case "BEGIN ANT":
    				isAnt = true;
    				ant = new Ant();
    				targets = new ArrayList<Target>();
    				break;
    			case "BEGIN NEST":
    				isAnt = false;
    				nest = new Nest();
    				break;
    			case "END":
    				//APPELER CLASSE DE JULES
    				break;
    			default:
    				if(isAnt) classifyAnt(str);
    				else classifyNest(str);
    				break;
    			}
    			
    			System.out.println(str);
    		}*/
    		
    		/*if(!isAnt)
    			sorties = game.nestAction(nest);
    		else
    			sorties = game.detailledAntAction(ant, targets);
    		
    		for(String sortie : sorties) {
    			System.out.println(sortie);
    		}*/
            
        }
         
    }
    
    public static void classifyAnt(String str) {    	
    	String[] decoupage = str.split(" ");
    	switch(decoupage[0]){
	    	case "TYPE":
				ant.setId(Integer.parseInt(decoupage[1]));
				break;
	    	case "MEMORY":
	    		ant.setMemory(Integer.parseInt(decoupage[1]), Integer.parseInt(decoupage[2]));
				break;
	    	case "STAMINA":
	    		ant.setStamina(Integer.parseInt(decoupage[1]));
				break;
	    	case "SEE_ANT":
	    		targets.add(new Target(Integer.parseInt(decoupage[1]),Type.ANT,decoupage[2],
	    				decoupage[4],Integer.parseInt(decoupage[3]),Integer.parseInt(decoupage[5]),0));
				break;
	    	case "SEE_NEST":
	    		targets.add(new Target(Integer.parseInt(decoupage[1]),Type.NEST,decoupage[2],
	    				decoupage[4],Integer.parseInt(decoupage[3]),0,0));
				break;
	    	case "SEE_FOOD":
	    		targets.add(new Target(Integer.parseInt(decoupage[1]),Type.FOOD,decoupage[2],
	    				"",Integer.parseInt(decoupage[3]),Integer.parseInt(decoupage[4]),0));
				break;
	    	case "SEE_PHEROMONE":
	    		targets.add(new Target(Integer.parseInt(decoupage[1]),Type.PHEROMONE,decoupage[2],
	    				"",Integer.parseInt(decoupage[3]),Integer.parseInt(decoupage[4]),
	    				Integer.parseInt(decoupage[5])));
				break;
			default: break;
    	}
    }
    
    public static void classifyNest(String str) {    	
    	String[] decoupage = str.split(" ");
    	switch(decoupage[0]){
	    	case "STOCK":
				nest.setFood(Integer.parseInt(decoupage[1]));
				break;
	    	case "MEMORY":
	    		int[] memory = new int[20];
	    	    for(int i = 0; i < 20; ++i) memory[i] = Integer.parseInt(decoupage[i+1]);
				nest.setMemory(memory);
				break;
			default: break;
    	}
    }
    
    public static ArrayList<String> recoie() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> reponse = new ArrayList<String>();
        boolean  isReceiving = false;
        while(true) {
        	if(isReceiving == true) {
        		if( sc.nextLine().equals("END")) {
            		isReceiving = false;
            		sc.close();
            		return reponse;
            	}else reponse.add(sc.nextLine());
        	}
        	else if( sc.nextLine().equals("BEGIN NEST") || sc.nextLine().equals("BEGIN ANT") ) isReceiving = true;
        }
    }
}
