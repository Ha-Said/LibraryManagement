package dao;
import java.util.List;

import entity.Member;

public interface MemberDAO {
    void addMember(Member member);               // Add a new member to the database
    Member getMemberById(int id);                 // Get a member by their ID
    Member getMemberByUsername(String username);  // Get a member by their username
    List<Member> getAllMembers();                 // Get a list of all members
    void updateMember(Member member);             // Update a member's information
    void deleteMember(int id);  // Delete a member by ID
    Member validateLogin(String username, String password); //validate login
}
