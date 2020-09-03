DROP TABLE IF EXISTS userdata;

CREATE TABLE userdata (
  userName varchar(50) NOT NULL,
  role varchar(45) NOT NULL,
  userId int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (userId)
) ;

INSERT INTO userdata
(userName,
role)
VALUES
('Sonali', 'Admin');
