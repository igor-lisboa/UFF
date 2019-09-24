/*
GRUPO:
IGOR LISBOA
CAIO WHEY
PAOLA MOREIRA
MATHEUS FERNANDES
DIOGO VALENTE

Cidade(Codigo, Nome)

Pessoa (Codigo, Nome, Telefone, Codigo_Cidade)
    Codigo_Cidade Referencia Cidade

Escola(Codigo, Nome, Codigo_Cidade, Codigo_Pessoa_Diretor)
    Codigo_Cidade Referencia Cidade
    Codigo_Pessoa_Diretor Referencia Professor
    
Professor (Codigo_Pessoa, RG, CPF, Titulação)
    Codigo_Pessoa Referencia Pessoa
    
Turma (Codigo, Nome, Codigo_Escola)
    Codigo_Escola Referencia Escola
    
Disciplina (Codigo, Nome)

Ministra (Codigo_Pessoa_Professor, Codigo_Disciplina,Codigo_Turma)
    Codigo_Pessoa_Professor Referencia Pessoa
    Codigo_Disciplina Referencia Disciplina
    Codigo_Turma Referencia Turma

Aluno (Codigo_Pessoa_Aluno, Matricula_Aluno, Data_Nascimento, Codigo_Turma)
    Codigo_Pessoa_Aluno Referencia Pessoa
    Codigo_Turma Referencia Turma

Contato (Codigo_Pessoa_Aluno,Nome, Telefone)
    Codigo_Pessoa_Aluno Referencia Pessoa
*/
/*


------------------------------------------------------------------------------------------------------------------



*/
/* CREATE SCHEMA E TABLES */
DROP SCHEMA
IF EXISTS `trab_bd`;

CREATE SCHEMA
IF NOT EXISTS `trab_bd`;

USE `trab_bd`;

DROP TABLE
IF EXISTS `trab_bd`.`cidade`;

CREATE TABLE `trab_bd`.`cidade` (
	`codigo` INT (5) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR (50) NOT NULL,
	PRIMARY KEY (`codigo`)
);

DROP TABLE
IF EXISTS `trab_bd`.`pessoa`;

CREATE TABLE `trab_bd`.`pessoa` (
	`codigo` INT (5) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR (50) NOT NULL,
	`telefone` VARCHAR (50) NOT NULL,
	`codigo_cidade` INT (5) NOT NULL,
	PRIMARY KEY (`codigo`)
);

DROP TABLE
IF EXISTS `trab_bd`.`escola`;

CREATE TABLE `trab_bd`.`escola` (
	`codigo` INT (5) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR (50) NOT NULL,
	`codigo_pessoa_diretor` INT (5) NOT NULL,
	`codigo_cidade` INT (5) NOT NULL,
	PRIMARY KEY (`codigo`)
);

DROP TABLE
IF EXISTS `trab_bd`.`professor`;

CREATE TABLE `trab_bd`.`professor` (
	`codigo_pessoa_professor` INT (5) NOT NULL,
	`rg` INT (50) NOT NULL,
	`cpf` INT (50) NOT NULL,
	`titulacao` VARCHAR (50) DEFAULT NULL,
	PRIMARY KEY (`codigo_pessoa_professor`)
);

DROP TABLE
IF EXISTS `trab_bd`.`turma`;

CREATE TABLE `trab_bd`.`turma` (
	`codigo` INT (5) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR (50) NOT NULL,
	`codigo_escola` INT (5) NOT NULL,
	PRIMARY KEY (`codigo`)
);

DROP TABLE
IF EXISTS `trab_bd`.`disciplina`;

CREATE TABLE `trab_bd`.`disciplina` (
	`codigo` INT (5) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR (50) NOT NULL,
	PRIMARY KEY (`codigo`)
);

DROP TABLE
IF EXISTS `trab_bd`.`ministra`;

CREATE TABLE `trab_bd`.`ministra` (
	`codigo_pessoa_professor` INT (5) NOT NULL,
	`codigo_disciplina` INT (5) NOT NULL,
	`codigo_turma` INT (5) NOT NULL,
	PRIMARY KEY (
		`codigo_pessoa_professor`,
		`codigo_disciplina`,
		`codigo_turma`
	)
);

DROP TABLE
IF EXISTS `trab_bd`.`aluno`;

