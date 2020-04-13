package book.chap09;

public class RubberDuck extends Duck {
	RubberDuck(){
		flyBehavior = new FlyNoWay();
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

}
