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

/**
 * @author cty
 * @date 2022/6/24
 */
@Table(name = "user_word")
@Erupt(name = "UserWord", power = @Power(importable = true, export = true))
@Entity
public class UserWord {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    @EruptField(
            views = @View(title = "内容"),
            edit = @Edit(title = "内容", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String tag;
}
