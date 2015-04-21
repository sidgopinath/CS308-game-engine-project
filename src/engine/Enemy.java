/*
 * 
 */
package engine;

import interfaces.Authorable;
import interfaces.Collidable;
import interfaces.Movable;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


// TODO: Auto-generated Javadoc
/**
 * The Class Enemy.
 * 
 * 
 * @author Brooks, Patrick, Robert, and Sid.
 *
 * 
 */




public class Enemy extends GridObject implements Collidable, Movable {



	private Integer mySpeed;
	
	private EnemyMovement myEnemyMovement;

	/** The my damage. */
	private Integer myDamage;

	/** The my health. */
	private Integer myHealth;

	/** The my collision bounds. */
	private Shape myCollisionBounds;

	/** The my steps. */
	private LinkedList<Tile> myTilePath;

	/** The my rad. */
	private int myRad;

	private Integer myID; // set by GAE

	/** The timer. */
	private Timer timer;


	private Double distanceWalked;


	private Path myPath;
	//orientation??
	//State?


	/**
	 * Instantiates a new enemy.
	 */
	public Enemy(){

		myTilePath = new LinkedList<Tile>();
	}
	
	/**
	 * The following exist for the GAE's use
	 */
	public void setHealth(int x){
		myHealth = x;
	}
	
	public void setSpeed(int x){
		mySpeed = x;
	}
	
	public void setDamage(int x){
		myDamage = x;
	}
	
	public int getHealth(){
		return myHealth;
	}
	
	public int getSpeed(){
		return mySpeed;
	}
	
	public int getDamage(){
		return myDamage;
	}
	
	//TODO: Fix this with new projectile info

	public void executeEffect(Projectile projectile) {
	/*	// change stuff
		mySpeed -= projectile.myEffect.getSpeedDamage();
		// if its not final do stuff
		if (!projectile.myEffect.isFinal()) {
			timer = new Timer();
			timer.schedule(
					new reverseEffect(projectile.myEffect.getSpeedDamage()),
					projectile.myEffect.getDuration());
		}*/
	}





		/**
		 * Sets the steps.
		 *
		 * @param steps the new steps
		 */

		public void setSteps(LinkedList<Tile> steps){
			myTilePath = steps;
		}

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */


		public List<String> getWalkables(){
			return myAccessNames;
		}


		void setPath(Path p){
			myPath = p.generateNew();

		}

		public List<Tile> getTilePath(){
			return myTilePath;

		}

		public void setTilePath(LinkedList<Tile> l){
			myTilePath = l; 
		}






		/**
		 * Gets the enemy damage.
		 *
		 * @return the enemy damage
		 */
		public Integer getEnemyDamage(){
			return myDamage;
		}





		/**
		 * The Class reverseEffect.
		 */
		class reverseEffect extends TimerTask {

			/** The speed change. */
			private Integer speedChange;

			/**
			 * Instantiates a new reverse effect.
			 *
			 * @param speed the speed
			 */
			reverseEffect(Integer speed) {
				speedChange = speed;
			}

			/* (non-Javadoc)
			 * @see java.util.TimerTask#run()
			 */
			public void run() {
				mySpeed += speedChange;
				timer.cancel();
			}
		}

		/* (non-Javadoc)
		 * @see interfaces.Collidable#isDead()
		 */
		@Override
		public boolean isDead() {
			if (myHealth <= 0) {
				return true;
			}
			return false;
		}

		/* (non-Javadoc)
		 * @see interfaces.Collidable#getCollisionBounds()
		 */
		public Shape getCollisionBounds() {
			return myCollisionBounds;
		}

		/* (non-Javadoc)
		 * @see interfaces.Collidable#setCollisionBounds()
		 */
		@Override
		public void setCollisionBounds() {
			//myCollisionBounds = new Ellipse2D.Double(myLocation.x, myLocation.y,myRad * 2, myRad * 2);

		}


		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}


		@Override
		public Placement move() {
			return myPath.getNext();
		}


		@Override
		public boolean evaluateCollision(Collidable collider) {
			// TODO Auto-generated method stub
			return false;
		}

		public EnemyMovement getMovement() {
			return myEnemyMovement;
		}
	}



