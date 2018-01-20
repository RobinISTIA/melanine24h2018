/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Classes;



import java.util.*;

public class IAMelanin
{

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
       System.out.println("Hello world");
    	/*Game partie = new Game(0, 200);*/
       	while(nbrFourmis < 3) {
        	recoie();
        	System.out.println("NEW_ANT 1 \n END");
        	nbrFourmis ++;
        	recoie();
        	System.out.println("OUT_ANT 1 \n END");
        	nbrdehors++;
        	for(int i=0; i<nbrdehors; ++i) {
           		recoie();
           		System.out.println("EXPLORE \n END");
           	}
        	
       	}
       	while(true){
       	for(int i=0; i<4; ++i) {
       		if(i == 0) {
       			System.out.println("END");
       		}
       		else {
       			System.out.println("EXPLORE \n END");
       		}
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