CREATE TABLE `trab_bd`.`aluno` (
	`codigo_pessoa_aluno` INT (5) NOT NULL,
	`matricula_aluno` INT (5) NOT NULL,
	`data_nascimento` date NOT NULL,
	`codigo_turma` INT (5) NOT NULL,
	PRIMARY KEY (`codigo_pessoa_aluno`)
);

DROP TABLE
IF EXISTS `trab_bd`.`contato`;

CREATE TABLE `trab_bd`.`contato` (
	`codigo_pessoa_aluno` INT (5) NOT NULL,
	`nome` VARCHAR (50) NOT NULL,
	`telefone` INT (50) NOT NULL,
	PRIMARY KEY (
		`codigo_pessoa_aluno`,
		`nome`
	)
);

/* ALTERS TABLE */
ALTER TABLE `trab_bd`.`pessoa` ADD FOREIGN KEY (`codigo_cidade`) REFERENCES `trab_bd`.`cidade` (`codigo`);

ALTER TABLE `trab_bd`.`escola` ADD FOREIGN KEY (`codigo_cidade`) REFERENCES `trab_bd`.`cidade` (`codigo`);

ALTER TABLE `trab_bd`.`escola` ADD FOREIGN KEY (`codigo_pessoa_diretor`) REFERENCES `trab_bd`.`professor` (`codigo_pessoa_professor`);

ALTER TABLE `trab_bd`.`professor` ADD FOREIGN KEY (`codigo_pessoa_professor`) REFERENCES `trab_bd`.`pessoa` (`codigo`);

ALTER TABLE `trab_bd`.`turma` ADD FOREIGN KEY (`codigo_escola`) REFERENCES `trab_bd`.`escola` (`codigo`);

ALTER TABLE `trab_bd`.`ministra` ADD FOREIGN KEY (`codigo_pessoa_professor`) REFERENCES `trab_bd`.`professor` (`codigo_pessoa_professor`);

ALTER TABLE `trab_bd`.`ministra` ADD FOREIGN KEY (`codigo_disciplina`) REFERENCES `trab_bd`.`disciplina` (`codigo`);

ALTER TABLE `trab_bd`.`ministra` ADD FOREIGN KEY (`codigo_turma`) REFERENCES `trab_bd`.`turma` (`codigo`);

ALTER TABLE `trab_bd`.`aluno` ADD FOREIGN KEY (`codigo_pessoa_aluno`) REFERENCES `trab_bd`.`pessoa` (`codigo`);

ALTER TABLE `trab_bd`.`aluno` ADD FOREIGN KEY (`codigo_turma`) REFERENCES `trab_bd`.`turma` (`codigo`);

ALTER TABLE `trab_bd`.`contato` ADD FOREIGN KEY (`codigo_pessoa_aluno`) REFERENCES `trab_bd`.`aluno` (`codigo_pessoa_aluno`);

/*


------------------------------------------------------------------------------------------------------------------



*/
/* INSERTS */
INSERT INTO `trab_bd`.`cidade` (`nome`)
VALUES
	('Niteroi'),
	('Rio de Janeiro'),
	('Barra do Pirai');

INSERT INTO `trab_bd`.`pessoa` (
	`nome`,
	`telefone`,
	`codigo_cidade`
)
VALUES
	(
		'Igor Lisboa',
		'21997457343',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Rio de Janeiro'
		)
	),
	(
		'Octavio Costa',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Paola Gurgel',
		2453543509,
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	('Luisa Cunha', 24323209, 1),
	(
		'Bruna Monteiro',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Beatriz Marques',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Isabella Soares',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Lucas Beghe',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Gustavo Luppi',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Lara Cariello',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Paola Moreira',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Rio de Janeiro'
		)
	),
	(
		'Caio Barros',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Rio de Janeiro'
		)
	),
	(
		'Matheus Francois',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Diegon',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Celmo',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Vania',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Daniel',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Oscar',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Salvador',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Flavio',
		'2453543509',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	);

INSERT INTO `trab_bd`.`professor` (
	`codigo_pessoa_professor`,
	`rg`,
	`cpf`,
	`titulacao`
)
VALUES
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Salvador'
		),
		674747,
		248388443,
		'PhD'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Diegon'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Celmo'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Vania'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Daniel'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Oscar'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Flavio'
		),
		2147547,
		24555433,
		'Pos-Graduacao'
	);

