INSERT INTO `category` (`id`, `category_name`)
SELECT 1, '족발·보쌈'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 1);

INSERT INTO `category` (`id`, `category_name`)
SELECT 2, '찜·탕·찌개'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 2);

INSERT INTO `category` (`id`, `category_name`)
SELECT 3, '돈까스·회·일식'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 3);

INSERT INTO `category` (`id`, `category_name`)
SELECT 4, '피자'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 4);

INSERT INTO `category` (`id`, `category_name`)
SELECT 5, '고기·구이'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 5);

INSERT INTO `category` (`id`, `category_name`)
SELECT 6, '양식'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 6);

INSERT INTO `category` (`id`, `category_name`)
SELECT 7, '치킨'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 7);

INSERT INTO `category` (`id`, `category_name`)
SELECT 8, '중식'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 8);

INSERT INTO `category` (`id`, `category_name`)
SELECT 9, '아시안'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 9);

INSERT INTO `category` (`id`, `category_name`)
SELECT 10, '백반·죽·국수'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 10);

INSERT INTO `category` (`id`, `category_name`)
SELECT 11, '도시락'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 11);

INSERT INTO `category` (`id`, `category_name`)
SELECT 12, '분식'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 12);

INSERT INTO `category` (`id`, `category_name`)
SELECT 13, '패스트푸드'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 13);

INSERT INTO `category` (`id`, `category_name`)
SELECT 14, '카페·디저트'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 14);

INSERT INTO `category` (`id`, `category_name`)
SELECT 15, '기타'
    WHERE NOT EXISTS (SELECT 1 FROM `category` WHERE `id` = 15);