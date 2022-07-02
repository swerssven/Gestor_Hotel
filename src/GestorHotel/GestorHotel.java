package GestorHotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class GestorHotel {

	private JFrame frmGestorHabitacionHotel;

	private static Planta[] plantas;

	private JTextField txt_nombre;
	private JTextField txt_llegada;
	private JTextField txt_salida;
	private TextArea txt_registrosHabitaciones;
	private JComboBox cbx_plantaEntrada;
	private JComboBox cbx_hab;
	private JComboBox cbx_plantaSalida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		plantas = inicializaPlantas(3); // Llenamos array con instancias de tipo Planta

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorHotel window = new GestorHotel();
					window.frmGestorHabitacionHotel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestorHotel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorHabitacionHotel = new JFrame();
		frmGestorHabitacionHotel.setResizable(false);
		frmGestorHabitacionHotel.setTitle("GESTOR HABITACIONES HOTEL");
		frmGestorHabitacionHotel.setBounds(100, 100, 600, 500);
		frmGestorHabitacionHotel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestorHabitacionHotel.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 584, 182);
		frmGestorHabitacionHotel.getContentPane().add(tabbedPane);

		JPanel Entrada = new JPanel();
		tabbedPane.addTab("ENTRADA CLIENTE", null, Entrada, null);
		Entrada.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(36, 32, 46, 14);
		Entrada.add(lblNewLabel);

		JLabel lblDiaLlegada = new JLabel("Dia llegada");
		lblDiaLlegada.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaLlegada.setBounds(36, 71, 76, 14);
		Entrada.add(lblDiaLlegada);

		JLabel lblDiaSalda = new JLabel("Dia sal\u00EDda");
		lblDiaSalda.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaSalda.setBounds(36, 111, 58, 14);
		Entrada.add(lblDiaSalda);

		txt_nombre = new JTextField();
		txt_nombre.setBounds(145, 29, 125, 20);
		Entrada.add(txt_nombre);
		txt_nombre.setColumns(10);

		txt_llegada = new JTextField();
		txt_llegada.setColumns(10);
		txt_llegada.setBounds(145, 68, 125, 20);
		Entrada.add(txt_llegada);

		txt_salida = new JTextField();
		txt_salida.setColumns(10);
		txt_salida.setBounds(145, 108, 125, 20);
		Entrada.add(txt_salida);

		cbx_plantaEntrada = new JComboBox();
		cbx_plantaEntrada.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
		cbx_plantaEntrada.setBounds(486, 28, 50, 22);
		Entrada.add(cbx_plantaEntrada);

		JLabel lblPlanta_1 = new JLabel("Planta");
		lblPlanta_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlanta_1.setBounds(403, 32, 46, 14);
		Entrada.add(lblPlanta_1);

		JPanel Salida = new JPanel();
		tabbedPane.addTab("SALIDA CLIENTE", null, Salida, null);
		Salida.setLayout(null);

		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n");
		lblHabitacin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHabitacin.setBounds(88, 80, 87, 14);
		Salida.add(lblHabitacin);

		JLabel lblPlanta = new JLabel("Planta");
		lblPlanta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPlanta.setBounds(88, 31, 87, 14);
		Salida.add(lblPlanta);

		cbx_hab = new JComboBox();
		cbx_hab.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		cbx_hab.setBounds(173, 76, 50, 22);
		Salida.add(cbx_hab);

		cbx_plantaSalida = new JComboBox();
		cbx_plantaSalida.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3" }));
		cbx_plantaSalida.setBounds(173, 27, 50, 22);
		Salida.add(cbx_plantaSalida);

		JPanel Registros = new JPanel();
		Registros.setBounds(0, 179, 584, 282);
		frmGestorHabitacionHotel.getContentPane().add(Registros);
		Registros.setLayout(null);

		txt_registrosHabitaciones = new TextArea();
		txt_registrosHabitaciones.setBounds(16, 10, 558, 262);
		Registros.add(txt_registrosHabitaciones);

		txt_registrosHabitaciones.setText(refrescarRegistros(plantas));

		// Boton para la creacion de una reserva, se crea un nuevo cliente si los datos son correctos.
		JButton btnNewButton = new JButton("Realizar reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = null;
				int llegada = 0;
				int salida = 0;
				boolean correct = true;

				// Comprobación de los datos introducidos
				try {
					nombre = txt_nombre.getText();
					llegada = Integer.parseInt(txt_llegada.getText());
					salida = Integer.parseInt(txt_salida.getText());

					if (llegada > salida) {
						JOptionPane.showMessageDialog(null, "La llegada no puede ser mas tarde que la salida");
						txt_llegada.setText(null);
						txt_salida.setText(null);
						correct = false;
					} else if (llegada <= 0 || llegada > 365 || salida <= 0 || salida > 365) {
						JOptionPane.showMessageDialog(null,
								"La llegada y salida no pueden ser menores de 1 y mayores de 365");
						txt_llegada.setText(null);
						txt_salida.setText(null);
						correct = false;
					} else {
						correct = true;
					}

				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, "Datos erroneos, intentelo de nuevo");
					resetCamposTexto();
					correct = false;
				}

				// Si los datos introducidos son correctos, se procede a crear la reserva.
				if (correct) {
					Cliente clienteNuevo = new Cliente(nombre, llegada, salida);
					plantas[cbx_plantaEntrada.getSelectedIndex()].ocuparHabitPlanta(clienteNuevo);
					txt_registrosHabitaciones.setText(refrescarRegistros(plantas));
					resetCamposTexto();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(340, 71, 196, 59);
		Entrada.add(btnNewButton);

		// Boton para liberar una habitacion elegida por usuario en formulario
		JButton btn_liberarHabitacion = new JButton("Liberar Habitaci\u00F3n");
		btn_liberarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plantas[cbx_plantaSalida.getSelectedIndex()].liberarHabitPlanta(cbx_hab.getSelectedIndex() + 1);
				txt_registrosHabitaciones.setText(refrescarRegistros(plantas));
				resetCamposTexto();
			}
		});
		btn_liberarHabitacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_liberarHabitacion.setBounds(313, 31, 162, 67);
		Salida.add(btn_liberarHabitacion);
		
		JPanel Listados = new JPanel();
		tabbedPane.addTab("CREAR LISTADOS", null, Listados, null);
		Listados.setLayout(null);

		// Boton para refrescar los registros de las habitaciones del hotel.
		JButton btn_refrescarRegistros = new JButton("Refrescar listado a ocupacion actual");
		btn_refrescarRegistros.setBounds(10, 11, 247, 23);
		Listados.add(btn_refrescarRegistros);
		
		// Boton para guardar los registros actuales en un archivo de texto.
		JButton btn_guardarResgistros = new JButton("Guardar listado actual");
		btn_guardarResgistros.setBounds(314, 11, 255, 23);
		Listados.add(btn_guardarResgistros);
		
				
		// Boton para cargar registros anteriormente guardados.
		JButton btn_cargarRegistros = new JButton("Cargar listado");
		btn_cargarRegistros.setBounds(10, 79, 247, 23);
		Listados.add(btn_cargarRegistros);
		
		
		// Boton para crear y guardar un listado de las habitaciones libres
		JButton btn_listadoLibres = new JButton("Guardar listado habitaciones libres");
		btn_listadoLibres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showSaveDialog(null);

				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));

						for (Planta p : plantas) {
							writer.write("\n");
							for(Habitacion h: p.getHabitaciones()) {
								if(h.estaLibre()) {
									writer.write(h.toString() + "\n");
								}
							}
						}

						writer.close();

					} catch (IOException error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
				}
			}
		});
		btn_listadoLibres.setBounds(314, 45, 255, 23);
		Listados.add(btn_listadoLibres);
		
		// Boton para crear un listado de habitaciones ocupadas.
		JButton btn_listadoOcupadas = new JButton("Guardar listado habitaciones ocupadas");
		btn_listadoOcupadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showSaveDialog(null);

				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));

						for (Planta p : plantas) {
							writer.write("\n");
							for(Habitacion h: p.getHabitaciones()) {
								if(!h.estaLibre()) {
									writer.write(h.toString() + "\n");
								}
							}
						}

						writer.close();

					} catch (IOException error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
				}
			}
		});
		btn_listadoOcupadas.setBounds(314, 79, 255, 23);
		Listados.add(btn_listadoOcupadas);
		
		JPanel Guardar = new JPanel();
		tabbedPane.addTab("GUARDAR Y CARGAR", null, Guardar, null);
		Guardar.setLayout(null);
		
		// Boton para crear un fichero de serializacion de los objetos Planta
		JButton btn_guardaPrograma = new JButton("Guardar estado programa .ser");
		btn_guardaPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showSaveDialog(null);

				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileOutputStream outputStream = new FileOutputStream(file);
						
						ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
						
						for(Planta p: plantas) {
							outputObject.writeObject(p);
						}
						
						outputObject.close();
					
					} catch (FileNotFoundException error) {
						error.printStackTrace();
					} catch (IOException error) {
						error.printStackTrace();
					}
				}
			}
		});
		btn_guardaPrograma.setBounds(35, 26, 296, 23);
		Guardar.add(btn_guardaPrograma);
		
		
		// Boton para recuperar los objetos Planta desde un fichero
		JButton btn_cargarPrograma = new JButton("Cargar estado programa .ser");
		btn_cargarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showOpenDialog(null);

				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						FileInputStream inputStream = new FileInputStream(file);
						
						ObjectInputStream inputObject = new ObjectInputStream(inputStream);
						
						for(int i = 0; i < plantas.length; i++) {
							plantas[i] = (Planta) inputObject.readObject();
						}
						
						inputObject.close();
						
					} catch (FileNotFoundException error) {
						error.printStackTrace();
					} catch (ClassNotFoundException error) {
						error.printStackTrace();
					} catch (IOException error) {
						error.printStackTrace();
					}
				}
				txt_registrosHabitaciones.setText(refrescarRegistros(plantas));
			}
		});
		btn_cargarPrograma.setBounds(35, 70, 296, 23);
		Guardar.add(btn_cargarPrograma);
		
		// Boton para cargar un fichero de registros y visualizarlo
		btn_cargarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero = leerFichero();
				String salidaFichero = null;
				boolean correct = true;
				try {
					BufferedReader lector = new BufferedReader(new FileReader(fichero));
					String linea;
					salidaFichero = "";
					while ((linea = lector.readLine()) != null) {
						salidaFichero += linea + "\n";
					}
					lector.close();

				} catch (Exception error) {
					error.printStackTrace();
					correct = false;
				}

				if (correct) {
					txt_registrosHabitaciones.setText(salidaFichero);
				}
			}
		});
		
		
		// Boton para guardar los registros en un fichero.
		btn_guardarResgistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showSaveDialog(null);

				if (select == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));

						for (Planta p : plantas) {
							writer.write(p.toString() + "\n");
						}

						writer.close();

					} catch (IOException error) {
						// TODO Auto-generated catch block
						error.printStackTrace();
					}
				}
			}
		});
		
		btn_refrescarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_registrosHabitaciones.setText(refrescarRegistros(plantas));
			}
		});
	}

	// Metodo para llenar el Array "plantas" de plantas.
	public static Planta[] inicializaPlantas(int numPlantas) {
		Planta[] plantas = new Planta[numPlantas];
		for (int i = 0; i < plantas.length; i++) {
			plantas[i] = new Planta(i + 1);
		}
		return plantas;
	}

	// Metodo para refrescar los registros
	public String refrescarRegistros(Planta[] plantas) {
		String registro = "";
		for (Planta p : plantas) {
			registro += p.toString() + "\n";
		}

		return registro;
	}

	// Metodo para vaciar campos del formulario
	public void resetCamposTexto() {
		txt_nombre.setText(null);
		txt_llegada.setText(null);
		txt_salida.setText(null);
		cbx_plantaEntrada.setSelectedIndex(0);
		cbx_plantaSalida.setSelectedIndex(0);
		cbx_hab.setSelectedIndex(0);
	}

	// Metodo para leer un Fichero.
	public File leerFichero() {
		JFileChooser chooser = new JFileChooser();
		int select = chooser.showOpenDialog(null);

		if (select == JFileChooser.APPROVE_OPTION) {
			File fichero = chooser.getSelectedFile();
			return fichero;
		} else {
			return null;
		}
	}
}
