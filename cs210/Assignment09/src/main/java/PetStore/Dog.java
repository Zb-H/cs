package PetStore;

class Dog extends Pet {
	private double weight;
	
	public void setWeight(double newWeight) {
		weight = newWeight;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String speak() {
		return "Bark! Bark! Bark!";
	}
}
