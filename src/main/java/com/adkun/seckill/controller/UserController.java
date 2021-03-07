package com.adkun.seckill.controller;

import com.adkun.seckill.common.BusinessException;
import com.adkun.seckill.common.ErrorCode;
import com.adkun.seckill.common.ResponseModel;
import com.adkun.seckill.common.ToolBox;
import com.adkun.seckill.entity.User;
import com.adkun.seckill.service.UserService;
import com.sun.java.accessibility.util.TopLevelWindowListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PageRanges;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${adkun.web.path}", allowedHeaders = "*", allowCredentials = "true")
@Slf4j
public class UserController implements ErrorCode {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取验证码
     *
     * @param phone
     * @param session
     * @return
     */
    @GetMapping("/otp/{phone}")
    public ResponseModel getOTP(
            @PathVariable("phone") String phone,
            HttpSession session
    ) {
        // 获取OTP
        String otp = ToolBox.getOTP();
        // 绑定OTP
        session.setAttribute(phone, otp);
        // 提示消息
        log.info("您好，" + phone + "，您的验证码是 " + otp);

        return new ResponseModel();
    }

    /**
     * 注册
     *
     * @param otp
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/register")
    public ResponseModel register(String otp, User user, HttpSession session) {
        // 验证OTP
        String sessionOTP = (String) session.getAttribute(user.getPhone());
        if (StringUtils.isEmpty(otp) || StringUtils.isEmpty(sessionOTP) || !StringUtils.equals(otp, sessionOTP)) {
            throw new BusinessException(PARAMETER_ERROR, "验证码不正确！");
        }

        // 加密
        user.setPassword(ToolBox.md5(user.getPassword()));

        // 注册
        userService.register(user);

        return new ResponseModel();
    }

    /**
     * 登录
     * @param phone
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseModel login(String phone, String password, HttpSession session) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(PARAMETER_ERROR, "参数不合法！");
        }

        String encryptedPassword = ToolBox.md5(password);
        User user = userService.login(phone, password);
        session.setAttribute("loginUser", user);

        return new ResponseModel();
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public ResponseModel logout(HttpSession session) {
        session.invalidate();

        return new ResponseModel();
    }

    /**
     * 获取正在登录的用户
     * @param session
     * @return
     */
    @GetMapping("/status")
    public ResponseModel getUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return new ResponseModel(user);
    }
}
