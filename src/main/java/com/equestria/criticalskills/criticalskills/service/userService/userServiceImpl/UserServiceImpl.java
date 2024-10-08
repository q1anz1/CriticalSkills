package com.equestria.criticalskills.criticalskills.service.userService.userServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.equestria.criticalskills.criticalskills.exception.AccountException;
import com.equestria.criticalskills.criticalskills.exception.EditException;
import com.equestria.criticalskills.criticalskills.exception.LoginException;
import com.equestria.criticalskills.criticalskills.exception.UserException;
import com.equestria.criticalskills.criticalskills.mapper.adminMapper.AdminMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;

import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserInfoMapper;

import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;

import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.Result;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import com.equestria.criticalskills.criticalskills.utils.JsonWebTokenUtil;
import com.sun.net.httpserver.Authenticator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final AccountMapper accountMapper;
    private final UserInfoMapper userInfoMapper;
    private final RedisTemplate<String,String> redisTemplate;
    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private final JsonWebTokenUtil jsonWebTokenUtil;


     /*
     * TODO sql时间字段的自动注入
     *  */
    @Override
    public void addUser(RegisterDTO registerDTO) {
        String username= registerDTO.getUsername();
        String email = registerDTO.getEmail();
        String emailCode = registerDTO.getEmailCode();
        Account findAccount=accountMapper.selectByUsername(username);
        String realEmailCode=redisTemplate.opsForValue().get(email+"EmailCode");
        if (!emailCode.equals(realEmailCode)){
            throw new AccountException("验证码错误");
        }
        if(findAccount!=null) {
            throw new AccountException("用户已存在");
        }

        if (accountMapper.findEmail(email)!=null){
            throw new AccountException("该邮箱已被注册");
        }

        String password = registerDTO.getPassword();

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{11,19}$")){
            throw new AccountException("密码需要包含数字,大写及小写英文字母,长度至少为10且不超过20");
        }

        Account account= BeanUtil.copyProperties(registerDTO, Account.class);
        UserInfo userBasicInfo=BeanUtil.copyProperties(registerDTO, UserInfo.class);
        if (registerDTO.getInvite().equals("Equestria")){
            account.setRole(3);
        }else {
            account.setRole(2);
        }
            accountMapper.insertAccount(account);
            userInfoMapper.insertUserBasicInfo(userBasicInfo);
    }

    /*
    * TODO 检测该设备是否是第一次登录
    * */
    @Override
    public boolean login(LoginDTO loginDTO) {
        String username=loginDTO.getUsername();
        String password=loginDTO.getPassword();
        Account account=accountMapper.selectByUsername(username);
        String verification=loginDTO.getVerification();
        String realVerification=redisTemplate.opsForValue().get(username+"Verification");
        if (!verification.equals(realVerification)){
            throw new  LoginException("图片验证码错误");
        }
        if (account==null){
            throw new  LoginException("此用户不存在");
        }
        if (!account.getPassword().equals(password)){
            throw new  LoginException("密码错误");
        }
        return account.getRole()!=0;
    }

    @Override
    public void updatePasswordBySecurity(ForgetBySecurityDTO forgetBySecurityDTO) {
        String securityAsk=forgetBySecurityDTO.getSecurityAsk();
        String securityAns=forgetBySecurityDTO.getSecurityAns();
        String username=forgetBySecurityDTO.getUsername();
        String password=forgetBySecurityDTO.getPassword();
        Account account=accountMapper.selectByUsername(forgetBySecurityDTO.getUsername());
        String findSecurityAsk=account.getSecurityAsk();
        String findSecurityAns=account.getSecurityAns();
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{11,19}$")){
            throw new AccountException("密码需要包含数字,大写及小写英文字母,长度至少为10且不超过20");
        }
        if (securityAsk.equals(findSecurityAsk)&&securityAns.equals(findSecurityAns)){
            accountMapper.updatePassword(username,password);
        }

    }


    /*
    * TODO 验证码部分
    * */
    @Override
    public void updatePasswordByEmail(ForgetByEmailDTO forgetByEmailDTO) {
        String username=forgetByEmailDTO.getUsername();
        String password=forgetByEmailDTO.getPassword();
        String email=forgetByEmailDTO.getEmail();
        String emailCode=forgetByEmailDTO.getEmailCode();
        String realEmailCode=redisTemplate.opsForValue().get(email+"EmailCode");
        if (!emailCode.equals(realEmailCode)){
            throw new AccountException("验证码错误");
        }
        String regex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{11,19}$";
        if (!password.matches(regex)){
            throw new AccountException("密码需要包含数字,大写及小写英文字母,长度至少为10且不超过20");
        }
        if (true){
            accountMapper.updatePassword(username,password);
        }

    }
    //根据id返回用户信息
    @Override
    public User getUserById(Long id) {
        Account user=adminMapper.findUserByUserId(id);
        if(user==null){
            throw new UserException("用户不存在");
        }else if (user.getRole()==0){
            throw new UserException("查询用户信息失败");
        }
        return userMapper.selectById(id);
    }

    //修改用户
    @Override
    public void updateUser(User user , HttpServletRequest httpServletRequest) {
        Map map = jsonWebTokenUtil.getMap(httpServletRequest);
        String username = map.get("username").toString();

        if(user.getUsername().equals(username)) {
            Long id = user.getId();
            Account users = adminMapper.findUserByUserId(id);
            if (users == null) {
                throw new UserException("用户不存在");
            } else if (users.getRole() == 0) {
                throw new UserException("修改用户信息失败");
            }
            userMapper.updateInUser(user);
        }
        else{
            throw new UserException("用户不正确");
        }
    }

    //清空用户
    @Override
    public void clearUser(Long id , HttpServletRequest httpServletRequest) {
        Account users = adminMapper.findUserByUserId(id);

        Map map = jsonWebTokenUtil.getMap(httpServletRequest);
        String username = map.get("username").toString();

        if(users.getUsername().equals(username)) {
            userMapper.clearUserFields(id);
        }
        else{
            throw new UserException("清空用户信息失败");
        }


    }

    //上传头像
    @Override
    public void uploadAvator(Long id, String url) {
        Account user=adminMapper.findUserByUserId(id);
        if(user==null){
            throw new UserException("用户不存在");
        }else if (user.getRole()==0){
            throw new UserException("修改用户信息失败");
        }
        userMapper.updateAvator(id, url);
    }

    //上传图片
    @Override
    public void uploadPhoto(Long id, String url) {
        Account user=adminMapper.findUserByUserId(id);
        if(user==null){
            throw new UserException("用户不存在");
        }else if (user.getRole()==0){
            throw new UserException("修改用户信息失败");
        }
        userMapper.updatePhoto(id, url);
    }

    @Override
    public void uploadVideo(Long id, String url) {
        Account user=adminMapper.findUserByUserId(id);
        if(user==null){
            throw new UserException("用户不存在");
        }else if (user.getRole()==0){
            throw new UserException("修改用户信息失败");
        }
        userMapper.updateVideo(id, url);
    }

    }
