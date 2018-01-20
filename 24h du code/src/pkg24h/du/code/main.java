/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg24h.du.code;

/**
 *
 * @author Zuzu
 */

import java.util.*;

public class main
{

    private static int nbrFourmis = 0;
    private static int food = 200;
    private static String stream = "";
    private static int scenarioId = 1;
    private static ArrayList<Integer> antList = new ArrayList<Integer>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while(true){
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
    
    public void envoie(String[] cmds) {
        if(cmds.length != 0) {
           System.out.println("BEGIN");
        } 
        for(int i=0; i<cmds.length; i++) {
            System.out.println(cmds[i]);
        }
        System.out.println("END");     
    }
    
    public String[] recoie() {
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
    }
}
