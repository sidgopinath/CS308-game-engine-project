package gae.model;

import engine.gameLogic.GameObject;
import gae.model.inventory.Inventory;
import gae.view.inventorypane.UpdateListener;
import game_data.XMLWriter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Not yet implemented
 * 
 * @author Peter
 */
public class Model implements Receiver {

	Inventory myInventory;

	public Model() {

		myInventory = new Inventory();

	}

	@Override
	public void addObject(String type) {
		myInventory.addObject(type);
	}

	@Override
	public void runOnObject(String obj, Method method, Object... params) {
		myInventory.runOnObject(obj, method, params);
	}

	@Override
	public Object getFromObject(String obj, Method method, Object... params) {
		return myInventory.getFromObject(obj, method, params);
	}

	@Override
	public void removeObject(String obj) {
		myInventory.removeObject(obj);
	}

	@Override
	public String getType(String obj) {
		return myInventory.getType(obj);
	}

	@Override
	public Set<String> getList(String type) {
		return myInventory.getList(type);
	}

	@Override
	public void saveFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportFile(String obj) {
		GameObject object = myInventory.getObject(obj);
		try {
			XMLWriter.SaveGameData(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// might not be used
	// is used
	@Override
	public void setListener(String type, UpdateListener ul) {
		myInventory.setListener(type, ul);
	}

	// We need a way of getting every instances of one kind (all created towers
	// for example)

}
