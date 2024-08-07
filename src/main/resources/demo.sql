-- Delete if exist
DROP DATABASE IF EXISTS demo;

-- Create database
CREATE DATABASE demo
	ENCODING = 'UTF8'
	LOCALE_PROVIDER = 'libc'
	CONNECTION LIMIT = -1
	IS_TEMPLATE = False;

-- Use database
USE demo;

DROP TABLE users;

-- Profile
CREATE TABLE IF NOT EXISTS profile (
									   profile_id UUID DEFAULT GEN_RANDOM_UUID(),
	profile_name VARCHAR(20) NOT NULL,
	CONSTRAINT uk_profile_name UNIQUE (profile_name),
	CONSTRAINT pk_profile PRIMARY KEY (profile_id)
	);

-- Users
CREATE TABLE IF NOT EXISTS users (
									 user_id UUID DEFAULT GEN_RANDOM_UUID(),
	username VARCHAR(20) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(60) NOT NULL,
	password VARCHAR(32) NOT NULL,
	profile_id UUID NOT NULL,
	CONSTRAINT uk_username UNIQUE (username),
	CONSTRAINT pk_users PRIMARY KEY (user_id),
	CONSTRAINT fk_users_profile FOREIGN KEY (profile_id) REFERENCES profile (profile_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT
	);

-- Providers
CREATE TABLE IF NOT EXISTS provider (
										provider_id UUID DEFAULT GEN_RANDOM_UUID(),
	identification VARCHAR(20) NOT NULL,
	full_name VARCHAR(100) NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	CONSTRAINT uk_id_provider UNIQUE (identification),
	CONSTRAINT pk_provider PRIMARY KEY (provider_id)
	);

-- ALTER TABLE provider RENAME fullname TO full_name;

-- Clients
CREATE TABLE IF NOT EXISTS client (
									  client_id UUID DEFAULT GEN_RANDOM_UUID(),
	identification VARCHAR(20) NOT NULL,
	full_name VARCHAR(100) NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	CONSTRAINT uk_id_client UNIQUE (identification),
	CONSTRAINT pk_client PRIMARY KEY (client_id)
	);

-- ALTER TABLE client RENAME fullname TO full_name;

-- Products
CREATE TABLE product (
						 product_id UUID DEFAULT GEN_RANDOM_UUID(),
						 product_name VARCHAR(20) NOT NULL,
						 stock SMALLINT DEFAULT 0,
						 price DECIMAL DEFAULT 0,
						 description VARCHAR(100) DEFAULT '',
						 user_id UUID NOT NULL,
						 CONSTRAINT uk_product_name UNIQUE (product_name),
						 CONSTRAINT pk_product PRIMARY KEY (product_id),
						 CONSTRAINT fk_product_users FOREIGN KEY (user_id) REFERENCES users (user_id)
							 ON UPDATE RESTRICT ON DELETE RESTRICT,
						 CONSTRAINT ck_prod_stock CHECK (stock > 0), -- verifica que cantidad sea mayor a 0
						 CONSTRAINT ck_prod_price CHECK (price > 0) -- verifica que precio sea mayor a 0
);

-- Table <Shopping>
CREATE TABLE IF NOT EXISTS shopping (
										shopping_id UUID DEFAULT GEN_RANDOM_UUID(),
	shopping_date DATE DEFAULT NOW() NOT NULL,
	provider_id UUID NOT NULL,
	product_id UUID NOT NULL,
	amount SMALLINT NOT NULL,
	total DECIMAL NOT NULL,
	user_id UUID NOT NULL,
	CONSTRAINT pk_shopping PRIMARY KEY (shopping_id),
	CONSTRAINT fk_shopping_provider FOREIGN KEY (provider_id) REFERENCES provider (provider_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT fk_shopping_product FOREIGN KEY (product_id) REFERENCES product (product_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT ck_shopping_amount CHECK (amount > 0),
	CONSTRAINT ck_shopping_total CHECK (total > 0),
	CONSTRAINT fk_shopping_user FOREIGN KEY (user_id) REFERENCES users (user_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT
	);

-- Table <Sale>sale
CREATE TABLE IF NOT EXISTS sale (
									sale_id UUID DEFAULT GEN_RANDOM_UUID(),
	sale_date DATE DEFAULT NOW() NOT NULL,
	client_id UUID NOT NULL,
	product_id UUID NOT NULL,
	amount SMALLINT NOT NULL,
	total DECIMAL NOT NULL,
	user_id UUID NOT NULL,
	CONSTRAINT pk_sale PRIMARY KEY (sale_id),
	CONSTRAINT fk_sale_client FOREIGN KEY (client_id) REFERENCES client (client_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT fk_sale_product FOREIGN KEY (product_id) REFERENCES product (product_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT ck_sale_amount CHECK (amount > 0),
	CONSTRAINT ck_sale_total CHECK (total > 0),
	CONSTRAINT fk_sale_user FOREIGN KEY (user_id) REFERENCES users (user_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT
	);

-- Table <Audit>
CREATE TABLE IF NOT EXISTS audit (
									 audit_id UUID DEFAULT GEN_RANDOM_UUID(),
	audit_date TIMESTAMP NOT NULL DEFAULT NOW(),
	user_id UUID NOT NULL,
	action_performed VARCHAR(20) NOT NULL,
	table_name VARCHAR(20) NOT NULL,
	previous JSON NOT NULL,
	new JSON,
	CONSTRAINT pk_audit PRIMARY KEY (audit_id),
	CONSTRAINT fk_audit_users FOREIGN KEY (user_id) REFERENCES users (user_id)
	ON UPDATE RESTRICT ON DELETE RESTRICT
	);



-- Insertion
INSERT INTO profile (profile_name) VALUES ('Administrador'), ('Cajero');
INSERT INTO profile (profile_name) VALUES ('Contador');

SELECT * FROM profile;

INSERT INTO users (username, first_name, last_name, password, profile_id)
VALUES
	('alozada', 'Alex', 'Lozada', MD5('lozada123+'), '9d4c0c03-2e31-4f8a-b941-bc4c29d93383'),
	('pperez', 'Pedro', 'Perez', MD5('perez123+'), '1fb47e89-966c-4f4c-9d67-285a88af3f2b');

INSERT INTO users (username, first_name, last_name, password, profile_id)
VALUES ('puri', 'Pedro', 'Purihuaman', MD5('puri123+'), '81dd51a1-4dd6-4d7f-ad13-d05f5740acbe');

SELECT * FROM users;


INSERT INTO product (product_name, stock, price, user_id)
VALUES ('Nevera', 5, 12000, '25ae5615-08b5-4d4a-b3fc-532d5c69a342');

INSERT INTO product (product_name, stock, price, user_id)
VALUES
	('Lavadora', 1, 8900, '505b5401-18d5-4926-92d7-5b11d4727468'),
	('Secadora', 3, 7400, '7c12c006-f986-45d3-9242-435da7a92343'),
	('Calentador', 1, 3200, '505b5401-18d5-4926-92d7-5b11d4727468');


SELECT * FROM product;

INSERT INTO provider (identification, full_name, address, phone)
VALUES
	('123456789', 'Proveedor LTDA', 'Cra 1 #2 - 2', '2114477 EXT 123'),
	('987654321', 'Proveedor SAC', 'Cra. 20 MZ. H', '+51 986734521');

SELECT * FROM provider;

INSERT INTO client (identification, full_name, address, phone)
VALUES
	('987654321', 'Compratodo S.A.S', 'Av Busquela cra Encuentrela', '948579651'),
	('741852963', 'Cliente Frecuente', 'El vecino', '954784140');

SELECT * FROM client;


-- Functions <Stored Procedure>


-- Function <authentication>
CREATE OR REPLACE FUNCTION authentication (_username CHARACTER, _password CHARACTER)
RETURNS TABLE (
	user_id UUID,
	username VARCHAR,
	first_name VARCHAR,
	last_name VARCHAR,
	profile_id UUID,
	profile_name VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT us.user_id, us.username, us.first_name, us.last_name, prof.profile_id, prof.profile_name
FROM users AS us
		 NATURAL JOIN profile AS prof
WHERE us.username = _username AND us.password = MD5(_password);
IF NOT FOUND THEN
		RAISE EXCEPTION 'El usuario o la contraseña con coinciden';
END IF;
END $$;


ALTER FUNCTION authentication(_username CHARACTER, _password CHARACTER) OWNER TO facturationsystem;

SELECT user_id, first_name, last_name, profile_id, profile_name FROM authentication('pperez', 'perez123+');
SELECT user_id, first_name, last_name, profile_id, profile_name FROM authentication('purii', 'perez123+');

-- Function <consult_product>
CREATE OR REPLACE FUNCTION consult_product()
RETURNS SETOF product
LANGUAGE plpgsql
AS $$
BEGIN
RETURN QUERY
SELECT product_id, product_name, stock, price, description, user_id
FROM product ORDER BY product_name;
END $$;

ALTER FUNCTION consult_product() OWNER TO facturationsystem;

SELECT * FROM product;
-- ALTER TABLE products RENAME amount TO stock;

-- Function <consult_providers>
CREATE OR REPLACE FUNCTION consult_provider()
RETURNS SETOF provider
LANGUAGE plpgsql
AS $$ -- RETURNS <VOID, SETOF(conjunto)>
BEGIN
RETURN QUERY
SELECT provider_id, identification, full_name, address, phone
FROM provider ORDER BY full_name;
END $$;


ALTER FUNCTION consult_provider() OWNER TO facturationsystem;

SELECT * FROM provider;
SELECT provider_id, identification, full_name, address, phone
FROM consult_provider();


-- Function <consult_clients>
CREATE OR REPLACE FUNCTION consult_client()
RETURNS SETOF client
LANGUAGE plpgsql
AS $$ -- RETURNS <VOID, SETOF(conjunto)>
BEGIN
RETURN QUERY
SELECT client_id, identification, full_name, address, phone
FROM client ORDER BY full_name;
END $$;


ALTER FUNCTION consult_client() OWNER TO facturationsystem;

SELECT client_id, identification, full_name, address, phone
FROM consult_client();


-- Triggers

-- Function Trigger audit products
CREATE OR REPLACE FUNCTION tg_product_audit ()
RETURNS TRIGGER
LANGUAGE plpgsql AS $$
BEGIN
	IF TG_OP = 'UPDATE' THEN
		INSERT INTO audit(user_id, action_performed, table_name, previous, new)
SELECT NEW.user_id, 'ACTUALIZAR', 'PRODUCTO', row_to_json(OLD.*), row_to_json(NEW.*);
END IF;
RETURN NEW;
END $$;

-- Product audit trigger
CREATE TRIGGER tg_product_audit
	AFTER UPDATE ON product
	FOR EACH ROW EXECUTE PROCEDURE tg_product_audit();



-- Function Trigger audit shopping
CREATE OR REPLACE FUNCTION tg_shopping_audit()
RETURNS TRIGGER
LANGUAGE plpgsql AS $$
BEGIN
	IF TG_OP = 'INSERT' THEN
		INSERT INTO audit(user_id, action_performed, table_name, previous, new)
SELECT NEW.user_id, 'INSERTAR', 'COMPRAS', row_to_json(NEW.*), NULL;
END IF;
RETURN NEW;
END $$;

-- Shpping audit trigger
CREATE TRIGGER tg_shopping_audit
	AFTER UPDATE ON shopping
	FOR EACH ROW EXECUTE PROCEDURE tg_shopping_audit();



-- Function <shopping>
CREATE OR REPLACE FUNCTION buy (
	_provider_id UUID,
	_product_id UUID,
	_amount SMALLINT,
	_price DECIMAL,
	_user_id UUID
)
RETURNS UUID
LANGUAGE plpgsql AS $$
DECLARE _billId UUID;
BEGIN
	-- Se inserta el registro de la compra
INSERT INTO shopping(provider_id, product_id, amount, total, user_id)
VALUES (_provider_id, _product_id, _amount, _price, _user_id)
	RETURNING shopping_id INTO _bill_id; -- retorna el id_compra y lo almacena en idFactura

IF FOUND THEN
		-- Se actualiza el stock de productos
UPDATE product
SET stock = stock + _amount, price = _price, user_id = _user_id
WHERE product_id = _product_id;
ELSE
		RAISE EXCEPTION 'No fue posible insertar el registro de la compra';
END IF;
RETURN _billId;
END $$;

select * from product;

-- Function <consult_shopping>
CREATE OR REPLACE FUNCTION consult_shopping(_limit SMALLINT, _page SMALLINT)
RETURNS TABLE (
	shopping_id UUID,
	shopping_date DATE,
	provider CHARACTER VARYING,
	product CHARACTER VARYING,
	amount SMALLINT,
	total DECIMAL
)
LANGUAGE plpgsql AS $$
DECLARE
_start SMALLINT;
BEGIN
	_start = _limit * _page - _limit;
RETURN QUERY
SELECT shop.shopping_id, shop.shopping_date, prov.full_name AS provider, prod.product_name AS product, shop.amount, shop.total
FROM shopping AS shop INNER JOIN provider AS prov
								 ON shop.provider_id = prov.provider_id
					  INNER JOIN product AS prod
								 ON shop.product_id = prod.product_id
	LIMIT _limit
OFFSET _start;
END $$;



-- Function Trigger audit sale
CREATE OR REPLACE FUNCTION tg_sale_audit()
RETURNS TRIGGER
LANGUAGE plpgsql AS $$
BEGIN
	IF TG_OP = 'INSERT' THEN
		INSERT INTO audit(user_id, action_performed, table_name, previous, new)
SELECT NEW.user_id, 'INSERTAR', 'VENTAS', row_to_json(NEW.*), NULL;
END IF;
RETURN NEW;
END $$;

-- Sale audit trigger
CREATE TRIGGER tg_sale_audit
	AFTER INSERT ON sale
	FOR EACH ROW EXECUTE PROCEDURE tg_sale_audit();

select * from audit;

-- Function <sell> (vender)
CREATE OR REPLACE FUNCTION sell(
	_client_id UUID,
	_product_id UUID,
	_amount SMALLINT,
	_user_id UUID
)
RETURNS UUID
LANGUAGE plpgsql AS $$
DECLARE
_total DECIMAL; _stock SMALLINT; _billId UUID;
BEGIN
	-- Se busca el precio de venta y se valida si hay stock de ventas
SELECT (prod.price * 1.3), prod.stock
INTO STRICT _total, _stock
FROM product AS prod WHERE prod.product_id = _product_id;

-- Si hay suficiente stock, se vender
IF _stock >= _amount THEN
		INSERT INTO sale(client_id, product_id, amount, total, user_id)
		VALUES (_client_id, _product_id, _amount, _total, _user_id)
		RETURNING sale_id INTO _billId;

		IF FOUND THEN
			-- Se actualiza el stock del producto
UPDATE product
SET stock = stock - _amount, user_id = _user_id
WHERE product_id = _product_id;
ELSE
			RAISE EXCEPTION 'No fue posible insertar el registro de la venta';
END IF;
ELSE
		RAISE EXCEPTION 'No hay suficiente stock para la venta %', _stock;
END IF;
RETURN _billId;

EXCEPTION
	WHEN NO_DATA_FOUND THEN
		RAISE EXCEPTION 'No se encontró el producto a vender';
END $$;

select * from sale;


-- Function <consult_sale>
CREATE OR REPLACE FUNCTION consult_sale(_limit SMALLINT, _page SMALLINT)
RETURNS TABLE (
	sale_id UUID,
	sale_date DATE,
	client CHARACTER VARYING,
	product CHARACTER VARYING,
	amount SMALLINT,
	total DECIMAL
)
LANGUAGE plpgsql AS $$
DECLARE
_start SMALLINT;
BEGIN
	_start = _limit * _page - _limit;
RETURN QUERY SELECT sal.sale_id, sal.sale_date, cli.full_name AS client, prod.product_name AS product, sal.amount, sal.total
	FROM sale AS sal INNER JOIN client AS cli
		ON sal.client_id = cli.client_id
		INNER JOIN product AS prod
		ON sal.product_id = prod.product_id
	LIMIT _limit
	OFFSET _start;
END $$;

select * from sale;

-- Function <consult_inventory>
CREATE OR REPLACE FUNCTION consult_inventory(_limit SMALLINT, _page SMALLINT)
RETURNS SETOF products
LANGUAGE plpgsql AS $$
DECLARE
_start SMALLINT;
BEGIN
	_start = _limit * _page - _limit;
RETURN QUERY SELECT product_id, product_name, amount, price, user_id
	FROM product
	ORDER BY product_id
	LIMIT _limit
	OFFSET _start;
END $$;




-- Test functions

-- Llamamos a las funciones con un select cuando devuelven un solo valor
-- Enviamos los valores de esa manera para castear los tipos, si no postgres lo haría su tipo principal
SELECT buy(
			   '9f57a9be-d936-4d3d-959c-1179c75f1c1e',
			   '1039ae51-30f9-4bde-b62f-06ecbd58dcbc',
			   1::SMALLINT,
			   13500,
			   'e4b5e523-e8bc-4227-823c-bdbcc4319bb0'
	   );



SELECT sell(
			   '6c9570b3-4a54-45cf-bd52-454ec473fd33',
			   'ac43b581-57bc-47ce-90c7-b6d65899c72d',
			   2::SMALLINT,
			   'bab98249-05e0-4776-ab58-5911a9286a47'
	   );




SELECT * FROM providers;
SELECT * FROM clients;
SELECT * FROM sale;
SELECT * FROM products;
SELECT * FROM users;
SELECT * FROM auditorias;
SELECT * FROM shopping;

SELECT * FROM consult_sale(10::SMALLINT, 1::SMALLINT);
SELECT consult_products();
SELECT * FROM consult_clients();
SELECT * FROM consult_shopping(10::SMALLINT, 1::SMALLINT);








