package org.arc.ytpet.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.dao.BalanceMapper;
import org.arc.ytpet.core.dao.PrimInfoMapper;
import org.arc.ytpet.core.model.Balance;
import org.arc.ytpet.core.model.PrimInfo;
import org.arc.ytpet.core.model.databind.PrimInfoBlc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VipBalanceService {

    @Autowired
    BalanceMapper balanceMapper;

    @Autowired
    PrimInfoMapper primInfoMapper;
    //这里没有使用spring bean 容器管理 因为我对spring理解的不够深刻吧
    ObjectMapper objectMapper = new ObjectMapper();
    /*
    * @ description 查找所有的余额信息
    * @ return [{余额信息},...]
    * @ param null
    * */
    public String findAllBalanceWithPrimInfo()throws JsonProcessingException{
        List<Balance> list = balanceMapper.selectAll();
        Iterator<Balance> iterable = list.iterator();
        PrimInfoBlc primInfoBlc;
        Balance balance;
        PrimInfo primInfo;
        List<PrimInfoBlc> list1 = new ArrayList<>();
        while(iterable.hasNext()){
            balance = iterable.next();
            primInfo = primInfoMapper.selectByPrimaryKey(balance.getfCustomid());
            //生成primInfoBlc
            primInfoBlc = new PrimInfoBlc();
            primInfoBlc.setCustomId(balance.getfCustomid());
            primInfoBlc.setCustomName(primInfo.getCustomname());
            primInfoBlc.setBalance(balance.getBalance());
            //最重要的一句
            list1.add(primInfoBlc);
            primInfoBlc = null;
        }
        return objectMapper.writeValueAsString(list1);
    }

    /*
    * @ description 修改用户的余额信息
    * @ return boolean
    * */
    public boolean updateBlcByCustomId(Balance balance){
        if(balanceMapper.updateBlcByCustomId(balance) > 0)
            return true;
        return false;
    }

    // getters and setters
    public BalanceMapper getBalanceMapper() {
        return balanceMapper;
    }

    public void setBalanceMapper(BalanceMapper balanceMapper) {
        this.balanceMapper = balanceMapper;
    }

    public PrimInfoMapper getPrimInfoMapper() {
        return primInfoMapper;
    }

    public void setPrimInfoMapper(PrimInfoMapper primInfoMapper) {
        this.primInfoMapper = primInfoMapper;
    }
}
