package com.equestria.criticalskills.criticalskills.service.adminService.AdminServiceImpl;

import com.equestria.criticalskills.criticalskills.exception.DeleteException;
import com.equestria.criticalskills.criticalskills.exception.EditException;
import com.equestria.criticalskills.criticalskills.exception.EmailException;
import com.equestria.criticalskills.criticalskills.mapper.adminMapper.AdminMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.SystemMsgDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.Account;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.PageResult;
import com.equestria.criticalskills.criticalskills.service.adminService.AdminService;
import com.equestria.criticalskills.criticalskills.utils.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final EmailSender emailSender;

    @Override
    public void deleteUsers(List<String> usernames) {
        if (usernames == null || usernames.isEmpty()) {
            throw new DeleteException("删除失败");
        }

        adminMapper.deleteUsers(usernames);

    }

    @Override
    public PageResult<UserInfo> selectUsers(SelectUserDTO selectUserDTO) {
        int currnetPage=selectUserDTO.getCurrentPage();
        int pageSize=selectUserDTO.getPageSize();
        int startIndex=(currnetPage-1)*pageSize;
        selectUserDTO.setStartIndex(startIndex);

        List<UserInfo> userList = adminMapper.selectUserInfos(selectUserDTO);
        Iterator<UserInfo> iterator = userList.iterator();
        while (iterator.hasNext()) {
            UserInfo userInfo = iterator.next();
            Account user = adminMapper.findUserByUsername(userInfo.getUsername());
            if (user.getRole() == 0) {
                iterator.remove();
            }
        }

        int totalPage=userList.size()%2==0?userList.size()/2 : (userList.size()+1)/2 ;

        PageResult<UserInfo> list=new PageResult<>();
        list.setTotalPage(totalPage);
        list.setResult(userList);
        return list;
    }

    @Override
    public void sendSystemMsg(SystemMsgDTO systemMsgDTO){
        try {
            emailSender.sendSystemMessage(systemMsgDTO);
        }catch (Exception e){
            throw new EmailException("系统消息发送失败");
        }

    }

    @Override
    public void editUser(UserInfo userInfo) {
        Account user=adminMapper.findUserByUsername(userInfo.getUsername());
        if(user==null){
            throw new EditException("编辑用户信息失败");
        }else if (user.getRole()==0){
            throw new EditException("编辑用户信息失败");
        }

        try {
            adminMapper.updateUser(userInfo);
        }catch (Exception e){
            throw new EditException("编辑用户信息失败");
        }
    }


}
