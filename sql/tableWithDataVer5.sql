-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema addcom
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `addcom` ;

-- -----------------------------------------------------
-- Schema addcom
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `addcom` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `addcom` ;

-- -----------------------------------------------------
-- Table `addcom`.`certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`certificate` ;

CREATE TABLE IF NOT EXISTS `addcom`.`certificate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mathematics` INT NULL DEFAULT NULL,
  `chemistry` INT NULL DEFAULT NULL,
  `physics` INT NULL DEFAULT NULL,
  `english` INT NULL DEFAULT NULL,
  `ukrainian` INT NULL DEFAULT NULL,
  `informatics` INT NULL DEFAULT NULL,
  `geography` INT NULL DEFAULT NULL,
  `biology` INT NULL DEFAULT NULL,
  `history` INT NULL DEFAULT NULL,
  `gym` INT NULL DEFAULT NULL,
  `averageScore` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`roles` ;

CREATE TABLE IF NOT EXISTS `addcom`.`roles` (
  `id` INT NOT NULL DEFAULT '0',
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`studyform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`studyform` ;

CREATE TABLE IF NOT EXISTS `addcom`.`studyform` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `form` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`account` ;

CREATE TABLE IF NOT EXISTS `addcom`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(10) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `middleName` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `region` VARCHAR(45) NOT NULL,
  `certificate_id` INT NOT NULL,
  `roles_id` INT NOT NULL,
  `studyForm_id` INT NOT NULL,
  `faculties_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_certificate_id` (`certificate_id` ASC) VISIBLE,
  INDEX `fk_account_roles_id` (`roles_id` ASC) VISIBLE,
  INDEX `fk_account_studyForm_id` (`studyForm_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_certificate`
    FOREIGN KEY (`certificate_id`)
    REFERENCES `addcom`.`certificate` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_account_roles`
    FOREIGN KEY (`roles_id`)
    REFERENCES `addcom`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_account_studyForm`
    FOREIGN KEY (`studyForm_id`)
    REFERENCES `addcom`.`studyform` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`faculties`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`faculties` ;

CREATE TABLE IF NOT EXISTS `addcom`.`faculties` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `passingScoreFreeForm` INT NULL DEFAULT NULL,
  `passingScorePayForm` INT NULL DEFAULT NULL,
  `allPlaces` INT NULL DEFAULT NULL,
  `freeFormPlaces` INT NULL DEFAULT NULL,
  `payFormPlaces` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`status` ;

CREATE TABLE IF NOT EXISTS `addcom`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`order` ;

CREATE TABLE IF NOT EXISTS `addcom`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `faculties_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_account_id` (`account_id` ASC) VISIBLE,
  INDEX `fk_order_faculties_id` (`faculties_id` ASC) VISIBLE,
  INDEX `fk_order_status1_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `addcom`.`account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_faculties`
    FOREIGN KEY (`faculties_id`)
    REFERENCES `addcom`.`faculties` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `addcom`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB

DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `addcom`.`act`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `addcom`.`act` ;

CREATE TABLE IF NOT EXISTS `addcom`.`act` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `faculties_id` INT NOT NULL,
  `status`  VARCHAR(45) NULL DEFAULT NULL,
  `faculties_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_act_account1_idx` (`account_id` ASC) VISIBLE,
  INDEX `fk_act_faculties1_idx` (`faculties_id` ASC) VISIBLE,
  CONSTRAINT `fk_act_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `addcom`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_act_faculties1`
    FOREIGN KEY (`faculties_id`)
    REFERENCES `addcom`.`faculties` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

START TRANSACTION;

USE `AddCom`;
INSERT INTO `AddCom`.`certificate` (`id`, `mathematics`, `chemistry`, `physics`, `english`, `ukrainian`, `informatics`, `geography`, `biology`, `history`, `gym`, `averageScore`) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `AddCom`.`certificate` (`id`, `mathematics`, `chemistry`, `physics`, `english`, `ukrainian`, `informatics`, `geography`, `biology`, `history`, `gym`, `averageScore`) VALUES(2,4,4,4,4,4,4,4,4,4,4,40);
COMMIT;

START TRANSACTION;
INSERT INTO `addcom`.`status` (`id`, `status`) VALUES (1, 'rejected');
INSERT INTO `addcom`.`status` (`id`, `status`) VALUES (2, 'PayForm');
INSERT INTO `addcom`.`status` (`id`, `status`) VALUES (3, 'FreeForm');

COMMIT;

-- -----------------------------------------------------
-- Data for table `AddCom`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `AddCom`;
INSERT INTO `AddCom`.`roles` (`id`, `roleName`) VALUES (1, 'admin');
INSERT INTO `AddCom`.`roles` (`id`, `roleName`) VALUES (2, 'user');
INSERT INTO `AddCom`.`roles` (`id`, `roleName`) VALUES (3,'blockUser');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AddCom`.`studyForm`
-- -----------------------------------------------------
START TRANSACTION;
USE `AddCom`;
INSERT INTO `AddCom`.`studyForm` (`id`, `form`) VALUES (1, 'defaultForm');
INSERT INTO `AddCom`.`studyForm` (`id`, `form`) VALUES (2, 'FreeForm');
INSERT INTO `AddCom`.`studyForm` (`id`, `form`) VALUES (3, 'PayForm');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AddCom`.`faculties`
-- -----------------------------------------------------
START TRANSACTION;
USE `AddCom`;
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (1, 'FacultiesTest', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (2, 'rocket building', 40, 30, 100, 20, 80);
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (3, 'theoretical astrophysics', 35, 30, 100, 30, 70);
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (4, 'cosmology', 35, 30, 100, 30, 70);
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (6, 'rocket engines', 35, 30, 100, 30, 70);
INSERT INTO `AddCom`.`faculties` (`id`, `name`, `passingScoreFreeForm`, `passingScorePayForm`, `allPlaces`, `freeFormPlaces`, `payFormPlaces`) VALUES (7, 'rocket instrumentation', 35, 30, 100, 30, 70);
COMMIT;


-- -----------------------------------------------------
-- Data for table `AddCom`.`account`
-- -----------------------------------------------------
START TRANSACTION;
USE `AddCom`;
INSERT INTO `AddCom`.`account` (`id`, `login`, `password`, `firstName`, `lastName`, `middleName`, `email`, `city`, `region`, `certificate_id`, `roles_id`, `studyForm_id`, `faculties_name`) VALUES (1, 'admin', '21232F297A57A5A743894A0E4A801FC3', 'Aleksandrov', 'Oleg', 'Valerievich', 'admin@mail.com', 'Odessa', 'Odessa', 1, 1, 1, 'admin');
INSERT INTO `AddCom`.`account` (`id`, `login`, `password`, `firstName`, `lastName`, `middleName`, `email`, `city`, `region`, `certificate_id`, `roles_id`, `studyForm_id`, `faculties_name`) VALUES (2,'Юзер2','81DC9BDB52D04DC20036DBD8313ED055','Юрий','Гагарин','Алексеевич','poehali@mail.com','Москва','Московская',2,2,1,'matriculate');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AddCom`.`order`
-- -----------------------------------------------------
