package org.hashtag.library_management_system.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.entity.Loan;
import org.hashtag.library_management_system.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoanDao {

	@Autowired
    private LoanRepo loanRepo;

    public Loan saveLoan(Loan loan) {
        return loanRepo.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }

    public Loan getLoanById(int id) {
        Optional<Loan> Loan = loanRepo.findById(id);
        return Loan.orElse(null);
    }

    public void deleteLoan(int id) {
    	loanRepo.deleteById(id);
    }

    public Loan updateLoan(Loan loan, int id) {
        Optional<Loan> existingLoan = loanRepo.findById(id);
        if (existingLoan.isPresent()) {
            Loan updatedLoan = existingLoan.get();
            updatedLoan.setLoanDate(LocalDate.now());
            updatedLoan.setReturnDate(loan.getReturnDate());
            updatedLoan.setBook(loan.getBook());
            updatedLoan.setMember(loan.getMember());
            return loanRepo.save(updatedLoan);
        }
        return null;
    }

}
