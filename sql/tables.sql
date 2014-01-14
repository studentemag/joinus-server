DROP DATABASE IF EXISTS joinus;
CREATE DATABASE joinus;
USE joinus;

CREATE TABLE Users (
	id varchar(10),
	name varchar(10),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Meetings (
	id int AUTO_INCREMENT,
	title varchar(10),
	mocId varchar(10),
	PRIMARY KEY (id),
	FOREIGN KEY (mocId) REFERENCES Users(id)
) ENGINE=InnoDB;

CREATE TABLE Participants (
	userId varchar(10),
	eventId int,
	PRIMARY KEY (userId,eventId),
	FOREIGN KEY (userId) REFERENCES Users(id),
	FOREIGN KEY (meetingId) REFERENCES Meetings(id)
) ENGINE=InnoDB;

CREATE TABLE Guests (
	userId varchar(10),
	eventId int,
	PRIMARY KEY (userId,eventId),
	FOREIGN KEY (userId) REFERENCES Users(id),
	FOREIGN KEY (meetingId) REFERENCES Meetings(id)
) ENGINE=InnoDB;




