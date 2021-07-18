import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ExercicioJAVA {
    public static void main(String[] args) {
        System.out.println("Programa interrogatório ☻");
        System.out.println("Por favor respostas com sim ou não");

        Interrogado interrogado = new Interrogado();
        Scanner scanner = new Scanner(System.in);
        ListaPerguntas perguntas = new ListaPerguntas();

        boolean respostaValida;

        for(Pergunta pergunta : perguntas.getPerguntas()){
            do{
                System.out.println(pergunta.getPergunta());
                String resposta = scanner.nextLine();

                respostaValida = interrogado.verificarResposta(resposta);
                if(!respostaValida){
                    System.out.println("Digite uma resposta de sim ou não");
                }
            }while (!respostaValida);
        }
        System.out.println("Julgameto final = " + interrogado.getSuspeita());
    }
}

class Interrogado{
    public enum julgamento{
        INOCENTE, SUSPEITA, CUMPLICE, ASSASSINA
    }
    private julgamento suspeita;
    private int respostasAfirmativas;

    public Interrogado(){
        respostasAfirmativas = 0;
        suspeita = julgamento.INOCENTE;
    }

    private void adicionarSuspeita(){
        ++respostasAfirmativas;

        if(respostasAfirmativas == 2){
            suspeita = julgamento.SUSPEITA;
        }
        else if(respostasAfirmativas > 2 && respostasAfirmativas < 5){
            suspeita = julgamento.CUMPLICE;
        }
        else if (respostasAfirmativas > 4){
            suspeita = julgamento.ASSASSINA;
        }
    }

    public julgamento getSuspeita(){
        return suspeita;
    }

    public boolean verificarResposta(String resposta){
        resposta = resposta.toLowerCase(Locale.ROOT);

        if(resposta.equals("sim") || resposta.equals("não")){
            if(resposta.equals("sim")){
                adicionarSuspeita();
            }
        }
        else{
            return false;
        }
        return true;
    }
}

class ListaPerguntas{
    private final List<Pergunta> perguntas;

    public ListaPerguntas(){
        perguntas = new ArrayList<>(){};

        perguntas.add(new Pergunta("Telefonou para a vítima?"));
        perguntas.add(new Pergunta("Esteve no local do crime"));
        perguntas.add(new Pergunta("Mora perto da vítima?"));
        perguntas.add(new Pergunta("Devia para a vítima?"));
        perguntas.add(new Pergunta("Trabalhou com a vítima?"));
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }
}

class Pergunta{
    private final String pergunta;

    public Pergunta(String pergunta){
        this.pergunta = pergunta;
    }

    public String getPergunta() {
        return pergunta;
    }
}