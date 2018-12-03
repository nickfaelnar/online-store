-- SQLite3 Database 
-- Database creation is upon entering sqlite. Unable to include it here. 
-- sqlite3 FAELNAR.db  
-- Create Statements 
CREATE TABLE IF NOT EXISTS user 
  ( 
     user_id     INTEGER PRIMARY KEY auto_increment, 
     username    VARCHAR(10) NOT NULL UNIQUE, 
     password    VARCHAR(50) NOT NULL, 
     first_name  VARCHAR(20) NOT NULL, 
     middle_name VARCHAR(20) NOT NULL, 
     last_name   VARCHAR(20) NOT NULL, 
     cellular    VARCHAR(11) NOT NULL, 
     birth_date  TEXT NOT NULL, 
     email_add   VARCHAR(20) NOT NULL 
  ); 

CREATE TABLE IF NOT EXISTS credit_card 
  ( 
     card_id  INTEGER PRIMARY KEY auto_increment, 
     user_id  INTEGER, 
     card_num VARCHAR(20) NOT NULL, 
     exp_date TEXT NOT NULL, 
     UNIQUE(user_id, card_num), 
     FOREIGN KEY(user_id) REFERENCES user(user_id) 
  ); 

CREATE TABLE IF NOT EXISTS address 
  ( 
     address_id    INTEGER PRIMARY KEY auto_increment, 
     user_id       INTEGER, 
     house_bldg_st VARCHAR(20) NOT NULL, 
     brgy          VARCHAR(20) NOT NULL, 
     city          VARCHAR(20) NOT NULL, 
     prov          VARCHAR(20) NOT NULL, 
     FOREIGN KEY(user_id) REFERENCES user(user_id) 
  ); 

CREATE TABLE IF NOT EXISTS store 
  ( 
     store_id    INTEGER PRIMARY KEY auto_increment, 
     name        VARCHAR(20), 
     description VARCHAR(100) 
  ); 

CREATE TABLE IF NOT EXISTS item 
  ( 
     item_id     INTEGER PRIMARY KEY auto_increment, 
     store_id    INTEGER, 
     name        VARCHAR(20) NOT NULL, 
     description VARCHAR(100) NOT NULL, 
     price       DECIMAL(13, 2), 
     FOREIGN KEY(store_id) REFERENCES store(store_id) 
  ); 

CREATE TABLE IF NOT EXISTS orders 
  ( 
     order_id      INTEGER PRIMARY KEY auto_increment, 
     user_id       INTEGER NOT NULL, 
     status        INTEGER NOT NULL, 
     card_id       INTEGER, 
     address_id    INTEGER, 
     purchase_date DATE, 
     FOREIGN KEY(user_id) REFERENCES user(user_id), 
     FOREIGN KEY(card_id) REFERENCES credit_card(card_id), 
     FOREIGN KEY(address_id) REFERENCES address(address_id) 
  ); 

CREATE TABLE IF NOT EXISTS order_items 
  ( 
     order_item_id INTEGER PRIMARY KEY auto_increment, 
     order_id      INTEGER, 
     item_id       INTEGER, 
     FOREIGN KEY(order_id) REFERENCES orders(order_id), 
     FOREIGN KEY(item_id ) REFERENCES item(item_id) 
  ); 

-- Populate Fields 
INSERT INTO user 
            (username, 
             password, 
             first_name, 
             middle_name, 
             last_name, 
             cellular, 
             birth_date, 
             email_add) 
VALUES      ('juandc', 
             Sha1('passw0rd'), 
             'Juan', 
             'Santiago', 
             'Dela Cruz', 
             9171111111, 
             '1990-01-01', 
             'juan@gogol.com'); 

INSERT INTO credit_card 
            (user_id, 
             card_num, 
             exp_date) 
VALUES      (1, 
             123456789, 
             '2020-01-01'); 

INSERT INTO address 
            (user_id, 
             house_bldg_st, 
             brgy, 
             city, 
             prov) 
VALUES      (1, 
             '123456 D Mansion', 
             'Sampaguita', 
             'Quezon City', 
             'NCR'); 

INSERT INTO store 
            (name, 
             description) 
VALUES      ('YG Mall', 
             'One-stop shop for all your needs.'), 
            ('Galaw Sports', 
             'For your healthy-living lifestyle'); 

INSERT INTO item 
            (store_id, 
             name, 
             description, 
             price) 
VALUES      (1, 
             'Boxing Gloves', 
             'Boxing Equipment', 
             600.00), 
            (1, 
             'Mitts', 
             'For Outdoor Activity', 
             750.00), 
            (1, 
             'Desk Lamp', 
             'Standard Desk Lamp', 
             250.00), 
            (2, 
             'Lionsdale Gloves', 
             'Boxing Equipment', 
             1000.00), 
            (2, 
             'Hands Wrap', 
             'For Sport & Outdoor Activity', 
             150.00); 