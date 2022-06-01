package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.jpa.model.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "storageDetails")
@Erupt(name = "StorageDetails")
public class StorageDetails extends BaseModel{

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
            views = @View(title = "入库数量", sortable = true),
            edit = @Edit(title = "入库数量", search = @Search(vague = true), notNull = true)
    )
    private Integer inventory = 0;
}
