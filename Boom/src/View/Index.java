/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Controller.ctrCliente;
import Controller.ctrEmprestimo;
import Controller.ctrFuncionario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.data_acess_object.clienteDAO;
import model.data_acess_object.funcionarioDAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author nazar
 */
public class Index extends JFrame implements ActionListener {
    //Public
    Container geral;
    JPanel topLevel, mnLevel,titLevel, central;
    JTextField pesq;
    JButton btnPesq, btnLogin, menuIni, menuEmp, menuCli, menuReg, menuFunc, menuSett;
    JLabel lblTit, lblLogo;
    //Home
                //JLabel lblIconMic;
                //JPanel infoLevel;
                //JButton btnStart;
    JPanel infoLevel[], infoLevelIntern[], paneCircularHome, paneBarrasHome;
    JLabel infoLabel[];
    int liq, activo, entrada, saida;
    DefaultPieDataset dadosGraficoCircular;
    JFreeChart circularHome, barrasHome;
    ChartPanel grfCircularHome, grfBarrasHome;
    DefaultCategoryDataset dadosGraficoBarra;
    
    //CLients
    JPanel clientAll,searchClient;
    DefaultTableModel client;
    JTable tblClient, tblFuncio;
    JScrollPane scrollClient;
    JTextField entryID;
    JLabel txtDig;
    JButton pesqClient, addClient,removeCli;
    //Funcionario
    JPanel searchFuncio;
    JButton pesqFuncio, addFuncio, editFuncio, salFuncio, removeFuncio;
    JPanel funcioAll;
    DefaultTableModel funcio;
    //Emprestimo
    float vlPres;
    JPanel emp;
    JButton pesqEmp,newEmp,editEmp,payEmp, histEmp;
    JLabel iconProfile, txtCli, txtEmp, txtNome,txtCred,txtVf,txtVp, txtFuncio;
    JTextField  entryCli, entryEmp,entryNome,entryCred,entryVf,entryVp,entryFuncio;
           
    Cliente cl;
    Funcionario func;
    Emprestimo emprest;
    
