package Classes;

import java.util.*;


public class Nest{
	private int population;
	private int food;
	private int[] memory;
	private ArrayList<Ant> colony = null;
	
	public Nest(){
		this.population = 0;
		this.food = 0;
		this.memory = new int[20];
		this.colony = new ArrayList<Ant>();
	}
	
	public Nest(int population, int food){
		this.population = population;
		this.food = food;
		this.memory = new int[20];
		this.colony = new ArrayList<Ant>();
		if( population > 0) populate();
	}
	
	public int getPopulation(){
		return this.population;
	}
	
	public int getFood(){
		return this.food;
	}
	
	public void setPopulation(int population){
		this.population = population;
	}
	
	public void setFood(int food){
		this.food = food;
	}
	
	public void createAnt(int id){
		colony.add(new Ant(id));
	}
	
	public int outAnt(){
		int id = colony.get(0).getId();
		colony.remove(0);
		return id;
	}
	
	public void inAnt(int id){
		colony.add(new Ant(id));
	}
	
	private void populate(){
		for(int  i = 0; i < population; ++i) colony.add(new Ant(i));
	}

	public int[] getMemory() {
		return memory;
	}

	public void setMemory(int[] memory) {
		this.memory = memory;
	}	
	
	public int getNextMemory(){
		for(int i = 0; i < 20; ++i)
			if(this.memory[i] == 0)
				return i;
		
		return -1;
	}

	public int getColony() {
		// TODO Auto-generated method stub
		return colony.size();
	}
}