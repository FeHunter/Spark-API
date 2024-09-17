import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static spark.Spark.*;

public class AlunoController {
    public static void main (String[] args){
        AlunoService alunoService = new AlunoService();
        ObjectMapper mapper = new ObjectMapper();

        get("/alunos", (request, response) -> {
            String json = mapper.writeValueAsString(alunoService.listaDeAlunos());
            response.type("application/json");
            response.status(200);
            return json;
        });

        get("/alunos/:id" , (request, response) -> {
           int idParam = Integer.parseInt(request.params(":id"));
           Aluno aluno = alunoService.buscaAlunoPorId(idParam);
           if (aluno == null){
               response.status(400);
               return "Erro: Aluno não encontrado";
           }
           String json = mapper.writeValueAsString(aluno);
           response.type("application/json");
           response.status(200);
           return json;
        });
    }
}
