package com.example.book.model;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.annotation.sub_field.sub_edit.VL;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author cty
 * @date 2022/6/22
 */
@Entity
@Table(name = "address")
@Erupt(name = "Address")
public class Address extends BaseModel {

    @EruptField(
            views = @View(title = "地址"),
            edit = @Edit(title = "地址", type = EditType.MAP)
    )
    private String map;

    @EruptField(
            views = @View(title = "详细地址"),
            edit = @Edit(title = "详细地址", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String addressDetails;

    @EruptField(
            views = @View(title = "电话"),
            edit = @Edit(title = "电话", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String phone;

    @EruptField(
            views = @View(title = "联系人姓名"),
            edit = @Edit(title = "联系人姓名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

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
    private int gender = 0;
}
