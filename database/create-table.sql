use daily_new;

CREATE TABLE role (
	id bigint PRIMARY KEY auto_increment NOT NULL,
	role_name varchar(255) NULL,
	code varchar(255) NULL,
	createdby varchar(255) NULL,
	createddate TIMESTAMP NULL,
	modifiedby varchar(255) NULL,
	modifieddate TIMESTAMP NULL
);

CREATE TABLE user (
	id bigint PRIMARY KEY auto_increment NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    fullname varchar(255) NULL,
    status int NOT NULL,
    role_id bigint NOT NULL,
    createdby varchar(255) NULL,
	createddate TIMESTAMP NULL,
	modifiedby varchar(255) NULL,
	modifieddate TIMESTAMP NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role(id);

CREATE TABLE article (
	id bigint PRIMARY KEY auto_increment NOT NULL,
    title varchar(255) NULL,
    thumbnail varchar(255) NULL,
    content TEXT NULL,
    user_id bigint NOT NULL,
    category_id bigint NOT NULL,
    createdby varchar(255) NULL,
	createddate TIMESTAMP NULL,
	modifiedby varchar(255) NULL,
	modifieddate TIMESTAMP NULL
);

ALTER TABLE article ADD CONSTRAINT fk_article_user FOREIGN KEY (user_id) REFERENCES user(id);


CREATE TABLE category (
	id bigint PRIMARY KEY auto_increment NOT NULL,
    name varchar(255) NOT NULL,
    code varchar(255) NOT NULL,
    shortdescription varchar(255) NULL,
    createdby varchar(255) NULL,
	createddate TIMESTAMP NULL,
	modifiedby varchar(255) NULL,
	modifieddate TIMESTAMP NULL
);

ALTER TABLE article ADD CONSTRAINT fk_article_category FOREIGN KEY (category_id) REFERENCES category(id);

CREATE TABLE comment (
	id bigint PRIMARY KEY auto_increment NOT NULL,
    content TEXT NULL,
    user_id bigint NOT NULL,
    article_id bigint NOT NULL,
    createdby varchar(255) NULL,
	createddate TIMESTAMP NULL,
	modifiedby varchar(255) NULL,
	modifieddate TIMESTAMP NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_article FOREIGN KEY (article_id) REFERENCES article(id);