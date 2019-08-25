\connect hotelmanagement

CREATE TABLE hotel (
    hotel_id serial NOT NULL,
    name text NOT NULL,
    address text NOT NULL,
    email text,
    num_rooms integer NOT NULL,
    num_available_rooms integer NOT NULL,
    num_stars smallint NOT NULL,
    active boolean DEFAULT true NOT NULL,
	CONSTRAINT hotel_pk PRIMARY KEY (hotel_id)
);

CREATE INDEX hotel_lower_name
    ON hotel (lower(name));

CREATE USER app_user WITH PASSWORD 'app_password';
GRANT SELECT, UPDATE, INSERT ON TABLE hotel TO app_user;
GRANT USAGE, SELECT ON SEQUENCE hotel_hotel_id_seq TO app_user;

INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Alaska Island Resort', '777 Brockton Avenue', NULL, 250, 20, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Coast Hotels', '30 Memorial Drive', NULL, 130, 10, 3, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Candlewood Suites', '250 Hartford Avenue', 'info@candlewood.com', 200, 20, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Hilltop Heaven Resort', '700 Oak Street', NULL, 550, 130, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Mandarin Oriental', '66-4 Parkhurst', NULL, 102, 40, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Southern Hospitality', '137 Teaticket Hwy', NULL, 630, 200, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('The Sebastian Vail', '42 Fairhaven Commons Way', '', 450, 230, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Cape Grace', '374 William S Canning Blvd', 'desk@capegrace.com', 131, 100, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Eagles Watch Resort', '121 Worcester Rd', NULL, 98, 23, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Blooming Bed And Breakfast', '677 Timpany Blvd', NULL, 321, 140, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Jade Mountain', '337 Russell St', NULL, 456, 63, 3, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Hospitable Hotel', '295 Plymouth Street', NULL, 621, 78, 3, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('The River Front Resort', '1775 Washington St', NULL, 423, 98, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Parkview Hotel', '280 Washington Street', NULL, 111, 78, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Mint Hotel', '20 Soojian Dr', 'info@mint.com', 230, 45, 3, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Radisson Hotels & Resorts', '11 Jungle Road', 'contact@radison.com', 333, 21, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Glades Hotel', '301 Massachusetts Ave', NULL, 79, 2, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Aston Hotels & Resorts', '506 State Road', NULL, 85, 31, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('The Prince George Hotel', '742 Main Street', NULL, 222, 100, 4, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Luma Hotel', '200 Otis Street', 'info@luma.com', 54, 20, 5, true);
INSERT INTO hotel (name, address, email, num_rooms, num_available_rooms, num_stars, active) VALUES ('Royal Tulip Luxury Hotels', '180 North King Street', NULL, 77, 42, 5, true);

