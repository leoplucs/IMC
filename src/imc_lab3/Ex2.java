/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imc_lab3;

/**
 *
 * @author 41356527
 */
1 //importação das classes necessárias
    2 //layouts para alinhamento dos componentes
    3 import java.awt.FlowLayout;
    4 import java.awt.GridLayout;
    5 //janela
    6 import javax.swing.JFrame;
    7 //butão
    8 import javax.swing.JButton;
    9 //caixa de seleção
   10 import javax.swing.JComboBox;
   11 //inserção de texto e/ou imagem
   12 import javax.swing.JLabel;
   13 //classe Icon junto com ImageIcon anexão uma imagem a um Jlabel
   14 import javax.swing.Icon;
   15 import javax.swing.ImageIcon;
   16 //exibição de textos numa pequena caixa de mensagens
   17 import javax.swing.JOptionPane;
   18 //painel
   19 import javax.swing.JPanel;
   20 //caixa de textos
   21 import javax.swing.JTextArea;
   22 //campo para inserção de valores ou caracteres
   23 import javax.swing.JTextField;
   24 //Evento para de ouvir um evento do botão
   25 import java.awt.event.ActionListener;
   26 //Evento de executar uma ação do evento que foi ouvido(ativado)
   27 import java.awt.event.ActionEvent;
   28 //Tratador de erros
   29 import java.util.InputMismatchException;
   30 
   31 //Criação da classe EX2 extendendo a classe JFrame que foi importada
   32 public class Ex2 extends JFrame
   33 {
   34    //declaração das variáveis
   35    //Vetor de caracteres (String) de 2 colunas
   36    private String sexo[]={"Mulher","Homem"};
   37    //inteiros (int)
   38    int tipo, foto;
   39    //numeros de pontos flutuantes (double)
   40    double altura, peso, massa;
   41    //criando um array já com os valores configurados do tipo double
   42    double imc_homens[] ={20.7,26.4};
   43    double imc_mulheres[] ={19.1,25.8};
   44    private String string="";
   45 
   46    //criação do FlowLayout que alinha componentes da esquerda para a direita.
   47    private FlowLayout flowLayout = new FlowLayout();
   48    /*criação de GridLayout com 4 linhas e 2 colunas com 10 de espaço em largura
   49     e 1 de altura*/
   50    private GridLayout gridLayout = new GridLayout(4,2,10,1);
   51    //criação de um painel
   52    private JPanel gridJPanel = new JPanel();
   53 
   54    //criação de dois botões com os nomes Calcular e Limpar Dados
   55    private JButton butao = new JButton("Calcular");
   56    private JButton butao2 = new JButton("Limpar Dados");
   57    //criação das label's
   58    private JLabel Lsexo = new JLabel("Escolha o sexo:");
   59    private JLabel Laltura = new JLabel("Altura em cm:");
   60    private JLabel Lpeso = new JLabel("Peso em Kg:");
   61    private JLabel Lresultado = new JLabel("");
   62    private JLabel Lfoto = new JLabel("");
   63    //criação de campos com 5 de largura
   64    private JTextField Faltura = new JTextField("",5);
   65    private JTextField Fpeso = new JTextField("",5);
   66 
   67    //criação de uma caixa de seleção
   68    private JComboBox escolha = new JComboBox(sexo);
   69 
   70    //anexando a imagem um icone de nome limpar
   71    private Icon limpar = new ImageIcon(getClass().getResource("blank.gif"));
   72    //criando um vetor com as demais imagens
   73    private Icon imagemM[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon
   74    (getClass().getResource("normalM.jpg")),new ImageIcon(getClass().getResource("obesa.jpg"))};
   75    private Icon imagemH[] = {new ImageIcon(getClass().getResource("esqueleto.gif")), new ImageIcon
   76    (getClass().getResource("normalH.jpg")),new ImageIcon(getClass().getResource("obeso.jpg"))};
   77 
   78    //construtor de Ex2 sem argumentos
   79    public Ex2()
   80    {
   81     //título  da janela
   82     super("Calculo do IMC(índice de massa corporal)");
   83     //alinhamento do frame com o uso do objeto flowLayout
   84     super.setLayout(flowLayout);
   85     //tamanho da janela
   86     setSize(370, 160);
   87     //inclusão dos componentes de maximinizar, miniminizar e fechar
   88     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   89 
   90     //exibe 2 linhas da caixa de seleção ao clicá-la
   91     escolha.setMaximumRowCount(2);
   92     //alinha o painel com o objeto gridLayout
   93     gridJPanel.setLayout(gridLayout);
   94     //adiciona os componentes
   95     gridJPanel.add(Lsexo);
   96     gridJPanel.add(escolha);
   97     gridJPanel.add(Laltura);
   98     gridJPanel.add(Faltura);
   99     gridJPanel.add(Lpeso);
  100     gridJPanel.add(Fpeso);
  101     gridJPanel.add(butao);
  102     gridJPanel.add(butao2);
  103 
  104     //adiciona a janela principal
  105     super.add(gridJPanel);
  106     super.add(Lfoto);
  107     super.add(Lresultado);
  108 
  109     //criação de uma classe interna anônima para butao
  110     butao.addActionListener(
  111     new ActionListener()
  112     {
  113        public void actionPerformed(ActionEvent event)
  114        {
  115         switch (escolha.getSelectedIndex())
  116         {
  117            /*caso seja a primeira opção que foi selecionada na caixa de seleção
  118             configure tipo=0 e vá para o método Calculos*/
  119            case 0:
  120            {
  121              tipo=0; //mulher
  122              Calculos();
  123              break;
  124            }
  125            //caso seja a segunda opção, configura tipo como 1 e vá para o método Calculos.
  126            case 1:
  127            {
  128              tipo=1; //homem
  129              Calculos();
  130              break;
  131            }
  132         }
  133        }
  134      }//Fim da classe interna anônima
  135     );//fim da chamada para addActionListerner
  136 
  137     //classe interna anônima para butao2
  138     butao2.addActionListener(
  139     new ActionListener()
  140     {
  141        //ao clicar no butao2 de nome limpar chama o método limpar
  142        public void actionPerformed(ActionEvent event)
  143        {
  144         limpar();
  145        }
  146     }//Fim da classe interna anônima
  147     );//fim da chamada para addActionListerner
  148    }
  149 
  150    //método que realiza os calculos
  151    private void Calculos()
  152    {
  153        try //tratador de erros com try e catch
  154        {
  155           //pega e converte os caracteres em ponto flutuante do campo Faltura para a variavel altura
  156           altura=Double.parseDouble(Faltura.getText());
  157           //converte para metros
  158           altura/=100;
  159           //da mesma forma com Fpeso para a variável peso
  160           peso=Double.parseDouble(Fpeso.getText());
  161           //realiza calculos
  162           massa=peso/(altura*altura);
  163           /*Se a massa corporal for menor do que o estabelecido pelo vetor configure a variável
  164            string com essa frase*/
  165          if (tipo==0)
  166          {
  167             if (imc_mulheres[0]>massa)
  168             {
  169                string = String.format("CUIDADO!!!Voce estar abaixo do peso! IMC %.2f",massa);
  170                foto=0;
  171             }
  172             else if((imc_mulheres[0]<massa) && (massa<=imc_mulheres[1]))
  173             {
  174                string = String.format("PARABENS!!Voce estar com o peso ideal! IMC %.2f",massa);
  175                foto=1;
  176             }
  177             else
  178             {
  179                string = String.format("CUIDADO!!Voce estar obesa! IMC %.2f",massa);
  180                foto=2;
  181             }
  182             //configure a foto conforme a posição da variável foto
  183             Lfoto.setIcon(imagemM[foto]);
  184          }
  185          else if (tipo==1)
  186          {
  187             if (imc_homens[0]>massa)
  188             {
  189                string = String.format("CUIDADO!!!Voce estar abaixo do peso! IMC %.2f",massa);
  190                //configura a posição que será exibido a imagem
  191                foto=0;
  192             }
  193             else if((imc_homens[0]<massa) && (massa<imc_homens[1]))
  194             {
  195                string = String.format("PARABENS!!Voce estar com o peso ideal! IMC %.2f",massa);
  196                foto=1;
  197             }
  198             else
  199             {
  200                string = String.format("CUIDADO!!Voce estar obeso! IMC %.2f",massa);
  201                foto=2;
  202             }
  203             //configure a foto conforme a posição da variável foto
  204             Lfoto.setIcon(imagemH[foto]);
  205          }
  206           //reconfigure o tamanho da tela
  207           setSize(370, 500);
  208           //configure a label Lresultado com a variável string
  209           Lresultado.setText(string);
  210        }
  211        //caso ocorra uma excessão(erro) exiba uma mensagem nua caixa de mensagem
  212        catch(NumberFormatException exception)
  213        {
  214         JOptionPane.showMessageDialog(this,"No número inválido!\nEx: Use '.' ao invés de ',' para separar as casas decimais.","ERROR FATAL!!!",JOptionPane.ERROR_MESSAGE);
  215         //limpe s campos e variáveis
  216         Fpeso.setText("");
  217         Faltura.setText("");
  218         peso=0;
  219         altura=0;
  220        }
  221    }
  222    //método para limpar os dados da tela e retornar a tela ao seu tamanho original
  223    private void limpar()
  224    {
  225     Fpeso.setText("");
  226     Faltura.setText("");
  227     Lresultado.setText("");
  228     //substitua a imagem atual por essa
  229     Lfoto.setIcon(limpar);
  230     setSize(300, 160);
  231    }
  232 }//Fim da classe Ex2

