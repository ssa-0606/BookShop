package com.imikasa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Mikasa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private Long id;
    private String name;
    private String author;
    private String cover;
    private String price;
    private Integer categoryId;
    private Integer bookInfoId;

}
