package com.example.book.model;

import org.hibernate.annotations.GenericGenerator;
import xyz.erupt.annotation.Erupt;
import xyz.erupt.annotation.EruptField;
import xyz.erupt.annotation.sub_erupt.Power;
import xyz.erupt.annotation.sub_field.Edit;
import xyz.erupt.annotation.sub_field.EditType;
import xyz.erupt.annotation.sub_field.View;
import xyz.erupt.annotation.sub_field.sub_edit.DateType;
import xyz.erupt.annotation.sub_field.sub_edit.Search;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author cty
 * @date 2022/6/22
 */
@Erupt(name = "SellOrder", power = @Power(importable = true, export = true))
@Table(name = "sell_order")
@Entity
public class SellOrder {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    @Column(name = "id")
    @EruptField(views = @View(title = "订单号"))
    private Long id;

    @EruptField(
            views = @View(title = "用户id", sortable = true),
            edit = @Edit(title = "用户id", search = @Search(vague = true), notNull = true)
    )
    private Long userId;

    @EruptField(
            views = @View(title = "出库时间"),
            edit = @Edit(title = "出库时间", type = EditType.DATE, dateType = @DateType(type = DateType.Type.DATE_TIME))
    )
    private Date orderTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @OrderBy
    @EruptField(
            edit = @Edit(title = "出库清单", type = EditType.TAB_TABLE_ADD),
            views = @View(title = "出库清单")
    )
    private Set<OrderDetails> orderDetails;

    @EruptField(
            views = @View(title = "总价", sortable = true),
            edit = @Edit(title = "总价", search = @Search(vague = true), notNull = true)
    )
    private Float countPrice;
}
