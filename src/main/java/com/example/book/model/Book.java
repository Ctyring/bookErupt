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

@Table(name = "book")
@Erupt(name = "Book", primaryKeyCol = "ISBN")
@Entity
public class Book {
    @Id
    @Column(name = "ISBN")
    @EruptField(
            views = @View(title = "ISBN"),
            edit = @Edit(title = "ISBN", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private long ISBN;

    //文本输入
    @EruptField(
            views = @View(title = "书籍名"),
            edit = @Edit(title = "书籍名", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String name;

    @EruptField(
            views = @View(title = "图片"),
            edit = @Edit(title = "图片", type = EditType.ATTACHMENT,
                    attachmentType = @AttachmentType(type = AttachmentType.Type.IMAGE, maxLimit = 1))
    )
    private String picture;

    @EruptField(
            views = @View(title = "价格", sortable = true),
            edit = @Edit(title = "价格", search = @Search(vague = true), notNull = true)
    )
    private Float price;

    @EruptField(
            views = @View(title = "库存", sortable = true),
            edit = @Edit(title = "库存", search = @Search(vague = true), notNull = true)
    )
    private Integer inventory;

    @Lob
    @EruptField(
            views = @View(title = "介绍"),
            edit = @Edit(title = "介绍", type = EditType.TEXTAREA)
    )
    private String des;

    @EruptField(
            views = @View(title = "分类"),
            edit = @Edit(
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

    @EruptField(
            views = @View(title = "在售卖"),
            edit = @Edit(
                    title = "在售卖",
                    boolType = @BoolType(trueText = "是", falseText = "否"),
                    search = @Search
            )
    )
    private Boolean isSale = false;

    @EruptField(
            views = @View(title = "在收购"),
            edit = @Edit(
                    title = "在收购",
                    boolType = @BoolType(trueText = "是", falseText = "否"),
                    search = @Search
            )
    )
    private Boolean isPurchase = false;
}
