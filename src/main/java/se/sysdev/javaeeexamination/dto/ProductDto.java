package se.sysdev.javaeeexamination.dto;

import se.sysdev.javaeeexamination.model.Category;

public class ProductDto {
    private String name;
    private String description;
    private String imgName;
    private double price;
    private Category category;

    public ProductDto(String name, String description, String imgName, double price, Category category) {
        this.name = name;
        this.description = description;
        this.imgName = imgName;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgName() {
        return imgName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
