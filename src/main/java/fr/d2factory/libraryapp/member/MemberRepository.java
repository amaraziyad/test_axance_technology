package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.book.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberRepository {

    private Map<Integer, Member> members = new HashMap<>();

    private void addMember(Member member){
        members.put(member.getIdMember(),member);
    }

    private Member findMember(int idMember){
        return members.get(idMember);
    }
}
