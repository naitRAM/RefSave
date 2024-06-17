Drop Database If Exists RefSave;
Create Database RefSave;
Use RefSave;


Create Table Users (
id varchar(36) primary key,
username varchar(50) unique not null,
passphrase varchar(64) not null
);

Create Table Category (
id bit(3) primary key,
title char(10) unique not null
);


Create Table Ref (
id varchar(36) primary key,
label varchar(100) not null,
title varchar(100),
notes varchar(2000),
url varchar(2048),
timepoint time,
page_number bit(13),
created timestamp not null,
user_id varchar(36) not null,
category_id bit(3) not null,

foreign key fk_ref_users (user_id) references users(id),
foreign key fk_ref_category (category_id) references category(id)
);