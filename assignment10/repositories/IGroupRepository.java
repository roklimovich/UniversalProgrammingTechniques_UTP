package pjwstk.edu.pl.s27619.repositories;

import pjwstk.edu.pl.s27619.dtos.GroupDTO;

import java.util.List;

public interface IGroupRepository extends IRepository<GroupDTO> {

    List<GroupDTO> findByName(String name);
}
