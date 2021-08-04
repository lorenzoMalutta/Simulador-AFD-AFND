import java.io.*;

public class AFND {
  static  int[] aceitacao = {2};

  static  int estadoInicial = 0;

  static  int[][][] transicao = {
                                {{0,1}, {0}},
                                {{},   {2}},
                                {{},   {}}
                                };

  public  static void main(String args[])throws IOException {

      FileWriter arq = new FileWriter("C:\\Users\\loren\\IdeaProjects\\AFDgenerico\\src\\saida.txt");
      PrintWriter gravarArq = new PrintWriter(arq);

      // Cria o arquivo de entrada de teste
      String linha = new String();
      String nomeArquivo = "C:\\Users\\loren\\IdeaProjects\\AFDgenerico\\src\\entrada.txt";

      try {
          FileReader leitroDeArquivo = new FileReader(nomeArquivo);
          BufferedReader bufferDeArquivo = new BufferedReader(leitroDeArquivo);

          while (true) {
              linha = bufferDeArquivo.readLine();
              if (linha == null) {
                  break;
              }

              System.out.println("\n" + linha);

              String entrada = linha;

              int[] estados = {estadoInicial};

              int[] estadosFinais = testa(entrada, estados, 0);

              if (aceita(estadosFinais)) {
                  System.out.println("aceita");
                  gravarArq.println("aceita - 1");
              } else {
                  System.out.println("rejeita");
                  gravarArq.println("Rejeita - 0");
              }
          }
          arq.close();

      }catch (Exception e){

      }
  }

    private static int[] testa(String entrada, int[] estados, int posicao ){
        if(posicao == entrada.length()){
            if(aceita(estados)){
                return  estados;
            }
            System.out.println("backtrack Fim da cadeia");
            return null;
      }
      int elemento = Integer.parseInt(entrada.substring(posicao, posicao+1));
      for(int i:estados){
          
          imprimeCI(entrada, i, posicao);

          int[] novosEstados = transicao[i][elemento];

          if(novosEstados.length == 0){
              System.out.println("backtrack  sem opçoes ");
              return  null;
          }

          int[] transicoes = testa(entrada, novosEstados, posicao+1);

          if(transicoes != null) {
              return transicoes;
          }
      }
      return null;
  }
    private static boolean aceita(int[] estados){
        if(estados == null) {
            return false;
        }
        for (int i:estados){
            for(int j:aceitacao){
                if(i==j){
                    return true;
                }
            }
        }
        return false;
    }
    private static void imprimeCI(String entrada, int estado, int posicao){
        System.out.print(entrada.substring(0, posicao));
        System.out.print("[q"+estado+"]");
        System.out.println(entrada.substring(posicao));
    }
}
