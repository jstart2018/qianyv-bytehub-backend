package com.qianyvbytehub.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案dto
 *
 * @author: ChickenWing
 * @date: 2023/10/5
 */
@Data
public class SubjectAnswerBO implements Serializable {

    /**
     * 答案选项标识 A 、 B 、 C 、 D
     */
    private Integer optionType;

    /**
     * 该选项的内容
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;

}