    public Index(){
        setTitle("HOME");
        setSize(1360,745);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //Container GERAL
        geral = getContentPane();
        geral.setBackground(new java.awt.Color(237, 237, 237));
        geral.setLayout(null);
        
        //Icons
        ImageIcon logoPesq=new ImageIcon("Imagens\\logoPesquisar.jpeg");
        ImageIcon logoLogin=new ImageIcon("Imagens\\logoLogin.jpeg");
        ImageIcon logoHome=new ImageIcon("Imagens\\home.jpeg");
        ImageIcon logoClient=new ImageIcon("Imagens\\client.jpeg");
        ImageIcon logoEmprestimo=new ImageIcon("Imagens\\emprestimo.jpeg");
        ImageIcon logoFuncionario=new ImageIcon("Imagens\\funcionario.jpeg");
        ImageIcon logoRegistro=new ImageIcon("Imagens\\registro.jpeg");
        ImageIcon logoSettings=new ImageIcon("Imagens\\settings.jpeg");
        ImageIcon logoKhuvu=new ImageIcon("Imagens\\logoKhuvu.jpeg");
        
        //logo Khuvu
        lblLogo=new JLabel();
        lblLogo.setIcon(logoKhuvu);
        lblLogo.setBounds(30,10,200,80);
        //TextFields
        pesq=new JTextField("");//Para Pesquisar algo
        pesq.setBackground(Color.WHITE);
        
        pesq.setBounds(250,25,250,50);
        
        //Buttons
        btnPesq=new JButton();//Para Clicar em pesquisar
        btnPesq.setIcon(logoPesq);
        btnPesq.setBackground(new java.awt.Color(0, 223, 186));
        btnPesq.setBorder(null);
        btnPesq.setBounds(500,25,90,50);
        
        btnLogin=new JButton("˅");
        btnLogin.setBackground(new java.awt.Color(237, 237, 237));
        btnLogin.setIcon(logoLogin);
        btnLogin.setBorder(null);
        btnLogin.setBounds(1260,20,70,60);
        //Label TITULO
        Font ftTit=new Font("Courier",Font.BOLD, 19);
        lblTit= new JLabel("");
        lblTit.setFont(ftTit);
        lblTit.setHorizontalAlignment(SwingConstants.LEFT);
        lblTit.setBackground(new java.awt.Color(97,124,140));
        lblTit.setForeground(Color.WHITE);
        lblTit.setBounds(50,20,175,40);
        
        //Estererelizaação da BARRA DE MENU
        Font ft=new Font("Courier",Font.BOLD, 16);

        menuIni=new JButton("Página Inicial");
        menuIni.setName("ini");
        menuIni.setIcon(logoHome);
        menuIni.setHorizontalAlignment(SwingConstants.LEFT);
        menuIni.setBackground(new java.awt.Color(58,70,80));
        menuIni.setForeground(Color.WHITE);
        menuIni.setBorder(null);
        menuIni.setFont(ft);
        menuIni.setBounds(50,25,200,50);
        menuIni.addActionListener(this);
        
        menuEmp=new JButton("Empréstimo");
        menuEmp.setName("emp");
        menuEmp.setIcon(logoEmprestimo);
        menuEmp.setHorizontalAlignment(SwingConstants.LEFT);
        menuEmp.setBackground(new java.awt.Color(58,70,80));
        menuEmp.setForeground(Color.WHITE);
        menuEmp.setBorder(null);
        menuEmp.setFont(ft);
        menuEmp.setBounds(50,120,200,50);
        menuEmp.addActionListener(this);
        
        menuCli=new JButton("Cliente");
        menuCli.setName("cli");
        menuCli.setIcon(logoClient);
        menuCli.setHorizontalAlignment(SwingConstants.LEFT);
        menuCli.setBackground(new java.awt.Color(58,70,80));
        menuCli.setForeground(Color.WHITE);
        menuCli.setBorder(null);
        menuCli.setFont(ft);
        menuCli.setBounds(50,210,200,50);
        menuCli.addActionListener(this);
        
        menuFunc=new JButton("Funcionário");
        menuFunc.setName("fun");
        menuFunc.setIcon(logoFuncionario);
        menuFunc.setHorizontalAlignment(SwingConstants.LEFT);
        menuFunc.setBackground(new java.awt.Color(58,70,80));
        menuFunc.setForeground(Color.WHITE);
        menuFunc.setBorder(null);
        menuFunc.setFont(ft);
        menuFunc.setBounds(50,300,200,50);
        menuFunc.addActionListener(this);
        
        menuReg=new JButton("Registro");
        menuReg.setName("reg");
        menuReg.setIcon(logoRegistro);
        menuReg.setHorizontalAlignment(SwingConstants.LEFT);
        menuReg.setBackground(new java.awt.Color(58,70,80));
        menuReg.setForeground(Color.WHITE);
        menuReg.setBorder(null);
        menuReg.setFont(ft);
        menuReg.setBounds(50,390,200,50);
        
        menuSett=new JButton("Settings");
        menuSett.setName("set");
        menuSett.setIcon(logoSettings);
        menuSett.setHorizontalAlignment(SwingConstants.LEFT);
        menuSett.setBackground(new java.awt.Color(58,70,80));
        menuSett.setForeground(Color.WHITE);
        menuSett.setBorder(null);
        menuSett.setFont(ft);
        menuSett.setBounds(50,480,200,50);
        
        //Painel TOP
        topLevel=new JPanel();
        topLevel.setLayout(null);
        topLevel.setBounds(0,0,1360,100);
        topLevel.add(btnPesq);
        topLevel.add(btnLogin);
        topLevel.add(pesq);
        topLevel.add(lblLogo);
        //Painel Lateral
        mnLevel=new JPanel();
        mnLevel.setLayout(null);
        mnLevel.setBackground(new java.awt.Color(58,70,80));
        mnLevel.setBounds(0,100,250,645);
        mnLevel.add(menuIni);
        mnLevel.add(menuEmp);
        mnLevel.add(menuCli);
        mnLevel.add(menuFunc);
        mnLevel.add(menuReg);
        mnLevel.add(menuSett);
        //Painel Titulo de menu
        titLevel=new JPanel();
        titLevel.setLayout(null);
        titLevel.setBackground(new java.awt.Color(97,124,140));
        titLevel.setBounds(250,100,1110,80);
        titLevel.add(lblTit);
        //Painel Central
        central=new JPanel();
        central.setLayout(null);
        central.setBounds(250,180,1110,645);
        
        painelHome();
        
        geral.add(topLevel);
        geral.add(mnLevel);
        geral.add(titLevel);
        geral.add(central);
        setVisible(true);
    }
    
