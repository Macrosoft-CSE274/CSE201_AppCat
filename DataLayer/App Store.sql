CREATE DATABASE dbAppStore;

GO
USE dbAppstore;

------------------------------ CREATE TABLE BEGIN

GO
CREATE TABLE tblAppLabel
(
[AppLabelID]		int				NOT NULL IDENTITY PRIMARY KEY,
[AppLabelName]		varchar(50)		NOT NULL,
[Deleted]			bit				DEFAULT 0
);
 
GO
CREATE TABLE tblUser
(
[UserID]			int				NOT NULL IDENTITY PRIMARY KEY,
[UserName]			varchar(50)		NOT NULL,
[FirstName]			varchar(50)		NOT NULL,
[LastName]			varchar(50)		NOT NULL,
[Email]				varchar(100)	NOT NULL,
[UserPassword]		varchar(50)		NOT NULL,
[Deleted]			bit				DEFAULT 0
);

GO
CREATE TABLE tblPlatform
(
[PlatformID]		int				NOT NULL IDENTITY PRIMARY KEY,
[PlatformName]		varchar(50)		NOT NULL,
[Deleted]			bit				DEFAULT 0
);


GO
CREATE TABLE tblApp
(
[AppID]				int				NOT NULL IDENTITY PRIMARY KEY,
[AppName]			varchar(100)		NOT NULL,
[AppLabelID]		int				NOT NULL REFERENCES tblAppLabel([AppLabelID]),
[PlatformID]		int				NOT NULL REFERENCES tblPlatform([PlatformID]),
[AppIndustry]		varchar(100)		NOT NULL,
[Price]				float			NOT NULL,
[NumOfInstalls]		varchar(50)		NOT NULL,
[AverageStar]		float			NOT NULL,
[WebLink]			varchar(200)	NOT NULL,
[Description]		varchar(1500)	NOT NULL,
[Deleted]			bit				DEFAULT 0
);
--[TotalStar]			float			NOT NULL,
--[NumOfComments]		float			NOT NULL,
---------------------------------------------------
GO
CREATE TABLE tblComments
(
[CommentID]			int				NOT NULL IDENTITY PRIMARY KEY,
[AppID]				int				NOT NULL REFERENCES tblApp([AppID]),
[UserID]			int				NOT NULL REFERENCES tblUser([UserID]),
[CommentDetail]		varchar(300)	NOT NULL,
[CommentDate]		Date			NOT NULL,
[CommentStar]		float				NOT NULL,
[Deleted]			bit				DEFAULT 0
);

------------------------------ CREATE TABLE END
GO
USE dbAppstore;
--------------------------------INSERT DATA
GO
INSERT INTO tblPlatform
VALUES	('Android',0),
		('IOS',0),
		('Windows',0),
		('Mac',0),
		('Linux',0);
GO
INSERT INTO tblUser
VALUES	('zzhxbhj','Huijie','Bao','2333XD',0);

--bulk insert AppLabel by data online
GO 
BULK INSERT tblAppLabel
FROM 'C:\Users\zzhxb\Desktop\DataLayer\GetGoolePlayData\GetGoolePlayData\GetGoolePlayData\bin\Debug\AppLabel.txt'
-- The Address of AppLabel.txt may change
WITH (
	FIRSTROW = 2,
	FIELDTERMINATOR = '\t',
	ROWTERMINATOR = '\n',
	TABLOCK
);

--GO -- hard code insert
--INSERT INTO tblAppLabel
--VALUES	('Game',0),
--		('Finance',0),
--		('Shopping',0),
--		('News',0),
--		('Education',0)

GO 
BULK INSERT tblApp
FROM 'C:\Users\zzhxb\Desktop\DataLayer\GetGoolePlayData\GetGoolePlayData\GetGoolePlayData\bin\Debug\App.txt'
-- The Address of AppLabel.txt may change
WITH (
	FIRSTROW = 2,
	FIELDTERMINATOR = '\t',
	ROWTERMINATOR = '\n',
	TABLOCK
);


