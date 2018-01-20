package pkg24h.du.code;
public class Ant{
	
	private int id;
	private int food;
	private int stamina;
	
	private int mem0, mem1;
	
	public Ant(int id, int food){
		this.id = id;
		this.food = food;
		this.stamina = 10000;
	}
	
	public Ant(int id, int food, int stamina){
		super(id, food);
		this.stamina = stamina;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getFood(){
		return this.food;
	}
	
	public int getStamina(){
		return this.stamina;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setFood(int food){
		this.food = food;
	}
	
	public void setStamina(int stamina){
		this.stamina = stamina;
	}
}