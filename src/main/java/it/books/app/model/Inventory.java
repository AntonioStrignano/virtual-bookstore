package it.books.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //book
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    //price
    @Column(name = "price", nullable = false, columnDefinition = "decimal(5,2)")
    private Double price;

    //quantity
    @Column(name = "quantity", nullable = false, columnDefinition = "int(5)")
    private Integer quantity;

    //warehouse_location
    @Column(name = "wh_location", nullable = true, columnDefinition = "varchar(40)")
    private String warehouseLocation;

    //notes
    @Column(name = "notes", nullable = true, columnDefinition = "varchar(200)")
    private String notes;

    //status FK
    //discount FK
}
