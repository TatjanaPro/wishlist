package com.accenture.wishlist.business.repository.model;

import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "gift_item")

public class GiftItemDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_item_id")
    private Long gift_item_id;

    @OneToMany
    @JoinColumn(name = "wishlist_id")
    private Long wishlist_id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "purchase_url")
    private String purchase_url;

    @Column(name = "price")
    private double price;

    @Column(name = "gift_status")
    private GiftStatus gift_status;

    @Column(name = "priority")
    private Priority priority;
}
