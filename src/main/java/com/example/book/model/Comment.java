package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_erupt.Tree;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.ReferenceTreeType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author cty
 * @date 2022/6/22
 */
@Erupt(name = "Comment")
@Table(name = "comment")
@Entity
public class Comment {
    @EruptField(
            views = @View(title = "图书ISBN"),
            edit = @Edit(title = "图书ISBN")
    )
    private String ISBN;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    @EruptField(
            views = @View(title = "父节点ID"),
            edit = @Edit(title = "父节点ID")
    )
    private Long parentId = 0L;

    @EruptField(
            views = @View(title = "评论用户ID"),
            edit = @Edit(title = "评论用户ID")
    )
    private Long userId;

    @EruptField(
            views = @View(title = "被评论用户ID"),
            edit = @Edit(title = "被评论用户ID")
    )
    private Long targetId;

    @EruptField(
            views = @View(title = "评论内容"),
            edit = @Edit(title = "评论内容", type = EditType.TEXTAREA, notNull = true, search = @Search(vague = true))
    )
    private String content;

    @EruptField(
            views = @View(title = "创建时间"),
            edit = @Edit(title = "创建时间", type = EditType.DATE, dateType = @DateType(pickerMode = DateType.PickerMode.HISTORY))
    )
    private Date createTime;
}
