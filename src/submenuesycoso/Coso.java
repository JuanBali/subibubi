package submenuesycoso;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Coso extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	private int numeroDeAlumnoAnterior = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coso frame = new Coso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Coso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 47));
		textField.setBounds(223, 27, 189, 69);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero de alumno: ");
		lblNewLabel.setBounds(50, 16, 158, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNotaFinal = new JLabel("Nota final: ");
		lblNotaFinal.setBounds(117, 100, 91, 50);
		contentPane.add(lblNotaFinal);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 47));
		textField_1.setColumns(10);
		textField_1.setBounds(223, 112, 189, 69);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(15, 284, 589, 29);
		contentPane.add(btnNewButton);
		
		JButton btnIngresarDato = new JButton("Ingresar dato");
		btnIngresarDato.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnIngresarDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() 
						|| textField_1.getText().isEmpty()){
					//Error hay que llenar todo
					mensajeDeError("Hay que llenar todos los campos");
					return;
				}
				
				if(!esEntero(textField.getText()) 
						|| !esEntero(textField_1.getText())){
					//Hay que introducir enteros
					mensajeDeError("Hay que introducir numeros enteros en ambos campos");
					return;
				}
				
				int nota = Integer.parseInt(textField_1.getText());
				
				if(!esNotaValida(nota)){
					//Nota no es valida
					mensajeDeError("La nota no es valida. Por favor agregue una nueva nota entre 1 y 12");
					return;
				}
				
				int id = Integer.parseInt(textField.getText());
				
				if(!esNumeroDeAlumnoValido(id)){
					//No es numero de alumno valido
					mensajeDeError("Los numeros de alumnos deben de ser consecutivos." +
							"el proximo alumno debe de tener id = " + (numeroDeAlumnoAnterior + 1));
					return;
				}
				
				Alumno alumno = new Alumno(id, nota);
				alumnos.add(alumno);
				mensajeNormal("Alumno ingresado satisfactoriamente!");
				
				if(nota == 12)
					mensajeNormal("Que nota mas buena que se ha sacado!");
			}
		});
		btnIngresarDato.setBounds(427, 16, 177, 165);
		contentPane.add(btnIngresarDato);
		
		JButton btnVerAlumnosCon = new JButton("<html> Ver alumnos con <br> la nota mas alta </html>");
		btnVerAlumnosCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estaAlumnosVacio()) return;
				String str = "Los alumnos con la nota mas alta son los alumnos con ID: ";
				ArrayList<Integer> x = MisMetodos.alumnosMasAltos(alumnos);
				
				for(int i = 0; i < x.size(); ++i){
					str += i == x.size() - 1 ? x.get(i).toString() :
							x.get(i).toString() + ", ";
				}
				
				mensajeNormal(str);
			}
		});
		btnVerAlumnosCon.setBounds(29, 197, 177, 59);
		contentPane.add(btnVerAlumnosCon);
		
		JButton btnVerAlumnosCon_1 = new JButton("<html> Ver alumnos con <br> la nota mas baja </html>");
		btnVerAlumnosCon_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estaAlumnosVacio()) return;
				String str = "Los alumnos con la nota mas baja son los alumnos con ID: ";
				ArrayList<Integer> x = MisMetodos.alumnosMasBajos(alumnos);
				
				for(int i = 0; i < x.size(); ++i){
					str += i == x.size() - 1 ? x.get(i).toString() :
							x.get(i).toString() + ", ";
				}
				
				mensajeNormal(str);
			}
		});
		btnVerAlumnosCon_1.setBounds(221, 197, 177, 59);
		contentPane.add(btnVerAlumnosCon_1);
		
		JButton btnPromedioDeNotas = new JButton("Promedio de notas");
		btnPromedioDeNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estaAlumnosVacio()) return;
				
				mensajeNormal("Promedio de notas: " + MisMetodos.promedio(alumnos));
			}
		});
		btnPromedioDeNotas.setBounds(413, 197, 177, 59);
		contentPane.add(btnPromedioDeNotas);
	}
	
	boolean esEntero(String str){
		try{
			Integer.parseInt(str);
		}
		catch(java.lang.NumberFormatException e){
			return false;
		}
		return true;
	}
	
	boolean esNotaValida(int n){
		return (n >= 1 && n <= 12);
	}
	
	boolean esNumeroDeAlumnoValido(int n){
		if(n - 1 == numeroDeAlumnoAnterior){
			numeroDeAlumnoAnterior = n;
			return true;
		}
		return false;
	}
	
	void mensajeDeError(String str){
		JOptionPane.showMessageDialog(null, str, "Error, sanguango", JOptionPane.ERROR_MESSAGE);
	}
	
	void mensajeNormal(String str){
		JOptionPane.showMessageDialog(null, str);
	}
	
	boolean estaAlumnosVacio(){
		if(alumnos.isEmpty()){
			mensajeDeError("NO AGREGASTE NIGNUN ALUMNO...");
			return true;
		}
		return false;
	}
}