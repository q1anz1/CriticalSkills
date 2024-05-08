package com.equestria.criticalskills.criticalskills.service.adminService.AdminServiceImpl;

import com.equestria.criticalskills.criticalskills.exception.DeleteException;
import com.equestria.criticalskills.criticalskills.mapper.adminMapper.AdminMapper;
import com.equestria.criticalskills.criticalskills.pojo.commonPojo.DTO.SystemMsgDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userDTO.SelectUserDTO;
import com.equestria.criticalskills.criticalskills.pojo.userPojo.userEntity.UserInfo;
import com.equestria.criticalskills.criticalskills.result.PageResult;
import com.equestria.criticalskills.criticalskills.service.adminService.AdminService;
import com.equestria.criticalskills.criticalskills.utils.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    private final EmailSender emailSender;

    @Override
    public void deleteUsers(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new DeleteException("删除失败");
        }

        adminMapper.deleteUsers(ids);

    }

    @Override
    public PageResult<UserInfo> selectUsers(SelectUserDTO selectUserDTO) {
        int currnetPage=selectUserDTO.getCurrentPage();
        int pageSize=selectUserDTO.getPageSize();
        int startIndex=(currnetPage-1)*pageSize;
        selectUserDTO.setStartIndex(startIndex);

        List<UserInfo> userList =adminMapper.selectUserInfos(selectUserDTO);
        int totalPage=userList.size()%2==0?userList.size()/2 : (userList.size()+1)/2 ;

        PageResult<UserInfo> list=new PageResult<>();
        list.setTotalPage(totalPage);
        list.setResult(userList);
        return list;
    }

    @Override
    public void sendSystemMsg(SystemMsgDTO systemMsgDTO) throws MessagingException {
        emailSender.sendSystemMessage(systemMsgDTO);
    }


}
