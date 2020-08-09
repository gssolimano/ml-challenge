-- 1) Create database
create database magneto; 

-- 2) Create tables 
create table magneto.dna_test_results (
	id int not null auto_increment,
    dna_test varchar(2000) not null, 
    is_mutant boolean,
    primary key (id)
);

-- 3) Create indexs
create index idx_is_mutant_test_result on magneto.dna_test_results(is_mutant);  