DROP TABLE IF EXISTS tutorials;
CREATE TABLE tutorials (
      id INT AUTO_INCREMENT  PRIMARY KEY,
      title VARCHAR(50) NOT NULL,
      description VARCHAR(50) NOT NULL,
      published INT NOT NULL
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    content VARCHAR(50) NOT NULL,
    tutorial_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (tutorial_id) REFERENCES tutorials(id)
);