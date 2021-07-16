INSERT INTO user (id, email, password, name, role) VALUES 
(1, 'gerente@varejonline.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Admin', 'ROLE_ADMIN'),
(2, 'vendedor@varejonline.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'User', 'ROLE_CUSTOMER');

INSERT INTO PRODUCT_CATEGORY (id, NAME) VALUES 
(201, 'Celular'),
(202, 'Impressora'),
(203, 'Notebook'),
(204, 'Monitor');

INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QTY, CATEGORY_ID) VALUES 
(101, 'CELULAR SAMSUNG MS350', '2098.95', 50, 201),
(102, 'HP_IMPRESSORA T566', '348.95', 50, 202),
(103, 'MacAir G34', '9230.95', 50, 203),
(104, 'ALKATEL Q45PL', '1613.95', 50, 201),
(105, 'AOC Z22C', '1999.99', 50, 204);
