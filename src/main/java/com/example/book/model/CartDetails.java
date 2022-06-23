package com.example.book.model;

import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.AttachmentType;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;
import xyz.erupt.toolkit.handler.SqlChoiceFetchHandler;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author cty
 * @date 2022/6/22
 */
@Entity
@Table(name = "cart_details")
@Erupt(name = "CartDetails")
public class CartDetails extends BaseModel {
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

    @EruptField(
            views = @View(title = "数量", sortable = true),
            edit = @Edit(title = "数量", search = @Search(vague = true), notNull = true)
    )
    private Integer inventory = 0;

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
            views = @View(title = "单价", sortable = true),
            edit = @Edit(title = "单价", search = @Search(vague = true), notNull = true)
    )
    private Float purchasePrice;

    @EruptField(
            views = @View(title = "合计", sortable = true),
            edit = @Edit(title = "合计", search = @Search(vague = true), notNull = true)
    )
    private Float countPrice;
}
