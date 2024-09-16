import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertEquals(buscarID, resultado.getId());
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
