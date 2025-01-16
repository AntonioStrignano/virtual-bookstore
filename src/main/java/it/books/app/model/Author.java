package it.books.app.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false, columnDefinition = "tinytext")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "tinytext")
    private String lastName;

    @Column(name = "birth_date", nullable = false, columnDefinition = "date")
    private LocalDate birthDate;

    @Column(name = "death_date", columnDefinition = "date")
    private LocalDate deathDate;

    @Column(name = "nationality", nullable = false, columnDefinition = "varchar(30)")
    private String nationality;

    @Column(name = "bio", columnDefinition = "tinytext")
    private String bio;

    @Column(name = "website", columnDefinition = "mediumtext")
    private String website;

    @Column(name = "pro_pic", columnDefinition = "mediumtext")
    private String profilePicture;

    @Column(name = "social_media", columnDefinition = "mediumtext")
    private String socialMedia;

    @Column(name = "is_active", nullable = false, columnDefinition = "bool")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "main_genre")
    private Genre mainGenre;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    // ----------------------------
    // ----- GETTERS & SETTERS ----
    // ----------------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Genre getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(Genre mainGenre) {
        this.mainGenre = mainGenre;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
