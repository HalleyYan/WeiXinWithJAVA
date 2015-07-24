package hotpursuit.halley.service.impl;

import hotpursuit.halley.message.response.Music;
import hotpursuit.halley.message.response.MusicMessage;
import hotpursuit.halley.message.response.TextMessage;
import hotpursuit.halley.service.BuzznessService;
import hotpursuit.halley.utils.BaiduMusicUtil;
import hotpursuit.halley.utils.BaiduTranslateUtil;
import hotpursuit.halley.utils.FacePlusPlusUtil;
import hotpursuit.halley.utils.MessageUtil;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
/**
 * 
 * @author Halley
 *
 */
public class BuzznessServiceImpl implements BuzznessService {
	
	
	public String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容   
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析   
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）   
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号   
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型   
            String msgType = requestMap.get("MsgType");  
  
            // 回复文本消息   
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
  
            // 文本消息   
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                respContent = "您发送的是文本消息！";
                String content = requestMap.get("Content").trim();  
                if (content.startsWith("翻译")) {  
                    String keyWord = content.replaceAll("^翻译", "").trim();  
                    if ("".equals(keyWord)) {  
                        textMessage.setContent(getTranslateUsage());  
                    } else {  
                        textMessage.setContent(BaiduTranslateUtil.translate(keyWord));  
                    }  
                }
                
                if (content.startsWith("歌曲")) {  
                    // 将歌曲2个字及歌曲后面的+、空格、-等特殊符号去掉   
                    String keyWord = content.replaceAll("^歌曲[\\+ ~!@#%^-_=]?", "");  
                    // 如果歌曲名称为空   
                    if ("".equals(keyWord)) {  
                        respContent = getUsage();  
                    } else {  
                        String[] kwArr = keyWord.split("@");  
                        // 歌曲名称   
                        String musicTitle = kwArr[0];  
                        // 演唱者默认为空   
                        String musicAuthor = "";  
                        if (2 == kwArr.length)  
                            musicAuthor = kwArr[1];  
  
                        // 搜索音乐   
                        Music music = BaiduMusicUtil.searchMusic(musicTitle, musicAuthor);  
                        // 未搜索到音乐   
                        if (null == music) {  
                            respContent = "对不起，没有找到你想听的歌曲<" + musicTitle + ">。";  
                        } else {  
                            // 音乐消息   
                            MusicMessage musicMessage = new MusicMessage();  
                            musicMessage.setToUserName(fromUserName);  
                            musicMessage.setFromUserName(toUserName);  
                            musicMessage.setCreateTime(new Date().getTime());  
                            musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);  
                            musicMessage.setMusic(music);  
                            respMessage = MessageUtil.musicMessageToXml(musicMessage);  
                        }  
                    }  
                }  


            }  
            // 图片消息   
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！";  
             // 取得图片地址   
                String picUrl = requestMap.get("PicUrl");  
                // 人脸检测   
                String detectResult = FacePlusPlusUtil.detect(picUrl);  
                textMessage.setContent(detectResult);  

            }  
            // 地理位置消息   
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息   
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息   
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送   
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型   
                String eventType = requestMap.get("Event");  
                // 订阅   
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅   
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息   
                }  
                // 自定义菜单点击事件   
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                	// 事件KEY值，与创建自定义菜单时指定的KEY值对应   
                    String eventKey = requestMap.get("EventKey");  
  
                    if (eventKey.equals("11")) {  
                        respContent = "天气预报菜单项被点击！";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "公交查询菜单项被点击！";  
                    } else if (eventKey.equals("13")) {  
                        respContent = "周边搜索菜单项被点击！";  
                    } else if (eventKey.equals("14")) {  
                        respContent = "历史上的今天菜单项被点击！";  
                    } else if (eventKey.equals("21")) {  
                        respContent = "歌曲点播菜单项被点击！";  
                    } else if (eventKey.equals("22")) {  
                        respContent = "经典游戏菜单项被点击！";  
                    } else if (eventKey.equals("23")) {  
                        respContent = "美女电台菜单项被点击！";  
                    } else if (eventKey.equals("24")) {  
                        respContent = "人脸识别菜单项被点击！";  
                    } else if (eventKey.equals("25")) {  
                        respContent = "聊天唠嗑菜单项被点击！";  
                    } else if (eventKey.equals("31")) {  
                        respContent = "Q友圈菜单项被点击！";  
                    } else if (eventKey.equals("32")) {  
                        respContent = "电影排行榜菜单项被点击！";  
                    } else if (eventKey.equals("33")) {  
                        respContent = "幽默笑话菜单项被点击！";  
                    }  
                
                }  
            }  
            
            // 未搜索到音乐时返回使用指南   
            if (null == respMessage) {  
                if (null == respContent)  
                    respContent = getUsage();  
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);  
            } 
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
	
	public static String getUsage() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("歌曲点播操作指南").append("\n\n");  
        buffer.append("回复：歌曲+歌名").append("\n");  
        buffer.append("例如：歌曲存在").append("\n");  
        buffer.append("或者：歌曲存在@汪峰").append("\n\n");  
        buffer.append("回复“?”显示主菜单");  
        return buffer.toString();  
    }  
	
	public static String getPicUsage() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("人脸检测使用指南").append("\n\n");  
        buffer.append("发送一张清晰的照片，就能帮你分析出种族、年龄、性别等信息").append("\n");  
        buffer.append("快来试试你是不是长得太着急");  
        return buffer.toString();  
    } 

	
	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(MessageUtil.emoji(0xe148)).append("Q译通使用指南").append("\n\n");
		buffer.append("Q译通为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

	
}
