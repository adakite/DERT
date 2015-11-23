package gov.nasa.arc.dert.landscape;

import gov.nasa.arc.dert.util.ColorMap;

import java.io.Serializable;

/**
 * Data structure for information about a landscape layer.
 *
 */
public class LayerInfo implements Serializable, Comparable<LayerInfo> {

	public static enum LayerType {
		none, elevation, colorimage, grayimage, floatfield, intfield, unsignedbytefield, footprint, viewshed
	}

	// Name of the layer, presented in the UI
	public String name;

	// Layer type
	public LayerType type;

	// The percent that the layer contributes to the overall color of the
	// landscape
	public double blendFactor;

	// Flag to treat this layer as an overlay
	public boolean isOverlay;

	// The texture index for the layer
	public int layerNumber = -1;

	// The name of a color map used with this layer (for floatfields only)
	public String colorMapName;

	// Flag to use a gradient with the color map
	public boolean gradient;

	// The minimum value of this layer
	public double minimum;

	// The maximum value of this layer
	public double maximum;
	
	// Auto blending enabled for this layer
	public boolean autoBlend;

	// The color map object
	public transient ColorMap colorMap;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param type
	 * @param colorMapName
	 * @param minimum
	 * @param maximum
	 * @param gradient
	 */
	public LayerInfo(String name, String type, String colorMapName, double minimum, double maximum, boolean gradient) {
		this(name, type, 0, -1);
		this.colorMapName = colorMapName;
		this.minimum = minimum;
		this.maximum = maximum;
		this.gradient = gradient;
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param type
	 * @param blendFactor
	 * @param isOverlay
	 * @param layerNumber
	 */
	public LayerInfo(String name, String type, float blendFactor, int layerNumber) {
		this(name, LayerType.valueOf(type), blendFactor, layerNumber);
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param type
	 * @param blendFactor
	 * @param isOverlay
	 * @param layerNumber
	 */
	public LayerInfo(String name, LayerType type, float blendFactor, int layerNumber) {
		this.name = name;
		this.type = type;
		this.blendFactor = blendFactor;
		this.layerNumber = layerNumber;
		this.autoBlend = !isOverlay;
	}

	/**
	 * Constructor
	 * 
	 * @param that
	 */
	public LayerInfo(LayerInfo that) {
		this.name = that.name;
		this.type = that.type;
		this.blendFactor = that.blendFactor;
		this.isOverlay = that.isOverlay;
		this.layerNumber = that.layerNumber;
		this.gradient = that.gradient;
		this.colorMapName = that.colorMapName;
		this.minimum = that.minimum;
		this.maximum = that.maximum;
		this.colorMap = that.colorMap;
		this.autoBlend = that.autoBlend;
	}

	/**
	 * Compare two LayerInfo objects
	 */
	@Override
	public int compareTo(LayerInfo that) {
		return (this.name.compareTo(that.name));
	}

	@Override
	public String toString() {
		String str = name;
		if ((type == LayerType.footprint) || (type == LayerType.viewshed)) {
			str += " " + type;
		}
		return (str);
	}

	/**
	 * Prepare this LayerInfo object to be persisted.
	 */
	public void save() {
		if (colorMap != null) {
			colorMapName = colorMap.getName();
			gradient = colorMap.isGradient();
			minimum = colorMap.getMinimum();
			maximum = colorMap.getMaximum();
		}
	}

}
