package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@JsonPropertyOrder({ "categoryId", "name", "description" })
@Table(name = "categories")
public class Category extends Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Category()
    {
    }

    public Category(@JsonProperty("categoryId") int categoryId,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description)
    {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
