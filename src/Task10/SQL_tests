-- 1. Найти номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 долларов.
SELECT model, speed, hd
FROM PC
WHERE price < 500;

-- 2. Найти производителей принтеров. Вывести поля: maker.
SELECT DISTINCT maker
FROM Product
WHERE type = 'Printer';

-- 3. Найти номер модели, объем памяти и размеры экранов ноутбуков, цена которых превышает 1000 долларов.
SELECT model, ram, screen
FROM Laptop
WHERE price > 1000;

-- 4. Найти все записи таблицы Printer для цветных принтеров.
SELECT *
FROM Printer
WHERE color = 'y';

-- 5. Найти номер модели, скорость и размер жесткого диска для ПК, имеющих скорость cd 12x или 24x и цену менее 600 долларов.
SELECT model, speed, hd
FROM PC
WHERE cd IN ('12x', '24x') AND price < 600;

-- 6. Указать производителя и скорость для тех ноутбуков, которые имеют жесткий диск объемом не менее 100 Гбайт.
SELECT p.maker, l.speed
FROM Laptop l
JOIN Product p ON l.model = p.model
WHERE l.hd >= 100;

-- 7. Найти номера моделей и цены всех продуктов (любого типа), выпущенных производителем B (латинская буква).
SELECT p.model,
       COALESCE(pc.price, lp.price, pr.price) as price
FROM Product p
LEFT JOIN PC pc ON p.model = pc.model AND p.type = 'PC'
LEFT JOIN Laptop lp ON p.model = lp.model AND p.type = 'Laptop'
LEFT JOIN Printer pr ON p.model = pr.model AND p.type = 'Printer'
WHERE p.maker = 'B';

-- 8. Найти производителя, выпускающего ПК, но не ноутбуки.
SELECT DISTINCT maker
FROM Product
WHERE type = 'PC'
EXCEPT
SELECT DISTINCT maker
FROM Product
WHERE type = 'Laptop';

-- 9. Найти производителей ПК с процессором не менее 450 Мгц. Вывести поля: maker.
SELECT DISTINCT p.maker
FROM Product p
JOIN PC ON p.model = pc.model
WHERE p.type = 'PC' AND pc.speed >= 450;

-- 10. Найти принтеры, имеющие самую высокую цену. Вывести поля: model, price.
SELECT model, price
FROM Printer
WHERE price = (SELECT MAX(price) FROM Printer);

-- 11. Найти среднюю скорость ПК.
SELECT AVG(speed) as avg_speed
FROM PC;

-- 12. Найти среднюю скорость ноутбуков, цена которых превышает 1000 долларов.
SELECT AVG(speed) as avg_speed
FROM Laptop
WHERE price > 1000;

-- 13. Найти среднюю скорость ПК, выпущенных производителем A.
SELECT AVG(pc.speed) as avg_speed
FROM Product p
JOIN PC ON p.model = pc.model
WHERE p.maker = 'A' AND p.type = 'PC';

-- 14. Для каждого значения скорости процессора найти среднюю стоимость ПК с такой же скоростью. Вывести поля: скорость, средняя цена.
SELECT speed, AVG(price) as avg_price
FROM PC
GROUP BY speed
ORDER BY speed;

-- 15. Найти размеры жестких дисков, совпадающих у двух и более РС. Вывести поля: hd.
SELECT hd
FROM PC
GROUP BY hd
HAVING COUNT(*) >= 2
ORDER BY hd;

-- 16. Найти пары моделей РС, имеющих одинаковые скорость процессора и RAM.
-- В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i).
-- Порядок вывода полей: модель с большим номером, модель с меньшим номером, скорость, RAM.
SELECT pc1.model as model1, pc2.model as model2, pc1.speed, pc1.ram
FROM PC pc1
JOIN PC pc2 ON pc1.speed = pc2.speed
           AND pc1.ram = pc2.ram
           AND pc1.model < pc2.model
ORDER BY pc1.model, pc2.model;

-- 17. Найти модели ноутбуков, скорость которых меньше скорости любого из ПК. Вывести поля: type, model, speed.
SELECT DISTINCT 'Laptop' as type, l.model, l.speed
FROM Laptop l
WHERE l.speed < ALL (SELECT speed FROM PC);

-- Альтернативный вариант для 17:
-- SELECT DISTINCT 'Laptop' as type, l.model, l.speed
-- FROM Laptop l
-- WHERE l.speed < (SELECT MAX(speed) FROM PC);

-- 18. Найти производителей самых дешевых цветных принтеров. Вывести поля: maker, price.
SELECT p.maker, pr.price
FROM Product p
JOIN Printer pr ON p.model = pr.model
WHERE pr.color = 'y'
  AND pr.price = (
    SELECT MIN(price)
    FROM Printer
    WHERE color = 'y'
);

-- 19. Для каждого производителя найти средний размер экрана выпускаемых им ноутбуков.
-- Вывести поля: maker, средний размер экрана.
SELECT p.maker, AVG(l.screen) as avg_screen
FROM Product p
JOIN Laptop l ON p.model = l.model
WHERE p.type = 'Laptop'
GROUP BY p.maker
ORDER BY p.maker;

-- 20. Найти производителей, выпускающих по меньшей мере три различных модели ПК.
-- Вывести поля: maker, число моделей.
SELECT p.maker, COUNT(*) as model_count
FROM Product p
WHERE p.type = 'PC'
GROUP BY p.maker
HAVING COUNT(*) >= 3
ORDER BY model_count DESC;

-- 21. Найти максимальную цену ПК, выпускаемых каждым производителем.
-- Вывести поля: maker, максимальная цена.
SELECT p.maker, MAX(pc.price) as max_price
FROM Product p
JOIN PC ON p.model = pc.model
WHERE p.type = 'PC'
GROUP BY p.maker
ORDER BY p.maker;

