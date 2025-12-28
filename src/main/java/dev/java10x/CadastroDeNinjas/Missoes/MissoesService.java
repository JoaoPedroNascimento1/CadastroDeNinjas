package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService{

  private MissoesRepository missoesRepository;

  public MissoesService(MissoesRepository missoesRepository){
    this.missoesRepository = missoesRepository;
  }

  public List<MissoesModel> listarMissoes(){
    return missoesRepository.findAll();
  }

  public MissoesModel listarMissoesPorId(@PathVariable Long id){
    Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
    return missoesPorId.orElse(null);
  }

}
