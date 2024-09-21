import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlunoServiceTest {

    AlunoService alunoService;

    @BeforeEach
    public void setup (){
        alunoService = new AlunoService();
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
    void alunoServiceAlunoIdNaoEncontradoTest (){
        // Arrange
        int buscarID = 0;

        // act
        Aluno resultado = alunoService.buscaAlunoPorId(buscarID);

        // assert
        Assertions.assertNull(resultado);

    }

    @Test
    void alunoServiceAlunoIdEncontradoStatusCodeTest () {
        // Arrange
        final String urlApi = "http://localhost:4567/alunos/2";
        int statusCodeResposta = 0;

        // Act
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlApi)).build();
            HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
            statusCodeResposta = resposta.statusCode();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        // Assert
        assertEquals(200, statusCodeResposta);
    }

    @AfterEach
    public void tearDown (){
        alunoService = null;
    }
}
