package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JLabel maxLabel;
	private JTextField maxPhilo;
	private JLabel averageLabel;
	private JTextField averagePhilo;
	private JCheckBox tokenCare;

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
		this.startButton.addActionListener(new StartButtonListener());
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
		this.addPhilo.addActionListener(new AddPhiloButtonListener());
		this.getContentPane().add(this.addPhilo);
		this.addPhilo.setBounds(110, 30, 180, 30);
		this.waitingTime = new JTextField("");
		this.getContentPane().add(this.waitingTime);
		this.waitingTime.setBounds(50, 90, 60, 30);
		this.waitingButton = new JButton("Max. Wartezeit einstellen(in ms)");
		this.waitingButton.addActionListener(new WaitButtonListener());
		this.getContentPane().add(this.waitingButton);
		this.waitingButton.setBounds(120, 90, 220, 30);
		this.waitingDefault = new JLabel("(Default = 5s)");
		this.getContentPane().add(this.waitingDefault);
		this.waitingDefault.setBounds(350, 90, 100, 30);
		this.maxLabel = new JLabel(
				"Maximum gleichzeitig essender Philosophen: ");
		this.getContentPane().add(this.maxLabel);
		this.maxLabel.setBounds(100, 370, 260, 30);
		this.maxPhilo = new JTextField("0");
		this.getContentPane().add(this.maxPhilo);
		this.maxPhilo.setBounds(370, 370, 50, 30);
		this.averageLabel = new JLabel(
				"Durchschnitt von gleichzeitig essenden Philosophen");
		this.getContentPane().add(this.averageLabel);
		this.averageLabel.setBounds(60, 300, 300, 30);
		this.averagePhilo = new JTextField("0");
		this.getContentPane().add(this.averagePhilo);
		this.averagePhilo.setBounds(370, 300, 50, 30);
		this.tokenCare = new JCheckBox("Philosophen kümmern sich um Tokens");
		this.getContentPane().add(this.tokenCare);
		this.tokenCare.setBounds(50, 150, 250, 30);

		this.updateCountPhilo();
		this.setResizable(false);
		this.setVisible(true);
	}

	private void updateCountPhilo() {
		int result = PTOMonitor.getInstance().getThinking().size()
				+ PTOMonitor.getInstance().getEating().size();
		this.countPhilo.setText("" + result);
	}

	public void updateAveragePhilo(int count) {
		this.getAveragePhilo().setText("" + count);
	}

	public void updateMaxPhilo(int count) {
		this.getMaxPhilo().setText("" + count);
	}

	private void setWaitingTime(long wait) {
		PTOMonitor.getInstance().setWaitingTime(wait);
	}

	public static boolean isNumeric(String string) {
		try {
			Long.parseLong(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**** ActionListeners *******/

	public class WaitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (PhiloView.isNumeric(PhiloView.this.getWaitingTime().getText())) {
				PhiloView.this.setWaitingTime(Long.parseLong(PhiloView.this
						.getWaitingTime().getText()));
				PhiloView.this.getWaitingTime().setText("");
			}
		}
	}

	public class AddPhiloButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (PhiloView.isNumeric(PhiloView.this.getAmountPhilo().getText())) {
				PTOMonitor.getInstance().addPhilosophers(
						Integer.parseInt(PhiloView.this.getAmountPhilo()
								.getText()));
				PhiloView.this.getAmountPhilo().setText("");
				PhiloView.this.updateCountPhilo();
			}
		}
	}

	public class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!PTOMonitor.getInstance().isRunning()) {
				PTOMonitor.getInstance().startAllPhilosophers(
						PhiloView.this.getTokenCare().isSelected());
				PTOMonitor.getInstance().setRunning(true);
			}
		}
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

	public JLabel getMaxLabel() {
		return maxLabel;
	}

	public void setMaxLabel(JLabel maxLabel) {
		this.maxLabel = maxLabel;
	}

	public JTextField getMaxPhilo() {
		return maxPhilo;
	}

	public void setMaxPhilo(JTextField maxPhilo) {
		this.maxPhilo = maxPhilo;
	}

	public JLabel getAverageLabel() {
		return averageLabel;
	}

	public void setAverageLabel(JLabel averageLabel) {
		this.averageLabel = averageLabel;
	}

	public JTextField getAveragePhilo() {
		return averagePhilo;
	}

	public void setAveragePhilo(JTextField averagePhilo) {
		this.averagePhilo = averagePhilo;
	}

	public JCheckBox getTokenCare() {
		return tokenCare;
	}

	public void setTokenCare(JCheckBox tokenCare) {
		this.tokenCare = tokenCare;
	}

}
