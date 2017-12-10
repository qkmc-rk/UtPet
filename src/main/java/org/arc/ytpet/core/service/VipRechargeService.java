package org.arc.ytpet.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.dao.BalanceMapper;
import org.arc.ytpet.core.dao.ChargeMapper;
import org.arc.ytpet.core.dao.PrimInfoMapper;
import org.arc.ytpet.core.model.Balance;
import org.arc.ytpet.core.model.Charge;
import org.arc.ytpet.core.model.PrimInfo;
import org.arc.ytpet.core.model.databind.PrimInfoCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VipRechargeService {

    @Autowired
    PrimInfoMapper primInfoMapper;

    @Autowired
    ChargeMapper chargeMapper;

    @Autowired
    BalanceMapper balanceMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    /*
    * @ description 返回所有的充值信息
    * @ return [{充值信息},....]
    * */
    public String findAllRechargeWithPrimInfo() throws JsonProcessingException{
        List<Charge> list = chargeMapper.selectAll();
        Iterator<Charge> iterable = list.iterator();
        PrimInfoCharge primInfoCharge;
        Charge charge;
        PrimInfo primInfo;
        List<PrimInfoCharge> list1 = new ArrayList<>();
        while(iterable.hasNext()){
            charge = iterable.next();
            primInfo = primInfoMapper.selectByPrimaryKey(charge.getfCustomid());
            //PrimInfoCharge
            primInfoCharge = new PrimInfoCharge();
            primInfoCharge.setCustomId(charge.getfCustomid());
            primInfoCharge.setChargeTime(charge.getChargetime());
            primInfoCharge.setCustomName(primInfo.getCustomname());
            primInfoCharge.setAmount(charge.getAmount());
            //最重要的一句
            list1.add(primInfoCharge);
            primInfoCharge = null;
        }
        return objectMapper.writeValueAsString(list1);
    }

    /*
    * @ description 充值金额,需要同时修改用户的余额信息,是一个事务
    * @ return boolean
    * */
    @Transactional
    public boolean charge(Charge charge){
        //先修改充值记录在修改balance余额信息
        Balance balance;
        //修改余额的时候要先查出原来的余额,在相加
        balance = balanceMapper.selectByCustomId(charge.getfCustomid());
        double newBalance = balance.getBalance() + charge.getAmount();  //叠加余额,关键代码
        balance.setBalance(newBalance);
        if(chargeMapper.insert(charge) > 0)    //关键代码
            if(balanceMapper.updateBlcByCustomId(balance) > 0) //关键代码
                return true;
        return false;
    }


    //setters and getters
    public PrimInfoMapper getPrimInfoMapper() {
        return primInfoMapper;
    }

    public void setPrimInfoMapper(PrimInfoMapper primInfoMapper) {
        this.primInfoMapper = primInfoMapper;
    }

    public ChargeMapper getChargeMapper() {
        return chargeMapper;
    }

    public void setChargeMapper(ChargeMapper chargeMapper) {
        this.chargeMapper = chargeMapper;
    }

    public BalanceMapper getBalanceMapper() {
        return balanceMapper;
    }

    public void setBalanceMapper(BalanceMapper balanceMapper) {
        this.balanceMapper = balanceMapper;
    }
}
