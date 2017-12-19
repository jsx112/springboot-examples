package com.springboot.mutidatasource.base.repository.mongo;

import com.springboot.mutidatasource.base.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends MongoRepository<T, ID> {

}
