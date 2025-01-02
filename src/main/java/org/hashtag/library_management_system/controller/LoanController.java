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
    private LoanService loanService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Loan>> saveLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @GetMapping("/loans")
    public ResponseEntity<ResponseStructure<List<Loan>>> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Loan>> getLoanById(@PathVariable int id) {
        return loanService.getLoanById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteLoan(@PathVariable int id) {
    		return loanService.deleteLoan(id);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Loan>> updateLoan(@RequestBody Loan loan, @PathVariable int id) {
        return loanService.updateLoan(loan, id);
    }

}
