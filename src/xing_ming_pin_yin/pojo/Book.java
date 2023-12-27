package xing_ming_pin_yin.pojo;

public class Book {
    private String isbn;
    private String title;
    private String publisher;
    private String language;
    private Double price;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Book() {
    }

    public Book(String isbn, String title, String publisher, String language, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.language = language;
        this.price = price;
    }

    @Override
    public String toString() {
        return isbn+","+title+","+publisher+","+language+","+price;
    }
}
