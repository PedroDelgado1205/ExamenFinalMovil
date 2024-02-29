import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfinal.R
import com.example.examenfinal.models.Producto

class ProductoAdapter(private val context: Context, private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val tvProducto: TextView = itemView.findViewById(R.id.tvProducto)
        val tvPrecioU: TextView = itemView.findViewById(R.id.tvPrecioU)
        val tvPrecioT: TextView = itemView.findViewById(R.id.tvPrecioT)
        val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
        val ibMas: ImageButton = itemView.findViewById(R.id.ibMas)
        val ibMenos: ImageButton = itemView.findViewById(R.id.ibMenos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]
        when (producto.imagen) {
            "cola" -> holder.imgProducto.setImageResource(R.drawable.cola)
            "hamburguesa" -> holder.imgProducto.setImageResource(R.drawable.hamburguesa)
            "pancho" -> holder.imgProducto.setImageResource(R.drawable.pancho)
            "papas" -> holder.imgProducto.setImageResource(R.drawable.papas)
            "pizza" -> holder.imgProducto.setImageResource(R.drawable.pizza)
        }
        holder.tvProducto.text = producto.nombre
        holder.tvPrecioU.text = "$${producto.precio}"
        holder.tvCantidad.text = "${producto.cantidad}"
        holder.tvPrecioT.text = "$0.00"

        holder.ibMas.setOnClickListener {
            // Incrementar la cantidad
            val cantidadActual = holder.tvCantidad.text.toString().toDouble()
            holder.tvCantidad.text = (cantidadActual + 1).toString()
            producto.cantidad = holder.tvCantidad.text.toString().toDouble()
            val precioTotal = (cantidadActual + 1) * producto.precio
            holder.tvPrecioT.text = "$$precioTotal"
        }

        holder.ibMenos.setOnClickListener {
            // Decrementar la cantidad
            val cantidadActual = holder.tvCantidad.text.toString().toDouble()
            producto.cantidad = holder.tvCantidad.text.toString().toDouble()
            if (cantidadActual > 0) {
                holder.tvCantidad.text = (cantidadActual - 1).toString()
                val precioTotal = (cantidadActual - 1) * producto.precio
                holder.tvPrecioT.text = "$$precioTotal"
            }
        }
    }

    fun obtenerPreciosTotales(): List<Double> {
        val preciosTotales = mutableListOf<Double>()
        for (producto in productos) {
            val precioTotal = producto.cantidad * producto.precio
            preciosTotales.add(precioTotal)
        }
        return preciosTotales
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}
