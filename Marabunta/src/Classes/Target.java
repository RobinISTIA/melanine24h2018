package Classes;

public class Target {
	private Type type;
	private Zone zone;
	private Relation relation;
	private double distance;
	private int personalParameter;	
	
	public Target(Type type, Zone zone, Relation relation, double distance, int personalParameter) {
		super();
		this.type = type;
		this.zone = zone;
		this.relation = relation;
		this.distance = distance;
		this.personalParameter = personalParameter;
	}

	public Type getType() {
		return type;
	}

	public Zone getZone() {
		return zone;
	}

	public Relation getRelation() {
		return relation;
	}

	public double getDistance() {
		return distance;
	}

	public int getPersonalParameter() {
		return personalParameter;
	}
	
	
	
}
