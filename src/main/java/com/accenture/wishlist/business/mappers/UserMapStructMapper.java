package com.accenture.wishlist.business.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStructMapper {
/*
    @Mapping(source = "wishlist_id", target = "wishlist_id")
    UserDAO userToDAO (User user);

    @Mapping(source = "wishlist_id", target = "wishlist_id")
    User daoToUser (UserDAO userDAO);

    //custom methodi reshajut problemu mappinga List --> Can't map property "List<Long> wishlist_id" to "List<WishlistDAO> wishlist_id".
    @Named("wishlist_id")
    default List<WishlistDAO> wishlistIdsToDAOS(List<Long> wishlist_ids) {
        List<WishlistDAO> wishlistDAOS = new ArrayList<>();
        if (isNotEmpty(wishlist_ids)) {
            wishlist_ids.forEach(
                    wishlist_id -> wishlistDAOS.add(new WishlistDAO(wishlist_id)));
        }
        return wishlistDAOS;
    }

    @Named("wishlist_id")
    default List<Long> wishlistDAOToIds(List<WishlistDAO> wishlistDAOS) {
        List<Long> wishlist_ids = new ArrayList<>();
        if (isNotEmpty(wishlistDAOS)) {
            wishlistDAOS.forEach(
                    wishlistDAO -> wishlist_ids.add(wishlistDAO.getWishlist_id()));
        }
        return wishlist_ids;
    }*/

/*    @Named("collaborator")
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
    }*/
}
