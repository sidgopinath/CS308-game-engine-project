package GameEngine;

import java.awt.Shape;
import java.util.List;

import interfaces.Collidable;
import interfaces.Movable;

public class Tower extends Sprite implements Collidable, Movable{

	private String myImageString;
	private List<Integer> myAccessList;
	private Integer myRange;
	private Integer myFireRate;
	//projectile?
	//state?
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateCollision(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	public void fire(){
		
	}

	@Override
	public void setCollisionBounds() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Shape getCollisionBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
