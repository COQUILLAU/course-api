CREATE TABLE IF NOT EXISTS Users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Champ pour la date de création
);

CREATE TABLE IF NOT EXISTS Budgets (
    id_budget INT AUTO_INCREMENT PRIMARY KEY,
    budget_name VARCHAR(100) NOT NULL,
    total_montant DECIMAL(10, 2) NOT NULL,
    id_user INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Champ pour la date de création
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);

CREATE TABLE IF NOT EXISTS Achats (
    id_achat INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    montant DECIMAL(10, 2) NOT NULL,
    achat_date DATE NOT NULL,
    id_budget INT NOT NULL,
    id_user INT NOT NULL,
    FOREIGN KEY (id_budget) REFERENCES Budgets(id_budget),
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);

CREATE TABLE IF NOT EXISTS Salaires (
    id_salaire INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10, 2) NOT NULL,
    salaire_date DATE NOT NULL,
    id_user INT NOT NULL,
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);

CREATE TABLE logs (
    id_log BIGINT NOT NULL AUTO_INCREMENT,
    action VARCHAR(255),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ajout d'une date de log
    token VARCHAR(255),
    id_user BIGINT NOT NULL,
    PRIMARY KEY (id_log),
    FOREIGN KEY (id_user) REFERENCES Users(id_user)
);

-- Insertion d'utilisateurs avec un email
INSERT INTO Users (username, email, password) VALUES ('user1', 'user1@example.com', 'password1');
INSERT INTO Users (username, email, password) VALUES ('user2', 'user2@example.com', 'password2');
INSERT INTO Users (username, email, password) VALUES ('user3', 'user3@example.com', 'password3');

-- Insertion de budgets
INSERT INTO Budgets (budget_name, total_montant, id_user) VALUES ('Budget Familial', 5000.00, 1);
INSERT INTO Budgets (budget_name, total_montant, id_user) VALUES ('Vacances', 2000.00, 2);
INSERT INTO Budgets (budget_name, total_montant, id_user) VALUES ('Achat Voiture', 10000.00, 3);

-- Insertion de dépenses (achats)
INSERT INTO Achats (description, montant, achat_date, id_budget, id_user) VALUES ('Courses', 150.00, '2024-10-01', 1, 1);
INSERT INTO Achats (description, montant, achat_date, id_budget, id_user) VALUES ('Hôtel', 500.00, '2024-10-05', 2, 2);
INSERT INTO Achats (description, montant, achat_date, id_budget, id_user) VALUES ('Voiture neuve', 9000.00, '2024-10-10', 3, 3);

-- Insertion de salaires
INSERT INTO Salaires (montant, salaire_date, id_user) VALUES (2500.00, '2024-10-01', 1);
INSERT INTO Salaires (montant, salaire_date, id_user) VALUES (3000.00, '2024-10-01', 2);
INSERT INTO Salaires (montant, salaire_date, id_user) VALUES (3500.00, '2024-10-01', 3);
