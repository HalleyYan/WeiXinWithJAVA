package hotpursuit.halley.weinxinci;

import hotpursuit.halley.service.BuzznessService;
import hotpursuit.halley.service.impl.BuzznessServiceImpl;
import hotpursuit.halley.utils.ServletUtil;
import hotpursuit.halley.utils.SignUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Halley
 *
 */
public class TobeDvlperServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6542214974561492543L;
	
	/** 
     * 确认请求来自微信服务器 
     */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String,String> params = ServletUtil.buildReqParamToMap(req);
		
		// 微信加密签名   
        String signature = params.get("signature");
        //时间戳
        String timestamp = params.get("timestamp");
        //随机数
        String nonce = params.get("nonce");
        //随机字符串
        String echostr = params.get("echostr");
        
        PrintWriter out = resp.getWriter();  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败   
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr);  
        }  
        out.close();  
        out = null;
		
	}
	
	/** 
     * 处理微信服务器发来的消息 
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//消息的接收、处理、响应  
		
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）   
        req.setCharacterEncoding("UTF-8");  
        resp.setCharacterEncoding("UTF-8");  
        
        BuzznessService bs = new BuzznessServiceImpl();
  
        // 调用核心业务类接收消息、处理消息   
        String respMessage = bs.processRequest(req);  
          
        // 响应消息   
        PrintWriter out = resp.getWriter();  
        out.print(respMessage);  
        out.close(); 
	}
	
	

}
