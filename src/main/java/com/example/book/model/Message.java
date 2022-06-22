package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.util.Date;

/**
 * @author cty
 * @date 2022/6/22
 */
@Table(name = "message")
@Erupt(name = "Message")
@Entity
public class Message {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    @EruptField(
            views = @View(title = "留言内容"),
            edit = @Edit(title = "留言内容", type = EditType.TEXTAREA, notNull = true, search = @Search(vague = true))
    )
    private String content;

    @EruptField(
            views = @View(title = "姓名"),
            edit = @Edit(title = "姓名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    @EruptField(
            views = @View(title = "创建时间"),
            edit = @Edit(title = "创建时间", type = EditType.DATE, dateType = @DateType(pickerMode = DateType.PickerMode.HISTORY))
    )
    private Date createTime;
}
