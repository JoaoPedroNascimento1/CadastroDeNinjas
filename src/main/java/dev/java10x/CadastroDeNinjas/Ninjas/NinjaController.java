package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

  // Injecao de metodos/ injetar dependencia
  private final NinjaService ninjaService;

  public NinjaController(NinjaService ninjaService) {
    this.ninjaService = ninjaService;
  }

  @GetMapping("/boasvindas")
  public String boasVindas(){
    return "Essas é a minha primeira mensagem nessa rota";
  }

  @PostMapping("/criar")
  public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
    NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body("Ninja criado com sucesso: " +  novoNinja.getNome() + " (ID): " + novoNinja.getId());
  }

  // Mostrar todos os ninjas (READ)
  @GetMapping("/listar")
  public ResponseEntity<List<NinjaDTO>> listarNinjas(){
    List<NinjaDTO> ninjas = ninjaService.listarNinjas();
    return ResponseEntity.ok(ninjas);
  }

  @GetMapping("/listar/{id}")
  public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){
    NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
    if(ninja != null){
      return ResponseEntity.ok(ninja);
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
    }
  }

  @PatchMapping("/alterar/{id}")
  public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado){
    NinjaDTO ninja = ninjaService.atualizarNinja(id,ninjaAtualizado);
    if(ninja != null){
      return ResponseEntity.ok(ninja);
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não foi encontrado");
    }
  }

  @DeleteMapping("/deletar/{id}")
  public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){
    if(ninjaService.listarNinjasPorId(id) != null){
      ninjaService.deletarNinja(id);
      return ResponseEntity.ok("Ninja com o ID " + id + " deletado com sucesso");
    } else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o ID " + id + " não foi encontrado");
    }
  }
}
