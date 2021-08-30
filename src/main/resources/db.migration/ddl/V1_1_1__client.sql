CREATE TABLE client
(
  id   INT primary key,
  name VARCHAR(255) not null,
  surname VARCHAR(255) not null,
  idNumber VARCHAR(13),
  mobileNumber VARCHAR(20),
  physicalAddress VARCHAR(255),
);