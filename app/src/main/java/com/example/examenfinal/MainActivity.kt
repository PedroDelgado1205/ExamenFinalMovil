package com.example.examenfinal

import ProductoAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenfinal.models.Producto
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private lateinit var tvPrecioTP: TextView
    private lateinit var tvTax: TextView
    private lateinit var tvDelivery: TextView
    private lateinit var tvTotalPagar: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista de productos (sustituye esta lista con tus datos reales)
        val productos = listOf(
            Producto("cola", "Gaseosa", 1.99, 0.0),
            Producto("hamburguesa", "Hamburguesa", 4.99, 0.0),
            Producto("pancho", "HotDog", 2.00, 0.0),
            Producto("papas", "Papas", 1.50, 0.0),
            Producto("pizza", "Pizza", 14.99, 0.0)
        )

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductoAdapter(this, productos) // Aquí también corregí la inicialización del adaptador
        recyclerView.adapter = adapter

        tvPrecioTP = findViewById(R.id.tvPrecioTP)
        tvTax = findViewById(R.id.tvTax)
        tvDelivery = findViewById(R.id.tvDelivery)
        tvTotalPagar = findViewById(R.id.tvTotalPagar)

        val btnPedir: Button = findViewById(R.id.btnPedir)
        btnPedir.setOnClickListener {
            // Realizar cálculos
            calcularPrecios()
        }
    }

    private fun calcularPrecios() {
        // Obtener precios totales de los productos desde el adaptador
        val preciosTotales = adapter.obtenerPreciosTotales()

        // Calcular precio total de los productos
        val precioTP = preciosTotales.sum()
        val precioTPFormateado = "%.2f".format(precioTP)

        // Calcular impuesto por utilidades y plusvalía
        val tax = precioTP * 0.10
        val taxFormateado = "%.2f".format(tax)

        // Generar valor aleatorio para el costo de delivery
        val delivery = Random.nextDouble(0.50, 5.0)
        val deliveryFormateado = "%.2f".format(delivery)

        // Calcular total a pagar
        val totalPagar = precioTP + tax + delivery
        val totalPagarFormateado = "%.2f".format(totalPagar)

        // Mostrar resultados en las vistas
        tvPrecioTP.text = "$$precioTPFormateado"
        tvTax.text = "$$taxFormateado"
        tvDelivery.text = "$$deliveryFormateado"
        tvTotalPagar.text = "$$totalPagarFormateado"
    }
}
