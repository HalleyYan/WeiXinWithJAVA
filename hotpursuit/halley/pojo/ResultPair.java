package hotpursuit.halley.pojo;

import java.io.Serializable;
/**
 * 
 * @author Halley
 *
 */
public class ResultPair implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7582060206424342354L;
	// 原文   
    private String src;  
    // 译文   
    private String dst;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	} 
    
    
}
