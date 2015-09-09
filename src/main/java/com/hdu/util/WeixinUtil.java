package com.hdu.util;


import java.io.*;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.URL;
import java.security.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import com.hdu.model.Attend;
import com.hdu.weixinmenu.AccessToken;
import com.hdu.weixinmenu.Menu;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hdu.model.Article;

import com.hdu.model.Message;
import com.hdu.model.Reply;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信相关工具类
 */

public class WeixinUtil {

	/*
        public static String singleExamMarkToString(ExamMark em){
            if(null==em || em.getExam()==null)
                return null;
            StringBuilder sb = new StringBuilder();
            return sb.toString();
        }
        */
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	public static String singleAttendToString(Attend attend) {
		if (null == attend || attend.getPunchtime() == null)
			return null;
		StringBuilder sb = new StringBuilder();
		return sb.toString();
	}

	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 将回复消息对象转换成xml字符串
	 *
	 * @param reply 回复消息对象
	 * @return 返回符合微信接口的xml字符串
	 **/

	public static String replyToXml(Reply reply) {
		String type = reply.getMsgType();
		if (Reply.TEXT.equals(type)) {
			xstream.omitField(Reply.class, "articles");
			xstream.omitField(Reply.class, "articleCount");
			xstream.omitField(Reply.class, "musicUrl");
			xstream.omitField(Reply.class, "hQMusicUrl");
		} else if (Reply.MUSIC.equals(type)) {
			xstream.omitField(Reply.class, "articles");
			xstream.omitField(Reply.class, "articleCount");
			xstream.omitField(Reply.class, "content");
		} else if (Reply.NEWS.equals(type)) {
			xstream.omitField(Reply.class, "content");
			xstream.omitField(Reply.class, "musicUrl");
			xstream.omitField(Reply.class, "hQMusicUrl");
		}
		xstream.autodetectAnnotations(true);
		xstream.alias("xml", reply.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(reply);
	}


	/**
	 * 存储数据的Map转换为对应的Message对象
	 *
	 * @param map 存储数据的map
	 * @return 返回对应Message对象
	 */

	public static Message mapToMessage(Map<String, String> map) {
		if (map == null) return null;
		String msgType = map.get("MsgType");
		Message message = new Message();
		message.setToUserName(map.get("ToUserName"));
		message.setFromUserName(map.get("FromUserName"));
		Date createTime = new Date(Long.parseLong(map.get("CreateTime")) * 1000);
		DateUtils utils = new DateUtils();
		Timestamp createTimeNature = utils.converIntoNormal(createTime);
		message.setCreateTime(createTimeNature);
		message.setMsgType(msgType);
		message.setMsgId(map.get("MsgId"));
		if (msgType.equals(Message.TEXT)) {
			message.setContent(map.get("Content"));
		} else if (msgType.equals(Message.IMAGE)) {
			message.setPicUrl(map.get("PicUrl"));
		} else if (msgType.equals(Message.LINK)) {
			message.setTitle(map.get("Title"));
			message.setDescription(map.get("Description"));
			message.setUrl(map.get("Url"));
		} else if (msgType.equals(Message.LOCATION)) {
			message.setLocationX(map.get("Location_X"));
			message.setLocationY(map.get("Location_Y"));
			message.setScale(map.get("Scale"));
			message.setLabel(map.get("Label"));
		} else if (msgType.equals(Message.EVENT)) {
			message.setEvent(map.get("Event"));
			message.setEventKey(map.get("EventKey"));
		}
		return message;
	}

	/**
	 * 解析request中的xml 并将数据存储到一个Map中返回
	 *
	 * @param request
	 **/
	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			for (Element e : elementList)
				//遍历xml将数据写入map
				map.put(e.getName(), e.getText());
			inputStream.close();
			inputStream = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * sha1加密算法
	 *
	 * @param //key需要加密的字符串
	 * @return 加密后的结果
	 */
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}



/**
	 * 发送https请求并获取结果

*/

	public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr){
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try{
			//解决https请求的问题
			TrustManager[] trustManagers = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null,trustManagers,new SecureRandom());

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url =  new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			//兼容get\post两种方式
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			//当数据需要提交时
			if(null != outputStr ){
				OutputStream ouputstream = httpUrlConn.getOutputStream();

				ouputstream.write(outputStr.getBytes("UTF-8"));
				ouputstream.close();
			}

			//将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine())!=null ){
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());

		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}


	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

/**
	 * 获取access_token
	 *
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */

	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")); 	}
		}
		return accessToken;
	}


	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

/**
	 * 创建菜单
	 *
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */

	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
}
