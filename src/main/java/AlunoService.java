import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    List<Aluno> alunos = new ArrayList<>();

    public AlunoService (){
        alunos.add(new Aluno(1, "Felipe", 9));
        alunos.add(new Aluno(1, "Pedro", 9));
        alunos.add(new Aluno(1, "Marcos", 9));
    }

    public List<Aluno> listaDeAlunos (int id) {
        return alunos;
    }

    public Aluno buscaAlunoPorId (int id) {
        for (Aluno aluno : alunos){
            if (aluno.getId() == id){
                return aluno;
            }
        }
        return null;
    }

    public void editarAluno (Aluno aluno, Aluno novoAluno){
        aluno.setNome(novoAluno.getNome());
        aluno.setNota(novoAluno.getNota());
    }

    public void deletarAluno (int id){
        Aluno removerAluno = buscaAlunoPorId(id);
        alunos.remove(removerAluno);
    }

    public void adicionarAluno (Aluno aluno){
        alunos.add(aluno);
    }
}
