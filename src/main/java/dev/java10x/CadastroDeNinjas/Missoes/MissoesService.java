package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService{

  private final MissoesRepository missoesRepository;
  private final MissoesMapper missoesMapper;

  public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
    this.missoesRepository = missoesRepository;
    this.missoesMapper = missoesMapper;
  }

  public List<MissoesDTO> listarMissoes(){
    List<MissoesModel> missoes = missoesRepository.findAll();
    return missoes.stream()
      .map(missoesMapper::map)
      .collect(Collectors.toList());
  }

  public MissoesDTO listarMissoesPorId(Long id){
    Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
    return missoesPorId.map(missoesMapper::map).orElse(null);
  }

  public MissoesDTO criarMissoes(MissoesDTO missoesDTO){
    MissoesModel missoes = missoesMapper.map(missoesDTO);
    missoes = missoesRepository.save(missoes);
    return missoesMapper.map(missoes);
  }

  public MissoesDTO atualizarMissoes(Long id,MissoesDTO missoesDTO){
    Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
    if(missoesPorId.isPresent()){
      MissoesModel missoesAtualizado = missoesMapper.map(missoesDTO);
      missoesAtualizado.setId(id);
      MissoesModel missoesSalvo = missoesRepository.save(missoesAtualizado);
      return missoesMapper.map(missoesSalvo);
    }
    return null;
  }

  public void deletarMissaoPorId(Long id){
    missoesRepository.deleteById(id);
  }

}
