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
    void encontradoAlunoStatusCodeTest () {
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

    @Test
    void adicionarAlunoStatusCode (){
        // act
        final String apiUrl = "http://localhost:4567/alunos";
        String alunoJson = "{\"id\": 6, \"nome\": \"Joao\", \"nota\": 8}";
        int statusCodeResposta = 0;

        // arrange
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(alunoJson))
                    .build();
            HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());
            statusCodeResposta = resposta.statusCode();
        }catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        // asset
        assertEquals(201, statusCodeResposta);
    }

    @AfterEach
    public void tearDown (){
        alunoService = null;
    }
}
