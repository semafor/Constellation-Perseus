package no.jgdx.perseus.views;

import java.awt.LayoutManager;

public abstract class Screen {

	protected LayoutManager m;

	public Screen() {
	}

	protected LayoutManager getLayoutManager() {
		return this.m;
	}

	protected void setLayoutManager(LayoutManager m) {
		this.m = m;
	}
	
	protected void setUpLayoutManager() {}
	
	public void render() {}
	
	public void tick() {};
}
