package src;

public class PongDemo {
	public static void main(String[] args) {
		// Vec v = new Vec(90, 3);
		// System.out.println(v.getDegrees());
		// System.out.println(v.getMagnitude());

		// PhysicsObj o = new PhysicsObj();
		// o.setAcc(v);
		// o.setVel(new Vec(180, 5));
		// System.out.println(o.getAcc());
		// System.out.println(o.getVel());

		// o.setAcc(new Vec(29, 1));
		// o.setVel(new Vec(40, 2));
		// System.out.println(o.getAcc());
		// System.out.println(o.getVel());

		Player p = new Player();
		p.setAcc(new Vec(100, 1));
		p.setVel(new Vec(200, 2));
		System.out.println(p.getAcc());
		System.out.println(p.getVel());

		Ball b = new Ball();
		b.setAcc(new Vec(500, 5));
		b.setVel(new Vec(600, 6));
		System.out.println(b.getAcc());
		System.out.println(b.getVel());

		PongModel model = new PongModel();
		System.out.println(model.getScore());
	} 
}
