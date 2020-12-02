package se.sysdev.javaeeexamination.dto;

import se.sysdev.javaeeexamination.model.Category;

public class ProductDto {
    private String name;
    private Long price;
    private Category category;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
