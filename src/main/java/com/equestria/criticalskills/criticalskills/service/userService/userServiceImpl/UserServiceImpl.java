package com.equestria.criticalskills.criticalskills.service.userService.userServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.equestria.criticalskills.criticalskills.exception.AccountException;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserBasicInfoMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.LoginDTO;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetByEmailDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.ForgetBySecurityDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserBasicInfo;
import com.equestria.criticalskills.criticalskills.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AccountMapper accountMapper;
    private final UserBasicInfoMapper userBasicInfoMapper;



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

        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{11,19}$")==false){
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

        if (true){
            accountMapper.updatePassword(username,password);
        }

    }


}
