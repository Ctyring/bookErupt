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

/**
 * @author cty
 * @date 2022/6/22
 */
@Table(name = "audit")
@Erupt(name = "Audit")
@Entity
public class Audit {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    @EruptField(
            views = @View(title = "ISBN"),
            edit = @Edit(title = "ISBN", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String ISBN;

    @EruptField(
            views = @View(title = "书籍名"),
            edit = @Edit(title = "书籍名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    @Lob
    @EruptField(
            views = @View(title = "介绍"),
            edit = @Edit(title = "介绍", type = EditType.TEXTAREA)
    )
    private String des;

    @EruptField(
            views = @View(title = "破损情况"),
            edit = @Edit(
                    notNull = true,
                    search = @Search,
                    title = "破损情况",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            //参数一必填，表示sql语句
                            //参数二可不填，表示缓存时间，默认为3000毫秒，1.6.10及以上版本支持
                            fetchHandlerParams = {"select id, name from book_condition", "5000"}
                    ))
    )
    private String bookCondition;

    @EruptField(
            views = @View(title = "作者"),
            edit = @Edit(
                    notNull = true,
                    search = @Search,
                    title = "作者",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            //参数一必填，表示sql语句
                            //参数二可不填，表示缓存时间，默认为3000毫秒，1.6.10及以上版本支持
                            fetchHandlerParams = {"select id, name from author", "5000"}
                    ))
    )
    private String author;

    @EruptField(
            views = @View(title = "分类"),
            edit = @Edit(
                    notNull = true,
                    search = @Search,
                    title = "分类",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            //参数一必填，表示sql语句
                            //参数二可不填，表示缓存时间，默认为3000毫秒，1.6.10及以上版本支持
                            fetchHandlerParams = {"select id, name from category", "5000"}
                    ))
    )
    private String category;

    @EruptField(
            views = @View(title = "出版社"),
            edit = @Edit(
                    notNull = true,
                    search = @Search,
                    title = "出版社",
                    type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            fetchHandler = SqlChoiceFetchHandler.class,
                            //参数一必填，表示sql语句
                            //参数二可不填，表示缓存时间，默认为3000毫秒，1.6.10及以上版本支持
                            fetchHandlerParams = {"select id, name from press", "5000"}
                    ))
    )
    private String press;

    //文本输入
    @EruptField(
            views = @View(title = "版本"),
            edit = @Edit(title = "版本", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String version;

    @EruptField(
            views = @View(title = "图片"),
            edit = @Edit(title = "图片", type = EditType.ATTACHMENT,
                    attachmentType = @AttachmentType(type = AttachmentType.Type.IMAGE, maxLimit = 1, size = 10240))
    )
    private String picture;

    @EruptField(
            views = @View(title = "状态"),
            edit = @Edit(title = "状态", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "未审核", value = "0"),
                                    @VL(label = "已审核", value = "1"),
                            }
                    ))
    )
    private int status = 0;
}
