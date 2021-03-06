package engine.gameScreens;

import interfaces.MethodAnnotation;
import interfaces.SpecialEditorAnnotation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import player.manager.PlayerManager;
import interfaces.MethodAnnotation;
import interfaces.SpecialEditorAnnotation;
import engine.NodeState;

public class TitleScene extends GameNode {

	private String myBackgroundPicturePath;
	private String myTitleText;
	private Point myTitlePos = new Point();
	private String myTitleStyle;
	private List<NodeButton> myButtons;

	public TitleScene() {
		super();
	}
	
	@MethodAnnotation(editor=true, name="Title Scene Editor", type="titlescene", fieldName="")
	public void getGAEComponent() {
		return;
	}

	@Override
	public void render(PlayerManager playerManager) {
		// REVIEW: make sure these having nothing is fully functional
	}

	@Override
	public void update() {

	}

	@Override
	public NodeState checkState() {
		return NodeState.RUNNING;
	}
	
	public List<NodeButton> getButtons() {
		return myButtons;
	}
	
	@SpecialEditorAnnotation(specialeditor=true, name="Set Title Text", fieldName="myTitleText")
	public void setTitleText(String text) {
		myTitleText = text;
	}
	
	@SpecialEditorAnnotation(specialeditor=true, name="Set Title Position", fieldName="myTitlePos")
	public void setTitlePos(Integer x, Integer y) {
		myTitlePos.x = x;
		myTitlePos.y = y;
	}
	
	@SpecialEditorAnnotation(specialeditor=true, name="Set Title Style", fieldName="myTitleStyle")
	public void setTitleStyle(String style) {
		myTitleStyle = style;
	}
	
	@SpecialEditorAnnotation(specialeditor=true, name="Set Buttons", fieldName="myButtons")
	public void setButtons(ArrayList<NodeButton> buttons) {
		myButtons = buttons;
	}
}