CREATE TABLE diff (
  id varchar(100) primary key,
	left varchar(2000),
	right varchar(2000),
	created_at timestamp not null,
	updated_at timestamp not null,
	version integer not null
);