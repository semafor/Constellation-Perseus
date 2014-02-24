package no.jgdx.perseus.views;

import java.awt.LayoutManager;
import java.util.Map;

import javax.swing.JPanel;

public abstract class Screen {

	private final LayoutManager m;

	private final Map<String, JPanel> panels;

	public Screen(LayoutManager m, Map<String, JPanel> panels) {
		this.m = m;
		this.panels = panels;
	}
}
