// Prenda
public class Prenda{
	private TipoPrenda tipo;
	private double precioBase;
	private Estado estado;

	public Double precioFinal(){
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

// Venta
public class Item{
	private Prenda prenda;
	private int cantidad;

	public double precioItem(){
		return prenda.precioFinal() * cantidad;
	}
}

public abstract class Venta{
	private List<Item> items;
	private LocalDateTime fecha;

	public doube precioFinal(){
		double importeTotal = items.sum(item -> item.precioIte());

		return this.importeConRecargo(importeTotal);
	}

	public abstract double importeConRecargo(double importeTotal);
}

public class VentaEfectivo extends Venta{
	
	@Override
	public double importeConRecargo(double importeTotal){
		return importeTotal;
	}
}

public class VentaTarjeta extends Venta{
	private int cantidadDeCuotas;
	private double coeficienteFijo;

	@Override
	public double importeConRecargo(double importeTotal){
		return importeTotal + (cantidadDeCuotas * coeficienteFijo + (0.01 * importeTotal));
	}	
}

public class Macowins{
	private List<Venta> ventas;

	public void vender(Venta venta){
		ventas.add(venta);
	}

	public List<Venta> ventasDelDia(LocalDateTime fecha){
		return ventas.filter(venta -> venta.seVendioEnLaFecha(fecha));
	}

	public double gananciaDelDia(LocalDateTime fecha){
		return this.ventasDelDia(fecha).sum(venta -> venta.precioFinal());
	}
}