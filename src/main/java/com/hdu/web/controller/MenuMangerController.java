package com.hdu.web.controller;

import com.hdu.util.WeixinUtil;
import com.hdu.weixinmenu.AccessToken;
import com.hdu.weixinmenu.Button;
import com.hdu.weixinmenu.CommonButton;
import com.hdu.weixinmenu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Flight on 2015/8/1.
 */
@Controller
public class MenuMangerController {
    private static Logger log = LoggerFactory.getLogger(MenuMangerController.class);
    @RequestMapping(value = "/manager/MenuManager",method = RequestMethod.GET)
    public ModelAndView getMenuManger(){
        ModelAndView mv = new ModelAndView();
        // 第三方用户唯一凭证
        String appId = "wx9e27231fe16703fe";
        // 第三方用户唯一凭证密钥
        String appSecret = "fc321b23757286c378c190c19c717723";

        AccessToken accessToken = WeixinUtil.getAccessToken(appId, appSecret);
        if (null != accessToken) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(getMenu(), accessToken.getToken());
            // 判断菜单创建结果
            String notice = null;
            if (0 == result) {
                notice = "菜单创建成功！";
            }
            else
                notice =("菜单创建失败，错误码：" + result);
            log.info(notice);
            mv.addObject("notice",notice);
            mv.setViewName("redirect:/manager/messages" );
        }

        return mv;
    }
    public static Menu getMenu(){
        CommonButton btn = new CommonButton();
        btn.setName("扫码签到");
        btn.setType("scancode_push");
        btn.setKey("1");

        Menu menu = new Menu();
        menu.setButton(new Button[]{btn});

        return menu;
    }

}
