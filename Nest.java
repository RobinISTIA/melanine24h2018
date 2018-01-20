package pkg24h.du.code;
public class Nest{
	private int population;
	private int food;
	private int[] memory;
	private ArrayList<Ant> colony = null
	
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
	
	private void Populate(){
		for(int  i = 0; i < population; ++i) colony.add(new Ant(i));
	}
}