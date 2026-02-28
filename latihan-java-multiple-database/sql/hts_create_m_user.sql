create table m_user (
	user_id BIGSERIAL PRIMARY KEY,
	name VARCHAR (100),
	email VARCHAR (100) UNIQUE NOT NULL,
	sales_group_id BIGINT
)

drop table m_user;