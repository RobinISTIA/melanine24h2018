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

    private static int nbrFourmis = 0;
    private static int nbrdehors = 0;
    private static int food = 200;
    private static String stream = "";
    private static int scenarioId = 1;
    private static ArrayList<Integer> antList = new ArrayList<Integer>();
    private static String[] reception;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
       
    	
    	while(true){
    		String[] entries = recoie(); 
    		for(String str: entries) {
    			switch(str) {
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
    		}
            while(stream.length() < 100){
             
            }
        }
         
    }
    
    public static void classifyAnt(String str) {    	
    	String[] decoupage = str.split(" ");
    	switch(decoupage[2]){
	    	case "TYPE":
				ant.setId(Integer.parseInt(decoupage[3]));
				break;
	    	case "MEMORY":
	    		ant.setMemory(Integer.parseInt(decoupage[3]), Integer.parseInt(decoupage[4]));
				break;
	    	case "STAMINA":
	    		ant.setStamina(Integer.parseInt(decoupage[3]));
				break;
	    	case "SEE_ANT":
	    		targets.add(new Target(Integer.parseInt(decoupage[3]),Type.ANT,decoupage[4],
	    				decoupage[6],Integer.parseInt(decoupage[5]),Integer.parseInt(decoupage[7]),0));
				break;
	    	case "SEE_NEST":
	    		targets.add(new Target(Integer.parseInt(decoupage[3]),Type.NEST,decoupage[4],
	    				decoupage[6],Integer.parseInt(decoupage[5]),0,0));
				break;
	    	case "SEE_FOOD":
	    		if(decoupage[4] == "NEAR")
	    		targets.add(new Target(Integer.parseInt(decoupage[3]),Type.FOOD,decoupage[4],
	    				"",Integer.parseInt(decoupage[5]),Integer.parseInt(decoupage[6]),0));
				break;
	    	case "SEE_PHEROMONE":
	    		targets.add(new Target(Integer.parseInt(decoupage[3]),Type.PHEROMONE,decoupage[4],
	    				"",Integer.parseInt(decoupage[5]),Integer.parseInt(decoupage[6]),
	    				Integer.parseInt(decoupage[7])));
				break;
			default: break;
    	}
    }
    
    public static void classifyNest(String str) {    	
    	String[] decoupage = str.split(" ");
    	switch(decoupage[2]){
	    	case "STOCK":
				nest.setFood(Integer.parseInt(decoupage[3]));
				break;
	    	case "MEMORY":
	    		int[] memory = new int[20];
	    	    for(int i = 3; i < 20; ++i) memory[i] = Integer.parseInt(decoupage[3]);
				nest.setMemory(memory);
				break;
			default: break;
    	}
    }
        
    public void envoie(String _stream) {
        
       System.out.println(_stream);
          
    }
    
    public static String[] recoie() {
        Scanner sc = new Scanner(System.in);
        String[] reponse = null;
        int i = 0;
        while(true) {
            reponse[i] = sc.nextLine();
            if(reponse[i] == "END") {
                return reponse;
            }
        }
    }
}