INSERT INTO `trab_bd`.`escola` (
	`nome`,
	`codigo_pessoa_diretor`,
	`codigo_cidade`
)
VALUES
	(
		'UFF',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Salvador'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	),
	(
		'Salesiano',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Oscar'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`cidade`
			WHERE
				nome = 'Niteroi'
		)
	);

INSERT INTO `trab_bd`.`turma` (`nome`, `codigo_escola`)
VALUES
	(
		'1 PERIODO',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`escola`
			WHERE
				nome = 'UFF'
		)
	),
	(
		'NM321',
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`escola`
			WHERE
				nome = 'Salesiano'
		)
	);

INSERT INTO `trab_bd`.`disciplina` (`nome`)
VALUES
	('MATEMATICA'),
	('FISICA'),
	('PORTUGUES'),
	('HISTORIA'),
	('GEOGRAFIA'),
	('BD'),
	('PROG');

INSERT INTO `trab_bd`.`ministra` (
	`codigo_pessoa_professor`,
	`codigo_disciplina`,
	`codigo_turma`
)
VALUES
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Flavio'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'PROG'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Salvador'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'BD'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Oscar'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'MATEMATICA'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Diegon'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'FISICA'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Vania'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'PORTUGUES'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Celmo'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'HISTORIA'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Daniel'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`disciplina`
			WHERE
				nome = 'GEOGRAFIA'
		),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	);

INSERT INTO `trab_bd`.`aluno` (
	`codigo_pessoa_aluno`,
	`matricula_aluno`,
	`data_nascimento`,
	`codigo_turma`
)
VALUES
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Igor Lisboa'
		),
		1,
		STR_TO_DATE('15-12-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Caio Barros'
		),
		2,
		STR_TO_DATE('21-03-1995', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Matheus Francois'
		),
		3,
		STR_TO_DATE('02-05-1980', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Paola Moreira'
		),
		4,
		STR_TO_DATE('01-01-1999', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = '1 PERIODO'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Octavio Costa'
		),
		5,
		STR_TO_DATE('01-02-1999', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Paola Gurgel'
		),
		6,
		STR_TO_DATE('01-03-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Luisa Cunha'
		),
		7,
		STR_TO_DATE('01-06-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Bruna Monteiro'
		),
		8,
		STR_TO_DATE('01-08-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Beatriz Marques'
		),
		9,
		STR_TO_DATE('01-08-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Isabella Soares'
		),
		10,
		STR_TO_DATE('01-08-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Lucas Beghe'
		),
		11,
		STR_TO_DATE('11-10-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Gustavo Luppi'
		),
		12,
		STR_TO_DATE('11-05-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Lara Cariello'
		),
		13,
		STR_TO_DATE('11-05-1998', '%d-%m-%Y'),
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`turma`
			WHERE
				nome = 'NM321'
		)
	);

INSERT INTO `trab_bd`.`contato` (
	`codigo_pessoa_aluno`,
	`nome`,
	`telefone`
)
VALUES
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Igor Lisboa'
		),
		'Luciano da Silva Lisboa',
		26107456
	),
	(
		(
			SELECT
				codigo
			FROM
				`trab_bd`.`pessoa`
			WHERE
				nome = 'Igor Lisboa'
		),
		'Rosane de Mattos Rodrigues',
		2610556
	);

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (1)*/
SELECT
	e.nome AS `escola`,
	c.nome AS `cidade`
FROM
	`trab_bd`.`escola` e,
	`trab_bd`.`cidade` c
WHERE
	e.codigo_cidade = c.codigo
AND NOT EXISTS (
	SELECT
		pe.nome AS `aluno`
	FROM
		`trab_bd`.`escola` es,
		`trab_bd`.`cidade` ci,
		`trab_bd`.`turma` tu,
		`trab_bd`.`aluno` al,
		`trab_bd`.`pessoa` pe
	WHERE
		es.codigo_cidade = ci.codigo
	AND es.codigo = tu.codigo_escola
	AND tu.codigo = al.codigo_turma
	AND pe.codigo = al.codigo_pessoa_aluno
	AND pe.codigo_cidade <> es.codigo_cidade
	AND es.codigo = e.codigo
);

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (2)*/
SELECT
	p.nome AS `aluno`,
	a.matricula_aluno AS `matricula`
