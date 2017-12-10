package org.arc.ytpet.core.dao;

import org.arc.ytpet.core.model.Pet;
import org.springframework.stereotype.Repository;

@Repository("petMapper")
public interface PetMapper {
    int deleteByPrimaryKey(Integer petid);

    int deleteByCustomId(Integer customId);

    int insert(Pet record);

    int insertSelective(Pet record);

    Pet selectByPrimaryKey(Integer petid);

    int updateByPrimaryKeySelective(Pet record);

    int updateByPrimaryKey(Pet record);

    Pet selectByCustomId(Integer customId);
}