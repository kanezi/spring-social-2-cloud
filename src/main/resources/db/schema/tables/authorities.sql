create table authorities (
	id int not null,
	"name" varchar(255) null,
	provider varchar(255) null,
	constraint authorities_pkey primary key (id)
);