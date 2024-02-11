package com.accenture.wishlist.business.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapStructMapper {

/*
    @Mapping(source = "collaborator", target = "collaborator")
    WishlistDAO wishlistToDAO (Wishlist wishlist);

    @Mapping(source = "collaborator", target = "collaborator")
    Wishlist daoToWishlist (WishlistDAO wishlistDAO);

    @Named("collaborator")
    default List<WishlistDAO> wishlistIdListToDAOS(List<Long> wishlistIdsList) {
        List<WishlistDAO> wishlistDAOList = new ArrayList<>();
        if (isNotEmpty(wishlistIdsList)) {
            wishlistIdsList.forEach(
                    wishlistIdList -> wishlistDAOList.add(new WishlistDAO(wishlistIdList)));
        }
        return wishlistDAOList;
    }

    @Named("collaborator")
    default List<Long> wishlistDAOToIdList(List<WishlistDAO> wishlistDAOList) {
        List<Long> wishlistIdList = new ArrayList<>();
        if (isNotEmpty(wishlistDAOList)) {
            wishlistDAOList.forEach(
                    wishlistListDAO -> wishlistIdList.add(wishlistListDAO.getWishlist_id()));
        }
        return wishlistIdList;
    }
*/

}
