package org.hashtag.library_management_system.repo;

import org.hashtag.library_management_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Integer>{

}