    public void painelHome(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        lblTit.setText("Pagina Inicial");
        setTitle("HOME");
        
        central.setBackground(Color.WHITE);
        
        infoLevelIntern = new JPanel[8];
        infoLevel = new JPanel[4];
        infoLabel = new JLabel[8];
        
        for(int i =  0; i < infoLevel.length; i++)
            infoLevel[i] = new JPanel();
        
        for(int i =  0; i < infoLabel.length; i++){
            infoLabel[i] = new JLabel();
            infoLevelIntern[i] = new JPanel();
        }
        
        //Fonte
        Font infoFtU = new Font("Courier",Font.BOLD, 24);
        Font infoFtL = new Font("Courier",Font.ROMAN_BASELINE, 16);
        
        //Label Clientes
        infoLabel[0].setText("1000"); //BD info. nr de clientes
        infoLabel[0].setForeground (Color.white);
        infoLabel[0].setFont(infoFtU);
        infoLabel[1].setText("Clientes");
        infoLabel[1].setForeground (Color.white);
        infoLabel[1].setFont(infoFtL);
        
        //Label Emprestimo
        infoLabel[2].setText("400"); //BD info. nr de emprestimos
        infoLabel[2].setForeground (Color.white);
        infoLabel[2].setFont(infoFtU);
        infoLabel[3].setText("Emprestimos");
        infoLabel[3].setForeground (Color.white);
        infoLabel[3].setFont(infoFtL);
        
        //Label Liquidado
        infoLabel[4].setText("36"); //BD info. nr de liquidações
        infoLabel[4].setForeground (Color.white);
        infoLabel[4].setFont(infoFtU);
        infoLabel[5].setText("Liquidado");
        infoLabel[5].setForeground (Color.white);
        infoLabel[5].setFont(infoFtL);
        
        //Label Percentagem Emprestimo/Liquidado
        infoLabel[6].setText(String.valueOf(36/400f)+"%");
        infoLabel[6].setForeground (Color.white);
        infoLabel[6].setFont(infoFtU);
        infoLabel[7].setText("E/L");
        infoLabel[7].setForeground (Color.white);
        infoLabel[7].setFont(infoFtL);
        
        //Painel Clientes
        infoLevel[0].setBounds(50,30,200,90);
        infoLevel[0].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].setLayout(new GridLayout(2,1));
        infoLevelIntern[0].setBackground(new java.awt.Color(0,120,50));
        infoLevelIntern[1].setBackground(new java.awt.Color(0,120,50));
        infoLevel[0].add(infoLevelIntern[0]);
        infoLevel[0].add(infoLevelIntern[1]);
        infoLevelIntern[0].add(infoLabel[0]);
        infoLevelIntern[1].add(infoLabel[1]);
        
        //Painel Emprestimo
        infoLevel[1].setBounds(300,30,200,90);
        infoLevel[1].setBackground(new java.awt.Color(0,102,204));
        infoLevel[1].setLayout(new GridLayout(2,1));
        infoLevelIntern[2].setBackground(new java.awt.Color(0,120,204));
        infoLevelIntern[3].setBackground(new java.awt.Color(0,120,204));
        infoLevel[1].add(infoLevelIntern[2]);
        infoLevel[1].add(infoLevelIntern[3]);
        infoLevelIntern[2].add(infoLabel[2]);
        infoLevelIntern[3].add(infoLabel[3]);
        
