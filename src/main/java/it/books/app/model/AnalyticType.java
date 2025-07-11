package it.books.app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "analytic_types")
public class AnalyticType {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // name
    @NotBlank(message = "Analytic Type name empty.")
    @Column(name = "type_name", nullable = false, unique = true, columnDefinition = "VARCHAR(30)")
    private String name;

    // text template
    @NotBlank(message = "Analytic text template empty.")
    @Column(name = "text_template", nullable = false, columnDefinition = "tinytext")
    private String textTemplate;

    // FKs
    // analytics OtM
    @OneToMany(mappedBy = "analyticType")
    private List<Analytic> analyticId;

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextTemplate() {
        return textTemplate;
    }

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }

    public List<Analytic> getAnalyticId() {
        return analyticId;
    }

    public void setAnalyticId(List<Analytic> analyticId) {
        this.analyticId = analyticId;
    }

}
