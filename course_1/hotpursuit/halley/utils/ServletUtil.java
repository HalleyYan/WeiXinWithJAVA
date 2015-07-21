package hotpursuit.halley.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Halley
 *
 */
public class ServletUtil {
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> buildReqParamToMap(HttpServletRequest req){
		
		return req.getParameterMap();
	}

}
