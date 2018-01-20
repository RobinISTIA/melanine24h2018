package Classes;

public class Ant{
	
	private int id; //type
	private int food;
	private int stamina;
	
	private int mem0, mem1;
	
	public Ant(int id){
		this.id = id;
		this.food = 0;
		this.stamina = 10000;
	}
	
	public Ant(int id, int food){
		this(id);
		this.food = food;
		this.stamina = 10000;
	}
	
	public Ant(int id, int food, int stamina){
		this(id, food);
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
	
	public String setMemory(int mem0, int mem1){
		this.mem0 = mem0;
		this.mem1 = mem1;
		return "SET_MEMORY "+mem0+" "+mem1;
	}
	
	//Exclusif
	public String explore(){
		--stamina;
		return "EXPLORE";
	}
	
	
	public String putPheromone(int type){
		stamina -= 3;
		return "PUT_PHEROMONE "+type;
	}
	
	public String refillPheromone(int id){
		--stamina;
		return "RECHARGE_PHEROMONE "+id;
	}
	
	public String replacePheromone(int id, int type){
		stamina -= 2;
		return "CHANGE_PHEROMONE "+id+" "+type;
	}
	
	//Exclusif
	public String collect(int id,int qtt){
		stamina -= 4;
		return "COLLECT "+id+" "+qtt;
	}
	
	//Exclusif
	public String moveTo(int id){
		stamina -= 2;
		return "MOVE_TO "+id;
	}

	//Exclusif
	public String attack(int id, int force){
		stamina -= force;
		return "ATTACK "+id+" "+force;
	}
	
	public String suicide(){
		return "SUICIDE";
	}
	
	public String eat(int qtt){
		food -= qtt;
		stamina += qtt * 10;
		if(stamina > 10000) stamina = 10000;
		return "EAT "+qtt;
	}
	
	//Exclusif
	public String exchangeGive(int id, int qtt){
		food -= qtt;
		return "DO_TROPHALLAXIS "+id+" "+qtt;
	}
	
	public void exchangeReceive(int qtt){
		food += qtt;
		if(food > 1000) food = 1000;
	}
	
	public String enterInTheNest(int id){
		food = 0;
		return "NEST "+id;
	}
	
	//Exclusif
	public String turn(int angle){
		if(angle < -180 || angle > 180) angle = 0;
		--stamina;
		return "TURN "+angle;
	}
}