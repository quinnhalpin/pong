package src;

public class Player extends PhysicsObj{
	private static int width;
	private static int height;
	private String name;

	public Player() {
		this.width = 1;
		this.height = 5;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}
}
