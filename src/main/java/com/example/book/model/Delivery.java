package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.ChoiceType;
import xyz.erupt.annotation.sub_field.sub_edit.InputType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;
import xyz.erupt.annotation.sub_field.sub_edit.VL;

import javax.persistence.*;
import java.util.Set;

/**
 * @author cty
 * @date 2022/6/22
 */
@Table(name = "delivery")
@Erupt(name = "Delivery", power = @Power(importable = true, export = true))
@Entity
public class Delivery {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField
    private Long id;

    @EruptField(
            views = @View(title = "快递单号"),
            edit = @Edit(title = "快递单号", notNull = true, inputType = @InputType, search = @Search(vague = true))
    )
    private String deliveryId;

    @EruptField(
            views = @View(title = "地址"),
            edit = @Edit(title = "地址", type = EditType.MAP)
    )
    private String map;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "delivery_id")
    @OrderBy
    @EruptField(
            edit = @Edit(title = "配送清单", type = EditType.TAB_TABLE_ADD),
            views = @View(title = "配送清单")
    )
    private Set<DeliveryDetails> deliveryDetails;

    @EruptField(
            views = @View(title = "配送状态"),
            edit = @Edit(title = "配送状态", type = EditType.CHOICE,
                    choiceType = @ChoiceType(
                            vl = {
                                    @VL(label = "未配送", value = "0"),
                                    @VL(label = "正在配送", value = "1"),
                                    @VL(label = "已配送", value = "2"),
                            }
                    ))
    )
    private int status = 0;

    @EruptField(
            views = @View(title = "配送者id", sortable = true),
            edit = @Edit(title = "配送者id", search = @Search(vague = true))
    )
    private Long workerId;
}
