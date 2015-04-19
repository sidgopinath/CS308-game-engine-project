package engine;

import javafx.animation.KeyFrame;
import javafx.util.Duration;

public abstract class GameScene extends GameObject{
	
	private String myName;
	private GameScene myNext;
	//protected boolean myHasCompleted;
	private KeyFrame myScene;
	//protected boolean myGameLost;
	//protected boolean myGameWon;
	
	public GameScene(){
		//myGameLost = false;
	}

	public KeyFrame start(double frameRate) {
		myScene = new KeyFrame(Duration.millis(frameRate * 10), e -> update());
		return myScene;
	}

	public abstract void update();
	
	public abstract boolean isComplete();
	
	public GameScene getNextScene(){
		return myNext;
	}
	
	public KeyFrame getCurScene(){
		return myScene;
	}
	
	
	public void setNextScene(GameScene gameScene){
		myNext = gameScene;
	}
}