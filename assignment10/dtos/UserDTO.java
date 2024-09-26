package pjwstk.edu.pl.s27619.dtos;

import java.util.LinkedList;
import java.util.List;

public class UserDTO extends DTOBase {
    private String login;
    private String password;
    private List<GroupDTO> groups;

    public UserDTO() {
    }

    public UserDTO(int id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public void addGroup(GroupDTO group) {
        if (groups == null) {
            groups = new LinkedList<GroupDTO>();
        }
        groups.add(group);
    }

    public void deleteGroup(GroupDTO group) {
        if (groups != null) {
            groups.remove(group);
        }
    }

}
