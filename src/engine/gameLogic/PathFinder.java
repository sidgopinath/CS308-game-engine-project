package engine.gameLogic;

import interfaces.Collidable;
import interfaces.Shootable;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import engine.EnemyMovement;
import engine.Grid;
import engine.sprites.Enemy;
import engine.sprites.Projectile;
import engine.sprites.Tile;

public class PathFinder {
	
	private Grid myGrid;
	private HashMap<String, Path> myEnemyPaths; 
	// this will be an issue when multiple enemies of the same type require different paths..
	// ie multiple different ports with the same waves
	
	public PathFinder(Grid grid){
		myGrid = grid;
	}
	
	//REFACTOR to make this a generic setPath()... for both enemies and projectiles
	public void setEnemyPath(Enemy enemy, Wave w){
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
		Placement[] placementArray = new Placement[tiles.size()];
		for (int i = 0; i < tiles.size(); i++)
			placementArray[i] = new Placement(tileArray[i].getLocation());
	
		List<Placement> myMovements = new LinkedList<Placement>();
		
		Placement lastStraight = new Placement();
		lastStraight = placementArray[0];
		
		for (int i = 2; i < placementArray.length; i++){
			if(placementArray[i-2].getLocation().x != placementArray[i].getLocation().x && placementArray[i-2].getLocation().y != placementArray[i].getLocation().y){
				myMovements.addAll(generateStretch(lastStraight, placementArray[i-2], enemy.getMovement()));
				myMovements.addAll(generateTurn(placementArray[i-2], placementArray[i], enemy.getMovement()));
				lastStraight = placementArray[i];	
			}
		}
		
		// MAKE SURE THIS CAST WORKS
		return new Path((LinkedList<Placement>) myMovements); 
	}
	
	
	// Given two points which represent two tiles on the ends of a straightaway
	List<Placement> generateStretch(Placement p1, Placement p2, EnemyMovement m){
		
		Point2D.Double start = p1.getLocation(); // MAKE SURE THE UPDATES BELOW...
		Point2D.Double end = p2.getLocation();
		
		int myCoordProperty = 0;
	
		// 1. Adjust actual coordinates as necessary from Tile Locations
		
		if(start.x != end.x){
			start.setLocation(start.x + (myGrid.getTiles()[(int)start.x][(int)start.y].getWidth())*((start.x < end.x)?1:0) , start.y); 
			end.setLocation(end.x + (myGrid.getTiles()[(int)end.x][(int)end.y].getWidth())*((start.x < end.x)?0:1), end.y);
			myCoordProperty = 0;
		}
		
		else if(start.y != end.y){
			start.setLocation(start.x, start.y + (myGrid.getTiles()[(int)start.x][(int)start.y].getWidth())*((start.y < end.y)?1:0));
			end.setLocation(end.x, end.y + (myGrid.getTiles()[(int)end.x][(int)end.y].getWidth())*((start.y < end.y)?0:1));
			myCoordProperty = 1;
		}
		
		// ...RESULT IN p1 and p2 BEING UPDATED HERE:
		// calculate Placements based on points and coordinate property
		
		List<Placement> stretch = m.makeStretch(p1, p2, myCoordProperty);
		return stretch;

	}
	
	// Given two points which represent Tiles on either side of a Corner Tile
	List<Placement> generateTurn(Placement start, Placement end, EnemyMovement m){
		// TODO 
		
		List<Placement> turn = m.makeTurn(start, end);
		return turn;
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

