package engine;

import interfaces.Collidable;
import interfaces.Movable;
import interfaces.Shootable;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PathFinder {
	
	//private Tile[][] myTiles;
	private Grid myGrid;
	private HashMap<String, Path> myEnemyPaths; 
	// this will be an issue when multiple enemies of the same type require different paths..
	// ie multiple different ports with the same waves
	
	public PathFinder(Grid grid){
		myGrid = grid;
	}
	
	//REFACTOR to make this a generic setPath()... for both enemies and projectiles
	void setEnemyPath(Enemy enemy, Wave w){
		if(!myEnemyPaths.containsKey(enemy.getName()))
			myEnemyPaths.put(enemy.getName(), findEnemyPath(enemy, myGrid.getPortFor(w)));
		
		enemy.setPath(myEnemyPaths.get(enemy.getName()));
	}
	

	
	public Path findEnemyPath(Enemy enemy, Tile port){
		Tile current = port;
		LinkedList<Tile> path = new LinkedList<Tile>();
		boolean pathFound = false;
		while(!pathFound){
			path.add(current);
			current = findNextTile(current, enemy);	
			if (current == null){ // perhaps give Tile a way to check if it has a Tower on it...?
				pathFound = true;
				
			}
		}
		return convertToPath(path, enemy);
	}
	
	private Path convertToPath(LinkedList<Tile> tiles, Enemy enemy){
		Tile[] tileArray = (Tile[]) tiles.toArray();
		
		List<Placement> myMovements = new LinkedList<Placement>();
		
		Tile lastStraight = tileArray[0];
		
		for (int i = 2; i < tileArray.length; i++){
			if(tileArray[i-2].getX() != tileArray[i].getX() && tileArray[i-2].getY() != tileArray[i].getY()){
				myMovements.addAll(generateStretch(lastStraight.getLocation(), tileArray[i-2].getLocation(), enemy.getMovement()));
				myMovements.addAll(generateTurn(tileArray[i-2].getLocation(), tileArray[i].getLocation(), enemy.getMovement()));
				lastStraight = tileArray[i];	
			}
		}
		
		// MAKE SURE THIS CAST WORKS
		return new Path((LinkedList<Placement>) myMovements); 
	}
	
	
	// Given two points which represent two tiles on the ends of a straightaway
	List<Placement> generateStretch(Point2D.Double Start, Point2D.Double End, EnemyMovement m){
		Start = (Point2D.Double) Start;
		int myCoordProperty = 0;
	
		// 1. Adjust actual coordinates as necessary from Tile Locations
		
		if(Start.x != End.x){
			Start.setLocation(Start.x + (myGrid.getTiles()[(int)Start.x][(int)Start.y].getWidth())*((Start.x < End.x)?1:0) , Start.y); 
			End.setLocation(End.x + (myGrid.getTiles()[(int)End.x][(int)End.y].getWidth())*((Start.x < End.x)?0:1), End.y);
			myCoordProperty = 0;
		}
		
		else if(Start.y != End.y){
			Start.setLocation(Start.x, Start.y + (myGrid.getTiles()[(int)Start.x][(int)Start.y].getWidth())*((Start.y < End.y)?1:0));
			End.setLocation(End.x, End.y + (myGrid.getTiles()[(int)End.x][(int)End.y].getWidth())*((Start.y < End.y)?0:1));
			myCoordProperty = 1;
		}
		
		// calculate Placements based on points and coordinate property
		
		List<Placement> stretch = m.makeStretch(Start, End, myCoordProperty);
		return stretch;

	}
	
	// Given two points which represent Tiles on either side of a Corner Tile
	List<Placement> generateTurn(Point2D.Double Start, Point2D.Double End, EnemyMovement m){
		// TODO 
		return null;
	}
	
	
	
	
	public Tile findNextTile(Tile current, Enemy enemy){
		for (Tile t: getTileNeighbors(current)){
			if(enemy.getWalkables().contains(t.getName()) && !enemy.getTilePath().contains(t)){
				enemy.getTilePath().add(t);
				return t;
			}
		}
		return null;
		// TODO
		// SHOULD return null if no next tile is found, or if this tile is a base..there is a null check in findPath	
	}
	
	public List<Tile> getTileNeighbors(Tile t){
		if (t == null)
			System.out.println("Grid.getTileNeighbors called with null Tile");
		int x = t.getX();
		int y = t.getY();
		List<Tile> neighbors = new ArrayList<Tile>();
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		for (int i = 0; i < dx.length; i++){
			if(x + dx[i] < myGrid.getTiles().length && 
					x + dx[i] >= 0 &&
					y + dy[i] < myGrid.getTiles()[0].length &&
					y + dy[i] >= 0){
				Tile temp = (myGrid.getTiles()[x + dx[i]][y + dy[i]]);
				neighbors.add(temp);
				//System.out.println(temp.getX() + ", " + temp.getY());
				
			}
		}

		return neighbors;
	}

	public Path target(Shootable s, Collidable c) {
		// TODO CREATE PROJECTILE PATH BETWEEN OBJECTS
		
		
		return null;
	}

	public void generateProjectile(Projectile projectile, Path path) {
		Projectile newP = new Projectile(projectile);
		newP.setPath(path);
		myGrid.placeGridObjectAt(projectile, path.getNext());
	}

}