        //Painel Liquidado
        infoLevel[2].setBounds(550,30,200,90);
        infoLevel[2].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].setLayout(new GridLayout(2,1));
        infoLevelIntern[4].setBackground(new java.awt.Color(178,102,255));
        infoLevelIntern[5].setBackground(new java.awt.Color(178,102,255));
        infoLevel[2].add(infoLevelIntern[4]);
        infoLevel[2].add(infoLevelIntern[5]);
        infoLevelIntern[4].add(infoLabel[4]);
        infoLevelIntern[5].add(infoLabel[5]);
        
        //Painel Percentagem Emprestimo/Liquidado
        infoLevel[3].setBounds(800,30,200,90);
        infoLevel[3].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].setLayout(new GridLayout(2,1));
        infoLevelIntern[6].setBackground(new java.awt.Color(255,153,51));
        infoLevelIntern[7].setBackground(new java.awt.Color(255,153,51));
        infoLevel[3].add(infoLevelIntern[6]);
        infoLevel[3].add(infoLevelIntern[7]);
        infoLevelIntern[6].add(infoLabel[6]);
        infoLevelIntern[7].add(infoLabel[7]);
        
        //semanal, mensal, anual;
        
        activo = 30;
        liq = 10;
        entrada=10000000;
        saida=1000000;
        
        //Painel Grafico Circular
        paneCircularHome = new JPanel();
        paneCircularHome.setBounds(50,150,500,360);
        paneCircularHome.setLayout(new BorderLayout());
        paneCircularHome.setBackground(new java.awt.Color(147,147,147));
        dadosGraficoCircular = new DefaultPieDataset();
        dadosGraficoCircular.setValue("Entrada", entrada); // Definindo categorias
        dadosGraficoCircular.setValue("Saídas", saida);
        
        //Grafico Circular
        circularHome = ChartFactory.createPieChart("Activos", dadosGraficoCircular, true,true,false);
        grfCircularHome = new ChartPanel(circularHome);
        paneCircularHome.add(grfCircularHome, BorderLayout.CENTER);
        
        //Painel Grafico de barras
        paneBarrasHome = new JPanel();
        paneBarrasHome.setBounds(570,150,440,360);
        paneBarrasHome.setLayout(new BorderLayout());
        paneBarrasHome.setBackground(new java.awt.Color(147,147,147));
        dadosGraficoBarra = new DefaultCategoryDataset();
        dadosGraficoBarra.setValue(activo,"Activo", "Activo");
        dadosGraficoBarra.setValue(liq,"Liquidado", "Liquidado");
        
         //Grafico de Barras
        barrasHome = ChartFactory.createBarChart("Emprestimo", "", "Saldo", dadosGraficoBarra);
        grfBarrasHome = new ChartPanel(barrasHome);
        paneBarrasHome.add(grfBarrasHome, BorderLayout.CENTER);
                
        central.add(infoLevel[0]);
        central.add(infoLevel[1]);
        central.add(infoLevel[2]);
        central.add(infoLevel[3]);
        central.add(paneCircularHome);
        central.add(paneBarrasHome);
        
    }
    
    public void painelClientes(){
        central.removeAll();
        central.revalidate();
        central.repaint();
        
        setTitle("CLIENTE");
        lblTit.setText("CLIENTE");
        //Painel contendo Lista de clientes
        clientAll=new JPanel();
        clientAll.setLayout(null);
        clientAll.setBackground(Color.WHITE);
        clientAll.setBounds(50,50,600,350);
        central.add(clientAll);
        
        JSeparator sep=new JSeparator(JSeparator.VERTICAL);
        sep.setBackground(Color.BLACK);
        sep.setBounds(675,50,1,350);
        central.add(sep);
        //Label do titulo
        JPanel borda=new JPanel();
        borda.setLayout(null);
        borda.setBackground(new java.awt.Color(58,70,80));
        borda.setBounds(0,0,600,50);
        clientAll.add(borda);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblWl=new JLabel("Lista de Clientes");
        lblWl.setFont(ft);
        lblWl.setBounds(125,10,350,30);
        lblWl.setHorizontalAlignment(SwingConstants.CENTER);
        lblWl.setForeground(Color.WHITE);
        borda.add(lblWl);
        //Table
        client= new DefaultTableModel();
        client.addColumn("ID");
        client.addColumn("Nome");
        client.addColumn("Contacto");
        tblClient=new JTable(client);
        scrollClient=new JScrollPane(tblClient);
        scrollClient.setBounds(0,50,600,300);
        readClientes();
        clientAll.add(scrollClient);
        //painel de pesquisa
        searchClient=new JPanel();
        searchClient.setLayout(null);
        searchClient.setBackground(Color.WHITE);
        searchClient.setBounds(700,50,360,195);
        central.add(searchClient);
        JPanel bordaS=new JPanel();
        bordaS.setLayout(null);
        bordaS.setBackground(new java.awt.Color(58,70,80));
        bordaS.setBounds(0,0,600,50);
        searchClient.add(bordaS);
        
        
        JLabel lblS=new JLabel("Pesquisar");
        lblS.setFont(ft);
        lblS.setBounds(105,10,150,30);
        lblS.setHorizontalAlignment(SwingConstants.CENTER);
        lblS.setForeground(Color.WHITE);
        bordaS.add(lblS);
        //Pesquisar
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Cliente:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        
        entryID=new JTextField();
        entryID.setBounds(50,100,260,25);
        
        pesqClient=new JButton("Pesquisar");
        pesqClient.setBackground(new java.awt.Color(0, 202, 188));
        pesqClient.setBounds(210,145,100,30);
        pesqClient.setForeground(Color.WHITE);
        pesqClient.addActionListener(this);
        
        searchClient.add(pesqClient);
        searchClient.add(txtDig);
        searchClient.add(entryID);
        //Acionar Clientes
        addClient=new JButton("ADD CLIENT");
        addClient.setBackground(new java.awt.Color(0, 202, 188));
        addClient.setForeground(Color.WHITE);
        addClient.setBounds(500,420,150,30);
        addClient.addActionListener(this);
        
        central.add(addClient);
        
    }
    
    public void painelFuncionario(){
        central.removeAll();
        central.revalidate();
        central.repaint();

        setTitle("Funcionário");
        lblTit.setText("Funcionário");
        
        //Search bar
        searchFuncio=new JPanel();
        searchFuncio.setLayout(null);
        searchFuncio.setBackground(Color.WHITE);
        searchFuncio.setBorder(BorderFactory.createLineBorder(Color.black));
        searchFuncio.setBounds(50,50,600,155);
        central.add(searchFuncio);
        JPanel bordaF=new JPanel();
        bordaF.setLayout(null);
        bordaF.setBackground(new java.awt.Color(58,70,80));
        bordaF.setBounds(0,0,600,50);
        searchFuncio.add(bordaF);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblF=new JLabel("Pesquisar");
        lblF.setFont(ft);
        lblF.setBounds(50,10,150,30);
        lblF.setHorizontalAlignment(SwingConstants.LEFT);
        lblF.setForeground(Color.WHITE);
        bordaF.add(lblF);
        //Pesquisar
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Funcionário:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        
        entryID=new JTextField(); //DIgitar o ID que pretende encontrar
        entryID.setBounds(50,100,260,30);
        
        pesqFuncio=new JButton("Pesquisar");
        pesqFuncio.setBackground(new java.awt.Color(0, 202, 188));
        pesqFuncio.setBounds(320,100,100,30);
        pesqFuncio.setForeground(Color.WHITE);
        pesqFuncio.addActionListener(this);
        
        searchFuncio.add(pesqFuncio);
        searchFuncio.add(txtDig);
        searchFuncio.add(entryID);
        //Lista Funcio
        funcioAll=new JPanel();
        funcioAll.setLayout(null);
        funcioAll.setBounds(50,225,600,260);
        funcioAll.setBackground(Color.WHITE);
        central.add(funcioAll);
        
        JPanel bordaL=new JPanel();
        bordaL.setLayout(null);
        bordaL.setBackground(new java.awt.Color(58,70,80));
        bordaL.setBounds(0,0,600,50);
        funcioAll.add(bordaL);
        
        JLabel lblL=new JLabel("Lista de Funcionários");
        lblL.setFont(ft);
        lblL.setBounds(50,10,200,30);
        lblL.setHorizontalAlignment(SwingConstants.LEFT);
        lblL.setForeground(Color.WHITE);
        bordaL.add(lblL);
        
       
        funcio=new DefaultTableModel();
        funcio.addColumn("ID");
        funcio.addColumn("Nome");
        tblFuncio=new JTable(funcio);
        JScrollPane scrollFuncio=new JScrollPane(tblFuncio);
        scrollFuncio.setBackground(Color.WHITE);
        scrollFuncio.setBounds(0,50,600,210);
        funcioAll.add(scrollFuncio);
        
         readFuncionarios();
        JSeparator barSep=new JSeparator(JSeparator.VERTICAL);
        barSep.setBounds(680,50,2,435);
        
        
        //TOOLS
        JPanel bordaT=new JPanel();
        bordaT.setLayout(null);
        bordaT.setBackground(new java.awt.Color(58,70,80));
        bordaT.setBounds(710,50,350,50);
        central.add(bordaT);
        
        JLabel lblT=new JLabel("TOOLS");
        lblT.setFont(ft);
        lblT.setBounds(50,10,200,30);
        lblT.setHorizontalAlignment(SwingConstants.LEFT);
        lblT.setForeground(Color.WHITE);
        bordaT.add(lblT);
        
        central.add(barSep);
        
        addFuncio=new JButton("ADD Funcionário");
        addFuncio.setBounds(790,120,170,40);
        addFuncio.setBackground(new java.awt.Color(0, 202, 188));
        addFuncio.setForeground(Color.WHITE);
        addFuncio.addActionListener(this);
        central.add(addFuncio);
        
        salFuncio=new JButton("Folha Salarial");
        salFuncio.setBounds(790,180,170,40);
        salFuncio.setBackground(new java.awt.Color(0, 202, 188));
        salFuncio.setForeground(Color.WHITE);
        central.add(salFuncio);
        
    }
    
    public void painelEmprestimo(){
        central.removeAll();
        central.revalidate();
        central.repaint();

        setTitle("EMPRÉSTIMO");
        lblTit.setText("Empréstimo");
        
        emp=new JPanel();
        emp.setLayout(null);
        emp.setBorder(BorderFactory.createLineBorder(Color.black));
        emp.setBackground(Color.WHITE);
        emp.setBounds(50,100,1010,370);
        
        JPanel bordaF=new JPanel();
        bordaF.setLayout(null);
        bordaF.setBackground(new java.awt.Color(58,70,80));
        bordaF.setBounds(0,0,1010,50);
        emp.add(bordaF);
        
        Font ft=new Font("Courier",Font.BOLD, 15);
        JLabel lblF=new JLabel("Informações do Empréstimo ");
        lblF.setFont(ft);
        lblF.setBounds(50,10,250,30);
        lblF.setHorizontalAlignment(SwingConstants.LEFT);
        lblF.setForeground(Color.WHITE);
        bordaF.add(lblF);
        //Create EMP
        newEmp=new JButton("Novo Empréstimo");
        newEmp.setBackground(new java.awt.Color(0, 202, 188));
        newEmp.setForeground(Color.WHITE);
        newEmp.setBounds(50,30,175,50);
        newEmp.addActionListener(this);
        
        histEmp=new JButton("Histórico");
        histEmp.setBackground(new java.awt.Color(0, 202, 188));
        histEmp.setForeground(Color.WHITE);
        histEmp.setBounds(250,30,175,50);
        
        JSeparator sep=new JSeparator(JSeparator.HORIZONTAL);
        sep.setForeground(Color.BLACK);
        sep.setBounds(50,90,1010,2);
        
        central.add(sep);
        central.add(newEmp);
        central.add(histEmp);
        //pesquisa
        Font ft2=new Font("Courier",Font.BOLD, 13);
        txtDig=new JLabel("Digite o ID do Empréstimo:");
        txtDig.setFont(ft2);
        txtDig.setBounds(50,60,200,30);
        emp.add(txtDig);
        
        entryID=new JTextField(); //DIgitar o ID que pretende encontrar
        entryID.setBounds(270,60,200,30);
        emp.add(entryID);
        
        pesqEmp=new JButton("Pesquisar");
        pesqEmp.setBackground(new java.awt.Color(0, 202, 188));
        pesqEmp.setBounds(470,60,100,30);
        pesqEmp.setForeground(Color.WHITE);
        pesqEmp.addActionListener(this);
        emp.add(pesqEmp);
        
        JSeparator separador=new JSeparator(JSeparator.HORIZONTAL);
        separador.setForeground(Color.BLACK);
        separador.setBounds(50,100,910,2);
        emp.add(separador);
        //foto de perfil
        iconProfile=new JLabel();
        iconProfile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        iconProfile.setBounds(50,110,175,200);
        emp.add(iconProfile);
        //Botoes operacao Emp
        JSeparator separador2=new JSeparator(JSeparator.VERTICAL);
        separador2.setForeground(Color.BLACK);
        separador2.setBounds(660,110,2,200);
        
        payEmp=new JButton("Efectuar Pagamento");
        payEmp.setBackground(new java.awt.Color(0, 202, 188));
        payEmp.setBounds(730, 110, 175, 35);
        payEmp.setForeground(Color.WHITE);
        payEmp.addActionListener(this);
       

        emp.add(payEmp);
        //DADDOS
        
        txtEmp=new JLabel("ID:");
        txtEmp.setBounds(235,110,120,20);
        entryEmp=new JTextField("");
        entryEmp.setBounds(370,110,130,20);
        entryEmp.setEditable(false);
        
        txtCli=new JLabel("ID Cliente:");
        txtCli.setBounds(235,140,140,20);
        entryCli=new JTextField();
        entryCli.setBounds(370,140,130,20);
        entryCli.setEditable(false);
        
        txtNome=new JLabel("Nome do Cliente:");
        txtNome.setBounds(235,170,140,20);
        entryNome=new JTextField();
        entryNome.setBounds(370,170,230,20);
        entryNome.setEditable(false);
        
        txtCred=new JLabel("Valor do Crédito:");
        txtCred.setBounds(235,200,140,20);
        entryCred=new JTextField();
        entryCred.setBounds(370,200,150,20);
        entryCred.setEditable(false);
        
        txtVf=new JLabel("Valor Remanescente:");
        txtVf.setBounds(235,230,140,20);
        entryVf=new JTextField();
        entryVf.setBounds(370,230,150,20);
        entryVf.setEditable(false);
        
        txtVp=new JLabel("Valor Arrematado:");
        txtVp.setBounds(235,260,140,20);
        entryVp=new JTextField();
        entryVp.setBounds(370,260,150,20);
        entryVp.setEditable(false);
        
        txtFuncio=new JLabel("ID Funcionário:");
        txtFuncio.setBounds(235,290,140,20);
        entryFuncio=new JTextField();
        entryFuncio.setBounds(370,290,130,20);
        entryFuncio.setEditable(false);
        

        
        Font ft3=new Font("Courier",Font.PLAIN, 12);
        emp.add(txtEmp);
        emp.add(txtCli);
        emp.add(txtNome);
        emp.add(txtCred);
        emp.add(txtVf);
        emp.add(txtVp);
        emp.add(txtFuncio);
        emp.add(entryEmp);
        emp.add(entryCli);
        emp.add(entryNome);
        emp.add(entryCred);
        emp.add(entryVf);
        emp.add(entryVp);
        emp.add(entryFuncio);
        emp.add(separador2);
        
        central.add(emp);
    }
    
    public void readClientes(){ 
        ctrCliente ctrCli =new ctrCliente();
        Vector cle=ctrCli.listar();
        for(int i=0;i<cle.size();i++)
            client.addRow((Object[]) cle.elementAt(i));
        
    }
    
    public void readFuncionarios(){ 
        ctrFuncionario ctrFun =new ctrFuncionario();
        Vector lst=ctrFun.listar();
        for(int i=0;i<lst.size();i++){
            funcio.addRow((Object[]) lst.elementAt(i));
        }
    }
    
    public static void main(String[] args) {
        new Index();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==menuIni)
            painelHome();
        
        if(ae.getSource()==menuCli)
            painelClientes();
        if(ae.getSource()==menuFunc)
            painelFuncionario();
        if(ae.getSource()==menuEmp)
            painelEmprestimo();
        
        if(ae.getSource()==addClient){
            cl=new Cliente();
            painelClientes();
        }
        if(ae.getSource()==newEmp){
            
            emprest=new Emprestimo();}
        if(ae.getSource()==addFuncio){
            
            func=new Funcionario();
            painelFuncionario();
        }
        if(ae.getSource()==payEmp){
            payEmp pp= new payEmp(Integer.parseInt(entryEmp.getText()),vlPres);
        }
        
        if(ae.getSource()==pesqClient){
            int id=Integer.parseInt(entryID.getText());
            boolean scc;
            try{
                ctrCliente ctr=new ctrCliente();
                scc= ctr.encontrar(Integer.parseInt(entryID.getText()));
                if(scc==true){
                    editCliente edt=new editCliente(ctr.getId(), ctr.getNome(), ctr.getSexo(),ctr.getNasci(), ctr.getEstCvl(), ctr.getBi(), ctr.getMorada(), ctr.getRendMsl(), ctr.getCtt());
                
                }
                else
                    JOptionPane.showMessageDialog(null, "Cliente não Encontrado!");
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }finally{painelClientes();}
        }
        if(ae.getSource()==pesqFuncio){
            int id=Integer.parseInt(entryID.getText());
            boolean scc;
            try{
                ctrFuncionario ctr=new ctrFuncionario();
                scc= ctr.encontrar(id);
                if(scc==true){
                    editFuncionario edf=new editFuncionario(ctr.getId(),ctr.getNasc(),ctr.getNome(), ctr.getBi(), ctr.getSexo(), ctr.getSalario(),ctr.getCtt());
                }
                else
                    JOptionPane.showMessageDialog(null, "Funcionário não Encontrado!");
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERRO: "+e);
            }finally{painelFuncionario();}
        }
        
        if(ae.getSource()==pesqEmp){
           
            ctrEmprestimo ctrEmp = new ctrEmprestimo();
            boolean scc=ctrEmp.find(Integer.parseInt(entryID.getText()));
            if(scc==true){
                entryEmp.setText(Integer.toString(ctrEmp.empClass.getIdEmpr()));
                entryCli.setText(Integer.toString(ctrEmp.empClass.getIdCliente()));
                entryCred.setText(Float.toString(ctrEmp.empClass.getCredito()));
                entryVf.setText(Float.toString(ctrEmp.empClass.getValorTot()));
                entryVp.setText(Float.toString(ctrEmp.empClass.getVlPago()));  
                entryFuncio.setText(Integer.toString(ctrEmp.empClass.getIdFuncio()));
                vlPres=ctrEmp.empClass.getValorPrest();
            }
            else
                JOptionPane.showMessageDialog(null, "Emprestimo não encontrado");
                
        }
    }
}
