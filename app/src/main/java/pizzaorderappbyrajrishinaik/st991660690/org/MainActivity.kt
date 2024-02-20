package pizzaorderappbyrajrishinaik.st991660690.org

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import pizzaorderappbyrajrishinaik.st991660690.org.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var pizzaSize: String = "";
    private var pizzaPrice: Double = 0.0
    private var noOfToppings: Int = 0
    private var toppingPrice : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.txtDelHelp.visibility = View.INVISIBLE
        binding.btnDeliveryHelp.visibility = View.INVISIBLE

        fun printTotalPrice(pizzaPrice: Double, toppingPrice: Double){
            val totalPrice = pizzaPrice + toppingPrice
            binding.txtPrice.text = "Total Price: $" + totalPrice.toString()
        }

        binding.rdoGroup.setOnCheckedChangeListener { group, checkedId ->

            if(checkedId == binding.rdoSmall.id){
                pizzaSize = "Small"
                pizzaPrice = 9.0
                printTotalPrice(pizzaPrice,toppingPrice)
            }
            if(checkedId == binding.rdoMedium.id){
                pizzaSize = "Medium"
                pizzaPrice = 10.0
                printTotalPrice(pizzaPrice,toppingPrice)
            }
            if(checkedId == binding.rdoLarge.id){
                pizzaSize = "Large"
                pizzaPrice = 11.0
                printTotalPrice(pizzaPrice,toppingPrice)
            }

        }

        binding.chkMeat.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                noOfToppings++
                toppingPrice+=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
            else{
                noOfToppings--
                toppingPrice-=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
        }
        binding.chkCheese.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                noOfToppings++
                toppingPrice+=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
            else{
                noOfToppings--
                toppingPrice-=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
        }
        binding.chkVeg.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                noOfToppings++
                toppingPrice+=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
            else{
                noOfToppings--
                toppingPrice-=2
                printTotalPrice(pizzaPrice,toppingPrice)
            }
        }

        binding.swtDelivery.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.txtDelHelp.visibility = View.VISIBLE
                binding.btnDeliveryHelp.visibility = View.VISIBLE
            }
            else{
                binding.txtDelHelp.visibility = View.INVISIBLE
                binding.btnDeliveryHelp.visibility = View.INVISIBLE
            }
        }
        binding.btnDeliveryHelp.setOnClickListener { view ->
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder
                .setMessage("\nThank you for choosing our delivery service!" +
                        "\nWe will have your pizza delivered at your doorstep within 30 mins" +
                        "\nStandard delivery fee - $2.99\n")
                .setTitle("Delivery Information")
                .setNeutralButton("Ok"){dialog, which ->
                    val snackBar = Snackbar.make(view, "Please contact us for further details!!", BaseTransientBottomBar.LENGTH_SHORT)
                    snackBar.setTextColor(Color.parseColor("#32a852"))
                    snackBar.show()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
            true
        }

    }

    fun sendOrder(view: View) {

        val pPrize = pizzaPrice
        val topCount = noOfToppings
        val topPrice = toppingPrice
        val totPrice = pizzaPrice + toppingPrice

        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("PizzaSize", pizzaSize)
        intent.putExtra("PizzaPrice", pPrize.toString().toDoubleOrNull())
        intent.putExtra("ToppingCount",topCount.toString().toIntOrNull())
        intent.putExtra("ToppingPrice", topPrice.toString().toDoubleOrNull())
        intent.putExtra("TotalPrice", totPrice.toString().toDoubleOrNull())
        startActivity(intent)
    }
}