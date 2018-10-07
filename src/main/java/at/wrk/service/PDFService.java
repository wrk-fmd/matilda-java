package at.wrk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import at.wrk.model.Benutzer;
import at.wrk.repository.IPDFService;

@Service
public class PDFService implements IPDFService{

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Benutzer> findAll() {

        String sql = "SELECT * FROM Benutzer";

        List<Benutzer> cars = jtm.query(sql, new BeanPropertyRowMapper(Benutzer.class));

        return cars;
    }
}