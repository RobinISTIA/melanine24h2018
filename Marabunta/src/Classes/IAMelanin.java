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
    				break;
    			case "BEGIN NEST":
    				isAnt = false;
    				break;
    			case "END":
    				break;
    			default:
    				
    				break;
    			}
    		}
            while(stream.length() < 100){
             
            }
        }
         
    }
    
    public void getNestInfos(){
        
    }
    
    public void createAnt(){
       if(nbrFourmis < 12 && food > 100){
           
       } 
    }
    
    public void sendAnt(int id){
        
    }
    
    public void addStream(String partStream){
        
    }
    
    public void getAntInfo(int id){
        
    }
    
    public void envoie(String _stream) {
        
       System.out.println(_stream);
          
    }
    
    public static String[] recoie() {
        Scanner sc = new Scanner(System.in);
        String[] reponse = null;
        int i = 0;
        if(sc.nextLine() == "BEGIN") {
            while(true) {
                reponse[i] = sc.nextLine();
                if(reponse[i] == "END") {
                    return reponse;
                }
            }
        }
        return null;
    }
    
}