-- 22. Для каждого значения скорости процессора ПК, превышающего 600 МГц,
-- найти среднюю цену ПК с такой же скоростью. Вывести поля: speed, средняя цена.
SELECT speed, AVG(price) as avg_price
FROM PC
WHERE speed > 600
GROUP BY speed
ORDER BY speed;

-- 23. Найти производителей, которые производили бы как ПК, так и ноутбуки со скоростью не менее 750 Мгц.
-- Вывести поля: maker.
SELECT DISTINCT p.maker
FROM Product p
JOIN PC ON p.model = pc.model
WHERE p.type = 'PC' AND pc.speed >= 750
INTERSECT
SELECT DISTINCT p.maker
FROM Product p
JOIN Laptop l ON p.model = l.model
WHERE p.type = 'Laptop' AND l.speed >= 750
ORDER BY maker;

-- 24. Перечислить номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
(SELECT model, price, 'PC' as type
 FROM PC
 WHERE price = (SELECT MAX(price) FROM PC))
UNION
(SELECT model, price, 'Laptop' as type
 FROM Laptop
 WHERE price = (SELECT MAX(price) FROM Laptop))
UNION
(SELECT model, price, 'Printer' as type
 FROM Printer
 WHERE price = (SELECT MAX(price) FROM Printer))
ORDER BY price DESC;

-- 25. Найти производителей принтеров, которые производят ПК с наименьшим объемом RAM
-- и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM.
-- Вывести поля: maker.
SELECT DISTINCT p.maker
FROM Product p
WHERE p.type = 'Printer'
  AND p.maker IN (
    SELECT p2.maker
    FROM Product p2
    JOIN PC ON p2.model = pc.model
    WHERE p2.type = 'PC'
      AND pc.ram = (SELECT MIN(ram) FROM PC)
      AND pc.speed = (
        SELECT MAX(speed)
        FROM PC
        WHERE ram = (SELECT MIN(ram) FROM PC)
      )
);

-- ============================================
-- ДОПОЛНИТЕЛЬНО: Скрипт для тестирования всех запросов
-- ============================================
/*
DO $$
DECLARE
    counter INTEGER := 1;
    query_text TEXT;
    result_count INTEGER;
BEGIN
    RAISE NOTICE '=== Начало тестирования 25 запросов ===';

    -- Массив с запросами для тестирования
    CREATE TEMP TABLE test_queries (
        id INTEGER,
        description TEXT,
        query TEXT
    );

    -- Вставка всех запросов (первые 5 как пример)
    INSERT INTO test_queries VALUES
    (1, 'ПК дешевле $500', 'SELECT model, speed, hd FROM PC WHERE price < 500'),
    (2, 'Производители принтеров', 'SELECT DISTINCT maker FROM Product WHERE type = ''Printer'''),
    (3, 'Ноутбуки дороже $1000', 'SELECT model, ram, screen FROM Laptop WHERE price > 1000'),
    (4, 'Цветные принтеры', 'SELECT * FROM Printer WHERE color = ''y'''),
    (5, 'ПК с CD 12x/24x и < $600', 'SELECT model, speed, hd FROM PC WHERE cd IN (''12x'', ''24x'') AND price < 600');
    -- ... можно добавить остальные запросы

    -- Тестирование
    FOR counter IN 1..5 LOOP
        query_text := (SELECT query FROM test_queries WHERE id = counter);
        EXECUTE 'SELECT COUNT(*) FROM (' || query_text || ') as t' INTO result_count;
        RAISE NOTICE 'Запрос %: % - найдено % записей',
            counter,
            (SELECT description FROM test_queries WHERE id = counter),
            result_count;
    END LOOP;

    RAISE NOTICE '=== Тестирование завершено ===';

    DROP TABLE test_queries;
END $$;
*/

-- ============================================
-- ЗАПРОС ДЛЯ ПРОВЕРКИ СТРУКТУРЫ БАЗЫ ДАННЫХ
-- ============================================
/*
SELECT
    t.table_name as "Таблица",
    COUNT(c.column_name) as "Колонок",
    (SELECT COUNT(*) FROM information_schema.table_constraints tc
     WHERE tc.table_name = t.table_name AND tc.constraint_type = 'FOREIGN KEY') as "Внешних ключей",
    (SELECT COUNT(*) FROM information_schema.table_constraints tc
     WHERE tc.table_name = t.table_name AND tc.constraint_type = 'PRIMARY KEY') as "Первичных ключей"
FROM information_schema.tables t
JOIN information_schema.columns c ON t.table_name = c.table_name
    AND t.table_schema = c.table_schema
WHERE t.table_schema = 'public'
    AND t.table_type = 'BASE TABLE'
    AND t.table_name IN ('product', 'pc', 'laptop', 'printer')
GROUP BY t.table_name
ORDER BY t.table_name;
*/

-- ============================================
-- СТАТИСТИКА ПО БАЗЕ ДАННЫХ
-- ============================================
/*
SELECT
    'Product' as table_name,
    COUNT(*) as record_count,
    (SELECT COUNT(DISTINCT maker) FROM Product) as unique_makers,
    (SELECT COUNT(DISTINCT type) FROM Product) as unique_types
FROM Product
UNION ALL
SELECT 'PC', COUNT(*), NULL, NULL FROM PC
UNION ALL
SELECT 'Laptop', COUNT(*), NULL, NULL FROM Laptop
UNION ALL
SELECT 'Printer', COUNT(*),
    (SELECT COUNT(*) FROM Printer WHERE color = 'y') as color_printers,
    (SELECT COUNT(DISTINCT type) FROM Printer) as printer_types
FROM Printer
ORDER BY table_name;
*/