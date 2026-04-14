-- =========================
-- COUNTRY: India
-- =========================
INSERT INTO countries (name, iso_code, phone_code)
VALUES ('India', 'IN', '+91')
ON CONFLICT DO NOTHING;

-- =========================
-- STATE: Bihar
-- =========================
INSERT INTO states (country_id, name, state_code)
VALUES (
           (SELECT id FROM countries WHERE iso_code = 'IN'),
           'Bihar',
           'BR'
       )
ON CONFLICT DO NOTHING;

-- =========================
-- CITIES (Bihar - Comprehensive Urban List)
-- =========================

INSERT INTO cities (state_id, name)
SELECT s.id, city
FROM states s
         JOIN countries c ON s.country_id = c.id
         CROSS JOIN (
    VALUES

        ('Patna'),
        ('Gaya'),
        ('Bhagalpur'),
        ('Muzaffarpur'),
        ('Darbhanga'),
        ('Purnia'),
        ('Ara (Arrah)'),
        ('Begusarai'),
        ('Katihar'),
        ('Munger'),
        ('Chhapra'),
        ('Bettiah'),
        ('Sasaram'),
        ('Hajipur'),
        ('Dehri'),
        ('Siwan'),
        ('Motihari'),
        ('Nawada'),
        ('Bagaha'),
        ('Buxar'),
        ('Kishanganj'),
        ('Sitamarhi'),
        ('Jamalpur'),
        ('Jehanabad'),
        ('Aurangabad'),
        ('Lakhisarai'),
        ('Sheikhpura'),
        ('Araria'),
        ('Madhepura'),
        ('Saharsa'),
        ('Samastipur'),
        ('Banka'),
        ('Khagaria'),
        ('Supaul'),
        ('Sheohar'),
        ('Bhabua (Kaimur)'),
        ('Forbesganj'),
        ('Barh'),
        ('Bakhtiarpur'),
        ('Danapur'),
        ('Phulwari Sharif'),
        ('Hilsa'),
        ('Barbigha'),
        ('Rajgir'),
        ('Hisua'),
        ('Raxaul'),
        ('Marhaura'),
        ('Dalsinghsarai'),
        ('Rosera'),
        ('Barsoi'),
        ('Narkatiaganj')

) AS city_list(city)
WHERE c.iso_code = 'IN'
  AND s.state_code = 'BR';