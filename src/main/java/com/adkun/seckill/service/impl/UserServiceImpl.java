package com.adkun.seckill.service.impl;

import com.adkun.seckill.common.BusinessException;
import com.adkun.seckill.common.ErrorCode;
import com.adkun.seckill.common.ToolBox;
import com.adkun.seckill.component.ObjectValidator;
import com.adkun.seckill.dao.UserMapper;
import com.adkun.seckill.entity.User;
import com.adkun.seckill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService, ErrorCode {

    private final UserMapper userMapper;
    private final ObjectValidator validator;

    public UserServiceImpl(UserMapper userMapper, ObjectValidator validator) {
        this.userMapper = userMapper;
        this.validator = validator;
    }

    /**
     * 注册 事务
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        if (user == null) {
            throw new BusinessException(PARAMETER_ERROR, "参数不能为空！");
        }

        Map<String, String> result = validator.validate(user);
        if (result != null && result.size() > 0) {
            throw new BusinessException(PARAMETER_ERROR,
                    StringUtils.join(result.values().toArray(), ", ") + "!");
        }

        // 不需要sql查询手机号是否已注册
        // 如果手机号已注册，底层会抛出DuplicateKeyException
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(PARAMETER_ERROR, "手机号已注册！");
        }
    }

    @Override
    public User login(String phone, String password) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)) {
            throw new BusinessException(PARAMETER_ERROR, "参数不合法！");
        }

        User user = userMapper.selectByPhone(phone);
        String encryptedPassword = ToolBox.md5(password);
        if (user == null || !StringUtils.equals(encryptedPassword, user.getPassword())) {
            throw new BusinessException(USER_LOGIN_FAILURE, "帐号或密码错误！");
        }

        return user;
    }

    @Override
    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
