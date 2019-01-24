package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import ahorcado.Ahorcado;


public class Dibujo extends JFrame {
	
	private static final long serialVersionUID = -4350060006315893235L;

	JPanel panel = null;
	
	JButton boton = null;
	JButton jbtReiniciar = null;
	
	JLabel palabras = null;
	JLabel victoria = null;
	JLabel derrota = null;
	JLabel averiguar = null;
	
	JTextArea introducidas = null;
	
	JTextField letras = null;
	JTextField jtfPalabra = null;
	
	Ahorcado objAh = null;
	ImageIcon gif = null;
	
	private char [] cadena = null;
	private char [] cadenaAux = null;
	private String letra = null;
	
	public Dibujo() {
		
		objAh = new Ahorcado();
		inicializar();
		setVisible(true); 
		cargarPalabra();
	}	
	
	public void paint (Graphics g) {
		
		super.paint (g);
		int fallos = objAh.getFallos();
		
		//Horca
		g.drawLine(460, 70, 460, 500);
		g.drawLine(460, 70, 642, 70);
		g.drawLine(642, 70, 642, 200);
		g.drawLine(460, 500, 500, 550);
		g.drawLine(460, 500, 420, 550);
		g.drawLine(460, 500, 510, 510);
		g.drawLine(460, 500, 410, 510);
		
		// A cada parte del cuerpo se le añade todo el dibujo anterior
		switch (fallos)
		{
			case 1:
				//Cabeza
				g.drawOval(600, 200, 80, 80);
				break;
			case 2:
				//Cuerpo 1
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				break;
			//Brazos
			case 3:
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				g.drawLine(640, 300, 690, 350);
				break;
			case 4:
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				g.drawLine(640, 300, 690, 350);
				g.drawLine(640, 300, 590, 350);
				break;
			case 5:
				//Cuerpo 2
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				g.drawLine(640, 300, 690, 350);
				g.drawLine(640, 300, 590, 350);
				g.drawLine(640, 380, 640, 460);
				break;
			//Piernas
			case 6:
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				g.drawLine(640, 300, 690, 350);
				g.drawLine(640, 300, 590, 350);
				g.drawLine(640, 380, 640, 460);
				g.drawLine(640, 460, 690, 510);
				break;
			case 7:
				g.drawOval(600, 200, 80, 80);
				g.drawLine(640, 280, 640, 380);
				g.drawLine(640, 300, 690, 350);
				g.drawLine(640, 300, 590, 350);
				g.drawLine(640, 380, 640, 460);
				g.drawLine(640, 460, 690, 510);
				g.drawLine(640, 460, 590, 510);
				break;
		}
	}
	
	private void inicializar(){
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		panel = new JPanel(null);
			panel.setBackground(Color.YELLOW);
			add(panel);
		
		palabras = new JLabel("Letras introducidas:");
			palabras.setBounds(80, 70, 120, 30);
			panel.add(palabras);
			
		introducidas = new JTextArea();
			introducidas.setBorder(new LineBorder(Color.blue, 2));
			introducidas.setEditable(false);
			introducidas.setBounds(80, 120, 200, 200);
			panel.add(introducidas);
			
		victoria = new JLabel("Victoria");
			victoria.setBounds(70, 370, 150, 30);
			victoria.setFont(new Font("Arial", Font.BOLD, 30));
			victoria.setForeground(Color.GREEN);
			victoria.setVisible(false);
			panel.add(victoria);
			
		derrota = new JLabel("Derrota");
			derrota.setBounds(210, 400, 150, 30);
			derrota.setFont(new Font("Arial", Font.BOLD, 30));
			derrota.setForeground(Color.RED);
			derrota.setVisible(false);
			panel.add(derrota);
			
		boton = new JButton("Introducir");
			boton.setBounds(40, 500, 120, 30);
			boton.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.RED, Color.BLUE));
			boton.setBackground(new Color(219, 234, 255));
			boton.addActionListener(e -> jugar());
			panel.add(boton);	
			
		jbtReiniciar = new JButton("Reiniciar");
			jbtReiniciar.setBounds(650, 500, 100, 30);
			jbtReiniciar.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.RED, Color.BLUE));
			jbtReiniciar.setBackground(new Color(219, 234, 255));
			jbtReiniciar.setVisible(false);
			jbtReiniciar.addActionListener(l -> reiniciar());
			panel.add(jbtReiniciar);
					
		letras = new JTextField();
			letras.setBounds(180, 500, 100, 30);
			letras.setBorder(new LineBorder(Color.blue, 2));
			letras.addKeyListener(new KeyAdapter() {
		        public void keyTyped(KeyEvent e) {
		            if (letras.getText().length() >= 1) // limitar a 1 caracter
		            	e.consume();
		        }
		    });
			panel.add(letras);
			
		averiguar = new JLabel("Palabra: ");
			averiguar.setBounds(30, 30, 80, 30);
			averiguar.setFont(new Font("Arial", Font.BOLD, 15));
			averiguar.setForeground(Color.BLUE);
			panel.add(averiguar);
			
		jtfPalabra = new JTextField();
			jtfPalabra.setBounds(110, 30, 150, 30);
			jtfPalabra.setEditable(false);
			jtfPalabra.setFont(new Font("Consolas", Font.BOLD, 15));
			jtfPalabra.setBorder(new LineBorder(Color.blue, 2));
			panel.add(jtfPalabra);
			
		getRootPane().setDefaultButton(boton);
	}
	
	private void reiniciar() {
		
		cargarPalabra();
		objAh.setAciertos(0);
		objAh.setFallos(0);
		objAh.setAcertadas();
		victoria.setVisible(false);
		derrota.setVisible(false);
		boton.setEnabled(true);
		repaint();
		jbtReiniciar.setVisible(false);
	}

	private void jugar()  {
		
		if (letras.getText().length()>0)
		{
			letra = letras.getText().toUpperCase().trim();
			objAh.partida(letra);
			introducidas.append(letra + " ");
		}
		letras.setText(null);
		separar();
		repaint();
		comprobarFinal();	
	}

	private void comprobarFinal() {

		if (objAh.getAciertos() == objAh.getTamaño())
		{
			gif = new ImageIcon(getClass().getResource("gif.gif"));
			JOptionPane.showMessageDialog(null, gif, "Felicidades", -1);
			victoria.setVisible(true);
			boton.setEnabled(false);
			jbtReiniciar.setVisible(true);
		}
		else if (objAh.getFallos() == 7)
		{
			gif = new ImageIcon(getClass().getResource("gif2.gif"));
			cadena = objAh.getPalabra().toCharArray();
			separar();
			JOptionPane.showMessageDialog(null, gif, "Perdedor", -1);
			derrota.setVisible(true);
			boton.setEnabled(false);
			jbtReiniciar.setVisible(true);
		}
	}

	
	private void cargarPalabra() {
		
		objAh.setPalabra();
		cadena = objAh.getRayas();
		cadenaAux = new char [cadena.length*2];
		separar();	
	}

	private void separar() {
		
		for (int i=0, j=0; i<cadenaAux.length; i++)
		{
			cadenaAux[i] = cadena[j++];
			cadenaAux[++i] = ' ';
		}
		jtfPalabra.setText(" "+String.valueOf(cadenaAux));
	}
}
