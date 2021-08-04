import java.io.*;

public class AFDgenerico {

    // tabela de transicao
    static int[][] transicao = {{1,1}, {0,0}};

    // Estado final
    static int[] aceitacao = {0};

    // Estado Inicial
    static int estadoInicial = 0;

        public static void main (String args[])throws IOException{

            // Cria o arquivo saida
            FileWriter arq = new FileWriter("C:\\Users\\loren\\IdeaProjects\\AFDgenerico\\src\\saida.txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            // Cria o arquivo de entrada de teste
            String linha = new String();
            String nomeArquivo = "C:\\Users\\loren\\IdeaProjects\\AFDgenerico\\src\\entrada.txt";

            try {
                FileReader leitroDeArquivo = new FileReader(nomeArquivo);
                BufferedReader bufferDeArquivo = new BufferedReader(leitroDeArquivo);

                while (true)
                {
                    linha = bufferDeArquivo.readLine();
                    if (linha == null)
                    {
                        break;
                    }

                    System.out.println("\n"+linha);

                    // entrada a ser testada
                    String entrada = linha;

                    int estado = estadoInicial;
                    int posicao = 0;

                    while (posicao < entrada.length())
                    {
                        imprimeCI(entrada, estado, posicao);

                        char elemento = entrada.charAt(posicao);

                        int elementoInt = Integer.parseInt(elemento + "");

                        estado = transicao[estado][elementoInt];
                        posicao++;
                    }

                    imprimeCI(entrada, estado, posicao);

                    boolean aceita = false;

                    for (int i : aceitacao)
                    {
                        if (estado == i) aceita = true;
                    }

                    if (aceita)
                    {
                        System.out.println("Aceito");
                        gravarArq.println("Aceito - 1");
                    } else {
                        System.out.println("Rejeita");
                        gravarArq.println("Rejeita - 0");
                    }
                }

                arq.close();

                }catch (Exception e){

                }
            }

        public static void imprimeCI(String entrada, int estado, int posicao){
            System.out.print(entrada.substring(0, posicao));
            System.out.print("[q"+estado+"]");
            System.out.println(entrada.substring(posicao));
        }
    }
