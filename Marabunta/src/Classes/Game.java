package Classes;

import java.util.*;

public class Game {

	private Nest myNest;
	private ArrayList<Ant> antList;
	private int maxAnt = 10;
	private int nextAntId =1;
	private int numVersion = 2;
	
	public Game(int population, int food){
		myNest = new Nest(population, food);
		antList = new ArrayList<Ant>();
		nextAntId = population;
	}
	
	public ArrayList<String> nestAction(Nest nest){
		ArrayList<String> stream = new ArrayList<String>();
		stream.add("BEGIN");
		boolean actionExclusive = true;
		
		/*
		myNest.setFood(nest.getFood());
		myNest.setPopulation(nest.getPopulation());
		
		if(!newMemory(myNest, nest)){
			myNest.setMemory(nest.getMemory());
			int[] memory = nest.getMemory();
			stream.add("MEMORY "+memory[0]+" "+memory[1]+" "+memory[2]+" "+memory[3]+" "+memory[4]+" "+memory[5]+" "+
					memory[6]+" "+memory[7]+" "+memory[8]+" "+memory[9]+" "+memory[10]+" "+memory[11]+" "+memory[12]+" "+
					memory[13]+" "+memory[14]+" "+memory[15]+" "+memory[16]+" "+memory[17]+" "+memory[18]+" "+memory[19]);
		}
		
		*/
		
		if(actionExclusive && nest.getColony() > 0)
		{
			int antId = nest.outAnt();
			antList.add(new Ant(antId));
			stream.add("ANT_OUT "+antId+" 0 0 0");
			actionExclusive = false;
		}
		
		
		if(nest.getColony() + antList.size() < maxAnt && actionExclusive && nest.getFood() > 100){
			nest.createAnt(nextAntId);
			stream.add("ANT_NEW "+nextAntId);
			actionExclusive = false;
			nextAntId++;
		}		
		
		stream.add("END");
		return stream;
	}
	
	
	public ArrayList<String> shortAntAction(Ant ant){
		
		ArrayList<String> stream = new ArrayList<String>();
		stream.add("BEGIN");
		stream.add("EXPLORE");
		stream.add("END");
		return stream;
	}

	public ArrayList<String> detailledAntAction(Ant ant, ArrayList<Target> targets){
		ArrayList<String> stream = new ArrayList<String>();
		stream.add("BEGIN");
		boolean actionExclusive = true;
		
		ArrayList<Food> foodList = getFoods(targets);
		ArrayList<Integer> idxNestList = getNests(targets);
		ArrayList<Pheromone> pheromoneList = getPheromones(targets);
		
		Ant antFound = getAnt(ant.getId());
		if(antFound.getId() != -1){	
			if(foodList.size() != 0 && actionExclusive){
				Food food = lookAtNearestFood(foodList);
				if(food.ID != -1 && ant.getFood() < 1000 && ant.getMemory1() == 0){
					if(food.zone.equals("NEAR")){
						int ramasse = min(1000-ant.getFood(), food.quantity-1);
						stream.add(ant.collect(food.ID,ramasse));
						if(ramasse == food.quantity-1)
							stream.add(ant.setMemory(0, 1));
					}
						
					
					else{
						stream.add(ant.moveTo(food.ID));
						stream.add(ant.setMemory(ant.getMemory0()+1, ant.getMemory1()));
					}
							
					
					actionExclusive = false;
				}
			}
			if((idxNestList.size() > 0 && actionExclusive && ant.getFood()>500) || ant.getMemory1() == 1){
				Target t = lookAtNearestNests(targets, idxNestList);
				if(t != null){
					if(t.getZone().equals("NEAR")){
						stream.add(ant.enterInTheNest(t.getId()));
						removeFromOutside(ant);
					}
						
					else
						stream.add(ant.moveTo(t.getId()));
					
					actionExclusive = false;
				}
					
			}
			
			if((pheromoneList.size() != 0 && actionExclusive && ant.getFood()>500) || ant.getMemory1() == 1 || ant.getMemory0() >= 50){
				int idx = lookForOldestPheromone(pheromoneList);
				stream.add(ant.moveTo(idx));
				stream.add(ant.putPheromone(ant.getId()));
			}else if(actionExclusive){
				stream.add(ant.explore());
				stream.add(ant.putPheromone(ant.getId()));
				stream.add(ant.setMemory(ant.getMemory0()+1, ant.getMemory1()));
			}
				
		}
		else{
			stream.add(ant.explore());
			stream.add(ant.putPheromone(505));
		}	
		stream.add("END");
		return stream;
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
		
		return new Food(-1, 0, 0, "DOESNTEXIST");
		
	}
	
	public boolean newMemory(Nest nest, Nest newNest){
		boolean test = true;
		for(int i = 0; i < 20; ++i)
			test = (nest.getMemory()[i] == nest.getMemory()[i]) && test;
		
		return test;
	}
	
	public Target lookAtNearestNests(ArrayList<Target> targets, ArrayList<Integer> idxNests){
		Target t = null;
		int idx = -1;
		int dist = 32767;
		
		for(int i = 0; i < idxNests.size(); ++i){
			if(targets.get(idxNests.get(i)).getDistance() < dist){
				idx = idxNests.get(i);
				dist = dist;
			}
		}
		
		if(idx != -1)
			t = targets.get(idx);
		
		return t;
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
	
	public ArrayList<Food> getFoods(ArrayList<Target> targets){
		ArrayList<Food> foods = new ArrayList<Food>();
		for (Iterator<Target> i = targets.iterator(); i.hasNext();) {
		    Target item = i.next();
		    if(item.getType() == Type.FOOD)
		    	foods.add(new Food(item.getId(), item.getDistance(), item.getPersonalParameter(), item.getZone()));
		}
		
		return foods;
	}
	
	public ArrayList<Integer> getNests(ArrayList<Target> targets){
		ArrayList<Integer> idxList = new ArrayList<Integer>();
		for (int i = 0; i < targets.size(); ++i) {
		    if(targets.get(i).getType() == Type.NEST)
		    	idxList.add(i);
		}
		
		return idxList;
	}
	
	public ArrayList<Pheromone> getPheromones(ArrayList<Target> targets){
		ArrayList<Pheromone> pheromones = new ArrayList<Pheromone>();
		for (Iterator<Target> i = targets.iterator(); i.hasNext();) {
		    Target item = i.next();
		    if(item.getType() == Type.PHEROMONE)
		    	pheromones.add(new Pheromone(item.getTypePheromone(), item.getId(), item.getPersonalParameter(), item.getDistance(), item.getZone()));
		}
		
		return pheromones;
	}
	
	public int lookForOldestPheromone(ArrayList<Pheromone> pheromones){
		int id = -1;
		int min = 32767;
		for (Iterator<Pheromone> i = pheromones.iterator(); i.hasNext();) {
			Pheromone item = i.next();
		    if(min > item.persistence){
		    	id = item.ID;
		    	min = item.persistence;
		    }
		}
		
		
		return id;
	}
	
	public void removeFromOutside(Ant ant){
		for(int i = 0; i < antList.size(); ++i)
			if(ant.getId() == antList.get(i).getId()){
				antList.remove(i);
				return;
			}
				
	}
}
