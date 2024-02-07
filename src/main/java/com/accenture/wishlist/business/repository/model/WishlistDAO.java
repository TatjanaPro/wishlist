package com.accenture.wishlist.business.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "wishlist")

public class WishlistDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlist_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private UserDAO owner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "colaborator_id")
    @JoinColumn(name = "user_id") //?? inverse Join column
    private List<UserDAO> collaborator;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "event_category")
    private String event_category;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @OneToMany(mappedBy = "wishlistDAO")
    private List<GiftItemDAO> giftItemDAOS;

}
