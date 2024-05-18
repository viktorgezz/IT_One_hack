-- Ввод данных в таблицу CATEGORY
INSERT INTO CATEGORY (title) VALUES
                                 ('Food'),
                                 ('Utilities'),
                                 ('Transport'),
                                 ('Entertainment'),
                                 ('Healthcare');

-- Ввод данных в таблицу ACCOUNT
INSERT INTO ACCOUNT (login, password, email) VALUES
                                                 ('user1', 'password1', 'user1@example.com'),
                                                 ('user2', 'password2', 'user2@example.com'),
                                                 ('user3', 'password3', 'user3@example.com'),
                                                 ('user4', 'password4', 'user4@example.com'),
                                                 ('user5', 'password5', 'user5@example.com');


-- Ввод данных в таблицу GOAL
-- Учитывая, что поле 'account_id' уникальное, каждый Account будет иметь только одну цель.
INSERT INTO GOAL (account_id, income, economy) VALUES
                                                   (5, 5000.00, 500.00),
                                                   (6, 6000.00, 600.00),
                                                   (7, 7000.00, 300.00),
                                                   (8, 8000.00, 400.00),
                                                   (9, 9000.00, 200.00);

-- Ввод данных в таблицу EXPENSE
INSERT INTO EXPENSE (account_id, category_id, title, amount, time) VALUES
                                                                       (5, 1, 'Groceries', 150.00, '2023-05-10 08:00:00'),
                                                                       (6, 2, 'Electric bill', 75.00, '2023-05-10 09:00:00'),
                                                                       (7, 3, 'Bus ticket', 20.00, '2023-05-10 10:00:00'),
                                                                       (8, 4, 'Cinema', 50.00, '2023-05-10 11:00:00'),
                                                                       (9, 5, 'Medication', 30.00, '2023-05-10 12:00:00'),
                                                                       (5, 1, 'Groceries', 150.00, '2023-05-10 08:00:00'),
                                                                       (5, 2, 'Электронная дудка', 400.00, '2023-05-10 08:00:00')
;


-- Ввод данных в таблицу FRIENDS
-- Здесь создаются связи между разными пользователями, устанавливая дружеские отношения.
INSERT INTO FRIENDS (account1_id, account2_id) VALUES
                                                   (5, 6),
                                                   (5, 7),
                                                   (6, 7),
                                                   (8, 9),
                                                   (7, 8);