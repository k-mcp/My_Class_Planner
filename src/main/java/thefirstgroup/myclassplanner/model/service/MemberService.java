package thefirstgroup.myclassplanner.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thefirstgroup.myclassplanner.model.dao.MemberMapper;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    public void login(String email, String password) {
    }
}
