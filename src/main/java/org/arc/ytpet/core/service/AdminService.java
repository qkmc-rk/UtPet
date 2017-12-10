package org.arc.ytpet.core.service;

import org.arc.ytpet.core.dao.AdminMapper;
import org.arc.ytpet.core.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

//    登录服务
    /**
     * name login
     * description user login
     *  @ param String password
     *  @ return boolean
     *  @ Author qkmc@outlook.com
     *
     */
    public Admin login(Admin admin){
        if(admin != null && admin.getPassword() != null){
            Admin adm = adminMapper.selectByPassword(admin.getPassword());
            if(adm != null) return adm;
            return null;
        }
        return null;
    }

    public boolean findAdminByPassword(String password){
        if(adminMapper.selectByPassword(password) != null)
            return true;
        return false;
    }
    public AdminMapper getAdminMapper() {
        return adminMapper;
    }

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Admin getAdminByPassword(String adminpassword) {
        return adminMapper.selectByPassword(adminpassword);
    }
}
