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
    private MemberService memberService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Member>> saveMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @GetMapping("/members")
    public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Member>> getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteMember(@PathVariable int id) {
    		return memberService.deleteMember(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Member>> updateMember(@RequestBody Member member, @PathVariable int id) {
        return memberService.updateMember(member, id);
    }

}
