package gui;

import java.util.logging.*;

import javax.swing.*;

import logic.*;

public class GraphicEndTile extends AbstractGraphic {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1454880402469371046L;
	private static final Logger LOGGER = Logger.getLogger( GraphicEndTile.class.getName() );
	private EndTile endtile;
	public GraphicEndTile() {
		super(IconCollection.getInstance().getTarget());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicEndTle created");
	}

	public GraphicEndTile(EndTile oendtile) {
		super(IconCollection.getInstance().getTarget());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicEndTle created");
		setEndtile(oendtile);
	}

	@Override
	public void draw(JLabel label) {
		if(endtile.getThing() == null)
		{
			if(endtile.getFrictionMod() == null)
			{
				label.setIcon(IconCollection.getInstance().getTarget());
				return;
			}
			if(endtile.getFrictionMod().getClass().equals(Oil.class))
				label.setIcon(IconCollection.getInstance().getEndoil());
			if(endtile.getFrictionMod().getClass().equals(Honey.class))
				label.setIcon(IconCollection.getInstance().getEndhoney());
		return;
		
		}
		if(endtile.getThing().getClass().equals(Crate.class))
			label.setIcon(IconCollection.getInstance().getBox());
		if(endtile.getThing().getClass().equals(Worker.class))
			if(SokobanGui.getInstance().getWorker(true).equals(endtile.getThing()))
				label.setIcon(IconCollection.getInstance().getRedontile());
			else
				label.setIcon(IconCollection.getInstance().getBlueontile());
	}

	/**
	 * @return the endtile
	 */
	public EndTile getEndtile() {
		return endtile;
	}

	/**
	 * @param endtile the endtile to set
	 */
	public void setEndtile(EndTile endtile) {
		this.endtile = endtile;
	}


}