--GO
--INSERT INTO tblApp
--VALUES	('POKEMON GO',1,1,'Niantic, Inc.',0,'100,000,000 - 500,000,000',0,'https://play.google.com/store/apps/details?id=com.nianticlabs.pokemongo',0),
--		('TED',5,1,'TED Conferences LLC',0,'100,000,000 - 500,000,000',0,'https://play.google.com/store/apps/details?id=com.ted.android',0),
--		('MINECRAFT',1,1,'BLABLABALA',6.99,'10,000,000 - 50,000,000',0,'https://play.google.com/store/apps/details?id=com.mojang.minecraftpe',0)
-------------------------------INSERT DATA END

GO
USE dbAppstore;
------------------------------ CREATE PROCEDURE spSearchAppByKeywords
GO
CREATE PROCEDURE spSearchAppByKeywords
@keywords	varchar(100)
AS
	SET NOCOUNT ON
SELECT	a.AppName, 
		al.AppLabelName, 
		p.PlatformName, 
		a.AppIndustry, 
		a.Price, 
		a.NumOfInstalls, 
		a.AverageStar, 
		a.WebLink
FROM tblApp a
join tblAppLabel al
on a.AppLabelID = al.AppLabelID
join tblPlatform p
on p.PlatformID = a.PlatformID
WHERE a.AppName LIKE '%'+ @keywords + '%'
	AND a.Deleted = 0		;	
	
------------------------------ CREATE PROCEDURE spSearchAllByIndustryKeywords
GO
CREATE PROCEDURE spSearchAllByIndustryKeywords
@AppIndustryKeywords	varchar(100)
AS
	SET NOCOUNT ON
SELECT	a.AppName, 
		al.AppLabelName, 
		p.PlatformName, 
		a.AppIndustry, 
		a.Price, 
		a.NumOfInstalls, 
		a.AverageStar, 
		a.WebLink
FROM tblApp a
join tblAppLabel al
on a.AppLabelID = al.AppLabelID
join tblPlatform p
on p.PlatformID = a.PlatformID
WHERE a.AppIndustry LIKE '%'+ @AppIndustryKeywords + '%'
	AND a.Deleted = 0;

------------------------------ CREATE PROCEDURE spSearchAllByAppLabel
GO
CREATE PROCEDURE spSearchAllByAppLabel
@AppLabelName	varchar(50)
AS
	SET NOCOUNT ON
SELECT	a.AppName, 
		al.AppLabelName, 
		p.PlatformName, 
		a.AppIndustry, 
		a.Price, 
		a.NumOfInstalls, 
		a.AverageStar, 
		a.WebLink 
FROM tblApp a
join tblAppLabel al
on a.AppLabelID = al.AppLabelID
join tblPlatform p
on p.PlatformID = a.PlatformID
WHERE al.AppLabelName = @AppLabelName AND a.Deleted = 0;


------------------------------ CREATE PROCEDURE spLogin
GO
CREATE PROCEDURE spLogin
@UserName	varchar(50),
@Password	varchar(50)
AS
	SET NOCOUNT ON
IF EXISTS(SELECT * FROM tblUser WHERE UserName = @UserName AND UserPassword = @Password AND Deleted = 0)
	BEGIN
		SELECT [SUCCESS] = 1
	END
ELSE 
	BEGIN
		SELECT [SUCCESS] = 0
	END;

------------------------------ CREATE PROCEDURE spAddComment
GO
CREATE PROCEDURE spAddComment
@AppID				int,
@UserName			varchar(50),
@CommentDetail		varchar(300),
@CommentStar		float
AS
	SET NOCOUNT ON
