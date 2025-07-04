This project is a Java application to manage a small library catalogue, which includes books and magazines, and to keep track of loans to users.

It was developed using Maven - IntelliJ and manages data persistence on a PostgreSQL database via JPA (with Hibernate).

Database Structure (ER Diagram)
The file "er_diagram.png" is an image showing the tables and how they are connected to each other.

In the project, both Books and Magazines are considered "Catalogue Items". To handle this situation, I chose to use an inheritance strategy "JOINED".

This was the best choice for two key reasons:

1. This strategy creates a clean and tidy Database.
One table for the parent, and one for every child, instead of one "SINGLE-TABLE" with lots of empty columns (like an "author" column for a magazine), it creates a central "catalogue_items" table for shared info and separate, smaller tables for specific details ("books" and "magazines").

2.It makes relationships simple.
This was the most important reason. It allows the "loans" table to connect to a generic "Catalogue Item" with a single, clean link. This way, I don't have to worry if a loan is for a book or a magazine, the database structure handles it perfectly. With other strategies like "TABLE_PER_CLASS", this would not be possible because there would be no central "catalog_items" table to link to.

(to continue later)