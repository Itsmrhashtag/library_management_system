package org.hashtag.library_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.hashtag.library_management_system.entity.Member;
import org.hashtag.library_management_system.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

	@Autowired
    private MemberRepo MemberRepo;

    public Member saveMember(Member member) {
        return MemberRepo.save(member);
    }

    public List<Member> getAllMembers() {
        return MemberRepo.findAll();
    }

    public Member getMemberById(int id) {
        Optional<Member> member = MemberRepo.findById(id);
        return member.orElse(null);
    }

    public void deleteMember(int id) {
    	MemberRepo.deleteById(id);
    }

    public Member updateMember(Member member, int id) {
        Optional<Member> existingMember = MemberRepo.findById(id);
        if (existingMember.isPresent()) {
            Member updatedMember = existingMember.get();
            updatedMember.setEmail(member.getEmail());
            updatedMember.setName(member.getName());
            updatedMember.setPhone(member.getPhone());
            return MemberRepo.save(updatedMember);
        }
        return null;
    }
}
