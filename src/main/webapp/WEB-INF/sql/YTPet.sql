/*
	youta pets db design.
*/

/*
	t_admin:id,name,account,password,remarks
	
*/

drop table if exists t_admin;
create table t_admin(
	id int primary key auto_increment not null,
	name varchar(12) UNIQUE ,
	account varchar(32) not null,
	password varchar(32) not null
);

/*
	t_custom_prim:the primary info about user.

*/

drop table if exists t_custom_prim;
create table t_custom_prim(
	customid int primary key auto_increment not null,
	customname varchar(12),
	customphone varchar(32),
	crttime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


/*
	t_custom_blc:the money that custom has.(yu e)

*/


drop table if exists t_custom_blc;
create table t_custom_blc(
	blcid int primary key auto_increment not null,
	balance double,
	f_customid int unique,
	updtime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
	foreign key (f_customid) references t_custom_prim(customid)
);

/*
	this is recording that custom usually charge record.

*/

drop table if exists t_custom_chrginfo;
create table t_custom_chrginfo(
	chargeid int primary key auto_increment not null,
	f_customid int,
	chargetime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	amount double,
	
	foreign key (f_customid) references t_custom_prim(customid)
);


/*
	this is the pet info about custom

*/
drop table if exists t_custom_pet;
create table t_custom_pet(
	petid int primary key auto_increment not null,
	f_customid int unique,
	pettype enum('very big','big','small','very small') NOT NULL,
	petname varchar(32),
	petimage varchar(32),
	
	foreign key (f_customid) references t_custom_prim(customid)
);

/*
	custom purchase history

*/

drop table if exists t_custom_record;
create table t_custom_record(
	recordid int primary key auto_increment not null,
	f_customid int,
	paytime timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	paywhat varchar(64),
	amount double,
	remark varchar(64),
	
	foreign key (f_customid) references t_custom_prim(customid)
);









