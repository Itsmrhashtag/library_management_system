package org.hashtag.library_management_system.service;

import java.util.List;

import org.hashtag.library_management_system.dao.BookDao;
import org.hashtag.library_management_system.dao.LoanDao;
import org.hashtag.library_management_system.dao.MemberDao;
import org.hashtag.library_management_system.entity.Book;
import org.hashtag.library_management_system.entity.Loan;
import org.hashtag.library_management_system.entity.Member;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
	@Autowired
    private LoanDao loanDao;
	@Autowired
    private BookDao bookDao;
	@Autowired
    private MemberDao memberDao;

    public ResponseEntity<ResponseStructure<Loan>> saveLoan(Loan loan) {
        Loan savedLoan = loanDao.saveLoan(loan);
        if(savedLoan!=null) {
        	Book book=bookDao.getBookById(savedLoan.getBook().getBookId());
        	if(book!=null) {
        		savedLoan.setBook(book);
        	}
        	Member member=memberDao.getMemberById(savedLoan.getMember().getMemberId());
        	if(member!=null) {
        		savedLoan.setMember(member);
        	}
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.CREATED.value(), savedLoan, "Loan Saved Successfully");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.CREATED);
        }else {
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.BAD_REQUEST.value(), null, "Loan Not Saved");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.BAD_REQUEST);
        }
        
    }

    public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
        List<Loan> loans = loanDao.getAllLoans();
        if(loans.size()!=0) {
        	ResponseStructure<List<Loan>> structure = new ResponseStructure<List<Loan>>(HttpStatus.OK.value(), loans, "Loans Retrieved Successfully");
	        return new ResponseEntity<ResponseStructure<List<Loan>>>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<List<Loan>> structure = new ResponseStructure<List<Loan>>(HttpStatus.NOT_FOUND.value(), null, "Loans Not Found");
	        return new ResponseEntity<ResponseStructure<List<Loan>>>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Loan>> getLoanById(int id) {
        Loan loan = loanDao.getLoanById(id);
        if(loan!=null) {
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.OK.value(), loan, "Loan Retrieved Successfully");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
        }
        else {
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.NOT_FOUND.value(), null, "Loan Not Found");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Void>> deleteLoan(int id) {
    	Loan loan= loanDao.getLoanById(id);
    	if(loan!=null) {
    		loanDao.deleteLoan(id);
	        ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.OK.value(), null, "Loan Deleted Successfully");
	        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.OK);
    	}else {
    		ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.NOT_FOUND.value(), null, "Loan Not Found");
	        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.NOT_FOUND);
    	}
        
    }

    public ResponseEntity<ResponseStructure<Loan>> updateLoan(Loan loan,int id) {
        Loan updatedLoan = loanDao.updateLoan(loan, id);
        if(updatedLoan!=null) {
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.OK.value(), updatedLoan, "Loan Updated Successfully");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<Loan> structure = new ResponseStructure<Loan>(HttpStatus.NOT_FOUND.value(), updatedLoan, "Loan Not Found");
	        return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
        }
    }


}
