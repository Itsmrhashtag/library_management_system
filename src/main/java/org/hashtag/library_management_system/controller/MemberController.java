package org.hashtag.library_management_system.controller;

import java.util.List;

import org.hashtag.library_management_system.entity.Member;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.hashtag.library_management_system.service.MemberService;
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
@RequestMapping("member")
public class MemberController {
	@Autowired
    private MemberService MemberService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Member>> saveMember(@RequestBody Member member) {
        Member savedMember = MemberService.saveMember(member);
        if(savedMember!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.CREATED.value(), savedMember, "Member Saved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.CREATED);
        }else {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.BAD_REQUEST.value(), null, "Member Not Saved");
	        return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/members")
    public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers() {
        List<Member> members = MemberService.getAllMembers();
        if(members.size()!=0) {
        	ResponseStructure<List<Member>> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), members, "Members Retrieved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<List<Member>> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Members Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Member>> getMemberById(@PathVariable int id) {
        Member member = MemberService.getMemberById(id);
        if(member!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), member, "Member Retrieved Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }
        else {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Member Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteMember(@PathVariable int id) {
    	Member member= MemberService.getMemberById(id);
    	if(member!=null) {
    		MemberService.deleteMember(id);
	        ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), null, "Member Deleted Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
    	}else {
    		ResponseStructure<Void> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), null, "Member Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
    	}
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Member>> updateMember(@RequestBody Member member, @PathVariable int id) {
        Member updatedMember = MemberService.updateMember(member, id);
        if(updatedMember!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.OK.value(), updatedMember, "Member Updated Successfully");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<Member> structure = new ResponseStructure<>(
	                HttpStatus.NOT_FOUND.value(), updatedMember, "Member Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
        }
    }

}
