
CREATE TABLE `ted`.`coche` (
  `id` INT NOT NULL,
  `modelo` VARCHAR(45) NULL,
  `fabricante` VARCHAR(45) NULL,
  `num_cilindros` INT NULL,
  `num_cv` DECIMAL(7,2) NULL,
  PRIMARY KEY (`id`));

    INSERT INTO `ted`.`coche` (`id`, `modelo`, `fabricante`, `num_cilindros`, `num_cv`) VALUES ('1', '458 Italia', 'Ferrari', '12', '588.99');
  INSERT INTO `ted`.`coche` (`id`, `modelo`, `fabricante`, `num_cilindros`, `num_cv`) VALUES ('2', 'Huracán', 'Lamborghini', '16', '688.99');

  