-- insert some reference categories
Insert into Category (id, title) values (1, "TV"),
(2, "Film"), (3, "Book"), (4, "Website");

-- insert user Majid
Insert into Users (id, username, passphrase) values (
(select uuid()), "Majid", "password"
);

-- insert the book reference for Majid (referencing PageNumber from previous query)
Insert into Ref (id, label, title, notes, created, user_id, category_id, page_number) values 
((select uuid()),"A homer reference", "The Odyssey of Homer", "that was epic!", now(), (Select id from Users where username = "Majid"), 3, 346);

-- insert user Gaurav
Insert into Users (id, username, passphrase) values (
(select uuid()), "Gaurav", "password"
);


-- insert the movie reference for Gaurav (referencing Timepoint from previous query)
Insert into Ref (id, label, title, created, user_id, category_id, timepoint) values 
((select uuid()), "A Lord of the Ring reference", "Fellowship of the Ring",now(), (Select id from Users where username = "Gaurav"), 2, "02:03:55");

-- insert user Rami
Insert into Users (id, username, passphrase) values (
(select uuid()), "Rami", "password"
);


-- insert a website reference for Rami 
Insert into Ref (id, label, notes, created, user_id, category_id, url) values ((select uuid()), "A MySQL Docs reference", "this is the coolest website ever", now(), (Select id from Users where username = "Rami"), 4, "https://dev.mysql.com/doc");

-- insert the tv reference for Majid (referencing Timepoint from previous query)
Insert into Ref (id, label, title, notes, created, user_id, category_id, timepoint) values
((select uuid()), "Mr Robot opening scene", "Mr Robot S01E01", "this gave me goosebumps", now(), (Select id from Users where username = "Majid"), 1, "00:00:51");

-- get all references for Majid
SELECT 
    Ref.id AS RefId,
    Ref.label AS Label,
    Ref.user_id AS UserId,
    Users.username AS Username,
    Category.title AS Category,
    Ref.title AS Title,
    Ref.notes AS Notes,
    Ref.created AS Created,
    Ref.url AS URL,
    Ref.timepoint AS Timepoint,
    Ref.page_number AS `Page Number`
FROM
    Ref
        LEFT JOIN
    Users ON Users.id = Ref.user_id
        LEFT JOIN
    Category ON Ref.category_id = category.id
WHERE
    Username = 'Majid';

-- Add a film reference for Majid 
Insert into Ref (id, label, notes, created, user_id, category_id, timepoint) values 
((select uuid()), "A Godfather reference", "That flashback scene where Don Corleone exacts vengeance", now(), (Select id from Users where username = "Majid"), 2, "01:03:34");

-- Add a book reference for Gaurav
Insert into Ref (id, label, title, created, user_id, category_id, page_number) values
((select uuid()), "Crappiest read ever", "50 Shades of Grey", now(), (Select id from Users where username = "Gaurav"), 3, 5678);

-- Update the website reference for Rami
Update Ref set url = "https://start.spring.io", label = "create spring boot project" where user_id = (Select id from users where username = "Rami");