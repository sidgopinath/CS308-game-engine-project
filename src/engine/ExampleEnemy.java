package engine;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.JFileChooser;

public class ExampleEnemy {
	private ImageView myImage;
	private Double myHealthCapacity = 0.0;
	
	public ExampleEnemy(){
		myImage = new ImageView();
		
	}

	@MethodAnnoation(editor=true)
	public void SetSize(@ParameterAnnotation(name="Width") Double width, @ParameterAnnotation(name="Height") Double height){
		myImage.setFitWidth(width);
		myImage.setFitHeight(height);
	}
	
	@MethodAnnoation(editor=true)
	public void SetPosition(@ParameterAnnotation(name="X Location") Double x, @ParameterAnnotation(name="Y Location")Double y){
		myImage.setTranslateX(x);
		myImage.setTranslateY(y);
	}
	
	@MethodAnnoation(editor=true)
	public void SetHealthCapacity(@ParameterAnnotation(name="Health Capacity") Double health){
		this.setMyHealthCapacity(health);
	}
	
	@MethodAnnoation(editor=true)
	public void ChangeImage() {
		selectImage();
	}
	
	public void changeImage(Image img) {
		myImage.setImage(img);
	}
	
	public void changeImage(File file) {
		this.changeImage(new Image(file.toURI().toString()));
	}
	
	public void selectImage(){
		JFileChooser imageChooser = new JFileChooser(System.getProperties()
				.getProperty("user.dir") + "/src/images");
		imageChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int retval = imageChooser.showOpenDialog(null);
		if (retval != JFileChooser.APPROVE_OPTION) {
			return;
		}
		changeImage(imageChooser.getSelectedFile());
		return;
	}
	
	public String toString(){
		System.out.println("Object created: ExampleSprite");
		System.out.println("Image File: "+myImage.getImage().toString());
		System.out.println("Size: "+myImage.getFitWidth()+" "+myImage.getFitHeight());
		System.out.println("Position: "+myImage.getTranslateX()+" "+myImage.getTranslateY());
		System.out.println("Health Capacity: "+ myHealthCapacity);
		return null;
	}

	public Double getMyHealthCapacity() {
		return myHealthCapacity;
	}

	public void setMyHealthCapacity(Double myHealthCapacity) {
		this.myHealthCapacity = myHealthCapacity;
	}


}
