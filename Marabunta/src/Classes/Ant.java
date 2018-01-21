package Classes;

public class Ant{
	
	private int id; //type
	private int food;
	private int stamina;
	
	private int mem0, mem1;
	
	public Ant(){}
	
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
		if(checkActionPossible(1) == "") return "EXPLORE";
		else return checkActionPossible(1);
	}
	
	
	public String putPheromone(int type){
		if(checkActionPossible(3) == "") return "EXPLORE";
		else return checkActionPossible(3);
	}
	
	public String refillPheromone(int id){
		if(checkActionPossible(1) == "") return "RECHARGE_PHEROMONE"+id;
		else return checkActionPossible(1);
	}
	
	public String replacePheromone(int id, int type){
		if(checkActionPossible(2) == "") return "CHANGE_PHEROMONE "+id+" "+type;
		else return checkActionPossible(2);
	}
	
	//Exclusif
	public String collect(int id,int qtt){
		if(checkActionPossible(4) == "") return "COLLECT "+id+" "+qtt;
		else return checkActionPossible(4);
	}
	
	//Exclusif
	public String moveTo(int id){
		if(checkActionPossible(2) == "") return "MOVE_TO "+id;
		else return checkActionPossible(2);
	}

	//Exclusif
	public String attack(int id, int force){
		if(checkActionPossible(force) == "") return "ATTACK "+id+" "+force;
		else return checkActionPossible(force);
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
		if(checkActionPossible(qtt).equals("")) {
			food -= qtt;
			return "DO_TROPHALLAXIS "+id+" "+qtt;
		}else return checkActionPossible(qtt);
	}
	
	public void exchangeReceive(int qtt){
		food += qtt;
		if(food > 1000) food = 1000;
	}
	
	public String enterInTheNest(int id){
		food = 0;
		return "NEST "+id+"\n";
	}
	
	//Exclusif
	public String turn(int angle){
		if(angle < -180 || angle > 180) angle = 0;
		if(checkActionPossible(1) == "") return "TURN "+angle;
		else return checkActionPossible(1);
	}
	
	public String checkActionPossible(int usedStamina) {
		if(stamina - usedStamina < 1) 
		{
			if(food > 0) return eat(1);
			else return suicide();
		}
		else {
			stamina -= usedStamina;
			return "";
		}
	}
	
	public int getMemory0(){
		return mem0;
	}
	
	public int getMemory1(){
		return mem1;
	}
}