package org.hashtag.library_management_system.service;

import java.util.List;

import org.hashtag.library_management_system.dao.MemberDao;
import org.hashtag.library_management_system.entity.Member;
import org.hashtag.library_management_system.entity.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
    private MemberDao memberDao;

    public ResponseEntity<ResponseStructure<Member>> saveMember(Member member) {
        Member savedMember = memberDao.saveMember(member);
        if(savedMember!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.CREATED.value(), savedMember, "Member Saved Successfully");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.CREATED);
        }else {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.BAD_REQUEST.value(), null, "Member Not Saved");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.BAD_REQUEST);
        }
        
    }

    public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers() {
        List<Member> members = memberDao.getAllMembers();
        if(members.size()!=0) {
        	ResponseStructure<List<Member>> structure = new ResponseStructure<List<Member>>(HttpStatus.OK.value(), members, "Members Retrieved Successfully");
	        return new ResponseEntity<ResponseStructure<List<Member>>>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<List<Member>> structure = new ResponseStructure<List<Member>>(HttpStatus.NOT_FOUND.value(), null, "Members Not Found");
	        return new ResponseEntity<ResponseStructure<List<Member>>>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Member>> getMemberById(int id) {
        Member member = memberDao.getMemberById(id);
        if(member!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.OK.value(), member, "Member Retrieved Successfully");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
        }
        else {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.NOT_FOUND.value(), null, "Member Not Found");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.NOT_FOUND);
        }
        
    }

    public ResponseEntity<ResponseStructure<Void>> deleteMember(int id) {
    	Member member= memberDao.getMemberById(id);
    	if(member!=null) {
    		memberDao.deleteMember(id);
	        ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.OK.value(), null, "Member Deleted Successfully");
	        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.OK);
    	}else {
    		ResponseStructure<Void> structure = new ResponseStructure<Void>(HttpStatus.NOT_FOUND.value(), null, "Member Not Found");
	        return new ResponseEntity<ResponseStructure<Void>>(structure, HttpStatus.NOT_FOUND);
    	}
        
    }

    public ResponseEntity<ResponseStructure<Member>> updateMember(Member member, int id) {
        Member updatedMember = memberDao.updateMember(member, id);
        if(updatedMember!=null) {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.OK.value(), updatedMember, "Member Updated Successfully");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
        }else {
        	ResponseStructure<Member> structure = new ResponseStructure<Member>(HttpStatus.NOT_FOUND.value(), updatedMember, "Member Not Found");
	        return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.NOT_FOUND);
        }
    }
}
