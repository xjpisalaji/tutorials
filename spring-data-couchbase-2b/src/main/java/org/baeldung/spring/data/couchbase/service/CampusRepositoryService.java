package org.baeldung.spring.data.couchbase.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.baeldung.spring.data.couchbase.model.Campus;
import org.baeldung.spring.data.couchbase.repos.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
@Qualifier("CampusRepositoryService")
public class CampusRepositoryService implements CampusService {

    private CampusRepository repo;
    @Autowired
    public void setCampusRepository(CampusRepository repo) {
        this.repo = repo;
    }
    
    @Override
    public Campus find(String id) {
        return repo.findOne(id);
    }
    
    @Override
    public Set<Campus> findByName(String name) {
        return repo.findByName(name);
    }
    
    @Override
    public Set<Campus> findByLocationNear(Point point, Distance distance) {
        return repo.findByLocationNear(point, distance);
    }

    @Override
    public Set<Campus> findAll() {
        Set<Campus> campuses = new HashSet<>();
        Iterator<Campus> it = repo.findAll().iterator();
        while(it.hasNext()) {
            campuses.add(it.next());
        }
        return campuses;
    }

    @Override
    public void save(Campus campus) {
        repo.save(campus);
    }
}
