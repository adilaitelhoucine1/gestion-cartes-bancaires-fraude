
CREATE DATABASE IF NOT EXISTS banking_system

USE banking_system;

CREATE TABLE IF NOT EXISTS client (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(100) NOT NULL,
  email        VARCHAR(150) NOT NULL UNIQUE,
  phone        VARCHAR(20)  NOT NULL,
  created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS card (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  number             VARCHAR(20)  NOT NULL UNIQUE,
  expiration_date    DATE         NOT NULL,
  status             ENUM('ACTIVE','SUSPENDED','BLOCKED') NOT NULL DEFAULT 'ACTIVE',
  card_type          ENUM('DEBIT','CREDIT','PREPAID')     NOT NULL,
  client_id          BIGINT       NOT NULL,

  -- type-specific attributes
  daily_limit        DECIMAL(12,2) NULL,
  monthly_limit      DECIMAL(12,2) NULL,
  interest_rate      DECIMAL(5,2)  NULL,
  available_balance  DECIMAL(12,2) NULL,

  created_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT fk_card_client
    FOREIGN KEY (client_id) REFERENCES client(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;

ALTER TABLE card
  ADD CONSTRAINT chk_card_type_fields
  CHECK (
    (card_type = 'DEBIT'   AND daily_limit IS NOT NULL AND monthly_limit IS NULL AND interest_rate IS NULL AND available_balance IS NULL)
 OR (card_type = 'CREDIT'  AND monthly_limit IS NOT NULL AND interest_rate IS NOT NULL AND daily_limit IS NULL AND available_balance IS NULL)
 OR (card_type = 'PREPAID' AND available_balance IS NOT NULL AND daily_limit IS NULL AND monthly_limit IS NULL AND interest_rate IS NULL)
  );


CREATE TABLE IF NOT EXISTS card_operation (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  operation_date  TIMESTAMP    NOT NULL,
  amount          DECIMAL(12,2) NOT NULL CHECK (amount > 0),
  operation_type  ENUM('PURCHASE','WITHDRAWAL','ONLINE_PAYMENT') NOT NULL,
  location        VARCHAR(200) NOT NULL,
  card_id         BIGINT       NOT NULL,
  created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_operation_card
    FOREIGN KEY (card_id) REFERENCES card(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS fraud_alert (
  id           BIGINT AUTO_INCREMENT PRIMARY KEY,
  description  TEXT        NOT NULL,
  alert_level  ENUM('INFO','WARNING','CRITICAL') NOT NULL,
  card_id      BIGINT      NOT NULL,
  created_at   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_alert_card
    FOREIGN KEY (card_id) REFERENCES card(id)
    ON DELETE CASCADE
) ENGINE=InnoDB;


