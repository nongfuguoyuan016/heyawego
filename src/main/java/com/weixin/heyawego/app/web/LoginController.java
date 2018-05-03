package com.weixin.heyawego.app.web;

import com.weixin.HeyawegoApplication;
import com.weixin.heyawego.app.common.utils.CheckUtils;
import com.weixin.heyawego.app.common.utils.MessageUtils;
import com.weixin.heyawego.app.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private CoreService coreService ;

    @RequestMapping(value = "wx" , method = RequestMethod.GET)
    public String login(@RequestParam(required = true) String signature ,
                      @RequestParam(required = true) String timestamp ,
                      @RequestParam(required = true) String nonce ,
                      @RequestParam(required = true) String echostr){

        if(CheckUtils.checkSignature(signature, timestamp, nonce)){
            return echostr ;
        }
        return "unknowSource" ;
    }

    @RequestMapping(value = "wx" ,method = RequestMethod.POST)
    public String login(HttpServletRequest request){
        String rtn = "success" ;
        try {
            rtn = coreService.processRequest(request) ;
            System.out.println("\n------------------------ RTN_MSG -----------------------------------");
            System.out.println(rtn + "\n");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return rtn ;
    }


    @RequestMapping(value = "close")
    public void close(){
        HeyawegoApplication.context.close();
    }

    @RequestMapping(value = "createMenu")
    public String app(){
        return coreService.createMenu() ;
    }

    @RequestMapping(value = "token")
    public String token(){
        return coreService.getAccessToken() ;
    }


}
