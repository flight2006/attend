package com.hdu.web.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hdu.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hdu.model.Message;
import com.hdu.model.Reply;
import com.hdu.service.WeixinService;
import com.hdu.util.WeixinUtil;

@Controller()
public class WeixinController {

	private static final String TOKEN = "attend";

	public static int pagesize = 10;

	@Resource(name="weixinService")
	private WeixinService weixinService;

	@RequestMapping(value="/test",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String test(HttpServletRequest request){
		return weixinService.getEmployeeMessageHistoryByEmployeeId(30202);
	}


	//接收微信公众号接收的消息，处理后再做相应的回复
	@RequestMapping(value="/weixin",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String replyMessage(HttpServletRequest request){

			//仅处理微信服务端发的请求
		if (checkWeixinReques(request)) {
			Map<String, String> requestMap = WeixinUtil.parseXml(request);
			Message message = WeixinUtil.mapToMessage(requestMap);
			weixinService.addMessage(message);//保存接受消息到数据库
			String replyContent = Reply.WELCOME_CONTENT;
			String type = message.getMsgType();
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");



			if (type.equals(Message.TEXT)) {//仅处理文本回复内容
				String content = message.getContent();//消息内容
				String [] cs = content.split("_");//消息内容都以下划线_分隔
				if(cs.length == 2){
					int employeeid ;//员工编号
					String process = cs[1];//操作
					try {
						employeeid = Integer.parseInt(cs[0]);
						if("出勤".equals(process)){
							replyContent = weixinService.getSingleAttendStringByemployeeId(employeeid);
						}else if("出勤历史".equals(process)){
							replyContent = weixinService.getAttendHistoryStringByemployeeId(employeeid);
						}else if("留言".equals(process)){
							replyContent = weixinService.getSingleEmployeeMessageByemployeeId(employeeid);
						}else if("留言历史".equals(process)){
							replyContent = weixinService.getEmployeeMessageHistoryByEmployeeId(employeeid);
						}else if("动态".equals(process)){
							replyContent = weixinService.getSingleDeptNewsByemployeeId(employeeid);
						}else if("动态历史".equals(process)){
							replyContent = weixinService.getDeptNewsHistoryByemployeeId(employeeid);
						}
					} catch (NumberFormatException e) {
						replyContent = Reply.ERROR_CONTENT;
					}
				}
			}else if (type.equals(Message.SCAN)){
				String eventKey = requestMap.get("EventKey");
				if (eventKey.equals("1")){
					 replyContent = "扫码签到被点击";
				}
			}
			//拼装回复消息
			Reply reply = new Reply();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			DateUtils dateUtils = new DateUtils();
			Timestamp timestamp = dateUtils.converIntoNormal(new Date());
			reply.setCreateTime(timestamp);
			reply.setMsgType(Reply.TEXT);
			reply.setContent(replyContent);
			weixinService.addReply(reply);//保存回复消息到数据库
			//将回复消息序列化为xml形式
			String back = WeixinUtil.replyToXml(reply);
			System.out.println(back);
			return back;
		}else{
			return "error";
		}
	}


	//微信公众平台验证url是否有效使用的接口
	@RequestMapping(value="/weixin",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String initWeixinURL(HttpServletRequest request){
		String echostr = request.getParameter("echostr");
		if (checkWeixinReques(request) && echostr != null) {
			return echostr;
		}else{
			return "error";
		}
	}



/**
*
	 * 根据token计算signature验证是否为weixin服务端发送的消息
*/



	private static boolean checkWeixinReques(HttpServletRequest request){
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		if (signature != null && timestamp != null && nonce != null ) {
			String[] strSet = new String[] { TOKEN, timestamp, nonce };
			java.util.Arrays.sort(strSet);
			String key = "";
			for (String string : strSet) {
				key = key + string;
			}
			String pwd = WeixinUtil.sha1(key);
			return pwd.equals(signature);
		}else {
			return false;
		}
	}
/**
*
	 * 收到消息列表页面

*/
	@RequestMapping(value="/manager/messages",method=RequestMethod.GET)
	public ModelAndView listMessage(String pagenum,String notice){
		ModelAndView mv=new ModelAndView();
		mv.addObject("sidebar","messages");
		mv.setViewName("messages");
		int num = 1;
		if(null!=pagenum){
			num = Integer.parseInt(pagenum);
		}
		List<Message> list = weixinService.listMessage((num-1)*pagesize, pagesize);
		mv.addObject("messageList", list);
		mv.addObject("pagenum", num);
		mv.addObject("notice",notice);
		mv.addObject("length", list.size());
		return mv;
	}

/**
*
	 * 回复消息列表页面
*/

	@RequestMapping(value="/manager/replys",method=RequestMethod.GET)
	public ModelAndView listReply(String pagenum){
		ModelAndView mv=new ModelAndView();
		mv.addObject("sidebar","replys");
		mv.setViewName("replys");
		int num = 1;
		if(null!=pagenum){
			num = Integer.parseInt(pagenum);
		}
		List<Reply> list = weixinService.listReply((num-1)*pagesize, pagesize);
		mv.addObject("replyList", list);
		mv.addObject("pagenum", num);
		mv.addObject("length", list.size());
		return mv;
	}



}
