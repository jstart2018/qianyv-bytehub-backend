package com.qianyvbytehub.subject.appliaction.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.qianyvbytehub.subject.appliaction.convert.SubjectCategoryDTOConvert;
import com.qianyvbytehub.subject.appliaction.entity.SubjectCategoryDTO;
import com.qianyvbytehub.subject.common.entity.Result;
import com.qianyvbytehub.subject.domain.entity.SubjectCategoryBO;
import com.qianyvbytehub.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 新增分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(Objects.nonNull(subjectCategoryDTO.getCategoryName()),
                    "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert
                    .INSTANCE.convertDtoToBo(subjectCategoryDTO);
            //传入转化后的实体类
            log.info("subjectCategoryBO:{}", subjectCategoryBO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询大类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategory")
    public Result<List<SubjectCategoryDTO>> queryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategory.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert
                    .INSTANCE.convertDtoToBo(subjectCategoryDTO);
            //传入转化后的实体类
            log.info("subjectCategoryBO:{}", subjectCategoryBO);
            //查询
            List<SubjectCategoryBO> subjectCategoryBOList =
                    subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            log.info("SubjectCategoryController.queryCategory.subjectCategoryBOList:{}", JSON.toJSONString(subjectCategoryBOList));
            //转化
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConvert
                    .INSTANCE.convertBoToDtoList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryCategory.error:{}", e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }


    /**
     * 修改分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(Objects.nonNull(subjectCategoryDTO.getCategoryName()),
                    "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert
                    .INSTANCE.convertDtoToBo(subjectCategoryDTO);
            //传入转化后的实体类
            log.info("subjectCategoryBO:{}", subjectCategoryBO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}", e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }


    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConvert
                    .INSTANCE.convertDtoToBo(subjectCategoryDTO);
            //传入转化后的实体类
            log.info("subjectCategoryBO:{}", subjectCategoryBO);
            Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}", e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }



}