FROM
	`trab_bd`.`pessoa` p,
	`trab_bd`.`aluno` a
WHERE
	p.codigo = a.codigo_pessoa_aluno
AND NOT EXISTS (
	SELECT
		*
	FROM
		`trab_bd`.`contato` c
	WHERE
		c.codigo_pessoa_aluno = p.codigo
);

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (3)*/
SELECT
	t.codigo,
	t.nome
FROM
	`trab_bd`.`turma` t
WHERE
	(
		SELECT
			count(*)
		FROM
			`trab_bd`.`aluno` a
		WHERE
			a.codigo_turma = t.codigo
	) > 5;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (4)*/
SELECT
	p.codigo,
	p.nome,
	pr.titulacao
FROM
	`trab_bd`.`professor` pr,
	`trab_bd`.`pessoa` p
WHERE
	p.codigo = pr.codigo_pessoa_professor
AND (
	SELECT
		count(*)
	FROM
		(
			SELECT DISTINCT
				min.codigo_pessoa_professor,
				min.codigo_turma
			FROM
				`trab_bd`.`ministra` min
		) m
	WHERE
		m.codigo_pessoa_professor = pr.codigo_pessoa_professor
) >= 3;

/*


------------------------------------------------------------------------------------------------------------------



*/


/*PARTE 2 (5)*/
SELECT
	t.nome AS `Turma`,
	d.nome AS `Disciplina`,
	count(*) AS `QTD Professores`
FROM
	`trab_bd`.`turma` t,
	`trab_bd`.`ministra` m,
	`trab_bd`.`disciplina` d,
	`trab_bd`.`professor` profe,
	`trab_bd`.`pessoa` p
WHERE
	m.codigo_disciplina = d.codigo
AND t.codigo = m.codigo_turma
AND profe.codigo_pessoa_professor = p.codigo
AND m.codigo_pessoa_professor = profe.codigo_pessoa_professor
GROUP BY
	d.codigo,
	t.codigo;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (6)*/
SELECT
	e.nome AS `Escola`,
	p.nome AS `Diretor`
FROM
	`trab_bd`.`escola` e,
	`trab_bd`.`pessoa` p
WHERE
	e.codigo_pessoa_diretor = p.codigo
AND e.codigo_cidade <> p.codigo_cidade;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (7)*/
SELECT
	e.nome as `ESCOLA`,
	count(
		DISTINCT m.codigo_pessoa_professor
	) AS `QTD_PROFS`,
	count(DISTINCT t.codigo) AS `QTD_TURMAS`
FROM
	`trab_bd`.`ministra` m,
	`trab_bd`.`turma` t,
	`trab_bd`.`escola` e
WHERE
	m.codigo_turma = t.codigo
AND t.codigo_escola = e.codigo
GROUP BY
	t.codigo_escola;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (8) */
SELECT
	professores.ESCOLA_PROFESSORES AS `ESCOLA`,
	(
		alunos.QTD_ALUNOS / professores.QTD_PROFESSORES
	) AS `RAZAO ENTRE ALUNOS E PROFESSORES`
FROM
	(
		SELECT
			e.nome AS `ESCOLA_ALUNOS`,
			count(*) AS `QTD_ALUNOS`
		FROM
			`trab_bd`.`escola` e,
			`trab_bd`.`aluno` a,
			`trab_bd`.`turma` t
		WHERE
			t.codigo = a.codigo_turma
		AND t.codigo_escola = e.codigo
		GROUP BY
			e.codigo
	) alunos,
	(
		SELECT
			e.nome AS `ESCOLA_PROFESSORES`,
			count(*) AS `QTD_PROFESSORES`
		FROM
			`trab_bd`.`escola` e,
			`trab_bd`.`ministra` m,
			`trab_bd`.`turma` t
		WHERE
			t.codigo = m.codigo_turma
		AND t.codigo_escola = e.codigo
		GROUP BY
			e.codigo
	) professores
WHERE
	alunos.ESCOLA_ALUNOS = professores.ESCOLA_PROFESSORES;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (9)*/
SELECT
	a.matricula_aluno AS `Matricula`,
	p.nome AS `Aluno`,
	c.nome AS `Contato`,
	c.telefone AS `Telefone do Contato`
