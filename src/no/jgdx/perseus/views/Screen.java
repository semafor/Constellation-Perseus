package no.jgdx.perseus.views;

import java.awt.LayoutManager;

public abstract class Screen {

	protected LayoutManager m;

	public Screen(LayoutManager m) {
		this.m = m;
	}

	protected LayoutManager getLayoutManager() {
		return this.m;
	}

	protected void setLayoutManager(LayoutManager m) {
		this.m = m;
	}
}
