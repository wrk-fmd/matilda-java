package at.wrk.fmd.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DummyUserRepository implements UserRepository {

    @Override
    public List<at.wrk.fmd.model.Benutzer> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<at.wrk.fmd.model.Benutzer> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<at.wrk.fmd.model.Benutzer> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public at.wrk.fmd.model.Benutzer getOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<at.wrk.fmd.model.Benutzer> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<at.wrk.fmd.model.Benutzer> findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(at.wrk.fmd.model.Benutzer entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends at.wrk.fmd.model.Benutzer> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends at.wrk.fmd.model.Benutzer> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public at.wrk.fmd.model.Benutzer findByBenutzername(String benutzername) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<at.wrk.fmd.model.Benutzer> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

}