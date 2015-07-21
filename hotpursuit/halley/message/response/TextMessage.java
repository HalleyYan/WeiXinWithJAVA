package hotpursuit.halley.message.response;
/**
 * 
 * @author Halley
 *
 */
public class TextMessage extends BaseMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 984785489711963219L;
	// 回复的消息内容   
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 
    
    
}
