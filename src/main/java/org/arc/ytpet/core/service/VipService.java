package org.arc.ytpet.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.dao.*;
import org.arc.ytpet.core.model.Balance;
import org.arc.ytpet.core.model.Pet;
import org.arc.ytpet.core.model.PrimInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VipService {

    @Autowired
    private PrimInfoMapper primInfoMapper;
    @Autowired
    private BalanceMapper balanceMapper;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private ChargeMapper chargeMapper;
    @Autowired
    private RecordMapper recordMapper;

    private ObjectMapper objectMapper;

    /*
    * @ description 查询所有的vip记录，并以JSON格式返回
    * */
    public String allVipByJson()throws JsonProcessingException{
        List<PrimInfo> list = primInfoMapper.selectAll();
        objectMapper = new ObjectMapper();
        String rs = objectMapper.writeValueAsString(list);
        return rs;
    }

    /*
    * @ description 增加一个vip以及其相关信息
    * @ return boolean
    * */
    @Transactional
    public boolean addOneVip(PrimInfo primInfo, Pet pet, Balance balance){
        //增加一个用户,需要增加用户基本信息,用户余额信息,用户宠物信息
        //三个操作使用一个事务,保证结果的正确性.
        if(primInfoMapper.insertSelective(primInfo) <= 0)
            return false;
        else{
            //先查找主键
            PrimInfo primInfo1 = primInfoMapper.selectByName(primInfo.getCustomname());
            //设置外键键信息
            Integer customId = primInfo1.getCustomid();
            if(customId != null){
                pet.setfCustomid(customId);
                balance.setfCustomid(customId);

                petMapper.insertSelective(pet);
                balanceMapper.insertSelective(balance);

                return true;
            }else{
                return false;
            }
        }
    }

    /*
    * @ description 删除一个vip,需要删除其对应的所有信息
    * @ return boolean
    *
    * */
    @Transactional
    public boolean deleteOneVip(Integer customId){
        //删除其宠物信息,余额信息,个人信息,以及购物记录和充值记录
        //由于balance和pet都参照priminfo的主键作为外键,因此必须先删除子记录,其它也是.
        //删除balance
        if(balanceMapper.deleteByCustomId(customId) > 0 && petMapper.deleteByCustomId(customId) > 0 && primInfoMapper.deleteByPrimaryKey(customId) > 0)
            return true;
        return false;
    }

    /*
    * @ description 修改一个用户的信息
    * @ return boolean
    * */
    public boolean updateOneVipPrimInfo(PrimInfo primInfo){
        //修改一个用户的基本信息
        if(primInfoMapper.updateByPrimaryKeySelective(primInfo) <= 0)
            return false;
        return true;
    }

    /*
    * @ description 查找一个特定的用户
    * @ return json about primInfo petInfo balanceInfo
    * */
    public String selectOneVipWithPetBalanceJson(Integer customId)throws JsonProcessingException{
        PrimInfo primInfo = primInfoMapper.selectByPrimaryKey(customId);
        Pet pet = petMapper.selectByCustomId(customId);
        Balance balance = balanceMapper.selectByCustomId(customId);
        if(primInfo == null || pet == null || balance == null){
            return null;
        }
        String jsonString = "[" + objectMapper.writeValueAsString(primInfo) + "," + objectMapper.writeValueAsString(pet) + "," + objectMapper.writeValueAsString(balance)+ "]";
        return jsonString;
    }

    /*
    * @ description 查找所有用户的基本信息
    * */
    public String queryAllVipJson()throws JsonProcessingException{
        List<PrimInfo> list = primInfoMapper.selectAll();
        String jsonString = objectMapper.writeValueAsString(list);
        return jsonString;
    }

    /*
    * @ description 通过姓名查找一个用户
    * @ return boolean
    *
    * */
    public boolean findVipByName(String customName){
        PrimInfo primInfo = primInfoMapper.selectByName(customName);
        if(primInfo != null) return true;
        else    return false;
    }
    /*
    * @ description 通过电话查找一组用户
    * @ return boolean
    * */
    public PrimInfo getVipByName(String customName) {
        PrimInfo primInfo = primInfoMapper.selectByName(customName);
        if(primInfo != null) return primInfo;
        else    return null;
    }

    //  getter and setter
    public PrimInfoMapper getPrimInfoMapper() {
        return primInfoMapper;
    }

    public void setPrimInfoMapper(PrimInfoMapper primInfoMapper) {
        this.primInfoMapper = primInfoMapper;
    }

    public PetMapper getPetMapper() {
        return petMapper;
    }

    public void setPetMapper(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    public BalanceMapper getBalanceMapper() {
        return balanceMapper;
    }

    public void setBalanceMapper(BalanceMapper balanceMapper) {
        this.balanceMapper = balanceMapper;
    }


}
