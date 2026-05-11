package models;

public class Book {

    // =========================================
    // VARIABLES
    // =========================================

    private String title;

    private String author;

    private String price;

    private String image;

    private String description;

    private String category;

    private String pdf;

    private int id;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Book(

            String title,

            String author,

            String price,

            String image,

            String description,

            String category,

            String pdf) {

        this.title = title;

        this.author = author;

        this.price = price;

        this.image = image;

        this.description = description;

        this.category = category;

        this.pdf = pdf;
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getTitle() {

        return title;
    }

    public String getAuthor() {

        return author;
    }

    public String getPrice() {

        return price;
    }

    public String getImage() {

        return image;
    }

    public String getDescription() {

        return description;
    }

    public String getCategory() {

        return category;
    }

    public String getPdf() {

        return pdf;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}