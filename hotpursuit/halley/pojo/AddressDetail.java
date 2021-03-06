package hotpursuit.halley.pojo;

import java.io.Serializable;
/**
 * @description baidu map 
 * @author Halley
 *
 */
public class AddressDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512771321601943538L;
	
	private String city;
	private String city_code;
	private String district;
	private String province;
	private String street;
	private String street_number;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Override
	public String toString() {
		return "[city:"+city+",city_code:"+city_code+",district:"+district+",province:"+province+",street:"+street+",street_number:"+street_number+"]";

	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	
	

}
