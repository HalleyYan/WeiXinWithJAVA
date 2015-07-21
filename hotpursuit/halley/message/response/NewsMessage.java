package hotpursuit.halley.message.response;

import java.util.List;
/**
 * 
 * @author Halley
 *
 */
public class NewsMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7012194982297325734L;
	
	// 图文消息个数，限制为10条以内   
    private int articleCount;  
    // 多条图文消息信息，默认第一个item为大图   
    private List<Article> articles;
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	} 
    
    

}
