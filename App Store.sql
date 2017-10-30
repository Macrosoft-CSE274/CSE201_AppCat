CREATE DATABASE dbAppStore

GO
USE dbAppstore

------------------------------ CREATE TABLE BEGIN

GO
CREATE TABLE tblAppLabel
(
[AppLabelID]		int				NOT NULL IDENTITY PRIMARY KEY,
[AppLabelName]		varchar(50)		NOT NULL,
[deleted]			bit				DEFAULT 0
)
 
GO
CREATE TABLE tblUser
(
[UserID]			int				NOT NULL IDENTITY PRIMARY KEY,
[UserName]			varchar(50)		NOT NULL,
[UserPassword]		varchar(50)		NOT NULL,
[deleted]			bit				DEFAULT 0
)

GO
CREATE TABLE tblPlatform
(
[PlatformID]		int				NOT NULL IDENTITY PRIMARY KEY,
[PlatformName]		varchar(50)		NOT NULL,
[deleted]			bit				DEFAULT 0
)


GO
CREATE TABLE tblApp
(
[AppID]				int				NOT NULL IDENTITY PRIMARY KEY,
[AppName]			varchar(50)		NOT NULL,
[AppLabelID]		int				NOT NULL REFERENCES tblAppLabel([AppLabelID]),
[PlatformID]		int				NOT NULL REFERENCES tblPlatform([PlatformID]),
[AppIndustry]		varchar(50)		NOT NULL,
[Price]				int				NOT NULL,
[NumOfInstalls]		varchar(50)		NOT NULL,
[WebLink]			varchar(100)	NOT NULL,
[deleted]			bit				DEFAULT 0
)


------------------------------ CREATE TABLE END
GO
USE dbAppstore
--------------------------------INSERT DATA
GO
INSERT INTO tblPlatform
VALUES	('Android',0),
		('IOS',0),
		('Windows',0),
		('Mac',0),
		('Linux',0)
GO
INSERT INTO tblUser
VALUES	('Huijie Bao','2333XD',0),
		('Dingbang Wang','6666gg',0)

--incompleted, should use bulk insert
GO
INSERT INTO tblAppLabel
VALUES	('Game',0),
		('Finance',0),
		('Shopping',0),
		('News',0),
		('Education',0)
--incompleted, should use bulk insert
GO
INSERT INTO tblApp
VALUES	('POKEMON GO',1,1,'Niantic, Inc.',0,'100,000,000 - 500,000,000','https://play.google.com/store/apps/details?id=com.nianticlabs.pokemongo',0),
		('TED',5,1,'TED Conferences LLC',0,'100,000,000 - 500,000,000','https://play.google.com/store/apps/details?id=com.ted.android',0),
		('MINECRAFT',1,1,'BLABLABALA',6.99,'10,000,000 - 50,000,000','https://play.google.com/store/apps/details?id=com.mojang.minecraftpe',0)
-------------------------------INSERT DATA END
GO
USE dbAppstore
------------------------------ CREATE PROCEDURE spSearchAppByKeywords
GO
CREATE PROCEDURE spSearchAppByKeywords
@keywords	varchar(50)
AS
	SET NOCOUNT ON
SELECT * 
FROM tblApp
WHERE AppName LIKE '%'+ @keywords + '%'
	AND deleted = 0

------------------------------ CREATE PROCEDURE spSearchAllByIndustryKeywords
GO
CREATE PROCEDURE spSearchAllByIndustryKeywords
@AppIndustryKeywords	varchar(50)
AS
	SET NOCOUNT ON
SELECT * 
FROM tblApp
WHERE AppIndustry LIKE '%'+ @AppIndustryKeywords + '%'
	AND deleted = 0

------------------------------ CREATE PROCEDURE spSearchAllByAppLabel
GO
CREATE PROCEDURE spSearchAllByAppLabel
@AppLabelName	varchar(50)
AS
	SET NOCOUNT ON
SELECT * 
FROM tblApp a
JOIN tblAppLabel al
ON a.AppLabelID = al.AppLabelID
WHERE al.AppLabelName = @AppLabelName AND a.deleted = 0


------------------------------ CREATE PROCEDURE spLogin
GO
CREATE PROCEDURE spLogin
@userName	varchar(50),
@password	varchar(50)
AS
	SET NOCOUNT ON
IF EXISTS(SELECT * FROM tblUser WHERE UserName = @userName AND UserPassword = @password AND deleted = 0)
	BEGIN
		SELECT [SUCCESS] = 1
	END
ELSE 
	BEGIN
		SELECT [SUCCESS] = 0
	END


	-----search need to change platformID&LabelID -> platform Name & Label Name
	-------- CREATE PROCEDURE -- filter by Price



