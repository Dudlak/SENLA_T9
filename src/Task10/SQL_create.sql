
CREATE DATABASE computer_products_db;

-- Создаем таблицу Product
CREATE TABLE Product (
    maker VARCHAR(50) NOT NULL,
    model VARCHAR(50) PRIMARY KEY,
    type VARCHAR(10) NOT NULL CHECK (type IN ('PC', 'Laptop', 'Printer'))
);

-- Создаем таблицу PC
CREATE TABLE PC (
    code SERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    speed INTEGER NOT NULL CHECK (speed > 0),
    ram INTEGER NOT NULL CHECK (ram > 0),
    hd DECIMAL(5,1) NOT NULL CHECK (hd > 0),
    cd VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) CHECK (price >= 0),
    FOREIGN KEY (model) REFERENCES Product(model) ON DELETE CASCADE
);

-- Создаем таблицу Laptop
CREATE TABLE Laptop (
    code SERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    speed INTEGER NOT NULL CHECK (speed > 0),
    ram INTEGER NOT NULL CHECK (ram > 0),
    hd DECIMAL(5,1) NOT NULL CHECK (hd > 0),
    screen DECIMAL(3,1) NOT NULL CHECK (screen > 0),
    price DECIMAL(10,2) CHECK (price >= 0),
    FOREIGN KEY (model) REFERENCES Product(model) ON DELETE CASCADE
);

-- Создаем таблицу Printer
CREATE TABLE Printer (
    code SERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    color CHAR(1) NOT NULL CHECK (color IN ('y', 'n')),
    type VARCHAR(10) NOT NULL CHECK (type IN ('Laser', 'Jet', 'Matrix')),
    price DECIMAL(10,2) CHECK (price >= 0),
    FOREIGN KEY (model) REFERENCES Product(model) ON DELETE CASCADE
);