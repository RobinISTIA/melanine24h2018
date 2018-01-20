package Classes;

public class Pheromone {
	public int persistence;
	public int ID;
	public int type;
	public int distance;
	public Zone zone;
	
	public Pheromone(int type, int ID, int persistence, int d, Zone z){
		this.persistence = persistence;
		this.ID = ID;
		this.type = type;
		this.distance = d;
		this.zone = z;
	}
	
	
}
