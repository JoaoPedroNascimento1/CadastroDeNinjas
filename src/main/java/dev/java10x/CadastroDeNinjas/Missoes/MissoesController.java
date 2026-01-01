package dev.java10x.CadastroDeNinjas.Missoes;

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
  public List<MissoesDTO> listarMissao(){
    return missoesService.listarMissoes();
  }

  @GetMapping("/listar/{id}")
  public MissoesDTO listarMissoesPorId(@PathVariable Long id){
    return missoesService.listarMissoesPorId(id);
  }

  // Post -- Mnadar uma requisição para criar as missoes
  @PostMapping("/criar")
  public MissoesDTO criarMissao(@RequestBody MissoesDTO missoes){
    return missoesService.criarMissoes(missoes);
  }

  // PUT -- Mnadar uma requisição para alterar as missoes
  @PutMapping("/alterar/{id}")
  public MissoesDTO alterarMissao(@PathVariable Long id, @RequestBody MissoesDTO missoes){
    return missoesService.atualizarMissoes(id, missoes);
  }

  // Delete -- Mnadar uma requisição para deletar as missoes
  @DeleteMapping("/deletar/{id}")
  public void deletarMissaoPorId(@PathVariable Long id){
    missoesService.deletarMissaoPorId(id);
  }
}