IF (EXISTS(SELECT * FROM tblUser WHERE UserName = @UserName AND Deleted = 0) 
	AND EXISTS(SELECT * FROM tblApp WHERE AppID = @AppID AND Deleted = 0))
	BEGIN
		
		INSERT INTO tblComments
		VALUES((SELECT TOP 1 AppID FROM tblApp WHERE AppID = @AppID AND Deleted = 0),
			   (SELECT TOP 1 UserID FROM tbluser WHERE UserName = @UserName AND Deleted = 0),
			   @CommentDetail,
			   GETDATE(),
			   @CommentStar,
			   0)
		--UPDATE tblComments
		--SET AppID = (SELECT TOP 1 AppID FROM tblApp WHERE AppName = @AppName AND Deleted = 0),
		--	UserID = (SELECT TOP 1 UserID FROM tbluser WHERE UserName = @UserName AND Deleted = 0)
		--WHERE CommentID = @@IDENTITY
		UPDATE tblApp
		SET AverageStar = (SELECT AVG(CommentStar) FROM tblComments WHERE AppID= @AppID)
		WHERE AppID = @AppID
		SELECT [SUCCESS] = 1
	END
ELSE 
	BEGIN
		SELECT [SUCCESS] = 0
	END;


-------- CREATE PROCEDURE -- spAddAPP

GO
CREATE PROCEDURE spAddAPP	
@AppName			varchar(100)	,
@AppLabelID			int				,
@PlatformID			int				,
@AppIndustry		varchar(100)	,
@Price				float			,
@NumOfInstalls		varchar(50)		,
@AverageStar		float			,
@WebLink			varchar(200)	,
@Description		varchar(1500)	
AS
	SET NOCOUNT ON
IF NOT EXISTS (SELECT NULL FROM tblAPP WHERE AppName = @AppName) 
	BEGIN
		INSERT INTO tblApp
		VALUES(		
					@AppName		,
					@AppLabelID		,
					@PlatformID		,
					@AppIndustry	,
					@Price			,
					@NumOfInstalls	,
					@AverageStar	,
					@WebLink		,
					@Description	,
					0	
				)
		SELECT [SUCCESS] = 1
	END
ELSE
	BEGIN
		SELECT [SUCCESS] = 0
	END;


-------- CREATE PROCEDURE -- spAddUser
GO
CREATE PROCEDURE spAddUser
@UserName			varchar(50),
@FirstName			varchar(50),
@LastName			varchar(50),
@email				varchar(100),
@UserPassword		varchar(50),
@ConfirmPassword	varchar(50)	
AS
	SET NOCOUNT ON
IF (NOT EXISTS (SELECT NULL FROM tblUser WHERE UserName = @UserName) )
	AND (@UserPassword = @ConfirmPassword)
	BEGIN
		INSERT INTO tblUser 
		VALUES(@UserName,@FirstName,@LastName,@email,@UserPassword,0)
		SELECT [SUCCESS] = 1
	END
ELSE
	BEGIN
		SELECT [SUCCESS] = 0
	END;


-------- CREATE PROCEDURE -- spUpdateUser
GO
CREATE PROCEDURE spUpdateUser
@UserID				int	,
@UserName			varchar(50),
@FirstName			varchar(50),
@LastName			varchar(50),
@email				varchar(100),
@UserPassword		varchar(50)
AS
	SET NOCOUNT ON
IF (SELECT Deleted FROM tblUser WHERE UserID = @UserID) = 0
	BEGIN
		UPDATE tblUser
		SET	UserName	 = @UserName	  ,
			FirstName	 = @FirstName	  ,
			LastName	 = @LastName	  ,
			email		 = @email		  ,
			UserPassword = @UserPassword  
		WHERE UserID = @UserID
		SELECT [SUCCESS] = 1
	END
ELSE
	BEGIN
		SELECT [SUCCESS] = 0
	END;




		-------- CREATE PROCEDURE -- filter


	-- store procedure that Add/delete/modify/read a user/comment/app
	--admin CRUD user/comment/app
	--user CUD own comment
	--user R app/comment
