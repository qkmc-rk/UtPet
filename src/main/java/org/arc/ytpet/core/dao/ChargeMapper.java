package org.arc.ytpet.core.dao;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.arc.ytpet.core.model.Charge;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeMapper {
    int deleteByPrimaryKey(Integer chargeid);

    int insert(Charge record);

    int insertSelective(Charge record);

    Charge selectByPrimaryKey(Integer chargeid);

    int updateByPrimaryKeySelective(Charge record);

    int updateByPrimaryKey(Charge record);

    List<Charge> selectAll();
}