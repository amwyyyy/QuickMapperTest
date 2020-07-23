package com.test.project.web.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.test.project.common.QuickBaseController;
import com.wen.commons.spring.version.ApiVersion;
import com.wen.commons.web.ResultObject;

/**
 * 主页的控制器
 *
 * @author denis.huang
 * @date 2017-01-22
 */
@Controller
@RequestMapping("/{version}/home")
@ApiVersion(1)
@Validated
public class HomeController extends QuickBaseController {
    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    @RequiresUser
    public ResultObject userInfo() {
        return buildSuccessResp(getLoginUser());
    }

    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    @ResponseBody
    @RequiresUser
    public DeferredResult<String> getMsg() {
        DeferredResult<String> result = new DeferredResult<>(30000L, null);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult("goodbye!");
        }).start();

        return result;
    }

    @RequestMapping(value = "getName", method = RequestMethod.GET)
    @ResponseBody
//    @RequiresPermissions("/home/getName")
    public ResultObject getName(@Min(1) @Max(2) Integer age) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "wen" + age);
        return buildSuccessResp(map);
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