FROM
	`trab_bd`.`contato` c,
	`trab_bd`.`pessoa` p,
	`trab_bd`.`aluno` a
WHERE
	c.codigo_pessoa_aluno = p.codigo
AND p.codigo = a.codigo_pessoa_aluno
ORDER BY
	a.matricula_aluno,
	c.nome;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*PARTE 2 (10)*/
SELECT
	p.nome AS `Professor`
FROM
	`trab_bd`.`pessoa` p
WHERE
	(
		SELECT
			count(*)
		FROM
			`trab_bd`.`ministra` m
		WHERE
			m.codigo_pessoa_professor = p.codigo
	) = 1;

/*


------------------------------------------------------------------------------------------------------------------



*/
/*SCRIPT PARA PASSAR CONTATOS DE UM ALUNO PARA O OUTRO*/
DROP PROCEDURE
IF EXISTS `trab_bd`.`troca_contatos`;

DELIMITER $$
CREATE PROCEDURE `trab_bd`.`troca_contatos` (
	IN codigo_aluno_contato_origem INT (5),
	IN codigo_aluno_contato_destino INT (5)
)
BEGIN
	UPDATE `trab_bd`.`contato` c
SET c.codigo_pessoa_aluno = codigo_aluno_contato_destino
WHERE
	c.codigo_pessoa_aluno = codigo_aluno_contato_origem;


END$$
DELIMITER ; 


/*exec troca_contatos*/
CALL troca_contatos (1, 2);

/*


------------------------------------------------------------------------------------------------------------------



*/
/*SCRIPT QUE DELETA PROF E ATUALIZA DIRECAO DE ESCOLA CASO PROF FOR DIRETOR*/
DROP PROCEDURE
IF EXISTS `trab_bd`.`delete_prof`;

DELIMITER $$
CREATE PROCEDURE `trab_bd`.`delete_prof` (IN codigo_professor INT(5))
BEGIN
	DECLARE
		qtd_escolas_dir,
		novo_diretor,
		codigo_escola_sel INT DEFAULT 0;

SELECT
	count(*) INTO qtd_escolas_dir
FROM
	`trab_bd`.`escola`
WHERE
	codigo_pessoa_diretor = codigo_professor;


IF qtd_escolas_dir > 0 THEN
	SELECT
		codigo INTO codigo_escola_sel
	FROM
		`trab_bd`.`escola`
	WHERE
		codigo_pessoa_diretor = codigo_professor;

SELECT
	min(codigo_pessoa_professor) INTO novo_diretor
FROM
	`trab_bd`.`ministra`
WHERE
	codigo_turma IN (
		SELECT
			codigo
		FROM
			`trab_bd`.`turma`
		WHERE
			codigo_escola = codigo_escola_sel
	)
AND codigo_pessoa_professor <> codigo_professor;

UPDATE `trab_bd`.`escola`
SET codigo_pessoa_diretor = novo_diretor
WHERE
	codigo_pessoa_diretor = codigo_professor;


END
IF;

DELETE
FROM
	`trab_bd`.`ministra`
WHERE
	codigo_pessoa_professor = codigo_professor;

DELETE
FROM
	`trab_bd`.`professor`
WHERE
	codigo_pessoa_professor = codigo_professor;

DELETE
FROM
	`trab_bd`.`pessoa`
WHERE
	codigo = codigo_professor;


END$$
DELIMITER ;

/*exec delete_prof*/
CALL delete_prof (19);

/*


------------------------------------------------------------------------------------------------------------------



*/
/*SCRIPT QUE FAZ PROFESSOR SUBSTITUTO ASSUMIR TURMAS MINISTRADAS PELO PROFESSOR QUE ENTROU DE LICENCA*/
DROP PROCEDURE
IF EXISTS `trab_bd`.`licenca`;

DELIMITER $$
CREATE PROCEDURE `trab_bd`.`licenca` (
	IN codigo_professor_licenca INT (5),
	IN codigo_professor_substituto INT (5)
)
BEGIN
	UPDATE `trab_bd`.`ministra`
SET codigo_pessoa_professor = codigo_professor_substituto
WHERE
	codigo_pessoa_professor = codigo_professor_licenca;


END$$
DELIMITER ;

/*exec licenca*/
CALL licenca (14, 15);