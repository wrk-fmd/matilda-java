package at.wrk.fmd.repository;

import java.util.List;

import at.wrk.fmd.model.Benutzer;

public interface IPDFService {

    public List<Benutzer> findAll();
}
