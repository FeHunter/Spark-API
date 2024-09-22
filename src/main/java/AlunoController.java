import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        post("/alunos", (request, response) -> {
            Aluno aluno;
            try {
                aluno = mapper.readValue(request.body(), Aluno.class);
                alunoService.adicionarAluno(aluno);
                response.status(201);
                return "Aluno adicionado com sucesso!";
            } catch (JsonProcessingException e) {
                response.status(400);
                return "Erro: JSON com problemas";
            }
        });

        put("/alunos/:id", (request, response) -> {
            int idParam = Integer.parseInt(request.params(":id"));
            Aluno aluno = alunoService.buscaAlunoPorId(idParam);
            if (aluno == null){
                response.status(400);
                return "Erro: Aluno não encontrado";
            }
            Aluno alunoEdit;
            try {
                alunoEdit = mapper.readValue(request.body(), Aluno.class);
            }catch (JsonProcessingException e){
                response.status(400);
                return "Erro: Json com problemas";
            }
            alunoService.editarAluno(aluno, alunoEdit);
            response.status(201);
            return "Aluno editado com sucesso!";
        });

        delete("/alunos/:id", (request, response) -> {
            int idParam = Integer.parseInt(request.params(":id"));
            Aluno aluno = alunoService.buscaAlunoPorId(idParam);
            if (aluno == null){
                response.status(400);
                return "Erro: Aluno não encontrado";
            }
            alunoService.deletarAluno(idParam);
            response.status(200);
            return "O aluno foi removido";
        });
    }
}
