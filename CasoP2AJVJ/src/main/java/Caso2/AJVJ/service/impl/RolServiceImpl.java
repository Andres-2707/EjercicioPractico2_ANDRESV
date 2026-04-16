package Caso2.AJVJ.service.impl;

import Caso2.AJVJ.domain.Rol;
import Caso2.AJVJ.repository.RolRepository;
import Caso2.AJVJ.service.RolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rol getRol(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }
}