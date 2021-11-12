CREATE SEQUENCE lcat_seq;
CREATE TABLE lcat_price (id integer NOT NULL DEFAULT nextval('lcat_seq'));
ALTER SEQUENCE lcat_seq OWNED BY lcat_price.id;