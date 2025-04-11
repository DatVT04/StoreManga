--drop DATABASE StoreManga;
--create DATABASE StoreManga
--ALTER DATABASE StoreManga SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
--DROP DATABASE StoreManga;

USE StoreManga;

-- Roles
CREATE TABLE Roles (
    RoleID INT PRIMARY KEY,
    RoleName VARCHAR(50)
);

-- Users
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) ,
    Username VARCHAR(50) PRIMARY KEY NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Email VARCHAR(100),
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Roles(RoleID)
);

--Profile
CREATE TABLE Profile (
    ProfileID INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    FirstName NVARCHAR(100),
	LastName NVARCHAR(100),
	Email VARCHAR(50),
    PhoneNumber VARCHAR(20),
    Address NVARCHAR(MAX),
    Gender BIT
);

-- Categories
CREATE TABLE Categories (
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    description NVARCHAR(MAX)
);

--Products
CREATE TABLE Products (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    releaseDate DATE NOT NULL,
    description  NVARCHAR(MAX) NOT NULL,
    image NVARCHAR(255) NOT NULL,
    cid INT NOT NULL,
    FOREIGN KEY (cid) REFERENCES Categories(id)
);

--Cart
CREATE TABLE Cart (
    CartID INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (Username) REFERENCES Users(Username),
    FOREIGN KEY (ProductID) REFERENCES Products(id)
);


--Orders
CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    Date DATE NOT NULL,
    UserName VARCHAR(50) NOT NULL, 
    TotalMoney DECIMAL(18, 2) NOT NULL,
    Status BIT NOT NULL, 
    CONSTRAINT FK_Order_User FOREIGN KEY (UserName) REFERENCES Users (Username) ON DELETE NO ACTION
);

--OrderDetails
CREATE TABLE OrderDetails (
    OrderDetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(18, 2) NOT NULL,
    CONSTRAINT FK_OrderDetail_Order FOREIGN KEY (OrderID) REFERENCES Orders (OrderID) ON DELETE CASCADE,
    CONSTRAINT FK_OrderDetail_Product FOREIGN KEY (ProductID) REFERENCES Products (id) ON DELETE CASCADE
);


INSERT INTO Roles (RoleID, RoleName)
VALUES (1, 'Admin'),
       (2, 'User');


INSERT INTO Users (Username, Password, Email, RoleID)
VALUES ('admin', 'admin', 'admin@example.com', 1),
       ('user1', '123', 'user1@example.com', 2),
       ('user2', '123', 'user2@example.com', 2);

INSERT INTO Profile (Username, FirstName, LastName, Email, PhoneNumber, Address, Gender)
VALUES 
    ('user1', N'Nguyen', N'Van A', 'nguyenvana@example.com', '0123456789', N'123 Le Loi, Ha Noi', 1),
    ('user2', N'Tran', N'Thi B', 'tranthib@example.com', '0987654321', N'456 Nguyen Trai, Ho Chi Minh City', 0);


INSERT INTO Categories (id, name, description) VALUES
    (1,N'Truyện tranh', N'Truyện tranh là một thể loại nghệ thuật kết hợp giữa hình ảnh và văn bản, thường thể hiện các câu chuyện từ đời sống đến các chủ đề kỳ ảo, phù hợp với nhiều đối tượng độc giả.'),
    (2,N'Truyện cổ tích', N'Truyện cổ tích là những câu chuyện dân gian mang tính giáo dục, thường có các yếu tố kỳ diệu và truyền tải các bài học cuộc sống, thường dành cho trẻ em.'),
    (3,N'Truyện ngắn', N'Truyện ngắn là thể loại văn học có độ dài ngắn, thường xoay quanh một sự kiện hoặc một tình huống nhất định, giúp độc giả dễ dàng tiếp nhận thông điệp.'),
    (4,N'Truyện trinh thám', N'Truyện trinh thám thường kể về các vụ án, các nhân vật điều tra và tìm kiếm sự thật, thu hút độc giả với những tình tiết gây cấn và bất ngờ.'),
	(5,N'Truyện hài', N'Truyện hài là thể loại truyện mang tính chất giải trí, thường có các tình huống dở khóc dở cười và những nhân vật hài hước, mang lại tiếng cười cho độc giả.');

