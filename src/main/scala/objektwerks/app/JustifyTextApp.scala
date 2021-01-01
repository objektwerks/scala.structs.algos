package objektwerks.app

object JustifyTextApp {
  import objektwerks.Text._

  def main(args: Array[String]): Unit = {
    val text ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam vel urna bibendum, pharetra mi quis, imperdiet nibh. Praesent dictum odio lacus, eget commodo sem aliquam rutrum. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec eget rhoncus mauris, quis vehicula mi. Quisque finibus purus non varius dictum. Pellentesque vulputate fringilla egestas. Nunc eleifend ex sed egestas cursus. Praesent molestie nisl in pretium vehicula. Vestibulum efficitur ut risus quis porta. Praesent non sem quam. Donec vitae arcu sapien. Quisque aliquet nibh in metus efficitur ullamcorper. Donec mattis dapibus nisl sed iaculis. Curabitur eu blandit enim. Fusce varius."
    val justifiedText = justify(text, 40)
    println(justifiedText)
  }
}