import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static spark.Spark.*;

public class AlunoController {
    public static void main (String[] args){
        AlunoService alunoService = new AlunoService();
        ObjectMapper mapper = new ObjectMapper();

        get("/alunos", (request, response) -> {
            response.type("application/json");
            List<Aluno> alunos = alunoService.listaDeAlunos();
            String json = mapper.writeValueAsString(alunos);
            response.status(200);
            return json;
        });
    }
}
