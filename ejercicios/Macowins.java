public class Prenda{
	private TipoPrenda tipo;
	private double precioBase;
	private Estado estado;

	public Double precioDeVenta(){
		return estado.precio(precioBase);
	}
}

public enum TipoPrenda{
	SACO, PANTALON, CAMISA;
}

public interface Estado{
	public Double precio(Double precioBase);
}

public class Nueva implements Estado{
	public double precio(double precioBase){
		return precioBase;
	}
}

public class Promocion implements Estado{
	private double descuentoFijo;
	
	public double precio(double precioBase){
		return precioBase - descuentoFijo;
	}
}

public class Liquidacion implements Estado{
	private int porcentajeDescuento;

	public double precio(double precioBase){
		return precioBase - (precioBase * porcentajeDescuento / 100);
	}
}