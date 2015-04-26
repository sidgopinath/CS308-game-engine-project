package gae.editorComponents;

import gae.model.Receiver;

import java.lang.reflect.Method;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * This class is used to customize sliders for integer inputs Possible
 * extension: adding multiple sliders in a group
 * 
 * @author ReyinaSenatus
 *
 */
public class SliderEditor extends EditorComponent{
	private double myMin;
	private double myMax;
	private double myCur;
	private Slider mySlider = new Slider();

	//TODO: Cast fecthValue
	//TODO: Check to see if fetchValue is null or not
	public SliderEditor(Receiver receiver, Method setMethod, Method getMethod, String objectName) {
		super(receiver, setMethod, objectName);
		// TODO Auto-generated constructor stub
		//In case the sliderEditorParams method is not called
		myMin = 0;
		myMax = 50;
		myCur = 25;
	}

	@Override
	public void setUpEditor() {
		// TODO Auto-generated method stub
		HBox h = new HBox();
		h.getChildren().add(sliderSetUp());
	}
	
	public void sliderEditorParams(double min, double max) {
		myMin = min;
		myMax = max;
		myCur = Math.floor((max-min)/2);
	}

	public Node sliderSetUp() {
		mySlider.setMax(myMax);
		mySlider.setMin(myMin);
		mySlider.setValue(myCur);
		mySlider.setShowTickLabels(true);
		mySlider.setMajorTickUnit(myCur);

		final Label sliderVal = new Label(Double.toString(mySlider.getValue()));

		mySlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				sliderVal.setText(String.format("%.1f", new_val));
				//Integer myVal = (int) mySlider.getValue();
                //sliderVal.setText(myVal.toString());
			}
		});

		return mySlider;
	}

	public Integer intValue() {// This is to be used on save event
		int myVal = (int) mySlider.getValue();
		return myVal;
	}
	
	public Double doubleValue() {// This is to be used on save event
		double myVal = mySlider.getValue();
		return myVal;
	}

}
