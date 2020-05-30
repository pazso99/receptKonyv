-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2019. Nov 17. 12:56
-- Kiszolgáló verziója: 10.3.16-MariaDB
-- PHP verzió: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `recept`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `alapanyag`
--

CREATE TABLE `alapanyag` (
  `id` int(11) NOT NULL,
  `nev` varchar(50) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `alapanyag`
--

INSERT INTO `alapanyag` (`id`, `nev`) VALUES
(1, 'paradicsomlé'),
(2, 'finomliszt'),
(3, 'napraforgó olaj'),
(4, 'vöröshagyma'),
(5, 'cukor'),
(6, 'betűtészta'),
(7, 'tyúkhús'),
(8, 'fokhagyma'),
(9, 'só'),
(10, 'bors'),
(11, 'víz'),
(12, 'répa'),
(13, 'zeller'),
(14, 'karalábé'),
(15, 'tojás'),
(16, 'tej'),
(17, 'olaj'),
(18, 'liszt'),
(19, 'sajt'),
(20, 'porcukor'),
(21, 'kakaópor'),
(22, 'étcsokoládé'),
(23, 'habtejszín'),
(28, 'rétesliszt'),
(29, 'vaj'),
(30, 'burgonya'),
(31, 'tejföl'),
(32, 'élesztő'),
(33, 'trapista sajt'),
(34, 'víz(meleg)');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `etel`
--

CREATE TABLE `etel` (
  `id` int(11) NOT NULL,
  `nev` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `tipus` varchar(10) COLLATE utf8_hungarian_ci NOT NULL,
  `ido` int(11) NOT NULL,
  `fo` int(11) NOT NULL,
  `elkeszites` varchar(2000) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `etel`
--

INSERT INTO `etel` (`id`, `nev`, `tipus`, `ido`, `fo`, `elkeszites`) VALUES
(1, 'Paradicsomleves', 'Leves', 23, 2, 'Az olajból és a lisztből rántást készítünk.\r\nFelöntjük a hideg paradicsomlével, és elkeverjük.\r\nBeletesszük a hagymát, ízlés szerint adunk hozzá cukrot, sót, borsot.\r\nFelforraljuk, forralás után kb. 10 percig főzzük.\r\nA betűtésztát sós vízben kifőzzük, csak tálaláskor tegyük a levesbe, mert megszívja magát.\r\nHa később felmelegítjük, egy kis vizet tegyünk bele, mert besűrűsödik.'),
(2, 'Húsleves', 'Leves', 255, 6, 'A tyúkot felvágjuk, átmossuk, a szívet, májat, tojást, zúzát kivesszük.\r\nEgy nagy fazékba öntünk 4 liter hideg vizet, beletesszük a húsokat, és közepes, nagy lángon felforraljuk. Ha felforrt, kis lángra vesszük, szűrővel lehabozzuk, hozzáadjuk a két végétől megszabadított, megmosott vöröshagymát és a fokhagymát, sózzuk, hozzáadjuk a borsot, és kis lángon, gyöngyöztetve főzzük másfél órát.\r\nHa letelt a másfél óra, és a leves finoman gyöngyözik, hozzáadjuk a megpucolt zellert és a karalábét.\r\nÚjabb 1,5 órát főzzük. Ha az idő letelt, megnézzük, hogy áll a hús - ha láthatóan puhul, hozzáadjuk a megpucolt, hasábra vágott répákat, és még egy órát gyöngyöztetjük. Ha szeretjük a zúzát és a szívet a levesben, azt is ilyenkor tesszük bele.\r\nHa letelt az egy óra,  megpuhultak a húsok és a zöldségek is, hozzáadjuk a csokor petrezselymet, a májat, és ha szeretjük, a tojásokat. Még tíz percig főzzük, majd elzárjuk alatta a lángot.\r\nA zöldségeket és a húsokat kivesszük, és külön tálba tesszük. A levest leszűrjük, és főtt csigatésztával, a hússal és a zöldségekkel kínáljuk.'),
(6, 'Fagyi', 'Sütemény', 20, 4, 'A tojásokat szétválasztjuk, a sárgáját fehéredésig keverjük a cukor felével és a kakaót is hozzáadjuk a keverékhez.\r\nA 10 dkg étcsokoládét gőz fölött megolvasztjuk, majd félretesszük, ha már kicsit kihűlt, hozzáadjuk az előző keverékhez.\r\nElektromos habverővel felverjük a behűtött tejszínt, és a krémbe forgatjuk. A tojásfehérjét a maradék cukorral habosra verjük, ezt is beforgatjuk, hozzákeverjük a maradék feldarabolt étcsokoládét.\r\nElsimítjuk egy fagylaltos tálba, s legalább 6 órára a fagyasztóba tesszük. 7,5 dl fagylalt lesz belőle.'),
(12, 'Pogácsa', 'Sütemény', 60, 4, 'A tejet a cukorral meglangyosítjuk, az élesztőt felfuttatjuk benne.\r\nA sajt és a lekenéshez való vaj kivételével az összes hozzávalóból tésztát gyúrunk. Az áttört krumpli még langyosan kerül bele a tálba. Miután szép egynem?re összegyúrtuk, lisztezett deszkán ujjnyi vastagra nyújtjuk, és megkenjük olvasztott vajjal, utána ráreszeljük a sajt majdnem felét. Hajtogatjuk, majd letakarva fél órát pihentetjük.\r\nFél óra múlva újra kinyújtjuk, megkenjük újra vajjal, és megszórjuk reszelt sajttal. Annyi sajtot hagyunk még, hogy szaggatás után meg tudjuk szórni a pogácsák tetejét. Újra meghajtjuk, majd megint pihentetjük fél órát.\r\nVégül 1 cm vastagra kinyújtjuk (ez most több lett, így a süt?ben a pogácsák jól el is d?ltek), majd kiszaggatjuk. A pogácsákat felvert tojással megkenjük, süt?papírral bélelt tepsire rakjuk méretes közökkel, mert sülés közben szépen megnőnek.\r\n200 fokra előmelegített sütőben, légkeverésen kb. 15 perc alatt megsül.'),
(13, 'Pizza', 'Főétel', 20, 4, 'A tészta hozzávalóit összegyúrjuk, kelesztőtálba tesszük fél órára.\r\nHa megkelt, kerekre kinyújtjuk, kerek sütőbe tesszük, a szósszal megkenjük, feltéteket rárakjuk.\r\nElőmelegített sütőben megsütjük.');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hozzadad`
--

CREATE TABLE `hozzadad` (
  `etel.id` int(11) NOT NULL,
  `alapanyag.id` int(11) NOT NULL,
  `mennyiseg` int(11) NOT NULL,
  `mertekegyseg` varchar(20) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `hozzadad`
--

INSERT INTO `hozzadad` (`etel.id`, `alapanyag.id`, `mennyiseg`, `mertekegyseg`) VALUES
(1, 6, 20, 'dkg'),
(1, 5, 3, 'ek'),
(1, 2, 3, 'ek'),
(1, 3, 3, 'ek'),
(1, 1, 7, 'dl'),
(1, 4, 1, 'fej'),
(2, 7, 1, 'kg'),
(2, 4, 1, 'db'),
(2, 8, 1, 'db'),
(2, 9, 0, 'ízlés szerint'),
(2, 13, 1, 'db'),
(2, 14, 1, 'db'),
(2, 12, 3, 'db'),
(2, 11, 4, 'l'),
(6, 15, 2, 'db'),
(6, 20, 4, 'ek'),
(6, 21, 2, 'ek'),
(6, 22, 15, 'dkg'),
(6, 23, 3, 'dl'),
(12, 2, 40, 'dkg'),
(12, 28, 20, 'dkg'),
(12, 29, 15, 'dkg'),
(12, 30, 20, 'dkg'),
(12, 15, 2, 'db'),
(12, 31, 2, 'dl'),
(12, 9, 1, 'ek'),
(12, 16, 1, 'dl'),
(12, 5, 3, 'csipet'),
(12, 32, 3, 'dkg'),
(12, 33, 30, 'dkg'),
(13, 2, 20, 'dkg'),
(13, 34, 1, 'dl'),
(13, 3, 2, 'ek'),
(13, 32, 3, 'csipet'),
(13, 5, 6, 'csipet'),
(13, 9, 2, 'csipet');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `alapanyag`
--
ALTER TABLE `alapanyag`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `etel`
--
ALTER TABLE `etel`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `hozzadad`
--
ALTER TABLE `hozzadad`
  ADD KEY `etel.id` (`etel.id`),
  ADD KEY `mennyiseg.id` (`alapanyag.id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `alapanyag`
--
ALTER TABLE `alapanyag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT a táblához `etel`
--
ALTER TABLE `etel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `hozzadad`
--
ALTER TABLE `hozzadad`
  ADD CONSTRAINT `hozzadad_ibfk_1` FOREIGN KEY (`etel.id`) REFERENCES `etel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `hozzadad_ibfk_2` FOREIGN KEY (`alapanyag.id`) REFERENCES `alapanyag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
