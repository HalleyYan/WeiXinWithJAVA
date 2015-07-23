package hotpursuit.halley.pojo;

import java.io.Serializable;

/**
 * 
 * @author Halley
 *
 */
public class BaiduMapResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7417570727259841544L;
	
	private String address;
	private Content content;
	private String status; //"0"返回正常
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		
		return "{address:"+address+"content:"+content.toString()+",status:"+status+"}";
	}
	
	

}
