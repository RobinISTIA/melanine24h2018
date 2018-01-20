package Classes;

import java.util.*;

public class Game {

	private ArrayList<Nest> nestList;
	private ArrayList<Ant> antList;
	private int maxAnt = 10;
	private int nextAntId;
	
	public Game(int population, int food){
		nestList.add(new Nest(population, food));
		antList = new ArrayList<Ant>();
		nextAntId = population;
	}
	
	public String nestAction(int id){
		String stream = "";
		boolean actionExclusive = true;
		Nest nest = nestList.get(id);
		
		if(actionExclusive && nest.getPopulation() > 0)
		{
			int antId = nest.outAnt();
			antList.add(new Ant(antId));
			stream += "ANT_OUT "+antId+" 0 0 0\n";
		}
		
		
		if(nest.getPopulation() + antList.size() < maxAnt && actionExclusive){
			nest.createAnt(nextAntId);
			stream += "NEW_ANT "+nextAntId+"\n";
			actionExclusive = false;
		}		
		
		
		return stream+"End";
	}
	public String shortAntAction(Ant ant){
		String stream = "";
		return stream +"END";
	}
	public String detailledAntAction(Ant ant, ArrayList<Pheromone> pheromoneList, ArrayList<Food> foodList, ArrayList<Nest> RecevideNestList, ArrayList<Ant> receivedAntList){
		String stream = "";
		boolean actionExclusive = true;
		
		Ant antFound = getAnt(ant.getId());
		if(ant.getId() != -1){
			
			if(foodList.size() != 0){
				Food food = lookAtNearestFood(foodList);
				if(food.ID != -1 && ant.getFood() < 1000){
					if(food.zone == Zone.NEAR)
						stream += ant.collect(food.ID, min(1000-ant.getFood(), food.quantity)) + "\n";
					
					else
						stream += ant.moveTo(food.ID) + "\n";	
					
					actionExclusive = false;
				}
			}
			if(pheromoneList.size() != 0){
				
			}
				
		}
		else{
			stream += ant.explore()+"\n";
			stream += ant.putPheromone(505)+"\n";
		}	
		
		return stream+"End";
	}
	
	public Ant getAnt(int id){
		
		for(int i = 0; i < antList.size(); ++i)
			if(antList.get(i).getId() == id)
				return antList.get(i);
		
		return new Ant(-1);
		
	}
	
	public Food lookAtNearestFood(ArrayList<Food> food){
		int min = 32767;
		int id = 0;
		
		for(int i = 0; i < food.size(); ++i)
			if(food.get(i).distance < min){
				min = food.get(i).distance;
				id = food.get(i).ID;
			}
				
		
		if(min != 32767)
			return food.get(id);
		
		return new Food(-1, 0, 0, Zone.DOESNTEXIST);
		
	}
	
	public int min(int a, int b){
		if(a<b)
			return a;
		return b;
	}
	
	public int max(int a, int b){
		if(a>b)
			return a;
		return b;
	}
	
}
