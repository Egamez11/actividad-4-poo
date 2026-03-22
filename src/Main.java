import java.util.ArrayList;
import java.util.List;

// 1. INTERFAZ (Abstracción)
interface Vendible {
    double calcularPrecioVenta();
}

// 2. CLASE ABSTRACTA (Abstracción y Encapsulamiento)
abstract class Producto implements Vendible {
    // Atributos protegidos para que las subclases puedan acceder a ellos
    protected String nombre;
    protected double precioBase;
    protected int cantidadStock;

    public Producto(String nombre, double precioBase, int cantidadStock) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidadStock = cantidadStock;
    }

    public void mostrarInformacionBase() {
        System.out.println("Producto: " + nombre + " | Stock: " + cantidadStock + " | Precio Base: $" + precioBase);
    }

    // Método abstracto: obliga a las subclases a implementar su propia versión
    public abstract void mostrarDetallesEspecificos();
}

// 3. SUBCLASES (Herencia y Polimorfismo)
class Electronico extends Producto {
    private int mesesGarantia;

    public Electronico(String nombre, double precioBase, int cantidadStock, int mesesGarantia) {
        super(nombre, precioBase, cantidadStock);
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.16; // Ejemplo: 16% de IVA
    }

    @Override
    public void mostrarDetallesEspecificos() {
        System.out.println("   [Detalle Electrónico] Garantía: " + mesesGarantia + " meses. Precio Final: $" + calcularPrecioVenta());
    }
}

class Alimento extends Producto {
    private String fechaCaducidad;

    public Alimento(String nombre, double precioBase, int cantidadStock, String fechaCaducidad) {
        super(nombre, precioBase, cantidadStock);
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public double calcularPrecioVenta() {
        return precioBase; // Ejemplo: Sin impuestos
    }

    @Override
    public void mostrarDetallesEspecificos() {
        System.out.println("   [Detalle Alimento] Caducidad: " + fechaCaducidad + ". Precio Final: $" + calcularPrecioVenta());
    }
}

class Ropa extends Producto {
    private String talla;

    public Ropa(String nombre, double precioBase, int cantidadStock, String talla) {
        super(nombre, precioBase, cantidadStock);
        this.talla = talla;
    }

    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.05; // Ejemplo: 5% de margen extra
    }

    @Override
    public void mostrarDetallesEspecificos() {
        System.out.println("   [Detalle Ropa] Talla: " + talla + ". Precio Final: $" + calcularPrecioVenta());
    }
}

// 4. CLASE PRINCIPAL
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE INVENTARIOS ===");

        // Creamos la lista polimórfica
        List<Producto> inventario = new ArrayList<>();

        inventario.add(new Electronico("Laptop Dell", 15000.0, 10, 12));
        inventario.add(new Alimento("Manzanas", 45.50, 100, "15/12/2026"));
        inventario.add(new Ropa("Camiseta de Algodón", 250.0, 50, "M"));

        // Demostración del polimorfismo en ejecución
        for (Producto p : inventario) {
            System.out.println("--------------------------------------------------");
            p.mostrarInformacionBase(); 
            p.mostrarDetallesEspecificos(); 
        }
    }
}