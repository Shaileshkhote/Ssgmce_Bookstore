package com.comparedost.ssgmce_bookstore;

public class CartItemModel {

    public static final int CART_ITEM=0;
    public static final int TOTAL_AMOUNT=1;

    private String PhotoURL;
    private String Book_Title,Selling_Price;

    public CartItemModel(String photoURL, String book_title,  String selling_price) {
        PhotoURL = photoURL;
        Book_Title = book_title;

        Selling_Price = selling_price;

    }

    public  CartItemModel(){}



    public String getBook_Title() {
        return Book_Title;
    }

    public void setBook_Title(String book_Title) {
        Book_Title = book_Title;
    }




    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        PhotoURL = photoURL;
    }

    public String getSelling_Price() {
        return Selling_Price;
    }

    public void setSelling_Price(String selling_Price) {
        Selling_Price = selling_Price;
    }
}
