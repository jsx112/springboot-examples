/**
 *    Copyright 2010-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.springboot.quartz.conf.data;

import static org.mybatis.spring.SqlSessionUtils.getSqlSession;
import static org.springframework.util.Assert.notNull;

import com.springboot.quartz.base.BaseEntry;
import com.springboot.quartz.util.Utils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;

/**
* 自定义id生成，在sql执行前进行id绑定
* @author jiasx
* @create 2017/9/23 17:27
**/
public class MybatisSqlSessionTemplate extends SqlSessionTemplate {

  public MybatisSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    super(sqlSessionFactory);
  }

  public MybatisSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
    super(sqlSessionFactory,executorType);
  }


  public MybatisSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType,
                                   PersistenceExceptionTranslator exceptionTranslator) {
    super(sqlSessionFactory,executorType,exceptionTranslator);
  }
  
  @Override
  public int insert(String statement, Object parameter) {
    if(parameter instanceof BaseEntry){
      BaseEntry entry = (BaseEntry)parameter;
      if(entry.getId()==null){
        entry.setId(Utils.generateId());
      }
    }
    return super.insert(statement, parameter);
  }

}
