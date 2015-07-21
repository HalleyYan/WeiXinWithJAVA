package hotpursuit.halley.message.response;
/**
 * 
 * @author Halley
 *
 */
public class MusicMessage extends BaseMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816831567423545646L;
	
	// 音乐   
    private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
    
    

}
