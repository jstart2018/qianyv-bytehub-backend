package com.qianyvbytehub.subject.domain.service.impl;


import com.alibaba.fastjson.JSON;
import com.qianyvbytehub.subject.common.entity.PageResult;
import com.qianyvbytehub.subject.common.enums.DeletedStateEnum;
import com.qianyvbytehub.subject.domain.convert.SubjectInfoConverter;
import com.qianyvbytehub.subject.domain.entity.SubjectInfoBO;
import com.qianyvbytehub.subject.domain.entity.SubjectOptionBO;
import com.qianyvbytehub.subject.domain.handle.subject.SubjectTypeHandler;
import com.qianyvbytehub.subject.domain.handle.subject.SubjectTypeHandlerFactory;
import com.qianyvbytehub.subject.domain.service.SubjectInfoDomainService;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectInfo;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectLabel;
import com.qianyvbytehub.subject.infra.basic.entity.SubjectMapping;
import com.qianyvbytehub.subject.infra.basic.service.SubjectInfoService;
import com.qianyvbytehub.subject.infra.basic.service.SubjectLabelService;
import com.qianyvbytehub.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    /**
     * 新增题目
     * @param subjectInfoBO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }

        //BO转成基础设施层的实体类：
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        //获取到添加完主表后，返回的题目主键id，即题目id
        subjectInfoBO.setId(subjectInfo.getId());
        //调用工厂，添加对应题目
        SubjectTypeHandler subjectHandler =
                subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectHandler.add(subjectInfoBO);

        //添加 题目、标签关联表：subject_mapping
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        //注意分类id和标签是多对多关系，如果一个分类两个标签，新增两条数据
        // 如果是 两个分类，两个标签，添加四条数据
        for (Integer categoryId : subjectInfoBO.getCategoryIds()) {
            for (Integer labelId : subjectInfoBO.getLabelIds()) {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setIsDeleted(DeletedStateEnum.UNDELETED.getCode());
                subjectMappingList.add(subjectMapping);
            }
        }
        subjectMappingService.batchInsert(subjectMappingList);

    }

    /**
     * 批量查询题目
     * @param subjectInfoBO
     * @return
     */
    @Override
    public PageResult<SubjectInfoBO> querySubjectList(SubjectInfoBO subjectInfoBO) {
        //封装分页返回的基础属性
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        //BO转基类实体
        SubjectInfo subjectInfo = SubjectInfoConverter
                .INSTANCE.convertBoToInfo(subjectInfoBO);
        //去两表联查，查总数：
        int count = subjectInfoService.countByCondition(subjectInfo,
                subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());
        if (count == 0)
            return pageResult;
        //分页查具体数据（双表）：
        int start = (subjectInfoBO.getPageNo()-1) * subjectInfoBO.getPageSize();//当前页数据的起始索引
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        //info转bo
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter
                .INSTANCE.convertListInfoToBO(subjectInfoList);
        pageResult.setRecord(subjectInfoBOS);

        return pageResult;
    }


    /**
     * 查询题目详情
     * @param subjectInfoBO
     * @return
     */
    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        log.info("SubjectController.querySubjectInfo.bo:{}", JSON.toJSONString(subjectInfoBO));
        //1、查info表，获取题目类型等基本信息
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        try {
            if (subjectInfo == null)
                throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException("题目不存在");
        }
        //2、去策略工厂查 题目的详细 选项内容options
        SubjectTypeHandler handler = null;
        try {
            handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        } catch (Exception e) {
            throw new RuntimeException("题目所属类型不存在");
        }
        SubjectOptionBO subjectOptionBO = handler.query(subjectInfo.getId());
        //3、合并
        SubjectInfoBO infoBO = SubjectInfoConverter
                .INSTANCE.convertOptionAndInfoToBo(subjectOptionBO, subjectInfo);

        //4、查题目的标签信息
            //先查mapping表获得标签list
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(infoBO.getId());//传入题目id
        List<Long> labelIds = subjectMappingService.queryLabelId(subjectMapping);
            //查标签表，获取标签name
        List<SubjectLabel> subjectLabels = subjectLabelService.batchQueryById(labelIds);
        //5、set标签name
        List<String> labelNames = new LinkedList<>();
        for (SubjectLabel subjectLabel : subjectLabels) {
            labelNames.add(subjectLabel.getLabelName());
        }
        infoBO.setLabelName(labelNames);

        //6、返回数据

        return infoBO;
    }


}
