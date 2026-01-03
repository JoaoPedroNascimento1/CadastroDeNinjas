package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

  private final MissoesService missaoService;

  public MissoesControllerUi(MissoesService missaoService) {
    this.missaoService = missaoService;
  }

  @GetMapping("/listar")
  public String listarMissoes(Model model) {
    List<MissoesDTO> missoes =  missaoService.listarMissoes();
    model.addAttribute("missoes", missoes);
    return "listarMissoes"; // Tem que retornar o nome da pagina que renderiza
  }

  @GetMapping("/deletar/{id}")
  public String deletarMissaoPorId(@PathVariable Long id) {
    missaoService.deletarMissaoPorId(id);
    return "redirect:/missoes/ui/listar";
  }

  @GetMapping("/listar/{id}")
  public String listarMissoesPorId(@PathVariable Long id, Model model) {
    MissoesDTO missao =  missaoService.listarMissoesPorId(id);
    if (missao !=null) {
      model.addAttribute("missao", missao);
      return "detalhesMissao";
    } else {
      model.addAttribute("mensagem", "Ninja não encontrado");
      return "listarMissoes";
    }
  }

  @GetMapping("/adicionar")
  public String mostrarFormularioAdicionarMissao(Model model) {
    model.addAttribute("missoes", new MissoesDTO());
    return "adicionarMissao";
  }

  @PostMapping("/salvar")
  public String salvarMissao(@ModelAttribute MissoesDTO missao, RedirectAttributes redirectAttributes) {
    missaoService.criarMissoes(missao);
    redirectAttributes.addFlashAttribute("mensagem", "Missao cadastrado com sucesso!");
    return "redirect:/missoes/ui/listar";
  }

}
