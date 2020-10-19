package booklist.model.bean;

import java.sql.Timestamp;

public class BookList {
	
    protected int bid;
    protected int cid;
    protected String booktitle;
    protected String description;
    protected String author;
    protected Timestamp publisheddate;
    protected String isbn;
    protected float price;
    protected int noofpages;
    protected String search;


    public BookList() {}
    
    public BookList(String booktitle, int cid, String description, String author, Timestamp publisheddate, String isbn, float price, int noofpages) {
        this.cid = cid;
        this.booktitle = booktitle;
        this.description = description;
        this.author = author;
        this.publisheddate = publisheddate;
        this.isbn = isbn;
        this.price = price;
        this.noofpages = noofpages;
        
    }

    public BookList(int bid, String booktitle, int cid, String description, String author, Timestamp publisheddate, String isbn, float price, int noofpages) {
    	this.bid = bid;
        this.cid = cid;
        this.booktitle = booktitle;       
        this.description = description;
        this.author = author;
        this.publisheddate = publisheddate;
        this.isbn = isbn;
        this.price = price;
        this.noofpages = noofpages;
        
    }


    public int getbid() {
        return bid;
    }
    public void setbid(int bid) {
        this.bid = bid;
    }
    
    public int getcid() {
        return cid;
    }
    public void setcid(int cid) {
        this.cid = cid;
    }
    
    public String getbooktitle() {
        return booktitle;
    }
    public void setbooktitle(String booktitle) {
        this.booktitle = booktitle;
    }
    
    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }
    
    public String getauthor() {
        return author;
    }
    public void setauthor(String author) {
        this.author = author;
    }
    
    public Timestamp getpublisheddate() {
        return publisheddate;
    }
    public void setpublisheddate(Timestamp publisheddate) {
        this.publisheddate = publisheddate;
    }
    
    public String getisbn() {
        return isbn;
    }
    public void setisbn(String isbn) {
        this.isbn = isbn;
    }

    public float getprice() {
        return price;
    }
    public void setprice(float price) {
        this.price = price;
    }
    
    public int getnoofpages() {
        return noofpages;
    }
    public void setnoofpages(int noofpages) {
        this.noofpages = noofpages;
    }
    
    public String search() {
    	return search;
    }
    public void setsearch(String search) {
    	this.search=search;
    }

    
}

