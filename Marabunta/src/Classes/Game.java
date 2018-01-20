package Classes;

import java.util.*;

public class Game {

	private Nest myNest;
	private ArrayList<Ant> antList;
	private int maxAnt = 10;
	private int nextAntId;
	
	public Game(int population, int food){
		myNest = new Nest(population, food);
		antList = new ArrayList<Ant>();
		nextAntId = population;
	}
	
	public ArrayList<String> nestAction(Nest nest){
		ArrayList<String> stream = new ArrayList<String>();
		boolean actionExclusive = true;
		myNest.setFood(nest.getFood());
		myNest.setPopulation(nest.getPopulation());
		
		if(actionExclusive && nest.getPopulation() > 0)
		{
			int antId = nest.outAnt();
			antList.add(new Ant(antId));
			stream.add("ANT_OUT "+antId+" 0 0 0\n");
		}
		
		
		if(nest.getPopulation() + antList.size() < maxAnt && actionExclusive){
			nest.createAnt(nextAntId);
			stream.add("NEW_ANT "+nextAntId+"\n");
			actionExclusive = false;
		}		
		
		stream.add("End");
		return stream;
	}
	public String shortAntAction(Ant ant){
		
		String stream = "";
		return stream +"END";
	}
	public ArrayList<String> detailledAntAction(Ant ant, ArrayList<Target> targets){
		ArrayList<String> stream = new ArrayList<String>();
		boolean actionExclusive = true;
		
		ArrayList<Food> foodList = getFoods(targets);
		ArrayList<Integer> idxNestList = getNests(targets);
		ArrayList<Pheromone> pheromoneList = getPheromones(targets);
		
		Ant antFound = getAnt(ant.getId());
		if(antFound.getId() != -1){	
			if(foodList.size() != 0 && actionExclusive){
				Food food = lookAtNearestFood(foodList);
				if(food.ID != -1 && ant.getFood() < 1000){
					if(food.zone.equals("NEAR"))
						stream.add(ant.collect(food.ID, min(1000-ant.getFood(), food.quantity)) + "\n");
					
					else
						stream.add(ant.moveTo(food.ID) + "\n");	
					
					actionExclusive = false;
				}
			}
			if(idxNestList.size() > 0 && actionExclusive && ant.getFood()>500){
				Target t = lookAtNearestNests(targets, idxNestList);
				if(t != null){
					if(t.getZone().equals("NEAR"))
						stream.add(ant.enterInTheNest(t.getId())+ "\n");
					else
						stream.add(ant.moveTo(t.getId()) + "\n");
					
					actionExclusive = false;
				}
					
			}
			
			if(pheromoneList.size() != 0 && actionExclusive && ant.getFood()>500){
				int idx = lookForOldestPheromone(pheromoneList);
				stream.add(ant.moveTo(idx) + "\n");
			}else if(actionExclusive){
				stream.add(ant.explore() + "\n");
				stream.add(ant.putPheromone(ant.getId())+"\n");
			}
				
		}
		else{
			stream.add(ant.explore()+"\n");
			stream.add(ant.putPheromone(505)+"\n");
		}	
		stream.add("End");
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
	
}
