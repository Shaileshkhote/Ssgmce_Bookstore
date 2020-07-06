package com.comparedost.ssgmce_bookstore;

public class EditorChoiceListItem {

    private String PhotoURL;
    private String Book_Title,Book_Author,Phone_No,Book_Edition,Book_Description,Orignal_Price,Book_Condition,Selling_Price,Listed_By;

    public EditorChoiceListItem(String photoURL, String book_title, String book_author, String phone_no, String book_edition, String book_description, String orignal_price, String book_condition, String selling_price, String listed_by) {
        PhotoURL = photoURL;
        Book_Title = book_title;
        Book_Author = book_author;
        Phone_No = phone_no;
        Book_Edition = book_edition;
        Book_Description = book_description;
        Orignal_Price = orignal_price;
        Book_Condition = book_condition;
        Selling_Price = selling_price;
        Listed_By = listed_by;
    }

    public EditorChoiceListItem(){}

    public String getBook_Author() {
        return Book_Author;
    }

    public void setBook_Author(String book_Author) {
        Book_Author = book_Author;
    }

    public String getBook_Title() {
        return Book_Title;
    }

    public void setBook_Title(String book_Title) {
        Book_Title = book_Title;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String phone_No) {
        Phone_No = phone_No;
    }

    public String getBook_Edition() {
        return Book_Edition;
    }

    public void setBook_Edition(String book_Edition) {
        Book_Edition = book_Edition;
    }

    public String getBook_Description() {
        return Book_Description;
    }

    public void setBook_Description(String book_Description) {
        Book_Description = book_Description;
    }

    public String getOrignal_Price() {
        return Orignal_Price;
    }

    public void setOrignal_Price(String orignal_Price) {
        Orignal_Price = orignal_Price;
    }

    public String getBook_Condition() {
        return Book_Condition;
    }

    public void setBook_Condition(String book_Condition) {
        Book_Condition = book_Condition;
    }

    public String getSelling_Price() {
        return Selling_Price;
    }

    public void setSelling_Price(String selling_Price) {
        Selling_Price = selling_Price;
    }

    public String getListed_By() {
        return Listed_By;
    }

    public void setListed_By(String listed_By) {
        Listed_By = listed_By;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        PhotoURL = photoURL;
    }
}
