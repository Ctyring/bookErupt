package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.util.Set;

@Erupt(name = "Role", power = @Power(importable = true, export = true))
@Table(name = "role")
@Entity
public class Role {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    //文本输入
    @EruptField(
            views = @View(title = "角色名"),
            edit = @Edit(title = "角色名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    //文本输入
    @EruptField(
            views = @View(title = "角色中文名"),
            edit = @Edit(title = "角色中文名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String chName;

    //文本输入
    @EruptField(
            views = @View(title = "描述"),
            edit = @Edit(title = "描述", type = EditType.TEXTAREA, inputType = @InputType, search = @Search(vague = true))
    )
    private String des;

    @ManyToMany  //多对多
    @JoinTable(
            name = "role_permission", //中间表表名，如下为中间表的定义，详见hibernate ManyToMany
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @EruptField(
            edit = @Edit(
                    title = "拥有权限",
                    type = EditType.TAB_TREE
            )
    )
    private Set<Permission> permissionSet;
}
