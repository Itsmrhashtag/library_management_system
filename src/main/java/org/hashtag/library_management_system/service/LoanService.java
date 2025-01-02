package org.hashtag.library_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.dao.LoanDao;
import org.hashtag.library_management_system.entity.Loan;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.repo.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoanService {
	@Autowired
    private LoanDao loanDao;

    public ResponseEntity<ResponseStructure<Loan>> saveLoan(Loan loan) {
        Loan savedLoan = loanDao.saveLoan(loan);
        if(savedLoan!=null) {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.CREATED.value(), savedLoan, "Loan Saved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
        }else {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.BAD_REQUEST.value(), null, "Loan Not Saved");
	        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
        }
        
    }

    public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
        List<Loan> loans = loanDao.getAllLoans();
        if(loans.size()!=0) {
        	ResponseStructure<List<Loan>> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), loans, "Loans Retrieved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<List<Loan>> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Loans Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Loan>> getLoanById(int id) {
        Loan loan = loanDao.getLoanById(id);
        if(loan!=null) {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), loan, "Loan Retrieved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }
        else {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Loan Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Void>> deleteLoan(int id) {
    	Loan loan= loanDao.getLoanById(id);
    	if(loan!=null) {
    		loanDao.deleteLoan(id);
	        ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), null, "Loan Deleted Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
    	}else {
    		ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Loan Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    	}
        
    }

    public ResponseEntity<ResponseStructure<Loan>> updateLoan(Loan loan,int id) {
        Loan updatedLoan = loanDao.updateLoan(loan, id);
        if(updatedLoan!=null) {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), updatedLoan, "Loan Updated Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<Loan> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), updatedLoan, "Loan Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }


}
