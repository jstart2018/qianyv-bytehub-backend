package com.qianyvbytehub.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目dto
 *
 * @author: ChickenWing
 * @date: 2023/10/5
 */
@Data
public class SubjectOptionBO implements Serializable {

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 题目选项的答案内容
     */
    private List<SubjectAnswerBO> optionList;

}

