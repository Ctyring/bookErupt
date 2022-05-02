package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_erupt.Tree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;

@Erupt(name = "Permission",
        power = @Power(importable = true, export = true),
        tree = @Tree(id = "id", label = "chName", pid = "parent.id", expandLevel = 2)
)
@Table(name = "permission")
@Entity
public class Permission {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    //文本输入
    @EruptField(
            views = @View(title = "权限名"),
            edit = @Edit(title = "权限名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    //文本输入
    @EruptField(
            views = @View(title = "权限中文名"),
            edit = @Edit(title = "权限中文名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String chName;

    //文本输入
    @EruptField(
            views = @View(title = "描述"),
            edit = @Edit(title = "描述", type = EditType.TEXTAREA, inputType = @InputType, search = @Search(vague = true))
    )
    private String des;

    @ManyToOne
    @EruptField(
            edit = @Edit(
                    title = "父权限",
                    type = EditType.REFERENCE_TREE,
                    referenceTreeType = @ReferenceTreeType(pid = "parent.id", label = "chName")
            )
    )
    private Permission parent;
}
