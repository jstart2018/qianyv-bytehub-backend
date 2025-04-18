package com.qianyvbytehub.subject.domain.handle.subject;


import com.qianyvbytehub.subject.common.enums.SubjectTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    //spring注入的特性：集合注入，一次性注入实现了SubjectTypeHandler接口的所有实现类
    @Resource
    List<SubjectTypeHandler> subjectHandlers = new LinkedList<>();

    //把枚举和题目类型一一对应放到map中，方便取出来
    Map<SubjectTypeEnum,SubjectTypeHandler> handlerMap = new HashMap<>();


    //真正提供给外部调用的方法：获得对应题目的handler
    public SubjectTypeHandler getHandler(int subjectType){

        //调用枚举类的方法，根据code获得对应的枚举
        SubjectTypeEnum subjectTypeEnum = SubjectTypeEnum.getByCode(subjectType);

        //将handler返回给外部，外部直接调用handler的add方法实现新增功能
        return handlerMap.get(subjectTypeEnum);
    }


    /**
     * 插入map数据
     * InitializingBean的接口，bean将被实例化的最后执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectHandler : subjectHandlers) {
            handlerMap.put(subjectHandler.getSubjectType(),subjectHandler);
        }
    }
}
