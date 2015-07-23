package hotpursuit.halley.pojo;

import java.io.Serializable;
/**
 * @description baidu map 
 * @author Halley
 *
 */
public class Content implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2475095662290213311L;
	
	private String address;
	private AddressDetail address_detail;
	private Point point;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "[address:"+address+",address_detail:"+address_detail.toString()+",point:"+point.toString()+"]";
	}
	public AddressDetail getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(AddressDetail address_detail) {
		this.address_detail = address_detail;
	}
	
	

}
