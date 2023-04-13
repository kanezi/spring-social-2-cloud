create table user_authorities (
	id int not null,
	authority_id int null,
	username varchar(255) null,
	constraint user_authorities_pkey primary key (id),
	constraint user_authority_authority_fk foreign key (authority_id) references authorities(id),
	constraint user_authority_user_fk foreign key (username) references users(username)
);