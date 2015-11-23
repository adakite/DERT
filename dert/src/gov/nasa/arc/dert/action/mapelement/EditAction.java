package gov.nasa.arc.dert.action.mapelement;

import gov.nasa.arc.dert.action.MenuItemAction;
import gov.nasa.arc.dert.scene.MapElement;
import gov.nasa.arc.dert.state.Configuration;
import gov.nasa.arc.dert.state.ConfigurationManager;
import gov.nasa.arc.dert.view.mapelement.MapElementsView;

/**
 * Context menu item for editing a map element.
 *
 */
public class EditAction extends MenuItemAction {

	protected MapElement mapElement;

	/**
	 * Constructor
	 * 
	 * @param mapElement
	 */
	public EditAction(MapElement mapElement) {
		super("Edit " + mapElement.getName());
		this.mapElement = mapElement;
	}

	@Override
	protected void run() {
		// Open the MapElements view
		Configuration currentConfig = ConfigurationManager.getInstance().getCurrentConfiguration();
		MapElementsView view = (MapElementsView) currentConfig.mapElementsState.open();
		view.selectMapElement(mapElement);
	}

}
