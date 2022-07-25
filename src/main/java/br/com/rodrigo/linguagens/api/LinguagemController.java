package br.com.rodrigo.linguagens.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

@Autowired
private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> getLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll();
        return linguagens;
    }

    @PostMapping("/linguagens")
    public Linguagem postLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return linguagemSalva;
    }

    @DeleteMapping("/linguagens/{id}")
    public String deleteLinguagem(@PathVariable("id") String id) {
        String mensagem;
        try{
            repositorio.deleteById(id);
            mensagem = "Linguagem deletada com sucesso!";
        }catch(Exception e){
            mensagem = "Erro ao deletar linguagem!";
        }
        return mensagem;
    }

    @PatchMapping("linguagens/{id}")
    public Linguagem updateLinguage(@PathVariable("id") String id, @RequestBody Linguagem request) {
        return repositorio.findById(id)
                .map(obj -> {
                    obj.setTitle(request.getTitle());
                    obj.setImage(request.getImage());
                    obj.setRanking(request.getRanking());
                    return repositorio.save(obj);
                })
                .orElseGet(() -> {
                    // TODO: implement NotFoundException
                    return new Linguagem();
                });
    }


  

}
