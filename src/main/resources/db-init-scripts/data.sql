INSERT INTO shortened_url (short_url, origin_url, created_at) VALUES
('http://short.url/abc', 'http://example.com/page1', '2024-04-01 10:00:00'),
('http://short.url/def', 'http://example.com/page2', '2024-04-02 12:00:00'),
('http://short.url/ghi', 'http://example.com/page3', '2024-04-03 14:00:00'),
('http://short.url/jkl', 'http://example.com/page4', '2024-04-04 16:00:00'),
('http://short.url/mno', 'http://example.com/page5', '2024-04-05 18:00:00');

INSERT INTO url_click (shortened_url_id, clicks, click_date, created_at) VALUES
(1, 10, '2024-04-01', '2024-04-01 10:00:00'),
(1, 40, '2024-04-02', '2024-04-02 10:00:00'),
(1, 25, '2024-04-03', '2024-04-03 10:00:00');