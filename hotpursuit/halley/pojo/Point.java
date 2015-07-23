package hotpursuit.halley.pojo;

import java.io.Serializable;

/**
 * @description baidu map 
 * @author Halley
 *
 */
public class Point implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5424528535589876789L;
	private String x;
	private String y;
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	@Override
	public String toString() {
		
		return "[x:"+x+",y"+y+"]";
	}
	
	
}
