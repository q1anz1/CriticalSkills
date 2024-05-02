package com.equestria.criticalskills.criticalskills.service.userService.userServiceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.equestria.criticalskills.criticalskills.exception.AccountException;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.AccountMapper;
import com.equestria.criticalskills.criticalskills.mapper.userMapper.UserBasicInfoMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.RegisterDTO;
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




    @Override
    public void addUser(RegisterDTO registerDTO) {
        String username= registerDTO.getUsername();
        String email = registerDTO.getEmail();
        if (accountMapper.findEmail(email)==email||accountMapper.findUsername(username)==username){
            throw new AccountException("用户已存在");
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





}
