package com.accenture.wishlist.business.repository.model;

import com.accenture.wishlist.model.Enum.EventCategory;
import com.accenture.wishlist.model.User;
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
import javax.persistence.ManyToMany;
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

    @OneToMany
    @JoinColumn(name = "user_id")
    private User owner_id;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private List<User> contributor;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "event_category")
    private EventCategory event_category;

    @Column(name = "start_date")
    private String start_date;

    @Column(name = "end_date")
    private String end_date;

}
