package org.hashtag.library_management_system.repo;

import org.hashtag.library_management_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer>{

}
