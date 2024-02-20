package pizzaorderappbyrajrishinaik.st991660690.org

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.text.toUpperCase
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import pizzaorderappbyrajrishinaik.st991660690.org.databinding.ActivityMainBinding
import pizzaorderappbyrajrishinaik.st991660690.org.databinding.ActivityPaymentBinding
import java.util.UUID

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_payment)
        binding = ActivityPaymentBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val intent = getIntent()

        val pizzaSize = intent.getStringExtra("PizzaSize")
        val pizzaCost = intent.getDoubleExtra(("PizzaPrice"),0.0)
        val noOfToppings = intent.getIntExtra("ToppingCount",0)
        val toppingCost = intent.getDoubleExtra("ToppingPrice", 0.0)
        val totalCost = intent.getDoubleExtra("TotalPrice",0.0)

        binding.txtPizzaSize.text = pizzaSize
        binding.txtPizzaCost.text = "$ " +pizzaCost.toString()
        binding.txtNoOfToppings.text = "" + noOfToppings + " X $2"
        binding.txtToppingCost.text = "$ " + toppingCost.toString()
        binding.txtTotalCost.text = "$ " + totalCost.toString()

        binding.btnPay.setOnClickListener { view ->
            binding.txtPizzaSize.setText("")
            binding.txtPizzaCost.setText("")
            binding.txtNoOfToppings.setText("")
            binding.txtToppingCost.setText("")
            binding.txtTotalCost.setText("")

            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("Thank you for the placing the order.\n" +
                        "Delicious Pizza on your way!!\n" +
                        "Please note the order number \nORD" + UUID.randomUUID().toString().substring(0,8).uppercase() + "\nfor tracking purpose")
                .setTitle("Order Placed - Payment Required")
                .setNeutralButton("Close"){dialog, which->

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
            true

        }
    }

    fun onCancel(view: View) {
        Toast.makeText(this, "Order Cancelled!!", Toast.LENGTH_SHORT).show()
        finish()
    }
}