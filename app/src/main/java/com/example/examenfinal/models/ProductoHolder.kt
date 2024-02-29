import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfinal.R

class ProductoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
    val tvProducto: TextView = itemView.findViewById(R.id.tvProducto)
    val tvPrecioU: TextView = itemView.findViewById(R.id.tvPrecioU)
    val tvPrecioT: TextView = itemView.findViewById(R.id.tvPrecioT)
    val tvCantidad: TextView = itemView.findViewById(R.id.tvCantidad)
    val ibMas: ImageButton = itemView.findViewById(R.id.ibMas)
    val ibMenos: ImageButton = itemView.findViewById(R.id.ibMenos)
}
