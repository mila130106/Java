package repository;

import functional.IdentityExtractor;
import models.Group;
import utils.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GroupRepository extends GenericRepository<Group> {
    public GroupRepository(IdentityExtractor<Group> extractor) {
        super(extractor);
    }

    public List<Group> findByFaculty(String faculty) {
        LoggerUtil.log("Finding groups for faculty: " + faculty);
        return items.stream()
                .filter(g -> g.getFaculty().equalsIgnoreCase(faculty))
                .collect(Collectors.toList());
    }

    public List<Group> findByYear(int year) {
        LoggerUtil.log("Finding groups for year: " + year);
        return items.stream()
                .filter(g -> g.getYear() == year)
                .collect(Collectors.toList());
    }
}
