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
	}

	public GraphicEndTile(EndTile oendtile) {
		super(IconCollection.getInstance().getTarget());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setEndtile(oendtile);
	}

	@Override
	public ImageIcon draw() { 
		if(endtile.getThing() != null)
		{
			if(endtile.getThing().getClass().equals(Crate.class))
				return IconCollection.getInstance().getBox();
			if(endtile.getThing().getClass().equals(Worker.class))
				if(SokobanGui.getInstance().getWorker(true).equals(endtile.getThing()))
					return IconCollection.getInstance().getRedontile();
				else
					return IconCollection.getInstance().getBlueontile();
				
		}
		else
		{
			if(endtile.getFrictionMod() != null)
			{
				if(endtile.getFrictionMod().getFriction() < 1)
					return IconCollection.getInstance().getEndoil();
				else
					return IconCollection.getInstance().getEndhoney();
			}
			return IconCollection.getInstance().getTarget();
		}	
		return null;
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
