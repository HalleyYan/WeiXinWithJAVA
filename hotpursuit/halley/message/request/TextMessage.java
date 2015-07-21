package hotpursuit.halley.message.request;
/**
 * 
 * @author Halley
 *
 */
public class TextMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -177083090193311432L;
	// 消息内容  
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}  


}
