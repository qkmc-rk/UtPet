package org.arc.ytpet.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.dao.BalanceMapper;
import org.arc.ytpet.core.dao.PrimInfoMapper;
import org.arc.ytpet.core.dao.RecordMapper;
import org.arc.ytpet.core.model.Balance;
import org.arc.ytpet.core.model.PrimInfo;
import org.arc.ytpet.core.model.Record;
import org.arc.ytpet.core.model.databind.PrimInfoBlc;
import org.arc.ytpet.core.model.databind.PrimInfoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VipConsumeService {

    @Autowired
    PrimInfoMapper primInfoMapper;
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    BalanceMapper balanceMapper;

    ObjectMapper objectMapper = new ObjectMapper();
    /*
    * @ description 返回所有的消费信息 查询不需要事务
    * @ return [{primInfoRecord},...]
    * */
    public String findAllRecordWithPrimInfo()throws JsonProcessingException{
        List<Record> list = recordMapper.selectAll();
        Iterator<Record> iterable = list.iterator();
        PrimInfoRecord primInfoRecord;
        Record record;
        PrimInfo primInfo;
        List<PrimInfoRecord> list1 = new ArrayList<>();
        while(iterable.hasNext()){
            record = iterable.next();
            primInfo = primInfoMapper.selectByPrimaryKey(record.getfCustomid());
            //生成PrimInfoRecord
            primInfoRecord = new PrimInfoRecord();
            primInfoRecord.setCustomId(primInfo.getCustomid());
            primInfoRecord.setCustomName(primInfo.getCustomname());
            primInfoRecord.setAmount(record.getAmount());
            primInfoRecord.setMark(record.getRemark());
            primInfoRecord.setPayWhat(record.getPaywhat());
            primInfoRecord.setRecordId(record.getRecordid());
            //最重要的一句
            list1.add(primInfoRecord);
            primInfoRecord = null;
        }
        return objectMapper.writeValueAsString(list1);
    }

    /*
    * @ description 增加一个消费记录 增加一条消费记录 要扣除相应的余额信息 所以这是一个事务
    * @ return boolean
    *
    * */
    @Transactional
    public boolean addOneConsume(Record record){
        Balance balance;
        Integer customId = record.getfCustomid();
        Double consumeAmount = record.getAmount();
        //找到balance中的balance,然后修改金额,在保存
        balance = balanceMapper.selectByCustomId(customId);
        balance.setBalance(balance.getBalance() - consumeAmount);
        if(balanceMapper.updateBlcByCustomId(balance) > 0)     //修改余额
        //现在增加记录
            if(recordMapper.insertSelective(record) > 0)
                return true;
            else
                return false;
            return false;
    }



    //getters and setters

    public PrimInfoMapper getPrimInfoMapper() {
        return primInfoMapper;
    }

    public void setPrimInfoMapper(PrimInfoMapper primInfoMapper) {
        this.primInfoMapper = primInfoMapper;
    }

    public RecordMapper getRecordMapper() {
        return recordMapper;
    }

    public void setRecordMapper(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }
}
