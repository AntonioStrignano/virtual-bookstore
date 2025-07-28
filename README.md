# virtual-bookstore

1. Virtual Bookstore
Develop a user-friendly and efficient virtual platform where book admirers can explore, select and purchase books. This platform will replicate the experience of visiting a physical bookstore in a digital environment, it will give an engaging space where users can have a Digital Book Exploration, Easy selection of books as per their interest they can have detailed book information, and can have a hassle-free shopping of their choice.

The Virtual Bookstore project aims to replicate the experience of browsing and buying books online by taking more than enough information about the particular book, this process of exploring and purchasing books online will give a feel to users just as one would in a physical bookstore. Users will search for books, add them to their shopping cart, and will proceed to checkout. This application provides an engaging and user-friendly interface for bookaholics.

Recommendation System: Implement a book recommendation engine based on user preferences, previous purchases, or ratings. You can use simple algorithms like collaborative filtering or even machine learning models for better recommendations.
User Reviews and Ratings: Allow users to rate books and write reviews. This will add an extra layer of engagement to the platform.
Payment Integration: Add integration with payment gateways (e.g., Stripe, PayPal) to make the purchase process more realistic.

Techstack: Spring boot, Spring Security, Spring Data JPA 

CRUDs to do
author (filtered GETs)
genres (setup blank defasulkt genre when deleted for artists and books)

author (if delete author, delete author record in every book)
genres (if delete genre, drop to a default blank genre)
book collections still to implements

USER LOGIC
controllers to do:
analytics


todo:
if delete customer
set customer reviews/orders as "delete user" name

set review logic linked to users

set bookstore role in user

inventory detail page

cart checkout

notification logics in corrispectives controllers

implement "add to cart" in book details

test:
delete users and go around

manage search history as analytics


customer side:
read book list
read book details

admin side:
see analytics
