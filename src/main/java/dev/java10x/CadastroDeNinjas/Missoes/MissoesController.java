package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

  // GET -- Mnadar uma requisição para mostrar as missoes
  @GetMapping("/listar")
  public String listarMissao(){
    return "Missoes listadas com sucesso";
  }

  // Post -- Mnadar uma requisição para criar as missoes
  @PostMapping("/criar")
  public String criarMissao(){
    return "Missao criada com sucesso";
  }

  // PUT -- Mnadar uma requisição para alterar as missoes
  @PutMapping("/alterar")
  public String alterarMissao(){
    return "Missao alterada com sucesso";
  }

  // Delete -- Mnadar uma requisição para deletar as missoes
  @DeleteMapping("/deletar")
  public String deletarMissao(){
    return "Missao deletada com sucesso";
  }
}
