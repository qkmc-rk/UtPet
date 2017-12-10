package org.arc.ytpet.core.dao;

import org.arc.ytpet.core.model.PrimInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrimInfoMapper {
    int deleteByPrimaryKey(Integer customid);

    int insert(PrimInfo record);

    int insertSelective(PrimInfo record);

    PrimInfo selectByPrimaryKey(Integer customid);

    int updateByPrimaryKeySelective(PrimInfo record);

    int updateByPrimaryKey(PrimInfo record);

    //查找所有的PrimInfo

    List<PrimInfo> selectAll();

    PrimInfo selectByName(String customname);
}