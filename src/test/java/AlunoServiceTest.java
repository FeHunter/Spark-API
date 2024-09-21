import java.io.IOException;
import java.net.http.HttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.*;

public class AlunoServiceTest {

    AlunoService alunoService;

    @BeforeEach
    public void setup (){
        alunoService = new AlunoService();

        // configurando o spark
        port(4567);
        get("/alunos/:id", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            Aluno aluno = alunoService.buscaAlunoPorId(id);
            if (aluno != null) {
                response.status(200);
                return "Aluno encontrado";
            } else {
                response.status(404);
                return "Aluno não encontrado";
            }
        });
        awaitInitialization();
    }

    @Test
    void alunoServiceAlunoIdEncontradoTest (){
        // Arrange
        int buscarID = 2;

        // act
        Aluno resultado = alunoService.buscaAlunoPorId(buscarID);

        // assert
        assertEquals(buscarID, resultado.getId());
    }

    @Test
    void alunoServiceAlunoIdEncontradoStatusCodeTest () throws IOException, InterruptedException {
        // Arrange
        int buscarID = 2;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4567/alunos/" + buscarID))
                .build();

        // act
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // assert
        assertEquals(200, response.statusCode(), "Teste ok");
    }

    @Test
    void alunoServiceAlunoIdNaoEncontradoTest (){
        // Arrange
        int buscarID = 0;

        // act
        Aluno resultado = alunoService.buscaAlunoPorId(buscarID);

        // assert
        Assertions.assertNull(resultado);

    }

    @AfterEach
    public void tearDown (){
        alunoService = null;
    }
}
