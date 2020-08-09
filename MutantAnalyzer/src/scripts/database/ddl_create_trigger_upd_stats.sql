DELIMITER //

create trigger upd_stats
after insert 
on magneto.dna_test_results for each row
begin 
	if new.is_mutant then
		update magneto.dna_test_stats 
		set count_mutant_dna = 1, 
			count_dna_test = 1; 
        
	else  
		update magneto.dna_test_stats 
        set count_mutant_dna = 1, 
            count_dna_test = 1 ;
    end if;
end//

DELIMITER ;



