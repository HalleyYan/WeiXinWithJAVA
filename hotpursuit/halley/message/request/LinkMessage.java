package hotpursuit.halley.message.request;
/**
 * 
 * @author Halley
 *
 */
public class LinkMessage extends BaseMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831743428613918612L;
	// 消息标题
	private String title;  
    // 消息描述   
    private String description;  
    // 消息链接   
    private String url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    
    
}