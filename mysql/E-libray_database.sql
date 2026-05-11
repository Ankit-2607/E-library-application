CREATE TABLE books (

    id INT PRIMARY KEY AUTO_INCREMENT,

    title VARCHAR(255),

    author VARCHAR(255),

    price VARCHAR(50),

    image VARCHAR(255),

    description TEXT,

    category VARCHAR(100),

    pdf VARCHAR(255)
);


INSERT INTO books
(title, author, price, image, description, category, pdf)

VALUES

(
'Atomic Habits',
'James Clear',
'$19.99',
'/assets/books/atomichabits.jpg',
'Tiny changes, remarkable results.',
'Self Help',
'atomichabits.pdf'
),

(
'Sapiens',
'Yuval Noah Harari',
'$24.99',
'/assets/books/sapiens.jpg',
'History of humankind.',
'History',
'sapiens.pdf'
),

(
'Dune',
'Frank Herbert',
'$29.99',
'/assets/books/dune.jpg',
'Epic science fiction novel.',
'Science Fiction',
'dune.pdf'
),

(
'Harry Potter',
'J.K. Rowling',
'$18.99',
'/assets/books/harrypotter.jpg',
'Wizarding world adventure.',
'Fantasy',
'harrypotter.pdf'
),

(
'The Alchemist',
'Paulo Coelho',
'$14.99',
'/assets/books/alchemist.jpg',
'Journey of destiny.',
'Self Help',
'alchemist.pdf'
),

(
'Rich Dad Poor Dad',
'Robert Kiyosaki',
'$21.99',
'/assets/books/richdadpoordad.jpg',
'Financial education book.',
'Finance',
'richdadpoordad.pdf'
);

use bibliox;

ALTER TABLE users
ADD phone VARCHAR(20),
ADD profile_image VARCHAR(255);

SELECT * FROM reading_progress;


ALTER TABLE users
ADD created_at TIMESTAMP
DEFAULT CURRENT_TIMESTAMP;

SHOW COLUMNS FROM users;