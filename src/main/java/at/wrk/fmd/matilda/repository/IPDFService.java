package at.wrk.fmd.matilda.repository;

import java.util.List;

import at.wrk.fmd.matilda.model.Benutzer;

public interface IPDFService {

    public List<Benutzer> findAll();
}
