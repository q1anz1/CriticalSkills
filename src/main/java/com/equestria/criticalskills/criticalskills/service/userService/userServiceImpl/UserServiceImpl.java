package com.equestria.criticalskills.criticalskills.service.userService.userServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.equestria.criticalskills.criticalskills.exception.AccountException;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserBasicInfoMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.User;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserBasicInfo;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AccountMapper accountMapper;
    private final UserBasicInfoMapper userBasicInfoMapper;

    @Autowired
    private UserMapper userMapper;

     /*
     * TODO 邮箱验证码验证(基础验证,核对申请邮箱,验证码过期时间)
     * TODO sql时间字段的自动注入
     *  */
    @Override
    public void addUser(RegisterDTO registerDTO) {
        String username= registerDTO.getUsername();
        String email = registerDTO.getEmail();
        Account findAccount=accountMapper.selectByUsername(username);
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
        if (registerDTO.getInvite().equals("Equestria")){
            account.setRole(3);
        }else {
            account.setRole(2);
        }

        UserBasicInfo userBasicInfo=BeanUtil.copyProperties(registerDTO, UserBasicInfo.class);
        try {
            accountMapper.insertAccount(account);
            userBasicInfoMapper.insertUserBasicInfo(userBasicInfo);
        }catch (Exception e){
            throw new AccountException(e.getMessage());
        }

    }

    /*
    * TODO 验证码部分
    * TODO 检测该设备是否是第一次登录
    * */
    @Override
    public boolean login(LoginDTO loginDTO) {
        String username=loginDTO.getUsername();
        String password=loginDTO.getPassword();
        Account account=accountMapper.selectByUsername(username);
        if (account==null){return false;}
        if (!account.getPassword().equals(password)){return false;}
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
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{11,19}$")){
            throw new AccountException("密码需要包含数字,大写及小写英文字母,长度至少为10且不超过20");
        }
        if (true){
            accountMapper.updatePassword(username,password);
        }

    }

    //修改用户
    @Override
    @Transactional
    public int updateUser(User user) {
        return baseMapper.updateById(user);
    }

    //清空用户
    @Override
    public void clearUser(Long id) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 假设除了id和username外，User实体类还有email和phone字段
        updateWrapper.set("name",null)
                .set("gender",null)
                .set("image",null)
                .set("video",null)
                .set("birthday",null)
                .set("email", null)
                .set("phone", null)
                .set("province",null)
                .set("city",null)
                .set("introduce",null)
                .eq("id", id);

        // 执行更新操作，只更新除了id和username之外的字段
        userMapper.update(null, updateWrapper);
    }

    //上传图片
    @Override
    public void uploadImage(Long id, String url) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("image", url)
                .eq("id", id);
        userMapper.update(null, updateWrapper);
    }

    //上传视频
    @Override
    public void uploadVideo(Long id, String url) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("video", url)
                .eq("id", id);
        userMapper.update(null, updateWrapper);
    }

    //根据id返回用户信息
    @Override
    public User getUserById(Long id) {
        return baseMapper.selectById(id);
    }



}
