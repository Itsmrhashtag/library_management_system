package org.hashtag.library_management_system.dao;

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
            if(loan.getLoanDate()!=null) updatedLoan.setLoanDate(loan.getLoanDate());
            if(loan.getReturnDate()!=null) updatedLoan.setReturnDate(loan.getReturnDate());
            if(loan.getBook()!=null) updatedLoan.setBook(loan.getBook());
            if(loan.getMember()!=null) updatedLoan.setMember(loan.getMember());
//            System.out.println(updatedLoan.getLoanDate()+" "+updatedLoan.getReturnDate());
            return loanRepo.save(updatedLoan);
        }
        return null;
    }

}
