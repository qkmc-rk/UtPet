package org.arc.ytpet.core.dao;

import org.arc.ytpet.core.model.Balance;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceMapper {
    int deleteByPrimaryKey(Integer blcid);

    int deleteByCustomId(Integer customId);

    int insert(Balance record);

    int insertSelective(Balance record);

    Balance selectByPrimaryKey(Integer blcid);

    int updateByPrimaryKeySelective(Balance record);

    int updateByPrimaryKey(Balance record);

    Balance selectByCustomId(Integer customId);

    List<Balance> selectAll();

    int updateBlcByCustomId(Balance record);
}