package Caso2.AJVJ.service;

import Caso2.AJVJ.domain.Rol;
import java.util.List;

public interface RolService {
    public List<Rol> getRoles();
    public Rol getRol(Long id);
    public void save(Rol rol);
    public void delete(Long id);
}