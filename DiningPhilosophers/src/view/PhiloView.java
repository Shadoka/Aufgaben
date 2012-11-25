package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import philosopher.PTOMonitor;

public class PhiloView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -823765794393297614L;

	private static PhiloView instance = null;

	private JButton startButton;
	private JButton stopButton;
	private JTextField countPhilo;
	private JLabel countLabel;
	private JTextField amountPhilo;
	private JButton addPhilo;
	private JTextField waitingTime;
	private JButton waitingButton;
	private JLabel waitingDefault;

	private PhiloView() {
		this.initializeUI();
	}

	public static PhiloView getInstance() {
		if (instance == null) {
			instance = new PhiloView();
		}
		return instance;
	}

	private void initializeUI() {
		this.setTitle("Dining Philosophers");
		this.getContentPane().setLayout(null);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.startButton = new JButton("Simulation starten");
		this.getContentPane().add(this.startButton);
		this.startButton.setBounds(50, 430, 140, 30);
		this.stopButton = new JButton("Simulation stoppen");
		this.getContentPane().add(this.stopButton);
		this.stopButton.setBounds(400, 430, 150, 30);
		this.countPhilo = new JTextField("");
		this.countPhilo.setEditable(false);
		this.getContentPane().add(this.countPhilo);
		this.countPhilo.setBounds(500, 30, 50, 30);
		this.countLabel = new JLabel("Anzahl Philosophen: ");
		this.getContentPane().add(this.countLabel);
		this.countLabel.setBounds(380, 30, 150, 30);
		this.amountPhilo = new JTextField("");
		this.getContentPane().add(this.amountPhilo);
		this.amountPhilo.setBounds(50, 30, 50, 30);
		this.addPhilo = new JButton("Philosoph/en hinzufügen");
		this.getContentPane().add(this.addPhilo);
		this.addPhilo.setBounds(110, 30, 180, 30);
		this.waitingTime = new JTextField("");
		this.getContentPane().add(this.waitingTime);
		this.waitingTime.setBounds(50, 90, 60, 30);
		this.waitingButton = new JButton("Max. Wartezeit einstellen(in ms)");
		this.getContentPane().add(this.waitingButton);
		this.waitingButton.setBounds(120, 90, 220, 30);
		this.waitingDefault = new JLabel("(Default = 5s)");
		this.getContentPane().add(this.waitingDefault);
		this.waitingDefault.setBounds(350, 90, 100, 30);

		this.updateCountPhilo();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void updateCountPhilo() {
		int result = PTOMonitor.getInstance().getThinking().size()
				+ PTOMonitor.getInstance().getEating().size();
		this.countPhilo.setText("" + result);
	}

	private void setWaitingTime(long wait) {
		PTOMonitor.getInstance().setWaitingTime(wait);
	}

	/******** Getter and Setter *************/

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JTextField getCountPhilo() {
		return countPhilo;
	}

	public void setCountPhilo(JTextField countPhilo) {
		this.countPhilo = countPhilo;
	}

	public JLabel getCountLabel() {
		return countLabel;
	}

	public void setCountLabel(JLabel countLabel) {
		this.countLabel = countLabel;
	}

	public JTextField getAmountPhilo() {
		return amountPhilo;
	}

	public void setAmountPhilo(JTextField amountPhilo) {
		this.amountPhilo = amountPhilo;
	}

	public JButton getAddPhilo() {
		return addPhilo;
	}

	public void setAddPhilo(JButton addPhilo) {
		this.addPhilo = addPhilo;
	}

	public JTextField getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(JTextField waitingTime) {
		this.waitingTime = waitingTime;
	}

	public JButton getWaitingButton() {
		return waitingButton;
	}

	public void setWaitingButton(JButton waitingButton) {
		this.waitingButton = waitingButton;
	}

	public JLabel getWaitingDefault() {
		return waitingDefault;
	}

	public void setWaitingDefault(JLabel waitingDefault) {
		this.waitingDefault = waitingDefault;
	}

}
