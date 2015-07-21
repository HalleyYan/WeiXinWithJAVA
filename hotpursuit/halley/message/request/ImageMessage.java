package hotpursuit.halley.message.request;
/**
 * 
 * 
 * @author Halley
 *
 */
public class ImageMessage extends BaseMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7836993885941488848L;
	//图片链接
	public String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}
