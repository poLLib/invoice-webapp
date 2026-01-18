CREATE TABLE person
(
    id                    BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name                  VARCHAR(255) NOT NULL,
    identification_number VARCHAR(255) NOT NULL UNIQUE,
    tax_number            VARCHAR(255),
    account_number        VARCHAR(255) NOT NULL,
    bank_code             VARCHAR(255) NOT NULL,
    iban                  VARCHAR(255),
    telephone             VARCHAR(255) NOT NULL,
    mail                  VARCHAR(255) NOT NULL,
    street                VARCHAR(255) NOT NULL,
    zip                   VARCHAR(255) NOT NULL,
    city                  VARCHAR(255) NOT NULL,
    country               VARCHAR(50)  NOT NULL,
    note                  VARCHAR(255),
    hidden                BOOLEAN DEFAULT FALSE
);

CREATE TABLE invoice
(
    id             BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    invoice_number INT          NOT NULL UNIQUE,
    issued         DATE         NOT NULL,
    due_date       DATE         NOT NULL,
    product        VARCHAR(255) NOT NULL,
    price          BIGINT       NOT NULL,
    vat            INT          NOT NULL,
    note           VARCHAR(255),
    buyer_id       BIGINT       NOT NULL,
    seller_id      BIGINT       NOT NULL,
    FOREIGN KEY (buyer_id) REFERENCES person (id),
    FOREIGN KEY (seller_id) REFERENCES person (id)
);

-- Indexes for invoice table (foreign keys for faster joins)
CREATE INDEX idx_invoice_buyer_id ON invoice(buyer_id);
CREATE INDEX idx_invoice_seller_id ON invoice(seller_id);