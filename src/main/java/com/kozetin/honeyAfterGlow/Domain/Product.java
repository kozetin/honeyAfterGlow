package com.kozetin.honeyAfterGlow.Domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String imageSource;
    private String type;
    private Integer price;
    private Set<String> tags;

}
