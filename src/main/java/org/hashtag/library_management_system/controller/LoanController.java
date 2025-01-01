package org.hashtag.library_management_system.controller;

import java.util.List;

import org.hashtag.library_management_system.entity.Loan;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loan")
public class LoanController {
	@Autowired
    private LoanService LoanService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Loan>> saveLoan(@RequestBody Loan loan) {
        Loan savedLoan = LoanService.saveLoan(loan);
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

    @GetMapping("/loans")
    public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
        List<Loan> loans = LoanService.getAllLoans();
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Loan>> getLoanById(@PathVariable int id) {
        Loan loan = LoanService.getLoanById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteLoan(@PathVariable int id) {
    	Loan loan= LoanService.getLoanById(id);
    	if(loan!=null) {
    		LoanService.deleteLoan(id);
	        ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), null, "Loan Deleted Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
    	}else {
    		ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Loan Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    	}
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Loan>> updateLoan(@RequestBody Loan loan, @PathVariable int id) {
        Loan updatedLoan = LoanService.updateLoan(loan, id);
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
