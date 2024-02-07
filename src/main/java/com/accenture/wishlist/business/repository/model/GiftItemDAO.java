package com.accenture.wishlist.business.repository.model;

import com.accenture.wishlist.model.Enum.GiftStatus;
import com.accenture.wishlist.model.Enum.Priority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private WishlistDAO wishlistDAO;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "purchase_url")
    private String purchase_url;

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "gift_status")
    private GiftStatus gift_status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

}
