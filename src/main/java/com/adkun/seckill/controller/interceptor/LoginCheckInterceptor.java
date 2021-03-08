package com.adkun.seckill.controller.interceptor;

import com.adkun.seckill.common.ErrorCode;
import com.adkun.seckill.common.ResponseModel;
import com.adkun.seckill.entity.User;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录检查拦截器
 *
 * @author adkun
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor, ErrorCode {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("loginUser");
        if (user == null) {
            // 说明用户不存在
            Map<Object, Object> data = new HashMap<>();
            data.put("code", USER_NOT_LOGIN);
            data.put("message", "用户未登录！");
            ResponseModel model = new ResponseModel(ResponseModel.STATUS_FAILURE, data);

            // 输出流
            PrintWriter writer = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            writer.write(JSONObject.toJSONString(model));
            return false;
        }
        return true;
    }
}
