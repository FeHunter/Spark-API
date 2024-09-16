import lombok.Data;

@Data
public class Aluno {
    private int id;
    private String nome;
    private int nota;

    public Aluno(int id, String nome, int nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nota=" + nota +
                '}';
    }
}