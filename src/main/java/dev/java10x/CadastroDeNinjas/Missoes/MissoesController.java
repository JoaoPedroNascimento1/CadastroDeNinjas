package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

  private MissoesService missoesService;

  public MissoesController(MissoesService missoesService) {
    this.missoesService = missoesService;
  }

  // GET -- Mnadar uma requisição para mostrar as missoes
  @GetMapping("/listar")
  public ResponseEntity<List<MissoesDTO>> listarMissao(){
    List<MissoesDTO> missoes = missoesService.listarMissoes();
    return ResponseEntity.ok(missoes);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id){
    MissoesDTO missoes = missoesService.listarMissoesPorId(id);
    if(missoes != null){
      return ResponseEntity.ok(missoes);
    } else{
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
    }
  }

  // Post -- Mnadar uma requisição para criar as missoes
  @PostMapping("/criar")
  public ResponseEntity<String> criarMissao(@RequestBody MissoesDTO missoes){
    MissoesDTO missoesDTO = missoesService.criarMissoes(missoes);
    return ResponseEntity.status(HttpStatus.CREATED).body("A missao: " + " ' " + missoesDTO.getNome() + " ' " + " foi criada com sucesso");
  }

  // PUT -- Mnadar uma requisição para alterar as missoes
  @PutMapping("/alterar/{id}")
  public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoes){
    MissoesDTO missoesAlterado = missoesService.atualizarMissoes(id, missoes);
    if(missoesAlterado != null){
      return ResponseEntity.ok(missoesAlterado);
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
    }
  }

  // Delete -- Mnadar uma requisição para deletar as missoes
  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id){
    if(missoesService.listarMissoesPorId(id) != null){
      missoesService.deletarMissaoPorId(id);
      return ResponseEntity.ok("Missão deletada com sucesso");
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
    }
  }
}
