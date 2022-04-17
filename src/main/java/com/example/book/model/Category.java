package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;

@Erupt(name = "Category", power = @Power(importable = true, export = true))
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    //文本输入
    @EruptField(
            views = @View(title = "分类名称"),
            edit = @Edit(title = "分类名称", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;
}
