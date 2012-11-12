package model.main.process;

import model.main.Component;

public class MaterialmapProcess implements Runnable {

	private Component comp;
	private boolean finished;

	private MaterialmapProcess(Component comp) {
		this.comp = comp;
		this.finished = false;
		this.run();
	}

	public static MaterialmapProcess create(Component comp) {
		return new MaterialmapProcess(comp);
	}

	@Override
	public void run() {

		this.getComp().getMaterialList();

		finished = true;

	}

	public Component getComp() {
		return comp;
	}

	public void setComp(Component comp) {
		this.comp = comp;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
