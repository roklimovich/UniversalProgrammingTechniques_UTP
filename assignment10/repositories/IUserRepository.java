package pjwstk.edu.pl.s27619.repositories;

import pjwstk.edu.pl.s27619.dtos.UserDTO;

import java.util.List;

public interface IUserRepository extends IRepository<UserDTO> {

    List<UserDTO> findByName(String username);
}
