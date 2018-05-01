DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts
( contact_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  contact_fname VARCHAR(50) NOT NULL,
  contact_lname VARCHAR(50),
  contact_email VARCHAR(50),
  contact_mobile BIGINT(15),
  contact_city VARCHAR(50),
  created_date DATE,
  CONSTRAINT contacts_pk PRIMARY KEY (contact_id)
);

INSERT INTO contacts
(contact_fname, contact_lname, contact_email, contact_mobile, contact_city, created_date)
VALUES
('sandeep', 'karimsetti', 'sandeepkarimsetti@gmail.com', 9912638661, 'Chennai', CURDATE());

