import app.cash.paparazzi.sample.R
import app.cash.paparazzi.sample.databinding.LaunchBinding
import com.xwray.groupie.databinding.BindableItem

class LaunchItem(val text: String) : BindableItem<LaunchBinding>() {
  override fun getLayout(): Int = R.layout.launch

  override fun bind(viewBinding: LaunchBinding, position: Int) {
    viewBinding.centerText.text = text
  }
}