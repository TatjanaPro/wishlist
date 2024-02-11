package com.accenture.wishlist.business.repository.model;

import lombok.Data;

@Data
public class WishlistDTO {

    private Long wishlist_id;
    private Long owner_id;
    //private User collaborator;
    private String title;
    private String description;
    private String event_category;
    private String start_date;
    private String end_date;

/*    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long wishlist_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private UserDAO owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "colaborator_id")
    @JoinColumn(name = "user_id") //?? inverse Join column
    private UserDAO collaborator; //List<UserDAO>

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

    public WishlistDAO(Long wishlistId) {
    }*/
}
