package at.wrk.repository;

import java.util.List;

import at.wrk.model.Benutzer;

public interface IPDFService {

    public List<Benutzer> findAll();
}
