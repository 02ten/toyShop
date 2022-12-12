$(document).ready(function (){

   $(".minusButton").on("click", function (evt) {
      evt.preventDefault();
      decreaseQuantity($(this));
   });
   $(".plusButton").on("click", function (evt) {
      evt.preventDefault();
      increaseQuantity($(this));
   });

   $(".link-remove").on("click", function (evt){
      evt.preventDefault();
      removeFromCart($(this))
   });
   updateTotal();
});
function removeFromCart(link){
   url = link.attr("href");

   $.ajax({
      type: "POST",
      url: url
   }).done(function (newSubTotal){
      updateSubTotal(newSubTotal, productId);
      updateTotal();
   });
}
function decreaseQuantity(link){
   productId=link.attr("pid");
   qtyInput=$("#quantity"+productId);
   newQty=parseInt (qtyInput. val ())-1;
   if (newQty>0) {
      qtyInput.val(newQty);
      updateQuantity(productId, newQty)
   }
}
function increaseQuantity(link){
   productId=link.attr("pid");
   qtyInput=$("#quantity"+productId);
   newQty=parseInt (qtyInput. val ())+1;
   if (newQty<11) {
      qtyInput.val(newQty);
      updateQuantity(productId, newQty)
   }
}
function updateQuantity(productId, quantity){

      url="/" + "cart/update/" +productId+"/" +quantity;
      console.log(url)
      $.ajax({
         type: "POST",
         url: url
      }).done(function (newSubTotal){
         updateSubTotal(newSubTotal, productId);
         updateTotal();
      });


}
function updateSubTotal(newSubtotal, productId){
   $("#subtotal" + productId).text(newSubtotal);

}
function updateTotal(){
   total = 0.0
   $(".productSubTotal").each(function(index, element){
      total = total + parseFloat (element.innerHTML);
   });
   $("#totalAmount").text(total + '₽')
}

