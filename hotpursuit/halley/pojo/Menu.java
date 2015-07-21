package hotpursuit.halley.pojo;

import java.io.Serializable;

/**
 * 
 * @author Halley
 *
 */
public class Menu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5393497450959000662L;
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}  

}
