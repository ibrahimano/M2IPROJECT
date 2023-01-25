package com.projet.back.services;

import com.projet.back.models.Fourniture;
import com.projet.back.repository.FournitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FournitureServiceImpl  {
    @Autowired
    private FournitureRepo fournitureRepository;

    public FournitureServiceImpl() {
    }

    public List<Fourniture> findAll() {
        return this.fournitureRepository.findAll();
    }

    public Fourniture findById(long id) {
        return (Fourniture)this.fournitureRepository.findById(id);
    }

    public Fourniture save(Fourniture fourniture) {
        return (Fourniture)this.fournitureRepository.save(fourniture);
    }

    @Transactional(
            readOnly = true
    )
    public List<Fourniture> searchByType(String type) {
        return this.fournitureRepository.findByTypeContaining(type);
    }

    public void deleteById(long id) {
        this.fournitureRepository.deleteById(id);
    }


    public Long countAllFournitures() {
        return fournitureRepository.count();
    }
}