INSERT INTO Products (name, quantity, price, releaseDate, description, image, cid) VALUES
--Truyện tranh
('Kimetsu No Yaiba', 10, 34000, '2023-10-02', 
 N'Kimetsu No Yaiba (Demon Slayer) kể về Tanjiro Kamado, một cậu bé trở thành thợ săn quỷ sau khi gia đình bị giết hại bởi quỷ. Cậu chiến đấu để cứu em gái Nezuko, người đã bị biến thành quỷ, trong hành trình tìm kiếm cách đảo ngược lời nguyền này.', 
 'https://cdnmedia.baotintuc.vn/Upload/EqV5H9rWgvy9oNikwkHLXA/files/phim.jpg', 1),

('SPY x FAMILY', 12, 46000 , '2023-11-02', 
 N'SPY x FAMILY kể về điệp viên Loid Forger, người phải tạo dựng một gia đình giả để thực hiện nhiệm vụ bí mật. Anh nhận nuôi cô bé Anya có khả năng đọc suy nghĩ và kết hôn với Yor, một sát thủ, nhưng cả ba đều che giấu thân phận thật của mình.', 
 'https://m.media-amazon.com/images/M/MV5BZDkwNjc0NWEtNzJlOC00N2YwLTk4MjktZGFlZDE2Y2QzOWI0XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 1),

('Conan', 13, 38000, '2016-10-02', 
 'Thám Tử Lừng Danh Conan kể về Shinichi Kudo, một thám tử trung học bị biến thành cậu bé Conan sau khi bị đầu độc. Dưới hình dạng trẻ con, Conan tiếp tục phá án và truy tìm tổ chức đứng sau âm mưu hãm hại mình.', 
 'https://upload.wikimedia.org/wikipedia/vi/6/63/Nhan_vat_Tham_tu_lung_danh_Conan.jpg', 1),

('Naruto',14, 32000, '2023-10-02', 
 N'Naruto Uzumaki, một ninja trẻ tuổi đầy nhiệt huyết, nuôi ước mơ trở thành Hokage để được công nhận trong ngôi làng của mình.', 
 'https://m.media-amazon.com/images/M/MV5BZTNjOWI0ZTAtOGY1OS00ZGU0LWEyOWYtMjhkYjdlYmVjMDk2XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 1),

('One Piece', 5,  44000 , '2023-05-02', 
 N'Monkey D. Luffy và đồng đội tìm kiếm kho báu One Piece huyền thoại để trở thành Vua Hải Tặc.', 
 'https://imgsrv.crunchyroll.com/cdn-cgi/image/fit=contain,format=auto,quality=85,width=480,height=720/catalog/crunchyroll/757bae5a21039bac6ebace5de9affcd8.jpg', 1),

('Dragon Ball', 6,  58000, '2009-07-02', 
 N'"Dragon Ball" kể về cuộc hành trình của Son Goku, một cậu bé có đuôi khỉ và sức mạnh phi thường. Goku lớn lên trong một ngôi làng nhỏ và tình cờ gặp Bulma, người đang tìm kiếm 7 viên ngọc rồng huyền thoại (Dragon Balls). Khi tập hợp đủ 7 viên ngọc này, người sở hữu có thể triệu hồi rồng thần Shenron và được ban tặng một điều ước bất kỳ.', 
 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhHd8xnqiJjdG1bsW1Dr7Dck8umBeDsYSHLw&s', 1),

	-- Truyện cổ tích
('Attack on Titan', 3, 43300, '2023-05-01', 
N'Nhân loại chiến đấu chống lại những người khổng lồ ăn thịt người để bảo vệ sự sống còn của họ.', 
'https://m.media-amazon.com/images/M/MV5BNjY4MDQxZTItM2JjMi00NjM5LTk0MWYtOTBlNTY2YjBiNmFjXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 2),

('Doraemon', 3, 56000, '2023-05-11', 
N'Doraemon kể về chú mèo máy từ tương lai giúp cậu bé Nobita vượt qua khó khăn trong cuộc sống. Với các bảo bối kỳ diệu, họ trải qua nhiều tình huống hài hước và ý nghĩa về tình bạn.', 
'https://cdn0.fahasa.com/media/catalog/product/d/o/doraemon-movie-story-mau_nobita-va-cuoc-dai-thuy-chien-o-xu-so-nguoi-ca_bia.jpg', 2),

('Fullmetal Alchemist', 3, 38000, '2019-09-08',
N'Hai anh em tìm kiếm Hòn đá Phù thủy để khôi phục lại cơ thể.', 
'https://m.media-amazon.com/images/I/819gbwpjLcL._AC_UF1000,1000_QL80_.jpg', 2),

('Bleach', 4, 96000, '2023-08-09', N'Ichigo trở thành Thần chết để bảo vệ thế giới khỏi các linh hồn.', 
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQ2UB2qNjYSqYnTOi5iQw3Wn4hV0iW8zXRVg&s', 2),


	-- Truyện trinh thám
('Death Note', 1, 39000, '2023-02-11',
'Một học sinh trung học sử dụng cuốn sổ tử thần để loại bỏ tội phạm.',
'https://upload.wikimedia.org/wikipedia/en/thumb/7/72/Death_Note_Characters.jpg/220px-Death_Note_Characters.jpg', 4),

('Fairy Tail', 50, 31000, '2022-01-12',
'Nhóm pháp sư tham gia vào các nhiệm vụ phép thuật khác nhau.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4tbpJnzuTdET8Z3DQFSu6i1kVfQBLl1afPA&s', 4),

('Black Clover', 75, 34000, '2023-01-14',
'Asta, một cậu bé không có phép thuật, ước mơ trở thành Vua Pháp Sư.',
'https://imgsrv.crunchyroll.com/cdn-cgi/image/fit=contain,format=auto,quality=85,width=480,height=720/catalog/crunchyroll/e108ae17d8d0407417cac40eb52d849a.jpg', 4),

('JoJo’s Bizarre Adventure', 12, 43000, '2020-11-12',
'Gia đình Joestar chiến đấu với các kẻ thù siêu nhiên qua nhiều thế hệ.',
'https://upload.wikimedia.org/wikipedia/en/a/aa/JoJo_Part_1_Phantom_Blood.jpg', 4),

('Hunter x Hunter', 80, 38300, '2023-12-09',
'Gon tìm kiếm cha mình trong hành trình trở thành một Thợ săn huyền thoại.',
'https://m.media-amazon.com/images/M/MV5BYzYxOTlkYzctNGY2MC00MjNjLWIxOWMtY2QwYjcxZWIwMmEwXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg', 4),

('The Promised Neverland', 80, 46500, '2023-03-09',
'Các trẻ mồ côi khám phá số phận thực sự của mình và lên kế hoạch trốn thoát.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtJti-Z7UOHoBbhzf1_XR2-ozaplk16XNh9Q&s', 4),

('Seven Deadly Sins', 90, 20000, '2023-02-02',
'Những hiệp sĩ chiến đấu chống lại ma quỷ để cứu vương quốc của mình.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsXzuzvXmUrEWuNfTvQLAuC1HQ8b2-XSA6UQ&s', 4),

('Blue Exorcist', 110, 12000, '2022-02-02',
'Rin Okumura, con trai của Satan, được huấn luyện để chiến đấu với quỷ dữ.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsXzuzvXmUrEWuNfTvQLAuC1HQ8b2-XSA6UQ&s', 4),

	-- Truyện ngắn
	('My Hero Academia', 1, 78000, '2009-03-11',
'Một thiếu niên có khả năng tâm linh đối phó với các thực thể siêu nhiên.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMw07B7wRB6yHfRIsoHMI_M7T3entavqypuA&s', 3),

('Tokyo Ghoul', 2, 36000, '2023-04-12',
'Kaneki đấu tranh giữa hai bản chất con người và quỷ ăn thịt của mình.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoIaQieHMvG6Q3QfPwUvLtfHuMaXkEeZd4ZQ&s', 3),

('Vinland Saga', 3, 23000, '2023-12-05',
'Các chiến binh Viking tìm kiếm sự trả thù và hòa bình.',
'https://static.wikia.nocookie.net/mob-psycho-100/images/8/8b/Mob_Psycho_100_Cover.jpg/revision/latest?cb=20161108223356', 3),

('Mob Psycho 100', 4, 19000, '2023-11-12',
'Lính cứu hỏa có năng lực siêu nhiên chiến đấu với hiện tượng tự phát hỏa.',
'https://upload.wikimedia.org/wikipedia/en/0/06/Fire_Force%2C_Volume_1.jpg', 3),


	-- Truyện hài
   ('Chainsaw Man', 1, 51000, '2023-04-12',
'Một thợ săn quỷ hợp nhất với quỷ cưa máy để chiến đấu với kẻ thù.',
'https://product.hstatic.net/200000280811/product/chainsaw-man-tap-1-01_75b15ee85bd04054bf6d36a5852fa9d7_cb7af4d231d044709bd0635de8a2b0be_master.jpg', 5),

('Fire Force', 2, 87000, '2023-05-11',
'Hai học sinh tiến hành cuộc chiến tâm lý để buộc người kia phải thổ lộ tình cảm trước.',
'https://upload.wikimedia.org/wikipedia/en/8/87/Kaguya-sama_Love_is_War_art.jpg', 5),

('Kaguya-sama: Love is War', 3, 31000, '2022-12-11',
'Subaru Natsuki bị đưa đến một thế giới giả tưởng nơi anh có thể quay ngược thời gian.',
'https://imgsrv.crunchyroll.com/cdn-cgi/image/fit=contain,format=auto,quality=85,width=480,height=720/catalog/crunchyroll/ecde98e22bf07699fc233530053b475c.jpg', 5),

(N'Re:Zero', 4, 16800, '2023-06-12',
'Guts, một lính đánh thuê đơn độc, chiến đấu với các thế lực bóng tối trong một thế giới trung cổ.',
'https://imgsrv.crunchyroll.com/cdn-cgi/image/fit=contain,format=auto,quality=85,width=480,height=720/catalog/crunchyroll/ecde98e22bf07699fc233530053b475c.jpg', 5),

(N'Berserk', 5, 89000, '2023-09-12',
'Những hiệp sĩ chiến đấu chống lại ma quỷ để cứu vương quốc của mình.',
'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsXzuzvXmUrEWuNfTvQLAuC1HQ8b2-XSA6UQ&s', 5);


--Select ord.OrderID, p.image, p.name,ord.ProductID, ord.Quantity, ord.UnitPrice, od.TotalMoney 
--                from  OrderDetails ord join Orders od 
--				on ord.OrderID = od.OrderID 
--				join Products p on p.id= ord.ProductID
--				Where od.UserName='anhpark';

go

-- khi email cua profile update thì user cũng update theo
CREATE TRIGGER UpdateUserEmailFromProfile
ON Profile
AFTER UPDATE
AS
BEGIN
    UPDATE Users
    SET 
        Email = i.Email
    FROM Users u
    INNER JOIN inserted i ON u.Username = i.Username;
END;

