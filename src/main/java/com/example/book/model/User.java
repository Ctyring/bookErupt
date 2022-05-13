package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.*;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.*;

@Table(name = "user")
@Erupt(name = "User")
@Entity
public class User {
    @Id
    @Column(name = "id")
    @EruptField(
            views = @View(title = "用户账号"),
            edit = @Edit(title = "用户账号", search = @Search)
    )
    private Long id;

    @EruptField(
            views = @View(title = "用户名"),
            edit = @Edit(title = "用户名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    @EruptField(
            edit = @Edit(title = "密码输入", inputType = @InputType(type = "password"))
    )
    private String password;

    @EruptField(
            views = @View(title = "头像"),
            edit = @Edit(title = "头像", type = EditType.ATTACHMENT,
                    attachmentType = @AttachmentType(type = AttachmentType.Type.IMAGE, maxLimit = 1))
    )
    private String picture;

    @EruptField(
            views = @View(title = "性别"),
            edit = @Edit(title = "性别", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "保密", value = "0"),
                                    @VL(label = "男", value = "1"),
                                    @VL(label = "女", value = "2"),
                            }
                    ))
    )
    private int gender;

    @EruptField(
            views = @View(title = "状态"),
            edit = @Edit(title = "状态", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "正常", value = "0"),
                                    @VL(label = "冻结", value = "1"),
                            }
                    ))
    )
    private int status;

    @EruptField(
            views = @View(title = "角色"),
            edit = @Edit(
                    notNull = true,
                    search = @Search,
                    title = "角色",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            //参数一必填，表示sql语句
                            //参数二可不填，表示缓存时间，默认为3000毫秒，1.6.10及以上版本支持
                            fetchHandlerParams = {"select id, ch_name from role", "5000"}
                    ))
    )
    private String role;

    @EruptField(
            views = @View(title = "生日"),
            edit = @Edit(title = "生日", type = EditType.DATE, dateType = @DateType(pickerMode = DateType.PickerMode.HISTORY))
    )
    private String birthday;

    @Lob
    @EruptField(
            edit = @Edit(title = "用户简介", type = EditType.TEXTAREA)
    )
    private String des;
}
