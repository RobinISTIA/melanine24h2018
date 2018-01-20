package Classes;

public class Target {
	private int id;
	private Type type;
	private String zone;
	private String relation;
	private int distance;
	private int personalParameter;
	private int typePheromone;
	
	public Target(int id, Type type, String zone, String relation, int distance, int personalParameter, int typePheromone) {
		super();
		this.id = id;
		this.type = type;
		this.zone = zone;
		this.relation = relation;
		this.distance = distance;
		this.personalParameter = personalParameter;
		this.typePheromone = typePheromone;
	}

	public Type getType() {
		return type;
	}

	public String getZone() {
		return zone;
	}

	public String getRelation() {
		return relation;
	}

	public int getDistance() {
		return distance;
	}

	public int getPersonalParameter() {
		return personalParameter;
	}

	public int getId() {
		return id;
	}

	public int getTypePheromone() {
		return typePheromone;
	}
}
