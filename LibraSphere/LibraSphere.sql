-- LibraSphere is a sphere or domain dedicated to books, literature, or libraries.


-- Set the character set and collation
SET NAMES utf8mb4;
SET CHARSET utf8mb4;

-- Drop the database if it exists
DROP DATABASE IF EXISTS libraSphere;

-- Create the database with utf8mb4 character set
CREATE DATABASE libraSphere CHARSET utf8mb4;
USE libraSphere;

-- Create the authors table
CREATE TABLE authors(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    yearOfBirth INT,
    nationality VARCHAR(45),
    gender ENUM('MAN', 'WOMAN'),
    biography TEXT
);


-- Create the awards table with a foreign key referencing authors
CREATE TABLE awards (
    authorId INT UNSIGNED,
    name VARCHAR(45),
    year INT,
    FOREIGN KEY (authorId) REFERENCES authors(id)
);

-- Create the members table
CREATE TABLE members(
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    dateOfBirth DATE,
    gender ENUM('MAN', 'WOMAN'),
    street VARCHAR(45),
    houseNumber VARCHAR(45),
    municipality VARCHAR(45),
    postcode INT,
    phoneNumber VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    membershipDate DATE NOT NULL
);

-- Create the books table
CREATE TABLE books (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(45),
    isbn VARCHAR(45) UNIQUE NOT NULL,
    genre ENUM(
    'ADVENTURE', 'BIOGRAPHY', 'CLASSICS', 'COMIC_GRAPHIC_NOVEL', 'CRIME_DETECTIVE', 
    'DRAMA', 'FANTASY', 'HISTORICAL_FICTION', 'HORROR', 'LITERARY_FICTION', 
    'MYSTERY', 'NON_FICTION', 'POETRY', 'ROMANCE', 'SCIENCE_FICTION', 
    'SHORT_STORY', 'SUSPENSE_THRILLER', 'YOUNG_ADULT', 'CHILDREN', 
    'SELF_HELP', 'TRAVEL', 'COOKBOOKS', 'MEMOIR', 'RELIGION_SPIRITUALITY', 
    'SCIENCE', 'HISTORY', 'PHILOSOPHY', 'ART', 'MUSIC', 
    'REFERENCE', 'TRUE_CRIME', 'EDUCATION', 'HEALTH', 'SPORTS', 
    'ANTHOLOGY', 'ESSAYS', 'FAIRY_TALES', 'SATIRE', 'TECHNOLOGY'
       ) NOT NULL,
    publicationDate DATE
);

-- Create the booksAuthors table with a composite primary key and foreign keys
CREATE TABLE booksAuthors (
    bookId INT UNSIGNED,
    authorId INT UNSIGNED,
    FOREIGN KEY (bookId) REFERENCES books(id),
    FOREIGN KEY (authorId) REFERENCES authors(id)
);

-- Create the copies table with foreign key referencing books
CREATE TABLE copies (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    bookId INT UNSIGNED,
    available BOOLEAN,

    -- attributes For PhysicalBook
    barcode VARCHAR(45) UNIQUE,
    location VARCHAR(45),
    shelfNumber INT,

    -- attributes For EBook
    fileHash VARCHAR(45) UNIQUE,
    fileFormat VARCHAR(45),
    visitLink VARCHAR(255),

    type ENUM('PhysicalCopy', 'DigitalCopy') NOT NULL,
    FOREIGN KEY (bookId) REFERENCES books(id)
);

-- Create the borrowedCopies table with composite primary key and foreign keys
CREATE TABLE borrowedCopies (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    memberId INT UNSIGNED,
    copyId INT UNSIGNED,
    borrowDate DATE,
    returnDate DATE,
    FOREIGN KEY (memberId) REFERENCES members(id),
    FOREIGN KEY (copyId) REFERENCES copies(id)
);





-- Create the user if it doesn't exist
CREATE USER IF NOT EXISTS student IDENTIFIED BY 'student';

-- Grant privileges to the user on all tables in the database
GRANT SELECT, INSERT, UPDATE, DELETE ON libraSphere.* TO 'student';

-- Apply the changes
FLUSH PRIVILEGES;

