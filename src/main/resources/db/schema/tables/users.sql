create table users (
	username varchar(255) not null,
	created_at timestamp(6) null,
	email varchar(255) null,
	image_url varchar(500) null,
	"name" varchar(255) null,
	"password" varchar(255) null,
	provider varchar(255) null,
	constraint users_pkey primary key (username)
);