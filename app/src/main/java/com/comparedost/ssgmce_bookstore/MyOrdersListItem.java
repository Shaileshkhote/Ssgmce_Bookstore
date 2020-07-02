package com.comparedost.ssgmce_bookstore;

public class MyOrdersListItem
{
   private int image;
   private String invoice_btn,book_name,order_id,date;

    public MyOrdersListItem(int image, String book_name, String order_id, String date,String invoice_btn) {
        this.image = image;
        this.book_name = book_name;
        this.order_id = order_id;
        this.date = date;
        this.invoice_btn = invoice_btn;
    }

    public String getInvoice_btn() {
        return invoice_btn;
    }

    public void setInvoice_btn(String invoice_btn) {
        this.invoice_btn = invoice_btn;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
